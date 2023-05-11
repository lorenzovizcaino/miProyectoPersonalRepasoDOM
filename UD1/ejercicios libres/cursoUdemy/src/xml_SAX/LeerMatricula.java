package xml_SAX;

import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

public class LeerMatricula {
    public static void main(String[] args) {
        Path path= Paths.get("ficheros","matricula.xml");

        try {
            SAXParserFactory saxParserFactory=SAXParserFactory.newInstance();
            SAXParser saxParser= saxParserFactory.newSAXParser();
            XMLReader xmlReader= saxParser.getXMLReader();
            MiControladoraXML_Matricula miControladoraXML=new MiControladoraXML_Matricula();
            xmlReader.setContentHandler(miControladoraXML);
            xmlReader.parse(path.toString());


        } catch (ParserConfigurationException | IOException | SAXException e) {
            throw new RuntimeException(e);
        }

    }
}
