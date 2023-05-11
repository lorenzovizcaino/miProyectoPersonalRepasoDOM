package SAX_Books;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.util.ArrayList;

public class LibrosHandlerNS extends DefaultHandler {

    private final String LIBRO_TAG="book" ;
    private final String AUTOR_TAG="author" ;
    private final String TITULO_TAG="title" ;
    private final String GENERO_TAG="genre" ;
    private final String PRECIO_TAG="price" ;
    private final String FECHA_PUBLICACION_TAG="publish_date" ;
    private final String DESCRIPCION_TAG="description" ;
    Book libro;
    static ArrayList <Book> libros=new ArrayList<>();
    static ArrayList <Book> librosNS=new ArrayList<>();
    StringBuffer stringBuffer=new StringBuffer();

    public static ArrayList<Book> DevolverLibros() {
        return libros;
    }

    public static ArrayList<Book> DevolverLibrosVendidos() {
        return librosNS;
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {

        switch (localName){
            case LIBRO_TAG:
                libro=new Book();
                if(uri.equals("http://www.libros.com")){
                    libros.add(libro);
                }else{
                    librosNS.add(libro);
                }
                libro.setId(attributes.getValue(0));
                break;
            case AUTOR_TAG:
            case TITULO_TAG:
            case GENERO_TAG:
            case PRECIO_TAG:
            case FECHA_PUBLICACION_TAG:
            case DESCRIPCION_TAG:
                stringBuffer.delete(0,stringBuffer.length());
                break;

        }
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {

        switch(localName){
            case AUTOR_TAG:
                libro.setAutor(stringBuffer.toString());
                break;
            case TITULO_TAG:
                libro.setTitulo(stringBuffer.toString());
                break;
            case GENERO_TAG:
                libro.setGenero(stringBuffer.toString());
                break;
            case PRECIO_TAG:
                libro.setPrecio(Float.parseFloat(stringBuffer.toString()));
                break;
            case FECHA_PUBLICACION_TAG:
                libro.setFechaPublicacion(stringBuffer.toString());
                break;
            case DESCRIPCION_TAG:
                libro.setDescripcion(stringBuffer.toString());
                break;
        }
    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {

        stringBuffer.append(ch,start,length);
    }
}
