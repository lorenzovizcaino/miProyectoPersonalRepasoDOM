package model;

import java.io.Serializable;

public class Alumno implements Serializable {
    private String id;
    private String nombre;
    private String dni;
    private int edad;
    private String curso;
    private boolean repite;

    public Alumno(String id, String nombre, String dni, int edad, String curso, boolean repite) {
        this.id = id;
        this.nombre = nombre;
        this.dni = dni;
        this.edad = edad;
        this.curso = curso;
        this.repite = repite;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public String getCurso() {
        return curso;
    }

    public void setCurso(String curso) {
        this.curso = curso;
    }

    public boolean isRepite() {
        return repite;
    }

    public void setRepite(boolean repite) {
        this.repite = repite;
    }

    @Override
    public String toString() {
        return "Alumno{" +
                "id='" + id + '\'' +
                ", nombre='" + nombre + '\'' +
                ", dni='" + dni + '\'' +
                ", edad=" + edad +
                ", curso='" + curso + '\'' +
                ", repite=" + repite +
                '}';
    }
}

