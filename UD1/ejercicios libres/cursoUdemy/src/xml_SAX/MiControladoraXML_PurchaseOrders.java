package xml_SAX;

import org.xml.sax.Attributes;
import org.xml.sax.ContentHandler;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class MiControladoraXML_PurchaseOrders extends DefaultHandler {

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        super.startElement(uri, localName, qName, attributes);
        switch(qName){
            case "PurchaseOrders":
                System.out.println(qName);
                break;
            case "PurchaseOrder":
                String atributo1=attributes.getQName(0)+" --> "+attributes.getValue(0);
                String atributo2=attributes.getQName(1)+" --> "+attributes.getValue(1);
                System.out.println("\t"+qName+": "+atributo1+", "+atributo2);
                break;
            case "Address":
                atributo1= attributes.getQName(0)+" --> "+attributes.getValue(0);
                System.out.println("\t\t"+qName+": "+atributo1);
                break;
            case "Name":
            case "Street":
            case "City":
            case "State":
            case "Zip":
            case "Country":
                System.out.print("\t\t\t"+qName+": ");
                break;
            case "DeliveryNotes":
                System.out.print("\t\t"+qName+": ");
                break;
            case "Items":
                System.out.println("\t\t"+qName);
                break;
            case "Item":
                atributo1= attributes.getQName(0)+" --> "+attributes.getValue(0);
                System.out.println("\t\t\t"+qName+": "+atributo1);
                break;
            case "ProductName":
            case "Quantity":
            case "USPrice":
            case "Comment":
                System.out.print("\t\t\t\t"+qName+": ");
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
