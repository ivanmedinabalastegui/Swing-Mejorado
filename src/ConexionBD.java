/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ConexionBD {

    Connection con;
    String db;
    String url;
    String user;
    String pass;

    public Connection conectar() throws SQLException {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con=DriverManager.getConnection(url, user, pass);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ConexionBD.class.getName()).log(Level.SEVERE, null, ex);
        }
        return con;
    }

    public ConexionBD() {
        url = "jdbc:mysql://localhost:3306/bdgestionfct?useSSL=false&serverTimezone=UTC";;
        user = "root";
        pass= "";
        System.out.println("conectado");
    }

    public void desconectar() throws SQLException{
        con.close();
    }
}