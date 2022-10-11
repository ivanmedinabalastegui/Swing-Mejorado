import javax.swing.*;
import javax.swing.plaf.nimbus.NimbusLookAndFeel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Calendar;

import static com.sun.java.accessibility.util.AWTEventMonitor.addWindowListener;

public class clase1 extends JPanel {
    BDselect bDselect = new BDselect();

    public clase1() {
        try {
            UIManager.setLookAndFeel( new NimbusLookAndFeel());
        } catch ( Exception e) {
            e.printStackTrace();
        }
        bDselect.getTablaProducto(new ConexionBD());

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        //final JTable table = new JTable(data, columnNames);
        final JTable table = new JTable(bDselect.modelo);

        table.setPreferredScrollableViewportSize(new Dimension(500, 70));
        //Creamos un JscrollPane y le agregamos la JTable
        JScrollPane scrollPane = new JScrollPane(table);
        //Agregamos el JScrollPane al contenedor
        this.add(scrollPane);
        //manejamos la salida

        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
    }

}
class BDselect {
    DefaultTableModel modelo = new DefaultTableModel();



    public void  getTablaProducto(ConexionBD cbd) {

        modelo.addColumn("CIF");
        modelo.addColumn("Nombre");
        modelo.addColumn("Direccion");
        modelo.addColumn("CP");
        modelo.addColumn("Localidad");
        modelo.addColumn("Jornada");
        modelo.addColumn("Modalidad");
        modelo.addColumn("Mail");
        modelo.addColumn("Cod_Empresas");




        try {
            Statement s = cbd.conectar().createStatement();


            ResultSet rs = s.executeQuery("select CIF, Nombre, Direccion, CP ,Localidad, Jornada, Modalidad, Mail, Cod_Empresas from empresas");

            while (rs.next()) {
                Object [] fila = new Object[9]; // Hay tres columnas en la tabla

                for (int i=0;i<9;i++)
                    fila[i] = rs.getObject(i+1); // El primer indice en rs es el 1, no el cero, por eso se suma 1.

                // Se añade al modelo la fila completa.
                modelo.addRow(fila);
                System.out.println(fila);

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

}
