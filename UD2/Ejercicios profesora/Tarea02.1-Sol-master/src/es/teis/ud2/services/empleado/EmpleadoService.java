/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package es.teis.ud2.services.empleado;

import es.teis.ud2.exceptions.InstanceNotFoundException;
import es.teis.ud2.exceptions.SaldoInsuficienteException;
import es.teis.ud2.model.Account;
import es.teis.ud2.model.AccountMovement;
import es.teis.ud2.model.Empleado;
import es.teis.ud2.model.dao.account.IAccountDao;
import es.teis.ud2.model.dao.accountMovement.IAccountMovementDao;
import es.teis.ud2.model.dao.empleado.IEmpleadoDao;
import java.math.BigDecimal;

/**
 *
 * @author maria
 */
public class EmpleadoService implements IEmpleadoServicio {

    private IEmpleadoDao empleadoDao;
    private IAccountDao accountDao;
    private IAccountMovementDao accountMovDao;

    public EmpleadoService(IEmpleadoDao empleadoDao, IAccountDao accountDao, IAccountMovementDao accountMovementDao) {
        this.empleadoDao = empleadoDao;
        this.accountDao = accountDao;
        this.accountMovDao = accountMovementDao;
    }

    @Override
    public Empleado create(Empleado empleado) {

        return this.empleadoDao.create(empleado);
    }

    @Override
    public AccountMovement transferir(int empnoOrigen, int empnoDestino, BigDecimal cantidad) throws SaldoInsuficienteException, InstanceNotFoundException, UnsupportedOperationException {
        int accMovId = -1;
        AccountMovement accMovement = null;
//Si la cantidad a transferir es >0
        if (cantidad.compareTo(BigDecimal.ZERO) == 1) {
            Account cuentaOrigen = accountDao.getAccountByEmpno(empnoOrigen);

            if (cuentaOrigen != null) {

                if (cuentaOrigen.getMontante().compareTo(cantidad) >= 0) {
                    Account cuentaDestino = accountDao.getAccountByEmpno(empnoDestino);

                    accMovId = accountDao.transferir(cuentaOrigen.getAccountId(), cuentaDestino.getAccountId(), cantidad);
                    if (accMovId != -1) {
                        accMovement = accountMovDao.read(accMovId);
                    }

                } else {
                    throw new SaldoInsuficienteException("No hay suficiente saldo en la cuenta: Cantidad actual: %.2f Cantidad a transferir: %.2f", cuentaOrigen.getMontante(), cantidad);
                }
            }
        } else {
            throw new UnsupportedOperationException("Las cantidades a transferir deben ser positivas");
        }

        return accMovement;
    }
}
