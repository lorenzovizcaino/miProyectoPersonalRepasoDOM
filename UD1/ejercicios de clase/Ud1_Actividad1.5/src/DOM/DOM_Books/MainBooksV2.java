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

public class MainBooksV2 {
    public static void main(String[] args) throws ParserConfigurationException, IOException, SAXException {
        final String CATALOGO_TAG = "catalog";
        ArrayList<Book> libros = new ArrayList<>();
        Book book;
        Path path = Paths.get("ficheros", "books.xml");
        File file = path.toFile();
        DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
        Document document = documentBuilder.parse(file);
        NodeList list = document.getElementsByTagName(CATALOGO_TAG).item(0).getChildNodes();
        MiManejador(list);
    }


    private static void MiManejador(NodeList list) {
        final String LIBRO_TAG = "book";
        final String AUTOR_TAG = "author";
        final String TITULO_TAG = "title";
        final String GENERO_TAG = "genre";
        final String PRECIO_TAG = "price";
        final String FECHA_PUBLICACION_TAG = "publish_date";
        final String DESCRIPCION_TAG = "description";
        Book book = null;
        NodeList hijo = null;
        ArrayList<Book> libros = new ArrayList<>();
        for (int i = 0; i < list.getLength(); i++) {
            Node node = list.item(i);
            if (node.getNodeName().equals(LIBRO_TAG)) {
                book = new Book();
                libros.add(book);
                book.setId(node.getAttributes().item(0).toString());
                hijo = node.getChildNodes();
                for (int j = 0; j < hijo.getLength(); j++) {
                    Node nodeHijo = hijo.item(j);
                    switch (nodeHijo.getNodeName()) {
                        case AUTOR_TAG:
                            book.setAutor(nodeHijo.getTextContent());
                            break;
                        case TITULO_TAG:
                            book.setTitulo(nodeHijo.getTextContent());
                            break;
                        case GENERO_TAG:
                            book.setGenero(nodeHijo.getTextContent());
                            break;
                        case PRECIO_TAG:
                            book.setPrecio(Float.parseFloat(nodeHijo.getTextContent()));
                            break;
                        case FECHA_PUBLICACION_TAG:
                            book.setFechaPublicacion(nodeHijo.getTextContent());
                            break;
                        case DESCRIPCION_TAG:
                            book.setDescripcion(nodeHijo.getTextContent());
                            break;
                    }
                }
            }
        }
        for (Book b : libros) {
            System.out.println(b);
        }
    }
}



