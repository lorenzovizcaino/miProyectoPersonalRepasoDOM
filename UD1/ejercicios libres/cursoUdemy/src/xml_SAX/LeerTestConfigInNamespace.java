package xml_SAX;

import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

public class LeerTestConfigInNamespace {
    public static void main(String[] args) {
        Path path= Paths.get("ficheros","TestConfigInNamespace.xml");
        leerXML(path);
    }

    private static void leerXML(Path path){
        SAXParserFactory saxParserFactory=SAXParserFactory.newInstance();
        try {
            SAXParser saxParser= saxParserFactory.newSAXParser();
            XMLReader xmlReader=saxParser.getXMLReader();
            MiControladoraXML_TestConfigInNamespace miControladoraXML=new MiControladoraXML_TestConfigInNamespace();
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
