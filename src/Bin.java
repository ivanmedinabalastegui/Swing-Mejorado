import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class Bin {
    public static void main(String args[]) throws SQLException, ClassNotFoundException {

        ControlBD controlBD = new ControlBD();


        //  controlBD.consultaTabla();
    //    controlBD.leerfichero();
    //    controlBD.intoValueDatos();
   //     controlBD.consultaTabla();

    }
}
class Alumnos {
    int cod;
    String dni;
    String nombre;
    String apellidos;
    String fechanacimiento;

    public Alumnos(int cod, String dni, String nombre, String apellidos, String fechanacimiento) {
        this.cod = cod;
        this.dni = dni;
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.fechanacimiento = fechanacimiento;
    }
}

// *******************************************************************************************************




// *******************************************************************************************************
class ControlBD {
    ArrayList<Alumnos> listAlumnos = new ArrayList<>();
    public void consultaTabla() throws ClassNotFoundException, SQLException {

        Class.forName("com.mysql.jdbc.Driver");
        Connection conexion = DriverManager.getConnection("jdbc:mysql://localhost:3306/bdgestionfct", "root", "");
        Statement stmt = conexion.createStatement();
        System.out.println("BD conectada");
        ResultSet rs = stmt.executeQuery("select * from ALUMNOS");
        while (rs.next()) {
            System.out.println(rs.getString("Cod_Alumno"));
            System.out.println(rs.getString("DNI"));
            System.out.println(rs.getString("Nombre"));
            System.out.println(rs.getString("Apellidos"));
            System.out.println(rs.getString("Fecha_Nac"));
        }
        rs.close();
        stmt.close();

    }
    public void intoValueDatos() throws ClassNotFoundException, SQLException {

        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection conexion = DriverManager.getConnection("jdbc:mysql://localhost:3306/bdgestionfct", "root", "");
        Statement stmt = conexion.createStatement();

        // String acctualiza = "INSERT INTO ALUMNOS(Cod_Alumno, DNI, Nombre, Apellidos, Fecha_Nac) VALUES('2', '21131742F', 'Juan', 'Santana Ruiz', '2008/12/31') ";
        PreparedStatement acctualiza = conexion.prepareStatement("INSERT INTO ALUMNOS(DNI, Nombre, Apellidos, Fecha_Nac) VALUES( ?, ?, ?, ?) ");


        for (Alumnos x:listAlumnos) {
            acctualiza.setString(1, x.dni);
            acctualiza.setString(2, x.nombre);
            acctualiza.setString(3, x.apellidos);

            String fecha = DateTimeFormatter.ofPattern("yyyy,MM,dd")
                    .format(LocalDate.parse(x.fechanacimiento, DateTimeFormatter.ofPattern("dd/MM/yyyy")));

            acctualiza.setString(4, fecha);

            int filActualizadas = acctualiza.executeUpdate();

        }



        stmt.close();
    }


// *******************************************************************************************************

    public void leerfichero() {
        // Se declaran las variables de método para guardar lo leído
        //Cod_alumno
        int cod;
        String dni;
        String nombre;
        String apellidos;
        String fechanacimiento;


        // Se genera el objeto en memoria para trasegar con el fichero
        File fich = new File("src/alumnos2cfs.dat");
        // Se crea el amarre para la "manguera base" que permita leer del fichero
        FileInputStream fis = null;
        // Se crea el amarre pensando en aplicar métodos específicos [readXXX()]
        DataInputStream dis = null;
        try {
            // Se crea la "manguera base" para grabar datos en el fichero
            fis = new FileInputStream(fich);
            // Se crea el envoltorio que aplique a la "manguera base" métodos como readXXX()
            dis = new DataInputStream(fis);
            // Extraemos datos del fichero hasta llegar al final del mismo



            while (dis.available() > 0) {
                cod = dis.readInt();
                dni = dis.readUTF();
                nombre = dis.readUTF();
                apellidos = dis.readUTF();
                fechanacimiento = dis.readUTF();

                Alumnos alumnos = new Alumnos(cod,dni,nombre,apellidos,fechanacimiento);
                listAlumnos.add(alumnos);



                System.out.println(cod);
                System.out.println(dni);
                System.out.println(nombre);
                System.out.println(apellidos);
                System.out.println(fechanacimiento);
            }

        } catch (IOException ioex) {
            System.out.println(ioex.getMessage());
            System.out.println(" ");
        } finally {
            try {
                if (fis != null) {
                    fis.close();
                }
                if (dis != null) {
                    dis.close();
                }
            } catch (IOException ioe) {
                System.out.println(ioe.getMessage());
                System.out.println(" ");
            }
        }

    }
}