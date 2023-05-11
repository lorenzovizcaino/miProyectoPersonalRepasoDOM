package xml_SAX;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

 public class MiControladoraXML_TestConfigInNamespace extends DefaultHandler {

    @Override
    public void startDocument() throws SAXException {
        super.startDocument();
        System.out.println("Iniciando Documento");
    }

//    @Override
//    public void endDocument() throws SAXException {
//        super.endDocument();
//        System.out.println("Finalizando Documento");
//    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        super.startElement(uri, localName, qName, attributes);
        switch(qName){
            case "Tests":
                System.out.println(qName);
                break;
            case "Test":
                String id=attributes.getQName(0)+": "+attributes.getValue(0);
                String type=attributes.getQName(1)+": "+attributes.getValue(1);
                System.out.println("\t"+qName+": "+id+" "+type);
                break;
            case "Name":
            case "CommandLine":
            case "Input":
            case "Output":
                System.out.print("\t\t"+qName+":\t");
                break;

        }
    }

//    @Override
//    public void endElement(String uri, String localName, String qName) throws SAXException {
//        super.endElement(uri, localName, qName);
//        System.out.println("Finalizando Elemento "+qName);
//    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        super.characters(ch, start, length);
        String texto=new String(ch, start, length).trim();
        if(texto.length()>0){
            System.out.println(texto);
        }

    }
}
