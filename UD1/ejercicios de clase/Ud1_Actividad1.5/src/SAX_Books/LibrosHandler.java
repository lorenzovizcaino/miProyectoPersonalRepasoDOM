package SAX_Books;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.util.ArrayList;

public class LibrosHandler extends DefaultHandler {
    Book book;
    static ArrayList<Book> books=new ArrayList<>();
    StringBuffer stringBuffer=new StringBuffer();
    private final String LIBRO_TAG="book" ;
    private final String AUTOR_TAG="author" ;
    private final String TITULO_TAG="title" ;
    private final String GENERO_TAG="genre" ;
    private final String PRECIO_TAG="price" ;
    private final String FECHA_PUBLICACION_TAG="publish_date" ;
    private final String DESCRIPCION_TAG="description" ;

    public static ArrayList<Book> RetornoLibros() {
        return books;
    }


    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        super.startElement(uri, localName, qName, attributes);
        switch(qName){
            case LIBRO_TAG:
                book=new Book();
                books.add(book);
                book.setId(attributes.getValue(0));
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
        super.endElement(uri, localName, qName);
        switch(qName){
            case AUTOR_TAG:
                book.setAutor(stringBuffer.toString());
                break;
            case TITULO_TAG:
                book.setTitulo(stringBuffer.toString());
                break;
            case GENERO_TAG:
                book.setGenero(stringBuffer.toString());
                break;
            case PRECIO_TAG:
                book.setPrecio(Float.parseFloat(stringBuffer.toString()));
                break;
            case FECHA_PUBLICACION_TAG:
                book.setFechaPublicacion(stringBuffer.toString());
                break;
            case DESCRIPCION_TAG:
                book.setDescripcion(stringBuffer.toString());
                break;
        }
    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        super.characters(ch, start, length);
        stringBuffer.append(ch,start,length);

    }
}
