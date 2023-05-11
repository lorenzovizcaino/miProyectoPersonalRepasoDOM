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

public class LeerTestConfigInNameespace {

    public static void main(String[] args) {
        Path path= Paths.get("ficheros","CustomersOrders.xml");
        File file=path.toFile();

        try {
            DocumentBuilderFactory documentBuilderFactory=DocumentBuilderFactory.newInstance();
            DocumentBuilder documentBuilder= documentBuilderFactory.newDocumentBuilder();
            Document document= documentBuilder.parse(file);

            NodeList list=document.getElementsByTagName("Root").item(0).getChildNodes();
            SwitchElements(list);

        } catch (ParserConfigurationException | IOException | SAXException e) {
            throw new RuntimeException(e);
        }

    }

    private static void SwitchElements(NodeList list) {
        for (int i = 0; i < list.getLength(); i++) {
            Node node=list.item(i);
            if(node.getNodeType()==Node.ELEMENT_NODE){
                switch (node.getNodeName()){
                    case "Customers":
                    case "Orders":
                        System.out.println(node.getNodeName());
                        NodeList hijo= node.getChildNodes();
                        SwitchElements(hijo);
                        break;
                    case "Customer":
                        String atributo=node.getAttributes().getNamedItem("CustomerID").getNodeValue();
                        System.out.println("\t"+node.getNodeName()+" -->ID: "+atributo);
                        hijo= node.getChildNodes();
                        SwitchElements(hijo);
                        break;
                    case "CompanyName":
                    case "ContactName":
                    case "ContactTitle":
                    case "Phone":
                    case "CustomerID":
                    case "EmployeeID":
                    case "OrderDate":
                    case "RequiredDate":

                        System.out.println("\t\t"+node.getNodeName()+" "+node.getTextContent());
                        break;
                    case "FullAddress":
                        System.out.println("\t\t"+node.getNodeName());
                        hijo= node.getChildNodes();
                        SwitchElements(hijo);
                        break;
                    case "Address":
                    case "City":
                    case "Region":
                    case "PostalCode":
                    case "Country":
                        System.out.println("\t\t\t"+node.getNodeName()+" "+node.getTextContent());
                        break;
                    case "Order":
                        System.out.println("\t"+node.getNodeName());
                        hijo= node.getChildNodes();
                        SwitchElements(hijo);
                        break;
                    case "ShipInfo":
                        if(node.hasAttributes()){
                            atributo= node.getAttributes().getNamedItem("ShippedDate").getNodeValue();
                            System.out.println("\t\t"+node.getNodeName()+" --> ShippedDate  "+atributo);
                        }else{

                            System.out.println("\t\t"+node.getNodeName());
                        }

                        hijo= node.getChildNodes();
                        SwitchElements(hijo);
                        break;

                    case "ShipVia":
                    case "Freight":
                    case "ShipName":
                    case "ShipAddress":
                    case "ShipCity":
                    case "ShipRegion":
                    case "ShipPostalCode":
                    case "ShipCountry":

                        System.out.println("\t\t\t"+node.getNodeName()+" "+node.getTextContent());
                        break;


                }
            }
        }
    }
}
