/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package es.teis.ud2;

import es.teis.ud2.model.Departamento;
import es.teis.ud2.model.Empleado;

import java.sql.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import javax.sql.DataSource;

/**
 *
 * @author maria
 */
public class Main {

    final static String SEPARATOR = "\t\t\t\t";

    public static void main(String[] args) {
        int numEmpleado;
        consultarDepts();
        consultarEmpleadosRangoSalarial(1000.50f, 2000.50f);
        borrarDept(40);
        Departamento operacionesDept = new Departamento(40,"OPERATIONS", "BOSTON");
        insertarDepartamentoConIdentity(operacionesDept);
        insertarDepartamento(operacionesDept);
        operacionesDept.setDeptName("OPERACIONES 2");
        actualizarDept(operacionesDept);
        nombresConSalario();
        nombresConSalarioConJefes();

        // SI QUEREMOS USAR UN PATRON DE FECHA A NUESTRO ANTOJO, LA SIGUIENTE LINEA
        //LocalDate seisNov = LocalDate.parse("6/11/2020", DateTimeFormatter.ofPattern("d/M/yyyy") );

        LocalDate fechaNacimiento=LocalDate.parse("1990-07-06");
        Empleado empleado=new Empleado("Juanito","Programador",7839,fechaNacimiento,2500,400,40);
        numEmpleado=insertarEmpleado(empleado);
        if(numEmpleado>0){
            System.out.println("Se ha introducido el empleado con el numero: "+numEmpleado);
        }else{
            System.out.println("No se ha introducido el empleado");
        }

        incrementarSalario(numEmpleado,10);

    }

    private static void incrementarSalario(int numEmpleado, float porcentaje) {
        double salarioInicial, salarioFinal = 0;
        DataSource ds = DBCPDataSourceFactory.getDataSource();

        try (
                Connection conexion = ds.getConnection();
                PreparedStatement pstmt = conexion.prepareStatement("select sal from emp where empno=?;");) {

            pstmt.setInt(1,numEmpleado);

            ResultSet result = pstmt.executeQuery();
            result.next();
            salarioInicial=result.getDouble(1);
            salarioFinal=salarioInicial*(1+(porcentaje/100));



        } catch (SQLException ex) {
            ex.printStackTrace();
            System.err.println("Ha ocurrido una excepción: " + ex.getMessage());

        }

        try (
                Connection conexion = ds.getConnection();
                PreparedStatement pstmt = conexion.prepareStatement("update emp set sal=? where empno=?;");) {

            pstmt.setDouble(1, salarioFinal);
            pstmt.setInt(2,numEmpleado);
            pstmt.executeUpdate();


        } catch (SQLException ex) {
            ex.printStackTrace();
            System.err.println("Ha ocurrido una excepción: " + ex.getMessage());

        }



    }

    private static int insertarEmpleado(Empleado empleado) {
        DataSource ds = DBCPDataSourceFactory.getDataSource();
        int numEmpleado;
        try (
                Connection conexion = ds.getConnection();
                PreparedStatement pstmt = conexion.prepareStatement("INSERT INTO [dbo].[EMP]( ENAME,  JOB, MGR, HIREDATE, SAL, COMM, DEPTNO) VALUES( ?, ?, ?, ?, ?, ?, ?);"
        )) {

            pstmt.setString(1, empleado.getNombre());
            pstmt.setString(2, empleado.getTrabajo());
            pstmt.setInt(3, empleado.getNumeroJefe());
            pstmt.setDate(4, Date.valueOf(empleado.getFechaNacimiento()));
            pstmt.setDouble(5, empleado.getSalario());
            pstmt.setDouble(6, empleado.getComplemento());
            pstmt.setInt(7, empleado.getDepartamento());


            int result = pstmt.executeUpdate();

            // Devolverá 0 para las sentencias SQL que no devuelven nada o el número de filas afectadas
            System.out.println("");
            System.out.println("--------------------------------------------------------------------------------------------------------------------");

            System.out.println("El número de filas afectadas es: " + result);
            if (result>0){
                numEmpleado=consultarEmpleadoPorNombreYFechaNacimiento(empleado);
                return numEmpleado;
            }


        } catch (SQLException ex) {
            ex.printStackTrace();
            System.err.println("Ha ocurrido una excepción: " + ex.getMessage());

        }
        return 0;

    }

