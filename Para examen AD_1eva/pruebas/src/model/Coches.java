package model;

import java.time.LocalDate;

public class Coches {
    private String matricula;
    private String marca;
    private int caballos;
    private LocalDate fechaVenta;
    private boolean climatizador;

    public Coches(String matricula, String marca, int caballos, LocalDate fechaVenta, boolean climatizador) {
        this.matricula = matricula;
        this.marca = marca;
        this.caballos = caballos;
        this.fechaVenta = fechaVenta;
        this.climatizador = climatizador;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public int getCaballos() {
        return caballos;
    }

    public void setCaballos(int caballos) {
        this.caballos = caballos;
    }

    public LocalDate getFechaVenta() {
        return fechaVenta;
    }

    public void setFechaVenta(LocalDate fechaVenta) {
        this.fechaVenta = fechaVenta;
    }

    public boolean isClimatizador() {
        return climatizador;
    }

    public void setClimatizador(boolean climatizador) {
        this.climatizador = climatizador;
    }

    @Override
    public String toString() {
        return "coches{" +
                "matricula='" + matricula + '\'' +
                ", marca='" + marca + '\'' +
                ", caballos=" + caballos +
                ", fechaVenta=" + fechaVenta +
                ", climatizador=" + climatizador +
                '}';
    }
}
