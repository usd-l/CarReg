/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DataBase;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.sql.Statement;


/**
 *
 * @author w
 */
public class Conexion {
    
    
    public Connection crearConex(){
        Connection conexion = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conexion = DriverManager.getConnection("jdbc:mysql://localhost:3306/eternal","root","Criss");
            System.out.println("Conexion lograda");
            
        } catch (Exception e) {
            System.out.println("Error en la conexion");
            
        }
        return conexion;
        
    }
    
    
    
public void ejecutarConsulta() {
        Connection conexion = null;
        Statement statement = null;
        ResultSet resultSet = null;

        try {
            // Crear la conexión
            conexion = crearConex();
            
            // Crear el Statement (un objeto para ejecutar consultas SQL)
            statement = conexion.createStatement();
            
            // Ejecutar la consulta SQL
            String query = "SELECT * FROM USERS";
            resultSet = statement.executeQuery(query);
            
            // Procesar los resultados
            while (resultSet.next()) {
                // Supongamos que la tabla USERS tiene las columnas 'id', 'nombre', 'email'
                int id = resultSet.getInt("id");
                String username = resultSet.getString("username");
                String psw = resultSet.getString("psw");
                
                System.out.println("ID: " + id + ", name: " + username + ", password: " + psw);
            }
        } catch (SQLException e) {
            System.out.println("Error al ejecutar la consulta");
            e.printStackTrace();
        } finally {
            // Asegurarse de cerrar los recursos
            try {
                if (resultSet != null) resultSet.close();
                if (statement != null) statement.close();
                if (conexion != null) conexion.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
    
    public static void main(String[] args) {
        Conexion con = new Conexion();
        con.crearConex();
        con.ejecutarConsulta();
    }
    
}
