package xml_SAX;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class MiControladoraXML_Books extends DefaultHandler {
    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        super.startElement(uri, localName, qName, attributes);
        switch(qName){
            case "Catalog":
                System.out.println(qName);
                break;
            case "Book":
                String atributo=attributes.getQName(0)+"="+attributes.getValue(0);
                System.out.println("\t"+qName+" --> "+atributo);
                break;
            case "Author":
            case "Title":
            case "Genre":
            case "Price":
            case "PublishDate":
            case "Description":
                System.out.print("\t\t"+qName+": ");
                break;

        }
    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        super.characters(ch, start, length);
        String texto=new String(ch,start,length).trim();
        if(texto.length()>0){
            System.out.println(texto);
        }

    }
}
