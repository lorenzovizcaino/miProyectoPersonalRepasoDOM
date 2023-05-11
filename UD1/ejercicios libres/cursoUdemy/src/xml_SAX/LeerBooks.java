package xml_SAX;

import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

public class LeerBooks {
    public static void main(String[] args) {
        Path path=Paths.get("ficheros", "books.xml");

        try {
            SAXParserFactory saxParserFactory=SAXParserFactory.newInstance();
            SAXParser saxParser=saxParserFactory.newSAXParser();
            XMLReader xmlReader= saxParser.getXMLReader();
            MiControladoraXML_Books miControladoraXML=new MiControladoraXML_Books();
            xmlReader.setContentHandler(miControladoraXML);
            xmlReader.parse(path.toString());


        } catch (ParserConfigurationException e) {
            throw new RuntimeException(e);
        } catch (SAXException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
