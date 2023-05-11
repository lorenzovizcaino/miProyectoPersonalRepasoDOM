package ad.teis.model;

public class Persona {
    private long id;
    private String nombre;
    private String dni;
    private int edad;
    private float salario;
    private boolean borrado;


    public Persona(long id, String dni, int edad, float salario) {
        this.id = id;
        this.dni = dni;
        this.edad = edad;
        this.salario = salario;
    }

    public Persona(long id, String dni, int edad, float salario, boolean borrado) {
        this.id = id;
        this.dni = dni;
        this.edad = edad;
        this.salario = salario;
        this.borrado = borrado;
    }

    public Persona(long id, String dni, String nombre, int edad, float salario, boolean borrado) {
        this.id = id;
        this.dni = dni;
        this.nombre = nombre;
        this.edad = edad;
        this.salario = salario;
        this.borrado = borrado;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public float getSalario() {
        return salario;
    }

    public void setSalario(float salario) {
        this.salario = salario;
    }

    public boolean isBorrado() {
        return borrado;
    }

    public void setBorrado(boolean borrado) {
        this.borrado = borrado;
    }

    @Override
    public String toString() {
        return "Persona{" +
                "id=" + id +
                ", dni='" + dni + '\'' +
                ", edad=" + edad +
                ", salario=" + salario +
                '}';
    }
}