    private static int consultarEmpleadoPorNombreYFechaNacimiento(Empleado empleado) {
        DataSource ds = DBCPDataSourceFactory.getDataSource();
        int numEmpleado=0;
        try (
                Connection conexion = ds.getConnection();
                PreparedStatement pstmt = conexion.prepareStatement("select empno from emp where ename=? and HIREDATE=?;");) {

            pstmt.setString(1, empleado.getNombre());
            pstmt.setString(2, String.valueOf(empleado.getFechaNacimiento()));


            ResultSet result = pstmt.executeQuery();
            result.next();
            numEmpleado=result.getInt(1);





        } catch (SQLException ex) {
            ex.printStackTrace();
            System.err.println("Ha ocurrido una excepción: " + ex.getMessage());

        }
        return  numEmpleado;
    }

    private static void nombresConSalarioConJefes() {
        DataSource ds = DBCPDataSourceFactory.getDataSource();

        try (
                Connection conexion = ds.getConnection();
                PreparedStatement pstmt = conexion.prepareStatement("SELECT  E.ENAME ,  E.SAL, J.EMPNO, J.ENAME FROM EMP E INNER JOIN EMP J ON E.MGR=J.EMPNO ORDER BY J.ENAME;");) {



            ResultSet result = pstmt.executeQuery();

            int columnas = result.getMetaData().getColumnCount();
            for (int i = 1; i <= columnas; i++) {
                System.out.print(result.getMetaData().getColumnName(i) + SEPARATOR);
            }

            System.out.println("");
            System.out.println("--------------------------------------------------------------------------------------------------------------------");
            while (result.next()) {
                System.out.println(result.getString(1) + SEPARATOR + result.getFloat(2)+ SEPARATOR + result.getInt(3) + SEPARATOR + result.getString(4));
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
            System.err.println("Ha ocurrido una excepción: " + ex.getMessage());

        }
    }

    private static void nombresConSalario() {
        DataSource ds = DBCPDataSourceFactory.getDataSource();

        try (
                Connection conexion = ds.getConnection();
                PreparedStatement pstmt = conexion.prepareStatement("SELECT ENAME, SAL FROM EMP ORDER BY SAL");) {



            ResultSet result = pstmt.executeQuery();

            int columnas = result.getMetaData().getColumnCount();
            for (int i = 1; i <= columnas; i++) {
                System.out.print(result.getMetaData().getColumnName(i) + SEPARATOR);
            }

            System.out.println("");
            System.out.println("--------------------------------------------------------------------------------------------------------------------");
            while (result.next()) {
                System.out.println(result.getString(1) + SEPARATOR + result.getFloat(2));
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
            System.err.println("Ha ocurrido una excepción: " + ex.getMessage());

        }

    }

    private static void consultarDepts() {
        DataSource ds = DBCPDataSourceFactory.getDataSource();

        try (
                 Connection conexion = ds.getConnection();
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

    private static void consultarEmpleadosRangoSalarial(float minSalario, float maxSalario) {

        DataSource ds = DBCPDataSourceFactory.getDataSource();

        try (
                 Connection conexion = ds.getConnection();
                 PreparedStatement pstmt = conexion.prepareStatement("SELECT ENAME, SAL FROM EMP WHERE SAL >= ? AND SAL <=? ORDER BY SAL DESC");) {

            pstmt.setFloat(1, minSalario);
            pstmt.setFloat(2, maxSalario);

            ResultSet result = pstmt.executeQuery();

            int columnas = result.getMetaData().getColumnCount();
            for (int i = 1; i <= columnas; i++) {
                System.out.print(result.getMetaData().getColumnName(i) + SEPARATOR);
            }

            System.out.println("");
            System.out.println("--------------------------------------------------------------------------------------------------------------------");
            while (result.next()) {
                System.out.println(result.getString(1) + SEPARATOR + result.getFloat(2));
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
            System.err.println("Ha ocurrido una excepción: " + ex.getMessage());

        }
    }

    private static void borrarDept(int deptNo) {
        DataSource ds = DBCPDataSourceFactory.getDataSource();

        try (
                 Connection conexion = ds.getConnection();
                 PreparedStatement pstmt = conexion.prepareStatement("DELETE FROM dept WHERE DEPTNO=?");) {

            pstmt.setInt(1, deptNo);

            int result = pstmt.executeUpdate();

            // Devolverá 0 para las sentencias SQL que no devuelven nada o el número de filas afectadas
            System.out.println("");
            System.out.println("--------------------------------------------------------------------------------------------------------------------");

            System.out.println("El número de filas afectadas  es: " + result);

        } catch (SQLException ex) {
            ex.printStackTrace();
            System.err.println("Ha ocurrido una excepción: " + ex.getMessage());

        }
    }
    
    private static void insertarDepartamentoConIdentity(Departamento departamento){
         DataSource ds = DBCPDataSourceFactory.getDataSource();

        try (
                 Connection conexion = ds.getConnection();  PreparedStatement pstmt = conexion.prepareStatement(
                         "SET IDENTITY_INSERT dbo.DEPT ON; \n"
                         + "INSERT INTO [dbo].[DEPT](DEPTNO, DNAME,  LOC) VALUES(?, ?, ?);\n"
                                 + " SET IDENTITY_INSERT dbo.DEPT OFF");) {

            pstmt.setInt(1, departamento.getDeptno());
            pstmt.setString(2, departamento.getDeptName());
            pstmt.setString(3, departamento.getLoc());

            int result = pstmt.executeUpdate();

            // Devolverá 0 para las sentencias SQL que no devuelven nada o el número de filas afectadas
            System.out.println("");
            System.out.println("--------------------------------------------------------------------------------------------------------------------");

            System.out.println("El número de filas afectadas es: " + result);

        } catch (SQLException ex) {
            ex.printStackTrace();
            System.err.println("Ha ocurrido una excepción: " + ex.getMessage());

        }
    }
    
    
     private static void insertarDepartamento(Departamento departamento){
         DataSource ds = DBCPDataSourceFactory.getDataSource();

        try (
                 Connection conexion = ds.getConnection();  PreparedStatement pstmt = conexion.prepareStatement(
                      
                          "INSERT INTO [dbo].[DEPT]( DNAME,  LOC) VALUES( ?, ?);"
                              )) {

            pstmt.setString(1, departamento.getDeptName());
            pstmt.setString(2, departamento.getLoc());

            int result = pstmt.executeUpdate();

            // Devolverá 0 para las sentencias SQL que no devuelven nada o el número de filas afectadas
            System.out.println("");
            System.out.println("--------------------------------------------------------------------------------------------------------------------");

            System.out.println("El número de filas afectadas es: " + result);

        } catch (SQLException ex) {
            ex.printStackTrace();
            System.err.println("Ha ocurrido una excepción: " + ex.getMessage());

        }
    }
    
    
      private static void actualizarDept(Departamento departamento){
         DataSource ds = DBCPDataSourceFactory.getDataSource();

        try (
                 Connection conexion = ds.getConnection();  PreparedStatement pstmt = conexion.prepareStatement(
                         "UPDATE [dbo].[DEPT]  SET DNAME=?,  LOC=? WHERE DEPTNO = ?")) {

            
            pstmt.setString(1, departamento.getDeptName());
            pstmt.setString(2, departamento.getLoc());
            pstmt.setInt(3, departamento.getDeptno());

            int result = pstmt.executeUpdate();

            // Devolverá 0 para las sentencias SQL que no devuelven nada o el número de filas afectadas
            System.out.println("");
            System.out.println("--------------------------------------------------------------------------------------------------------------------");

            System.out.println("El número de filas afectadas es: " + result);

        } catch (SQLException ex) {
            ex.printStackTrace();
            System.err.println("Ha ocurrido una excepción: " + ex.getMessage());

        }
    }
}
