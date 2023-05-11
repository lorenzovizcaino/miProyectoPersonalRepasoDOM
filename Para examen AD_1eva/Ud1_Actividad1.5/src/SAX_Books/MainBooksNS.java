package SAX_Books;

import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;

public class MainBooksNS {
    public static void main(String[] args) throws ParserConfigurationException, SAXException, IOException {
        SAXParserFactory saxParserFactory = SAXParserFactory.newInstance();
        saxParserFactory.setNamespaceAware(true);
        SAXParser saxParser = saxParserFactory.newSAXParser();
        File file = new File(Paths.get("ficheros", "booksNS.xml").toString());
        LibrosHandlerNS handler = new LibrosHandlerNS();
        saxParser.parse(file, handler);

        ArrayList<Book> libros=new ArrayList<>();
        ArrayList<Book> librosVendidos=new ArrayList<>();

        libros=LibrosHandlerNS.DevolverLibros();
        librosVendidos=LibrosHandlerNS.DevolverLibrosVendidos();
        System.out.println("LIBROS NO VENDIDOS");
        for (Book l:libros) {
            System.out.println(l);
        }
        System.out.println("LIBROS VENDIDOS");
        for (Book lv:librosVendidos) {
            System.out.println(lv);
        }
    }
}
