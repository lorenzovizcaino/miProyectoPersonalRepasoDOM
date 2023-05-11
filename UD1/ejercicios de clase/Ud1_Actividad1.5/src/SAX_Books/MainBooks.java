package SAX_Books;

import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;

public class MainBooks {
    public static void main(String[] args) throws ParserConfigurationException, SAXException, IOException {
        SAXParserFactory saxParserFactory = SAXParserFactory.newInstance();
        SAXParser saxParser = saxParserFactory.newSAXParser();
        File file = new File(Paths.get("ficheros", "books.xml").toString());
        LibrosHandler handler = new LibrosHandler();
        saxParser.parse(file, handler);

        ArrayList <Book> libros=new ArrayList<>();
        libros=LibrosHandler.RetornoLibros();

        for (Book b:libros) {
            System.out.println(b);
        }


    }


}
