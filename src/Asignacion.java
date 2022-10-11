import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.nio.charset.StandardCharsets;
import java.sql.*;

public class Asignacion extends JPanel {
    JPanel pEleccionAlumno;
    JPanel pEleccionEmpresa;
    JPanel pEleccionTutor;
    JLabel ltituloEleccion;
    JLabel ltituloEleccionEmp;
    JLabel ltituloEleccionTut;
    JPanel pMensaje;
    public JButton bConfirm;
    JPanel pBoton;
    public JComboBox cbAlumnos;
    public JComboBox cbEmpresas;
    public JComboBox cbTutores;
    public JLabel lMensaje;

    public Asignacion() {
        super();
        setSize(620, 340);
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        // Título


        //Paneles contenido
        pEleccionAlumno = new JPanel(new FlowLayout());
        pEleccionEmpresa = new JPanel(new FlowLayout());
        pEleccionTutor = new JPanel(new FlowLayout());
        pBoton = new JPanel(new FlowLayout());

        //Panel principal

        ltituloEleccion = new JLabel("Elección de Alumno");

        pEleccionAlumno.add(ltituloEleccion);

        ltituloEleccionEmp = new JLabel("Elección de Empresa");
        pEleccionEmpresa.add(ltituloEleccionEmp);

        ltituloEleccionTut = new JLabel("Elección de Tutor");
        pEleccionTutor.add(ltituloEleccionTut);

        cbAlumnos = new JComboBox();
        cbEmpresas = new JComboBox();
        cbTutores = new JComboBox();

        pEleccionAlumno.add(cbAlumnos);
        pEleccionEmpresa.add(cbEmpresas);
        pEleccionTutor.add(cbTutores);

        bConfirm = new JButton("Confirmar");
        pBoton.add(bConfirm);

        pMensaje = new JPanel();
        lMensaje = new JLabel("");
        lMensaje.setOpaque(true);
        lMensaje.setBackground(Color.lightGray);
        pMensaje.add(lMensaje);


        // Líneas para el JLabel inferior
        LineBorder line = new LineBorder(Color.white, 2, true);
        lMensaje.setBorder(line);

        // Añadimos al panel principal

        add(pEleccionAlumno);
        add(pEleccionEmpresa);
        add(pEleccionTutor);
        add(pBoton);
        add(pMensaje);


        try {
            Statement s = new ConexionBD().conectar().createStatement();
            ResultSet rs = s.executeQuery("select * from alumnos");

            String auxApellido;
            String auxNombre;
            while (rs.next()) {
                byte[] dataApellido = rs.getBytes("Apellidos");
                auxApellido = new String(dataApellido, StandardCharsets.UTF_8);
                byte[] dataNombre = rs.getBytes("Nombre");
                auxNombre = new String(dataNombre, StandardCharsets.UTF_8);
                cbAlumnos.addItem(auxApellido + ", " + auxNombre);
            }
        } catch (Exception x) {
            x.printStackTrace();
        }


        try {
            Statement s = new ConexionBD().conectar().createStatement();
            ResultSet rs = s.executeQuery("select * from empresas");

            String auxNombre;
            while (rs.next()) {
                byte[] dataNombre = rs.getBytes("Nombre");
                auxNombre = new String(dataNombre, StandardCharsets.UTF_8);
                cbEmpresas.addItem(auxNombre);
            }
        } catch (Exception x) {
            x.printStackTrace();
        }


        try {
            Statement s = new ConexionBD().conectar().createStatement();
            ResultSet rs = s.executeQuery("select * from tutores_p");

            String auxNombre;
            while (rs.next()) {
                byte[] dataNombre = rs.getBytes("Nombre");
                auxNombre = new String(dataNombre, StandardCharsets.UTF_8);
                cbTutores.addItem(auxNombre);
            }
        } catch (Exception x) {
            x.printStackTrace();
        }

        bConfirm.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    Connection conexion = new ConexionBD().conectar();

                    String alumno = cbAlumnos.getSelectedItem().toString();
                    String[] apellidoAux = alumno.split(",");
                    String codAlumno = null;
                    String empresa = cbEmpresas.getSelectedItem().toString();
                    String codEmpresa = null;
                    String tutor = cbTutores.getSelectedItem().toString();
                    String codTutor = null;

                    try {
                        Statement s = conexion.createStatement();
                        ResultSet rsCodAlumno = s.executeQuery("SELECT Cod_Alumno from Alumnos where Apellidos = '" + apellidoAux[0] + "'");
                        while (rsCodAlumno.next()) {
                            codAlumno = rsCodAlumno.getString(1);
                        }

                        ResultSet rsCodEmpresa = s.executeQuery("SELECT Cod_Empresas from Empresas where Nombre = '" + empresa + "'");
                        while (rsCodEmpresa.next()) {
                            codEmpresa = rsCodEmpresa.getString(1);
                        }
                        ResultSet rsCodTutor = s.executeQuery("SELECT Cod_Tutor from tutores_p where Nombre = '" + tutor + "'");
                        while (rsCodTutor.next()) {
                            codTutor = rsCodTutor.getString(1);
                        }

                        PreparedStatement ps = conexion.prepareStatement
                                ("INSERT INTO Asignacion (Cod_Alumno, Cod_Tutor, Cod_Empresas, Fecha_Asignacion) " +
                                        "VALUES (?, ?, ?, ?)");
                        String auxTime = String.valueOf(new Timestamp(System.currentTimeMillis()));
                        auxTime = auxTime.substring(0, auxTime.length() - 4);
                        ps.setString(1, codAlumno);
                        ps.setString(2, codTutor);
                        ps.setString(3, codEmpresa);
                        ps.setString(4, auxTime);
                        System.out.println(ps);

                        if (ps.executeUpdate() == 1) {
                            ResultSet rsTutorLaboral = s.executeQuery("SELECT Nombre_TL, Apellidos_TL from " +
                                    "empresas where Nombre = '" + empresa + "'");
                            String nombreTL = null;
                            String apellidoTL = null;
                            while (rsTutorLaboral.next()) {
                                nombreTL = rsTutorLaboral.getString(1);
                                apellidoTL = rsTutorLaboral.getString(2);
                            }

                            String mensaje = "<html> <p style=\"text-align:center\">El alumno " + alumno + " queda asignado a la empresa "
                                    + empresa + "<br/> supervisado por el tutor docente " + tutor +
                                    " y por el tutor laboral " + nombreTL + " " + apellidoTL + ".</p> </html>";
                            lMensaje.setText(mensaje);
                        } else {
                            JOptionPane.showMessageDialog(pEleccionAlumno, "No se ha podido registrar la asignación");
                        }

                        ps.close();
                    } catch (Exception ee) {
                        System.out.println(ee.getClass());
                    }

                    conexion.close();

                } catch (SQLException ex) {
                    ex.printStackTrace();
                }

            }

        });
    }
}



