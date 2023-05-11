package es.teis.ud2.model;

import java.time.LocalDate;
import java.util.Date;

public class Empleado {

    private int numeroEmpleado;
    private String nombre;
    private String trabajo;
    private int numeroJefe;
    private LocalDate fechaNacimiento;
    private double salario;
    private double complemento;
    private int departamento;

    public Empleado(int numeroEmpleado, String nombre, String trabajo, int numeroJefe, LocalDate fechaNacimiento, double salario, double complemento, int departamento) {
        this.numeroEmpleado = numeroEmpleado;
        this.nombre = nombre;
        this.trabajo = trabajo;
        this.numeroJefe = numeroJefe;
        this.fechaNacimiento = fechaNacimiento;
        this.salario = salario;
        this.complemento = complemento;
        this.departamento = departamento;
    }

    public Empleado(String nombre, String trabajo, int numeroJefe, LocalDate fechaNacimiento, double salario, double complemento, int departamento) {
        this.nombre = nombre;
        this.trabajo = trabajo;
        this.numeroJefe = numeroJefe;
        this.fechaNacimiento = fechaNacimiento;
        this.salario = salario;
        this.complemento = complemento;
        this.departamento = departamento;
    }

    public int getNumeroEmpleado() {
        return numeroEmpleado;
    }

    public void setNumeroEmpleado(int numeroEmpleado) {
        this.numeroEmpleado = numeroEmpleado;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTrabajo() {
        return trabajo;
    }

    public void setTrabajo(String trabajo) {
        this.trabajo = trabajo;
    }

    public int getNumeroJefe() {
        return numeroJefe;
    }

    public void setNumeroJefe(int numeroJefe) {
        this.numeroJefe = numeroJefe;
    }

    public LocalDate getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(LocalDate fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public double getSalario() {
        return salario;
    }

    public void setSalario(double salario) {
        this.salario = salario;
    }

    public double getComplemento() {
        return complemento;
    }

    public void setComplemento(double complemento) {
        this.complemento = complemento;
    }

    public int getDepartamento() {
        return departamento;
    }

    public void setDepartamento(int departamento) {
        this.departamento = departamento;
    }
}
