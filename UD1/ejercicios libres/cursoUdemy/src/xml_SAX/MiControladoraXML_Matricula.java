package xml_SAX;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;


public class MiControladoraXML_Matricula extends DefaultHandler {
    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        super.startElement(uri, localName, qName, attributes);
        switch (qName){
            case "matricula":
                System.out.println(qName);
                break;
            case "personal":
            case "pago":
                System.out.println("\t"+qName);
                break;
            case "dni":
            case "nombre":
            case "titulacion":
            case "curso_academico":
            case "tipo_matricula":
                System.out.print("\t\t"+qName+": ");
                break;
            case "domicilios":
                System.out.println("\t\t"+qName);
                break;
            case "domicilio":
                String atrib= attributes.getQName(0)+" --> "+attributes.getValue(0);
                System.out.println("\t\t\t"+qName+": "+atrib);
                break;
            case "nombre_domicilio":
                System.out.print("\t\t\t\t"+qName+": ");
                break;




        }
    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        super.characters(ch, start, length);
        String texto=new String(ch, start, length).trim();
        if(texto.length()>0){
            System.out.println(texto);
        }
    }
}
