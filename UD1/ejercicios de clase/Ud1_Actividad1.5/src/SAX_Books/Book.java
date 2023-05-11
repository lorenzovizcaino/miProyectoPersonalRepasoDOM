package SAX_Books;

public class Book {
    private String id;
    private String autor;
    private String titulo;
    private String genero;
    private float precio;
    private String fechaPublicacion;
    private String descripcion;

    public Book(String id, String autor, String titulo, String genero, float precio, String fechaPublicacion, String descripcion) {
        this.id = id;
        this.autor = autor;
        this.titulo = titulo;
        this.genero = genero;
        this.precio = precio;
        this.fechaPublicacion = fechaPublicacion;
        this.descripcion = descripcion;
    }

    public Book() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public float getPrecio() {
        return precio;
    }

    public void setPrecio(float precio) {
        this.precio = precio;
    }

    public String getFechaPublicacion() {
        return fechaPublicacion;
    }

    public void setFechaPublicacion(String fechaPublicacion) {
        this.fechaPublicacion = fechaPublicacion;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", autor='" + autor + '\'' +
                ", titulo='" + titulo + '\'' +
                ", genero='" + genero + '\'' +
                ", precio=" + precio +
                ", fechaPublicacion='" + fechaPublicacion + '\'' +
                ", descripcion='" + descripcion + '\'' +
                '}';
    }
}
