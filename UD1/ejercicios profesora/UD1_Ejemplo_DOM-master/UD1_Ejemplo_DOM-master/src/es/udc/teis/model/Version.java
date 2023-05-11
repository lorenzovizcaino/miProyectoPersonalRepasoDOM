package es.udc.teis.model;
public class Version {
    private double numero;
    private String nombre;
    private int api;

    public Version() {
    }

    public Version(double numero, String nombre, int api) {
        this.numero = numero;
        this.nombre = nombre;
        this.api = api;
    }
    
    

    public double getNumero() {
        return numero;
    }

    public void setNumero(double numero) {
        this.numero = numero;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getApi() {
        return api;
    }

    public void setApi(int api) {
        this.api = api;
    }

    @Override
    public String toString() {
        return "Version{" + "numero=" + numero + ", nombre=" + nombre + ", api=" + api + '}';
    }
    
    
}
