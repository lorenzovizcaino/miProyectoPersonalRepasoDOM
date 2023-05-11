/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package es.teis.ud2.model.dao.accountMovement;

import es.teis.ud2.data.DBCPDataSourceFactory;
import es.teis.ud2.exceptions.InstanceNotFoundException;
import es.teis.ud2.model.Account;
import es.teis.ud2.model.AccountMovement;
import es.teis.ud2.model.Empleado;
import es.teis.ud2.model.dao.AbstractGenericDao;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import javax.sql.DataSource;

/**
 *
 * @author maria
 */
public class AccountMovementSQLServerDao extends AbstractGenericDao<AccountMovement> implements IAccountMovementDao {

    private DataSource dataSource;

    public AccountMovementSQLServerDao() {
        this.dataSource = DBCPDataSourceFactory.getDataSource();
    }

    @Override
    public AccountMovement create(AccountMovement entity) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public AccountMovement read(int id) throws InstanceNotFoundException {

        int accountNoOrigen;
        int accountNoDestino;

        Date fecha;
        BigDecimal amount;
        int contador;
        AccountMovement accMovement = null;

        try (
                 Connection conexion = this.dataSource.getConnection();  PreparedStatement sentencia
                = conexion.prepareStatement(" SELECT\n"
                        + "      [ACCOUNT_ORIGIN_ID]\n"
                        + "      ,[ACCOUNT_DEST_ID]\n"
                        + "      ,[AMOUNT]\n"
                        + "      ,[DATETIME]\n"
                        + "  FROM [dbo].[ACC_MOVEMENT]"
                        + "WHERE ACCOUNT_MOV_ID=?");) {
            sentencia.setInt(1, id);

            ResultSet result = sentencia.executeQuery();
            if (result.next()) {
                contador = 0;

                accountNoOrigen = result.getInt(++contador);
                accountNoDestino = result.getInt(++contador);
                amount = result.getBigDecimal(++contador);
                fecha = result.getDate(++contador);

                Account cuentaOrigen = new Account();
                cuentaOrigen.setAccountId(accountNoOrigen);

                Account cuentaDestino = new Account();
                cuentaDestino.setAccountId(accountNoDestino);

                
                accMovement = new AccountMovement(id, cuentaOrigen, cuentaDestino, amount, fecha.toLocalDate());

            } else {
                throw new InstanceNotFoundException(id, getEntityClass());
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
            System.err.println("Ha ocurrido una excepción: " + ex.getMessage());

        }
        return accMovement;
    }

    @Override
    public boolean update(AccountMovement entity) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public boolean delete(int id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}
