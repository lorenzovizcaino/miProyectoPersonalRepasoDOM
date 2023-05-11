import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) throws ParserConfigurationException, SAXException, IOException {
        SAXParserFactory saxParserFactory = SAXParserFactory.newInstance();
        //saxParserFactory.setNamespaceAware(true);
        SAXParser saxParser = saxParserFactory.newSAXParser();
        File file = new File(Paths.get("ficheros", "personas.xml").toString());
       // VersionesHandler handler = new VersionesHandler();
        PersonasHandlerSinEndElements handler = new PersonasHandlerSinEndElements();
        saxParser.parse(file, handler);

        ArrayList<Personas> personas = handler.getVersiones();
        for (Personas p:personas) {
            System.out.println(p);
        }


    }
}
