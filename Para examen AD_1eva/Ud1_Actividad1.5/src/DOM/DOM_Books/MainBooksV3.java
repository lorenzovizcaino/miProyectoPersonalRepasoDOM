package DOM.DOM_Books;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;



public class MainBooksV3 {
    public static ArrayList<Book> libros = new ArrayList<>();
    public static Book book;
    public static void main(String[] args) throws ParserConfigurationException, IOException, SAXException {
        final String CATALOGO_TAG = "catalog";
        Path path = Paths.get("ficheros", "books.xml");
        File file = path.toFile();
        DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
        Document document = documentBuilder.parse(file);
        NodeList list = document.getElementsByTagName(CATALOGO_TAG).item(0).getChildNodes();
        MiManejador(list);
        for (Book b : libros) {
            System.out.println(b);
        }
    }


    private static void MiManejador(NodeList list) {
        final String LIBRO_TAG = "book";
        final String AUTOR_TAG = "author";
        final String TITULO_TAG = "title";
        final String GENERO_TAG = "genre";
        final String PRECIO_TAG = "price";
        final String FECHA_PUBLICACION_TAG = "publish_date";
        final String DESCRIPCION_TAG = "description";

        for (int i = 0; i < list.getLength(); i++) {
            Node node = list.item(i);
            switch (node.getNodeName()) {
                case LIBRO_TAG:
                    book = new Book();
                    libros.add(book);
                    book.setId(node.getAttributes().item(0).toString());
                    NodeList hijo = node.getChildNodes();
                    MiManejador(hijo);
                    break;
                case AUTOR_TAG:
                    book.setAutor(node.getTextContent());
                    break;
                case TITULO_TAG:
                    book.setTitulo(node.getTextContent());
                    break;
                case GENERO_TAG:
                    book.setGenero(node.getTextContent());
                    break;
                case PRECIO_TAG:
                    book.setPrecio(Float.parseFloat(node.getTextContent()));
                    break;
                case FECHA_PUBLICACION_TAG:
                    book.setFechaPublicacion(node.getTextContent());
                    break;
                case DESCRIPCION_TAG:
                    book.setDescripcion(node.getTextContent());
                    break;
            }
        }


    }

}






