/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package es.teis.ud2.dao.departamento;

import es.teis.ud2.DBCPDataSourceFactory;
import es.teis.ud2.model.Departamento;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.sql.DataSource;

/**
 *
 * @author mfernandez
 */
public class DepartamentoDao implements IDepartamentoDao {

    private DataSource dataSource;

    public DepartamentoDao() {
        this.dataSource = DBCPDataSourceFactory.getDataSource();
    }

    @Override
    public Departamento create(Departamento departamento) {

        try (
                 Connection conexion = this.dataSource.getConnection();
                 PreparedStatement pstmt = conexion.prepareStatement(
                "INSERT INTO [dbo].[DEPT]( DNAME,  LOC) VALUES( ?, ?);", Statement.RETURN_GENERATED_KEYS
        );) {

            pstmt.setString(1, departamento.getDeptName());
            pstmt.setString(2, departamento.getLoc());

            // Devolverá 0 para las sentencias SQL que no devuelven nada o el número de filas afectadas
            int result = pstmt.executeUpdate();

            ResultSet clavesResultado = pstmt.getGeneratedKeys();

            if (clavesResultado.next()) {
                int departamentoId = clavesResultado.getInt(1);
                departamento.setDeptno(departamentoId);
            } else {
                departamento = null;
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
            System.err.println("Ha ocurrido una excepción: " + ex.getMessage());
            departamento = null;
        }
        return departamento;
    }

    @Override
    public Departamento read(int id) {

        String nombreDept;
        String ubicacion;
        int deptNo;
        int contador;
        Departamento departamento = null;

        try (
                 Connection conexion = this.dataSource.getConnection();
                 PreparedStatement sentencia= conexion.prepareStatement("SELECT [DEPTNO]\n"
                        + "      ,[DNAME]\n"
                        + "      ,[LOC] FROM dbo.DEPT \n"
                        + "WHERE DEPTNO=?");) {
            sentencia.setInt(1, id);

            ResultSet result = sentencia.executeQuery();
            while (result.next()) {
                contador = 0;

                deptNo = result.getInt(++contador);
                nombreDept = result.getString(++contador);
                ubicacion = result.getString(++contador);

                departamento = new Departamento(deptNo,
                        nombreDept, ubicacion);

            }

        } catch (SQLException ex) {
            ex.printStackTrace();
            System.err.println("Ha ocurrido una excepción: " + ex.getMessage());

        }
        return departamento;
    }

    @Override
    public boolean update(Departamento departamento) {

        boolean actualizado = false;
        try (
                 Connection conexion = this.dataSource.getConnection();
                 PreparedStatement pstmt = conexion.prepareStatement(
                "UPDATE [dbo].[DEPT] "
                + " SET DNAME=?,  LOC=? WHERE DEPTNO = ?")) {

            pstmt.setString(1, departamento.getDeptName());
            pstmt.setString(2, departamento.getLoc());
            pstmt.setInt(3, departamento.getDeptno());

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
                PreparedStatement pstmt = conexion.prepareStatement("Delete from DEPT where DEPTNO=?;");) {

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
