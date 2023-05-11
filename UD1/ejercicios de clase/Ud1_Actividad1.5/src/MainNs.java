import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;

public class MainNs {

        public static void main(String[] args) throws ParserConfigurationException, SAXException, IOException {
            SAXParserFactory saxParserFactory = SAXParserFactory.newInstance();
            saxParserFactory.setNamespaceAware(true);
            SAXParser saxParser = saxParserFactory.newSAXParser();
            File file = new File(Paths.get("ficheros", "personasns.xml").toString());
          //PersonasHandlerNs handler = new PersonasHandlerNs();
            PersonasHandlerNsMejorado handler = new PersonasHandlerNsMejorado();
            saxParser.parse(file, handler);

            System.out.println("PERSONAS DEL ESPACIO DE NOMBRES: http://www.personas.com");
            ArrayList<Personas> personas = handler.getVersiones();
            for (Personas p:personas) {
                System.out.println(p);
            }
            System.out.println();
            System.out.println("PERSONAS DEL ESPACIO DE NOMBRES: http://www.personas.com/active");
            ArrayList<Personas> personasNs = handler.getVersionesNs();
            for (Personas p:personasNs) {
                System.out.println(p);
            }


        }

}
