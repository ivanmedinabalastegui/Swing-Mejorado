import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.*;
import javax.swing.border.Border;


import static javax.swing.WindowConstants.EXIT_ON_CLOSE;

public class example1 {
    public static void main(String args[]) {
        example12 alumnos = new example12("Ejercicio PEP3T_4 JAVA");

        alumnos.setVisible(true);
        alumnos.setDefaultCloseOperation(EXIT_ON_CLOSE);

    }
}


// *******************************************************************************************************
class example12 extends JFrame {
    public JPanel palumnos;
    JButton bInsert;
    JButton bDelete;




    public example12(String nombre) {
        super(nombre);
        this.setSize(1100, 500);
        this.setResizable(false);
        this.setBackground(Color.white);

        ConexionBD cdb = new ConexionBD();
        ControlBD controlBD = new ControlBD();
        ControlBDd controlBDd = new ControlBDd();



        // Panel main
        this.setLayout(new BorderLayout());
        // Panel superior


        // Panel izquierdo

        JPanel panelizquierdo = new JPanel();

        panelizquierdo.setBorder(new LineBorder(Color.BLUE, 3, false));

        panelizquierdo.setMaximumSize(new Dimension(100, 100));
        panelizquierdo.setLayout(new BoxLayout(panelizquierdo, BoxLayout.Y_AXIS));

        JTabbedPane menu = new JTabbedPane(SwingConstants.TOP);
        panelizquierdo.setBackground(Color.white);
        menu.addTab("primero", panelizquierdo);


        // ---------------------


        JButton home = new JButton(new ImageIcon("home.png"));
        home.setBorder(new EmptyBorder(40, 20, 60, 20));
        home.setBackground(Color.white);

        JButton empresa = new JButton(new ImageIcon("empresa.png"));
        empresa.setBorder(new EmptyBorder(10, 20, 10, 20));
        empresa.setBackground(Color.white);

        JButton alumno = new JButton(new ImageIcon("alumno.png"));
        alumno.setBorder(new EmptyBorder(20, 20, 10, 20));
        alumno.setBackground(Color.white);

        JButton profe = new JButton(new ImageIcon("profe.png"));
        profe.setBorder(new EmptyBorder(20, 20, 10, 20));
        profe.setBackground(Color.white);

        JButton asig = new JButton(new ImageIcon("asignacion.png"));
        asig.setBorder(new EmptyBorder(20, 20, 10, 20));
        asig.setBackground(Color.white);


        panelizquierdo.add(home);
        panelizquierdo.add(empresa);
        panelizquierdo.add(alumno);
        panelizquierdo.add(profe);
        panelizquierdo.add(asig);


        // ----------- Panel home

        JPanel panelhome = new JPanel();
        panelhome.setLayout(new BoxLayout(panelhome, BoxLayout.Y_AXIS));

        panelhome.setMaximumSize(new Dimension(800, 800));

        JLabel fotohome = new JLabel(new ImageIcon("icono.png"));
        Border border2 = fotohome.getBorder();
        Border margin2 = new EmptyBorder(50, 10, 50, 10);
        fotohome.setBorder(new CompoundBorder(border2, margin2));

        fotohome.setAlignmentX(Component.CENTER_ALIGNMENT);
        Color colorAzul = new Color(232, 232, 232, 255);

        fotohome.setBackground(colorAzul);
        panelhome.setBackground(colorAzul);


        JLabel infohome = new JLabel("Mejoras de las Interfaces (Proyecto3T de Programación de 1º DAM, versión Swing).");
        infohome.setFont(new Font("Arial", Font.BOLD, 15));

        JLabel equipo = new JLabel("Equipo de trabajo:");
        equipo.setFont(new Font("Arial", Font.BOLD, 15));

        JLabel equipo2 = new JLabel("Víctor Algaba Bueno");
        equipo2.setFont(new Font("Arial", Font.BOLD, 15));
        JLabel equipo3 = new JLabel("Iván García Jiménez");
        equipo3.setFont(new Font("Arial", Font.BOLD, 15));
        JLabel equipo4 = new JLabel("Iván Medina Balastegui");
        equipo4.setFont(new Font("Arial", Font.BOLD, 15));
        JLabel equipo5 = new JLabel("José Laureano");
        equipo5.setFont(new Font("Arial", Font.BOLD, 15));


        Border border3 = equipo.getBorder();
        Border margin3 = new EmptyBorder(25, 0, 20, 0);
        equipo.setBorder(new CompoundBorder(border3, margin3));

        panelhome.add(fotohome);
        panelhome.add(infohome);
        panelhome.add(equipo);
        panelhome.add(equipo2);
        panelhome.add(equipo3);
        panelhome.add(equipo4);
        panelhome.add(equipo5);

        // ------------ Panel empresa


        JPanel panelempresa = new JPanel();


        JPanel ptituloempresa = new JPanel();
        ptituloempresa.setBorder(new LineBorder(Color.BLUE, 3, false));
        JLabel tituloempresa = new JLabel("GESTIÓN DE DATOS DE EMPRESAS");
        tituloempresa.setFont(new Font("Arial", Font.BOLD, 22));
        ptituloempresa.setBackground(Color.white);
        //      tituloempresa.setForeground(Color.BLUE);
        ptituloempresa.add(tituloempresa);

        this.add(ptituloempresa, BorderLayout.NORTH);


        JLabel lCodEmpresa = new JLabel("Código Empresa:  ");
        JTextField tfCodEmpresa = new JTextField();
        tfCodEmpresa.setSize(new Dimension(20, 20));
        tfCodEmpresa.setHorizontalAlignment(0);
        tfCodEmpresa.setEditable(false);
        tfCodEmpresa.setBorder(BorderFactory.createLineBorder(Color.blue));


        JLabel lCifEmpresa = new JLabel("CIF: ");
        JTextField tfCifEmpresa = new JTextField();
        tfCifEmpresa.setMaximumSize(new Dimension(120, 20));


        JPanel p1Empresa = new JPanel();
        p1Empresa.setBorder(BorderFactory.createEmptyBorder(15, 0, 15, 0));

        p1Empresa.setLayout(new BoxLayout(p1Empresa, BoxLayout.X_AXIS));
        p1Empresa.add(lCodEmpresa);
        p1Empresa.add(tfCodEmpresa);
        p1Empresa.add(lCifEmpresa);
        p1Empresa.add(tfCifEmpresa);

        tfCodEmpresa.setMaximumSize(new Dimension(40, 20));

        JLabel lNombreEmpresa = new JLabel("Nombre: ");
        JTextField tfNombreEmpresa = new JTextField();
        tfNombreEmpresa.setMaximumSize(new Dimension(200, 20));

        // --------------------

        JLabel lDirecEmpresa = new JLabel("Dirección: ");
        JTextField tfDirecEmpresa = new JTextField();
        tfDirecEmpresa.setMaximumSize(new Dimension(150, 20));

        JPanel p2Empresa = new JPanel();
        p2Empresa.setBorder(BorderFactory.createEmptyBorder(15, 0, 15, 0));

        p2Empresa.setLayout(new BoxLayout(p2Empresa, BoxLayout.X_AXIS));
        p2Empresa.add(lNombreEmpresa);
        p2Empresa.add(tfNombreEmpresa);
        p2Empresa.add(lDirecEmpresa);
        p2Empresa.add(tfDirecEmpresa);


        JLabel lCodPostalEmpresa = new JLabel("C.P.: ");
        JTextField tfCodPostalEmpresa = new JTextField();
        tfCodPostalEmpresa.setMaximumSize(new Dimension(120, 20));

        JLabel lLocalidadEmpresa = new JLabel("Localidad: ");
        JTextField tfLocalidadEmpresa = new JTextField();
        tfLocalidadEmpresa.setMaximumSize(new Dimension(210, 20));

        JPanel p3Empresa = new JPanel();
        p3Empresa.setBorder(BorderFactory.createEmptyBorder(15, 0, 15, 0));

        p3Empresa.setLayout(new BoxLayout(p3Empresa, BoxLayout.X_AXIS));
        p3Empresa.add(lCodPostalEmpresa);
        p3Empresa.add(tfCodPostalEmpresa);
        p3Empresa.add(lLocalidadEmpresa);
        p3Empresa.add(tfLocalidadEmpresa);


        JLabel lJornadaEmpresa = new JLabel("Jornada: ");
        JComboBox<Object> cbJornadaEmpresa = new JComboBox<>();
        cbJornadaEmpresa.setMaximumSize(new Dimension(100, 20));
        cbJornadaEmpresa.addItem("Partida");
        cbJornadaEmpresa.addItem("Continua");


        JLabel lModalidadEmpresa = new JLabel("Modalidad: ");
        JComboBox<Object> cbModalidadEmpresa = new JComboBox<>();
        cbModalidadEmpresa.setMaximumSize(new Dimension(120, 20));
        cbModalidadEmpresa.addItem("Presencial");
        cbModalidadEmpresa.addItem("Semipresencial");
        cbModalidadEmpresa.addItem("Distancia");

        JPanel p5Empresa = new JPanel();
        p5Empresa.setBorder(BorderFactory.createEmptyBorder(15, 0, 15, 0));

        p5Empresa.setLayout(new BoxLayout(p5Empresa, BoxLayout.X_AXIS));
        p5Empresa.add(lJornadaEmpresa);
        p5Empresa.add(cbJornadaEmpresa);
        p5Empresa.add(lModalidadEmpresa);
        p5Empresa.add(cbModalidadEmpresa);

        // ---------------------

        JLabel lMailEmpresa = new JLabel("Mail: ");
        JTextField tfMailEmpresa = new JTextField();
        tfMailEmpresa.setMaximumSize(new Dimension(240, 20));

        JPanel p4Empresa = new JPanel();
        p4Empresa.setBorder(BorderFactory.createEmptyBorder(15, 0, 15, 0));

        p4Empresa.setLayout(new BoxLayout(p4Empresa, BoxLayout.X_AXIS));
        p4Empresa.add(lMailEmpresa);
        p4Empresa.add(tfMailEmpresa);

        // --------------------- Panel personas

        JLabel lDniRepLegal = new JLabel("DNI Rep. Legal: ");
        JTextField tfDniRepLegal = new JTextField();
        tfDniRepLegal.setMaximumSize(new Dimension(130, 20));

        JLabel lNombreRL = new JLabel("Nombre RL:");
        JTextField tfNombreRL = new JTextField();
        tfNombreRL.setMaximumSize(new Dimension(130, 20));

        JPanel p1Personas = new JPanel();
        p1Personas.setBorder(BorderFactory.createEmptyBorder(15, 0, 15, 0));

        p1Personas.setLayout(new BoxLayout(p1Personas, BoxLayout.X_AXIS));
        p1Personas.add(lDniRepLegal);
        p1Personas.add(tfDniRepLegal);
        p1Personas.add(lNombreRL);
        p1Personas.add(tfNombreRL);

        JLabel lApellidosRL = new JLabel("Apellidos RL:");
        JTextField tfApellidosRL = new JTextField();
        tfApellidosRL.setMaximumSize(new Dimension(130, 20));


        JLabel lDniTutLaboral = new JLabel("DNI Tut. Laboral: ");
        JTextField tfDniTutLaboral = new JTextField();
        tfDniTutLaboral.setMaximumSize(new Dimension(130, 20));

        JPanel p2Personas = new JPanel();
        p2Personas.setBorder(BorderFactory.createEmptyBorder(15, 0, 15, 0));

        p2Personas.setLayout(new BoxLayout(p2Personas, BoxLayout.X_AXIS));
        p2Personas.add(lApellidosRL);
        p2Personas.add(tfApellidosRL);
        p2Personas.add(lDniTutLaboral);
        p2Personas.add(tfDniTutLaboral);

        JLabel lNombreTL = new JLabel("Nombre TL: ");
        JTextField tfNombreTL = new JTextField();
        tfNombreTL.setMaximumSize(new Dimension(130, 20));

        JLabel lApellidosTL = new JLabel("Apellidos TL: ");
        JTextField tfApellidosTL = new JTextField();
        tfApellidosTL.setMaximumSize(new Dimension(130, 20));

        JPanel p3Personas = new JPanel();
        p3Personas.setBorder(BorderFactory.createEmptyBorder(15, 0, 15, 0));

        p3Personas.setLayout(new BoxLayout(p3Personas, BoxLayout.X_AXIS));
        p3Personas.add(lNombreTL);
        p3Personas.add(tfNombreTL);
        p3Personas.add(lApellidosTL);
        p3Personas.add(tfApellidosTL);


        JLabel lTelefonoTL = new JLabel("Tlf. TL:");
        JTextField tfTelefonoTL = new JTextField();
        tfTelefonoTL.setMaximumSize(new Dimension(130, 20));

        JPanel p4Personas = new JPanel();
        p4Personas.setBorder(BorderFactory.createEmptyBorder(15, 0, 15, 0));

        p4Personas.setLayout(new BoxLayout(p4Personas, BoxLayout.X_AXIS));
        p4Personas.add(lTelefonoTL);
        p4Personas.add(tfTelefonoTL);

        bInsert = new JButton("Insertar");
        bDelete = new JButton("Eliminar");

        JPanel p6Empresa = new JPanel();
        p6Empresa.setBorder(BorderFactory.createEmptyBorder(50, 50, 15, 0));

        p6Empresa.setLayout(new BoxLayout(p6Empresa, BoxLayout.X_AXIS));
        p6Empresa.add(bInsert);
        p6Empresa.add(bDelete);

        // ------------ Insertar modificar y eliminar


        // ------------


        //      panelempresa.setBorder(new LineBorder(Color.blue, 3, false));
        panelempresa.setLayout(new BoxLayout(panelempresa, BoxLayout.X_AXIS));


        JPanel empresal = new JPanel();
        empresal.setMaximumSize(new Dimension(500, 500));
        JPanel empresac = new JPanel();
        empresac.setMaximumSize(new Dimension(500, 500));

        //    empresal.setBorder(new LineBorder(Color.black, 0, true));
        //    empresac.setBorder(new LineBorder(Color.black, 0, true));


        empresal.setLayout(new BoxLayout(empresal, BoxLayout.Y_AXIS));
        empresac.setLayout(new BoxLayout(empresac, BoxLayout.Y_AXIS));
        //     empresar.setLayout(new BoxLayout(empresar, BoxLayout.X_AXIS));


        // -------------------------------------------
        //  empresal.setMaximumSize(new Dimension(300,500));


        empresal.add(p1Empresa);
        empresal.add(p2Empresa);
        empresal.add(p3Empresa);
        empresal.add(p4Empresa);
        empresal.add(p5Empresa);


        empresac.add(p1Personas);
        empresac.add(p2Personas);
        empresac.add(p3Personas);
        empresac.add(p4Personas);
        empresac.add(p6Empresa);


        // -------------------------------------------

        Border border = panelempresa.getBorder();
        Border margin = new EmptyBorder(50, 10, 10, 10);
        panelempresa.setBorder(new CompoundBorder(border, margin));

        // -------------------------------------------

        panelempresa.add(empresal, BorderLayout.CENTER);
        panelempresa.add(empresac, BorderLayout.CENTER);


        this.add(panelizquierdo, BorderLayout.WEST);
        //    panelempresa.setPreferredSize(new Dimension(1000,1000));

        JPanel panelcentro = new JPanel();
        this.add(panelcentro, BorderLayout.CENTER);

        panelcentro.add(panelempresa, BorderLayout.CENTER);
        panelcentro.add(panelhome, BorderLayout.CENTER);


        panelcentro.setLayout(new BoxLayout(panelcentro, BoxLayout.Y_AXIS));

        panelempresa.setVisible(false);
        panelhome.setVisible(true);

        // ------------- Panel Alumnos tutores
        palumnos = new JPanel();
        palumnos.setLayout(new BoxLayout(palumnos, BoxLayout.X_AXIS));
        palumnos.setVisible(false);

        JPanel palumtuto1 = new JPanel();
        //     palumtuto1.setBorder(new LineBorder(Color.black, 3, true));
        palumtuto1.setSize(new Dimension(400, 400));
        JPanel palumtuto2 = new JPanel();
        //      palumtuto2.setBorder(new LineBorder(Color.black, 3, true));
        palumtuto2.setSize(new Dimension(400, 400));

        palumnos.add(palumtuto1, BorderLayout.CENTER);
        palumnos.add(palumtuto2, BorderLayout.CENTER);

        Border border4 = palumtuto1.getBorder();
        Border margin4 = new EmptyBorder(50, 10, 10, 10);
        palumtuto1.setBorder(new CompoundBorder(border4, margin4));

        Border border5 = palumtuto2.getBorder();
        Border margin5 = new EmptyBorder(50, 10, 10, 10);
        palumtuto2.setBorder(new CompoundBorder(border5, margin5));

        // --------------------

        JButton boton = new JButton();
        JLabel imagen = new JLabel(new ImageIcon("XMLaTabla.png"));
        imagen.setHorizontalAlignment(JLabel.CENTER);
        boton.add(imagen);

        palumtuto1.add(boton);


        // ----------------------


        JButton boton2 = new JButton();
        JLabel imagen2 = new JLabel(new ImageIcon("DATaTabla.png"));
        boton2.add(imagen2);

        palumtuto2.add(boton2);

        // ----------------

        JPanel panelinferior = new JPanel();
        //    panelinferior.setMaximumSize(new Dimension(200,200));

        JLabel texto = new JLabel("Información del fichero ahora registrada en la tabla Tutores");
        Font fuente2 = new Font("ARIAL", 1, 10);
        //    texto.setFont(fuente2);
        Color Colortextoinferior = new Color(102, 153, 204);
        texto.setBackground(Colortextoinferior);
        texto.setOpaque(true);

        texto.setVisible(false);
        panelinferior.add(texto);
        palumtuto1.add(panelinferior);

        JPanel panelinferior2 = new JPanel();

        JLabel texto2 = new JLabel("Información del fichero ahora registrada en la tabla Alumnos");
        Font fuente22 = new Font("ARIAL", 1, 10);
        //   texto2.setFont(fuente22);
        Color Colortextoinferior2 = new Color(153, 204, 102);
        texto2.setBackground(Colortextoinferior2);
        texto2.setOpaque(true);

        texto2.setVisible(false);
        panelinferior2.add(texto2);
        palumtuto2.add(panelinferior2);

        // -----------
        panelcentro.add(palumnos);
        palumnos.setVisible(false);


        palumtuto1.setLayout(new BoxLayout(palumtuto1, BoxLayout.Y_AXIS));
        palumtuto2.setLayout(new BoxLayout(palumtuto2, BoxLayout.Y_AXIS));
        //     empresar.setLayout(new BoxLayout(empresar, BoxLayout.X_AXIS));


        panelcentro.add(palumnos, BorderLayout.CENTER);

        // ------------ Tabla

        clase1 Tablaadd = new clase1();
        BDselect bDselect = new BDselect();

        JPanel pTabla = new JPanel();
        //  pTabla.setMaximumSize(new Dimension(1000,500));
        pTabla.setLayout(new BoxLayout(pTabla, BoxLayout.Y_AXIS));


        pTabla.setBorder(new LineBorder(Color.black, 3, true));
        pTabla.setVisible(false);

        bDselect.getTablaProducto(new ConexionBD());
        pTabla.add(Tablaadd);
        panelcentro.add(pTabla);

        // Panel Asignacion
        Asignacion asignacion = new Asignacion();

        JPanel panelasig = new JPanel();
      //  panelasig.setMaximumSize(new Dimension(500,300));
        panelasig.setLayout(new BoxLayout(panelasig, BoxLayout.Y_AXIS));
        panelasig.setVisible(false);
        panelasig.add(asignacion);

        panelcentro.add(panelasig);

        // ------------

        home.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                panelempresa.setVisible(false);
                panelhome.setVisible(true);
                palumnos.setVisible(false);
                pTabla.setVisible(false);
                panelasig.setVisible(false);

            }

        });

        empresa.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                panelhome.setVisible(false);
                panelempresa.setVisible(true);
                palumnos.setVisible(false);
                pTabla.setVisible(false);
                panelasig.setVisible(false);


            }

        });

        alumno.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                panelhome.setVisible(false);
                panelempresa.setVisible(false);
                panelasig.setVisible(false);
                palumnos.setVisible(true);
                pTabla.setVisible(false);


            }

        });

        boton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                texto.setVisible(true);
                controlBD.leerfichero();
                try {
                    controlBD.intoValueDatos();
                } catch (ClassNotFoundException ex) {
                    ex.printStackTrace();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
                try {
                    controlBD.consultaTabla();
                } catch (ClassNotFoundException ex) {
                    ex.printStackTrace();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }


            }

        });

        boton2.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                texto2.setVisible(true);
                controlBDd.lecturaXML();
                try {
                    controlBDd.intoValueDatos();
                } catch (ClassNotFoundException ex) {
                    ex.printStackTrace();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
                try {
                    controlBDd.consultaTabla();
                } catch (ClassNotFoundException ex) {
                    ex.printStackTrace();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }


            }

        });

        profe.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                panelhome.setVisible(false);
                panelempresa.setVisible(false);
                palumnos.setVisible(false);
                pTabla.setVisible(false);
                panelasig.setVisible(true);


            }

        });

        asig.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                panelhome.setVisible(false);
                panelempresa.setVisible(false);
                palumnos.setVisible(false);
                panelasig.setVisible(false);
                pTabla.setVisible(true);


            }

        });

        bInsert.addActionListener(e -> {

            try {
                Connection conexion = cdb.conectar();
                try {
                    PreparedStatement ps = conexion.prepareStatement
                            ("INSERT INTO EMPRESAS " +
                                    "(CIF, Nombre, Direccion, CP, Localidad, Jornada, Modalidad" +
                                    ",Mail, DNI_RL, Nombre_RL, Apellidos_RL, DNI_TL" +
                                    ", Nombre_TL, Apellidos_TL, Tlfn_TL) " +
                                    "VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");

                    ps.setString(1, tfCifEmpresa.getText());
                    ps.setString(2, tfNombreEmpresa.getText());
                    ps.setString(3, tfDirecEmpresa.getText());
                    ps.setString(4, tfCodPostalEmpresa.getText());
                    ps.setString(5, tfLocalidadEmpresa.getText());
                    ps.setShort(6, (short) (cbJornadaEmpresa.getSelectedIndex() + 1));
                    ps.setShort(7, (short) (cbModalidadEmpresa.getSelectedIndex() + 1));
                    ps.setString(8, tfMailEmpresa.getText());
                    ps.setString(9, tfDniRepLegal.getText());
                    ps.setString(10, tfNombreRL.getText());
                    ps.setString(11, tfApellidosRL.getText());
                    ps.setString(12, tfDniTutLaboral.getText());
                    ps.setString(13, tfNombreTL.getText());
                    ps.setString(14, tfApellidosTL.getText());
                    ps.setString(15, tfTelefonoTL.getText());

                    if (ps.executeUpdate() == 1) {
                        JOptionPane.showMessageDialog(pTabla, "Empresa registrada correctamente");

                    } else {
                        JOptionPane.showMessageDialog(pTabla, ("No se ha podido registrar la empresa"));
                    }


                } catch (Exception ee) {
                    System.out.println(ee.getClass());
                }
                conexion.close();

            } catch (SQLException xx) {
                xx.printStackTrace();
            }

        });

        bDelete.addActionListener(e -> {
            try {
                Connection conexion = cdb.conectar();
                try {
                    PreparedStatement ps = conexion.prepareStatement
                            ("DELETE FROM empresas WHERE CIF = ?");
                    ps.setString(1, tfCifEmpresa.getText());

                    if (ps.executeUpdate() == 1) {
                        JOptionPane.showMessageDialog(pTabla, "Empresa eliminada correctamente");

                    } else {
                        JOptionPane.showMessageDialog(pTabla, "No se ha podido eliminar la empresa");
                    }

                    ps.close();
                } catch (Exception ee) {
                    System.out.println(ee.getClass());
                }
                conexion.close();

            } catch (SQLException xx) {
                xx.printStackTrace();
            }
        });

        Object currentCode = null;
        try {
            Statement s = cdb.conectar().createStatement();
            ResultSet rs = s.executeQuery("SELECT `AUTO_INCREMENT` FROM  INFORMATION_SCHEMA.TABLES " +
                    "WHERE TABLE_SCHEMA = 'bdgestionfct' AND   TABLE_NAME   = 'empresas'");
            if (rs.next()) {
                currentCode = rs.getObject("AUTO_INCREMENT");
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        tfCodEmpresa.setText(currentCode.toString());


    }
}


