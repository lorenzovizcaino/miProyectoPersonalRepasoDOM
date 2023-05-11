/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package ud2_ejemplobasicojdbc_sqlserver;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author maria
 */
public class UD2_EjemploBasicoJDBC_SQLServer {

    final static String SEPARATOR = "\t\t\t\t";

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        String url = "jdbc:sqlserver://localhost:1433;database=empresa;user=user;password=abc123.;encrypt=true;trustServerCertificate=true";
        try (
                 Connection conexion = DriverManager.getConnection(url);
                 Statement sentencia = conexion.createStatement();
                 ResultSet result = sentencia.executeQuery("SELECT * FROM dbo.DEPT");) {

            int columnas = result.getMetaData().getColumnCount();
            for (int i = 1; i <= columnas; i++) {
                System.out.print(result.getMetaData().getColumnName(i) + SEPARATOR);
            }

            System.out.println("");
            System.out.println("--------------------------------------------------------------------------------------------------------------------");
            while (result.next()) {
                System.out.println(result.getInt(1) + SEPARATOR + result.getString(2) + SEPARATOR + result.getString(3) + SEPARATOR);
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
            System.err.println("Ha ocurrido una excepción: " + ex.getMessage());
          
        }

    }

}
