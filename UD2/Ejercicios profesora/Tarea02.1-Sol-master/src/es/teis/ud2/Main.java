/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package es.teis.ud2;

import es.teis.ud2.exceptions.InstanceNotFoundException;
import es.teis.ud2.exceptions.SaldoInsuficienteException;
import es.teis.ud2.model.AccountMovement;
import es.teis.ud2.model.dao.account.AccountSQLServerDao;
import es.teis.ud2.model.dao.account.IAccountDao;
import es.teis.ud2.model.dao.accountMovement.AccountMovementSQLServerDao;
import es.teis.ud2.model.dao.accountMovement.IAccountMovementDao;
import es.teis.ud2.model.dao.empleado.EmpleadoSQLServerDao;
import es.teis.ud2.model.dao.empleado.IEmpleadoDao;
import es.teis.ud2.services.empleado.EmpleadoService;
import es.teis.ud2.services.empleado.IEmpleadoServicio;
import java.math.BigDecimal;

/**
 *
 * @author maria
 */
public class Main {

    public static void main(String[] args) {

        AccountMovement accMovement = transferirDineroEntreEmpleados(7369, 7499, new BigDecimal(1500));
        if (accMovement != null) {
            System.out.println("Se ha creado el registro: " + accMovement);
        }
    }

    private static AccountMovement transferirDineroEntreEmpleados(int empnoOrigen, int empnoDestino, BigDecimal cantidad) {
        AccountMovement accMovement = null;

        try {
            IAccountDao accountDao = new AccountSQLServerDao();
            IAccountMovementDao accountMovementDao = new AccountMovementSQLServerDao();
            IEmpleadoDao empleadoDao = new EmpleadoSQLServerDao();
            IEmpleadoServicio empleadoServicio = new EmpleadoService(empleadoDao, accountDao, accountMovementDao);

            accMovement = empleadoServicio.transferir(empnoOrigen, empnoDestino, cantidad);
        } catch (SaldoInsuficienteException | InstanceNotFoundException | UnsupportedOperationException ex) {
            ex.printStackTrace();
            System.err.println("Se ha detectado una excepción: " + ex.getMessage());
        }
        return accMovement;
    }

}
