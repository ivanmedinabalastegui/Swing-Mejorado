import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.swing.*;
import javax.swing.border.*;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.sql.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import static javax.swing.WindowConstants.EXIT_ON_CLOSE;

public class xml {
    public static void main(String args[]) throws SQLException, ClassNotFoundException {
        ControlBDd controlBDd = new ControlBDd();

        controlBDd.lecturaXML();
        controlBDd.intoValueDatos();
        controlBDd.consultaTabla();
    }
}
class Tutores {

    int cod;
    String NombreApellidos;
    String CorreoElectronico;
    String Telefono;

    public Tutores(int cod, String NombreApellidos, String CorreoElectronico, String Telefono) {
        this.cod = cod;
        this.NombreApellidos = NombreApellidos;
        this.CorreoElectronico = CorreoElectronico;
        this.Telefono = Telefono;
    }
}

class ControlBDd {
    ArrayList<Tutores> listTutores = new ArrayList<>();

    public void consultaTabla() throws ClassNotFoundException, SQLException {

        Class.forName("com.mysql.jdbc.Driver");
        Connection conexion = DriverManager.getConnection("jdbc:mysql://localhost:3306/bdgestionfct", "root", "");
        Statement stmt = conexion.createStatement();
        System.out.println("BD conectada");
        ResultSet rs = stmt.executeQuery("select * from Tutores_P");
        while (rs.next()) {
            System.out.println(rs.getString("Cod_Tutor"));
            System.out.println(rs.getString("NombreApellidos"));
            System.out.println(rs.getString("CorreoElectronico"));
            System.out.println(rs.getString("Telefono"));
        }
        rs.close();
        stmt.close();

    }

    public void intoValueDatos() throws ClassNotFoundException, SQLException {

        Class.forName("com.mysql.jdbc.Driver");
        Connection conexion = DriverManager.getConnection("jdbc:mysql://localhost:3306/bdgestionfct", "root", "");
        Statement stmt = conexion.createStatement();

        PreparedStatement acctualiza = conexion.prepareStatement("INSERT INTO tutores_p(Cod_Tutor, NombreApellidos, CorreoElectronico, Telefono) VALUES( ?, ?, ?, ? ) ");


        for (Tutores x : listTutores) {

            acctualiza.setString(1, String.valueOf(x.cod + 1));
            acctualiza.setString(2, x.NombreApellidos);
            acctualiza.setString(3, x.CorreoElectronico);
            acctualiza.setString(4, x.Telefono);


            int filActualizadas = acctualiza.executeUpdate();

        }


        stmt.close();

    }
    public void lecturaXML() {


        //Creamos una referencia (fichXML) de tipo File al cargar el fichero XML existente
        File fichXML = new File("src/tutoresdoc.xml");
        {

            //Parsea el fichero y lo convierte de fichero a documento XML recorrible
            DocumentBuilderFactory factoriaDoc = DocumentBuilderFactory.newInstance();
            DocumentBuilder constructDoc = null;
            try {
                constructDoc = factoriaDoc.newDocumentBuilder();
            } catch (ParserConfigurationException ex) {
                ex.printStackTrace();
            }
            Document docDOM = null;
            try {
                docDOM = constructDoc.parse(fichXML);
            } catch (SAXException | IOException ex) {
                ex.printStackTrace();
            }

            //Elimina nodos vacíos y combina adyacentes en caso de que los hubiera
            docDOM.getDocumentElement().normalize();
            //Localizamos e imprimimos cuál es el elemento raíz
            System.out.println("\n========================");
            Element raiz = docDOM.getDocumentElement();
            System.out.println("Elemento raíz: " + raiz.getNodeName());
            System.out.println("========================");
            //Vuelca a una lista los nodos que cuelgan del nodo raíz
            NodeList alumDam = docDOM.getElementsByTagName("tutordoc");
            for (int cont = 0; cont < alumDam.getLength(); cont++) {
                //Se recorre la lista con nodos y se extrae en nodo el que indica cont
                Node nodo = alumDam.item(cont);
                //Para saber qué nodo está procesando, imprime el nombre
                System.out.println("");
                System.out.println(nodo.getNodeName() + " --> Nodo: " + cont);
                //Comprueba si se encuentra ante un elemento
                if (nodo.getNodeType() == Node.ELEMENT_NODE) {
                    //Castea el nodo de tipo Node a uno de tipo Element
                    Element element = (Element) nodo;
                    System.out.println(element.getElementsByTagName("codtut").item(0).getTextContent());
                    System.out.println(element.getElementsByTagName("nomap").item(0).getTextContent());
                    System.out.println(element.getElementsByTagName("correo").item(0).getTextContent());
                    System.out.println(element.getElementsByTagName("telefono").item(0).getTextContent());

                    String cod = element.getElementsByTagName("codtut").item(0).getTextContent();
                    String NombreApellidos = element.getElementsByTagName("nomap").item(0).getTextContent();
                    String CorreoElectronico = element.getElementsByTagName("correo").item(0).getTextContent();
                    String Telefono = element.getElementsByTagName("telefono").item(0).getTextContent();

                    Tutores tutores = new Tutores(Integer.parseInt(cod), NombreApellidos, CorreoElectronico, Telefono);
                    listTutores.add(tutores);
                }
            }
            System.out.println(" ");
        }
    }

}
