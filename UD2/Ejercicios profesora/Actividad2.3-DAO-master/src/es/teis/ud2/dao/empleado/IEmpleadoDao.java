package es.teis.ud2.dao.empleado;

import es.teis.ud2.model.Empleado;

public interface IEmpleadoDao {

    public Empleado create(Empleado empleado);

    public Empleado read(int id);

    public boolean update(Empleado empleado);

    public boolean delete(int id);
}
