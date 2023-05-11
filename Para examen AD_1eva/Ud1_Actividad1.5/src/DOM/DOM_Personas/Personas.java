package DOM.DOM_Personas;

public class Personas {
    private long id;
    private String nombre;
    private String dni;
    private int edad;
    private float salario;
    private boolean borrado;

    public Personas(long id, String nombre, String dni, int edad, float salario, boolean borrado) {
        this.id = id;
        this.nombre = nombre;
        this.dni = dni;
        this.edad = edad;
        this.salario = salario;
        this.borrado = borrado;
    }

    public Personas() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
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
        return "Personas{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", dni='" + dni + '\'' +
                ", edad=" + edad +
                ", salario=" + salario +
                ", borrado=" + borrado +
                '}';
    }
}
