package es.teis.ud2.dao.empleado;

import es.teis.ud2.DBCPDataSourceFactory;
import es.teis.ud2.model.Departamento;
import es.teis.ud2.model.Empleado;

import javax.sql.DataSource;
import java.math.BigDecimal;
import java.sql.*;
import java.time.LocalDate;

public class EmpleadoDao implements  IEmpleadoDao{

    private DataSource dataSource;

    public EmpleadoDao() {
        this.dataSource = DBCPDataSourceFactory.getDataSource();
    }
    @Override
    public Empleado create(Empleado empleado) {
        try (
                Connection conexion = this.dataSource.getConnection();
                PreparedStatement pstmt = conexion.prepareStatement(
                        "INSERT INTO [dbo].[EMP]( ENAME,  JOB, MGR, HIREDATE, SAL, COMM, DEPTNO) VALUES( ?, ?, ?, ?, ?, ?, ?);", Statement.RETURN_GENERATED_KEYS
                );) {

            pstmt.setString(1, empleado.getNombre());
            pstmt.setString(2, empleado.getPuesto());
            pstmt.setInt(3,empleado.getJefe().getEmpleadoId());
            pstmt.setDate(4, Date.valueOf(empleado.getFechaContratacion()));
            pstmt.setBigDecimal(5, empleado.getSalario());
            pstmt.setBigDecimal(6, empleado.getComision());
            pstmt.setInt(7, empleado.getDepartamento().getDeptno());

            // Devolverá 0 para las sentencias SQL que no devuelven nada o el número de filas afectadas
            int result = pstmt.executeUpdate();

            ResultSet clavesResultado = pstmt.getGeneratedKeys();

            if (clavesResultado.next()) {
                int empleadoId = clavesResultado.getInt(1);
                empleado.setEmpleadoId(empleadoId);
            } else {
                empleado = null;
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
            System.err.println("Ha ocurrido una excepción: " + ex.getMessage());
            empleado = null;
        }
        return empleado;
    }

    @Override
    public Empleado read(int id) {

        int empleadoId;
        String nombre;
        String puesto;
        Empleado jefe = null;
        LocalDate fechaContratacion;
        BigDecimal salario;
        BigDecimal comision;
        Departamento departamento = null;
        Empleado empleado = null;
        int contador;

        try (
                Connection conexion = this.dataSource.getConnection();
                PreparedStatement pstmt= conexion.prepareStatement("select * from emp where EMPNO=?;");) {

            pstmt.setInt(1, id);
            ResultSet result = pstmt.executeQuery();
            while (result.next()) {
                contador = 0;

                empleadoId = result.getInt(++contador);
                nombre = result.getString(++contador);
                puesto = result.getString(++contador);
                jefe.setEmpleadoId(result.getInt(++contador));
                fechaContratacion=result.getDate(++contador).toLocalDate();
                salario = result.getBigDecimal(++contador);
                comision = result.getBigDecimal(++contador);
                departamento.setDeptno(result.getInt(++contador));

                empleado = new Empleado(nombre, puesto, jefe, fechaContratacion, salario, comision,departamento);

            }

        } catch (SQLException ex) {
            ex.printStackTrace();
            System.err.println("Ha ocurrido una excepción: " + ex.getMessage());

        }
        return empleado;

    }

    @Override
    public boolean update(Empleado empleado) {
        boolean actualizado = false;
        try (
                Connection conexion = this.dataSource.getConnection();
                PreparedStatement pstmt = conexion.prepareStatement(
                        "UPDATE EMP SET ENAME=?, JOB=?, MGR=?, HIREDATE=?, SAL=?, COMM=?, DEPTNO=? WHERE EMPNO=?;")) {

            pstmt.setString(1, empleado.getNombre());
            pstmt.setString(2, empleado.getPuesto());
            pstmt.setInt(3, empleado.getJefe().getEmpleadoId());
            pstmt.setDate(4, Date.valueOf(empleado.getFechaContratacion()));//ojo con esta fecha
            pstmt.setBigDecimal(5, empleado.getSalario());
            pstmt.setBigDecimal(6, empleado.getComision());
            pstmt.setInt(7,empleado.getDepartamento().getDeptno());

            int result = pstmt.executeUpdate();
            actualizado = (result == 1);

            // Devolverá 0 para las sentencias SQL que no devuelven nada o el número de filas afectadas
        } catch (SQLException ex) {
            ex.printStackTrace();
            System.err.println("Ha ocurrido una excepción: " + ex.getMessage());

        }
        return actualizado;


    }

    @Override
    public boolean delete(int id) {
        boolean delete=false;
        try (
                Connection conexion = this.dataSource.getConnection();
                PreparedStatement pstmt = conexion.prepareStatement("DELETE FROM EMP WHERE EMPNO=?;");) {

            pstmt.setInt(1, id);
            int result=pstmt.executeUpdate();
            delete = (result == 1);
        } catch (SQLException ex) {
            ex.printStackTrace();
            System.err.println("Ha ocurrido una excepción: " + ex.getMessage());
        }
        return delete;
    }
}
