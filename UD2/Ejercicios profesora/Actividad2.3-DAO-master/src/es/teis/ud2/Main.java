/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package es.teis.ud2;

import es.teis.ud2.model.Departamento;
import es.teis.ud2.model.Empleado;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;

import javax.sql.DataSource;

/**
 *
 * @author maria
 */
public class Main {

    final static String SEPARATOR = "\t\t\t\t";

    public static void main(String[] args) {
        consultarEmpleadosConSalario();
//        consultarEmpleadosConSalarioConJefe();

      //crearEInsertarEmpleado();
      // incrementarSalario(7936, 0.5f);
        //borrarEmpleado(7937);
    }

    private static void borrarEmpleado(int numEmp) {
        DataSource ds = DBCPDataSourceFactory.getDataSource();

        try (
                Connection conexion = ds.getConnection();
                PreparedStatement sentencia = conexion.prepareStatement("Delete from emp where empno=?;");) {

            sentencia.setInt(1, numEmp);
            if (sentencia.executeUpdate()>0){
                System.out.println("Se ha borrado el empleado num "+numEmp);

            }

        } catch (SQLException ex) {
            ex.printStackTrace();
            System.err.println("Ha ocurrido una excepción: " + ex.getMessage());

        }
    }

    private static void consultarEmpleadosConSalario() {
        DataSource ds = DBCPDataSourceFactory.getDataSource();

        try (
                 Connection conexion = ds.getConnection();
                 Statement sentencia = conexion.createStatement();  ResultSet result = sentencia.executeQuery("SELECT ENAME, SAL FROM dbo.EMP ORDER BY SAL");) {

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

    private static void consultarEmpleadosConSalarioConJefe() {
        DataSource ds = DBCPDataSourceFactory.getDataSource();

        try (
                 Connection conexion = ds.getConnection();  Statement sentencia = conexion.createStatement();  ResultSet result = sentencia.
                executeQuery("SELECT e.ENAME, e.SAL, j.ENAME, j.SAL FROM dbo.EMP e INNER JOIN dbo.EMP j ON e.MGR = j.EMPNO ORDER BY j.ename ");) {

            int columnas = result.getMetaData().getColumnCount();
            for (int i = 1; i <= columnas; i++) {
                System.out.print(result.getMetaData().getColumnName(i) + SEPARATOR);
            }

            System.out.println("");
            System.out.println("--------------------------------------------------------------------------------------------------------------------");
            while (result.next()) {
                System.out.println(result.getString(1) + SEPARATOR + result.getFloat(2) + SEPARATOR + result.getString(3) + SEPARATOR + result.getFloat(4));
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
            System.err.println("Ha ocurrido una excepción: " + ex.getMessage());

        }
    }

    private static Departamento getDepartamenteoById(int deptId) {

        String nombreDept;
        String ubicacion;
        int deptNo;
        int contador;
        Departamento departamento = null;
        DataSource ds = DBCPDataSourceFactory.getDataSource();
        try (
                 Connection conexion = ds.getConnection();
                 PreparedStatement sentencia = conexion.prepareStatement("SELECT [DEPTNO]\n"
                        + "      ,[DNAME]\n"
                        + "      ,[LOC] FROM dbo.DEPT \n"
                        + "WHERE DEPTNO=?");) {
            sentencia.setInt(1, deptId);

            ResultSet result = sentencia.executeQuery();
            while (result.next()) {
                contador = 0;
                deptNo = result.getInt(++contador);
                nombreDept = result.getString(++contador);
                ubicacion = result.getString(++contador);

                departamento = new Departamento(deptNo, nombreDept, ubicacion);

            }

        } catch (SQLException ex) {
            ex.printStackTrace();
            System.err.println("Ha ocurrido una excepción: " + ex.getMessage());

        }
        return departamento;
    }

    private static Empleado getEmpleadoByEmpleadoId(int empno,
            boolean inicializacionLazy) {
        String nombre;
        int empleadoId;
        String puesto;
        int jefeId;
        LocalDate fechaContratacion;
        Date fechaContratacionSQL;
        BigDecimal salario, comision;
        int departamentoId;
        int contador;
        Departamento departamento = null;
        Empleado jefe = null;
        Empleado empleadoRecuperado = null;

        DataSource ds = DBCPDataSourceFactory.getDataSource();

        try (
                 Connection conexion = ds.getConnection();  PreparedStatement pstmt = conexion.prepareStatement("SELECT [EMPNO]\n"
                + "      ,[ENAME]\n"
                + "      ,[JOB]\n"
                + "      ,[MGR]\n"
                + "      ,[HIREDATE]\n"
                + "      ,[SAL]\n"
                + "      ,[COMM]\n"
                + "      ,[DEPTNO] FROM dbo.EMP \n"
                + " WHERE EMPNO =?"
        );) {
            pstmt.setInt(1, empno);
            ResultSet result = pstmt.executeQuery();

            while (result.next()) {
                contador = 0;
                empleadoId = result.getInt(++contador);
                nombre = result.getString(++contador);
                puesto = result.getString(++contador);
                jefeId = result.getInt(++contador);
                fechaContratacion = result.getDate(++contador).toLocalDate();
                salario = result.getBigDecimal(++contador);
                comision = result.getBigDecimal(++contador);
                departamentoId = result.getInt(++contador);

                if (!inicializacionLazy) {
                    departamento = getDepartamenteoById(departamentoId);
                    jefe = getEmpleadoByEmpleadoId(jefeId, true);
                } else {
                    departamento = new Departamento();
                    departamento.setDeptno(departamentoId);

                    jefe = new Empleado();
                    jefe.setEmpleadoId(jefeId);
                }

                empleadoRecuperado = new Empleado(nombre, puesto, jefe, fechaContratacion, salario, comision, departamento);
                empleadoRecuperado.setEmpleadoId(empleadoId);

            }

        } catch (SQLException ex) {
            ex.printStackTrace();
            System.err.println("Ha ocurrido una excepción: " + ex.getMessage());

        }
        return empleadoRecuperado;
    }

    private static void crearEInsertarEmpleado() {
        Empleado jefe = getEmpleadoByEmpleadoId(7698, true);
        Departamento departamento = getDepartamenteoById(40);

        Empleado empleado = new Empleado("Pepe", "Comercial", jefe, LocalDate.now(),
                new BigDecimal(30000), new BigDecimal(0.02), departamento);
        int empleadoId = insertarEmpledo(empleado);
        if (empleadoId != -1) {
            System.out.println("Se ha insertado correctamente un empleado con empno=" + empleadoId);
        } else {
            System.out.println("Ha habido un problema al insertar un empleado");
        }

    }

    private static int insertarEmpledo(Empleado empleado) {

        int empleadoId = -1;
        DataSource ds = DBCPDataSourceFactory.getDataSource();

        try (
                 Connection conexion = ds.getConnection();  PreparedStatement pstmt = conexion.prepareStatement(
                "INSERT INTO [dbo].[EMP]\n"
                + "           ([ENAME]\n"
                + "           ,[JOB]\n"
                + "           ,[MGR]\n"
                + "           ,[HIREDATE]\n"
                + "           ,[SAL]\n"
                + "           ,[COMM]\n"
                + "           ,[DEPTNO])\n"
                + "     VALUES\n"
                + "           (? \n"
                + "           ,? \n"
                + "           ,? \n"
                + "           ,? \n"
                + "           ,? \n"
                + "           ,? \n"
                + "           ,? );", Statement.RETURN_GENERATED_KEYS
        );) {

            int contador = 0;
            pstmt.setString(++contador, empleado.getNombre());
            pstmt.setString(++contador, empleado.getPuesto());
            pstmt.setInt(++contador, empleado.getJefe().getEmpleadoId());
            pstmt.setDate(++contador, Date.valueOf(empleado.getFechaContratacion()));
            pstmt.setBigDecimal(++contador, empleado.getSalario());
            pstmt.setBigDecimal(++contador, empleado.getComision());
            pstmt.setInt(++contador, empleado.getDepartamento().getDeptno());

            // Devolverá 0 para las sentencias SQL que no devuelven nada o el número de filas afectadas
            if (pstmt.executeUpdate() > 0) {

                ResultSet clavesResultado = pstmt.getGeneratedKeys();

                if (clavesResultado.next()) {
                    empleadoId = clavesResultado.getInt(1);
                }

            }

        } catch (SQLException ex) {
            ex.printStackTrace();
            System.err.println("Ha ocurrido una excepción: " + ex.getMessage());

        }

        return empleadoId;
    }

    private static void incrementarSalario(int empId, float porcentaje) {

        DataSource ds = DBCPDataSourceFactory.getDataSource();

        try (
                 Connection conexion = ds.getConnection();  
                PreparedStatement pstmt = conexion.prepareStatement(
                "UPDATE [dbo].[EMP]  SET SAL=SAL*(1+?) "
                        + "WHERE EMPNO = ?")) {

            pstmt.setFloat(1, porcentaje);
            pstmt.setInt(2, empId);

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
