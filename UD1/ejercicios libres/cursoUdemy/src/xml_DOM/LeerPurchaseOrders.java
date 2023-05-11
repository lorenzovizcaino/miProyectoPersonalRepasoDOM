package xml_DOM;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

public class LeerPurchaseOrders {
    public static void main(String[] args) {
        Path path= Paths.get("ficheros","PurchaseOrders.xml");
        File file=path.toFile();
        DocumentBuilderFactory documentBuilderFactory=DocumentBuilderFactory.newInstance();

        try {
            DocumentBuilder documentBuilder=documentBuilderFactory.newDocumentBuilder();
            Document document= documentBuilder.parse(file);
            NodeList list=document.getElementsByTagName("PurchaseOrders").item(0).getChildNodes();
            SwitchElement(list);
        } catch (ParserConfigurationException | SAXException | IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static void SwitchElement(NodeList list) {
        for(int i=0;i< list.getLength();i++){
            Node node= list.item(i);
            if(node.getNodeType()==Node.ELEMENT_NODE){
                switch(node.getNodeName()){
                    case "PurchaseOrder":
                        String att1=node.getAttributes().getNamedItem("PurchaseOrderNumber").getNodeValue();
                        String att2=node.getAttributes().getNamedItem("OrderDate").getNodeValue();
                        System.out.println(node.getNodeName()+" --> PurchaseOrderNumber: "+att1+"\t"+"OrderDate: "+att2);
                        NodeList hijo= node.getChildNodes();
                        SwitchElement(hijo);
                        break;
                    case "Address":
                        att1=node.getAttributes().getNamedItem("Type").getNodeValue();
                        System.out.println("\t"+node.getNodeName()+" --> Type: "+att1);
                        hijo= node.getChildNodes();
                        SwitchElement(hijo);
                        break;
                    case "Name":
                    case "Street":
                    case "City":
                    case "State":
                    case "Zip":
                    case "Country":
                        System.out.println("\t\t"+node.getNodeName()+ " "+node.getTextContent());
                        break;
                    case "Items":
                        System.out.println("\t"+node.getNodeName());
                        hijo= node.getChildNodes();
                        SwitchElement(hijo);
                        break;
                    case "Item":
                        att1=node.getAttributes().getNamedItem("PartNumber").getNodeValue();
                        System.out.println("\t\t"+node.getNodeName()+" --> PartNumber: "+att1);
                        hijo= node.getChildNodes();
                        SwitchElement(hijo);
                        break;
                    case "ProductName":
                    case "Quantity":
                    case "USPrice":
                        System.out.println("\t\t\t"+node.getNodeName()+" "+node.getTextContent());
                        break;



                }
            }
        }
    }
}
