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

public class LeerBooks {
    public static void main(String[] args) {
        Path path= Paths.get("ficheros","books.xml");
        File file=path.toFile();
        DocumentBuilderFactory documentBuilderFactory=DocumentBuilderFactory.newInstance();
        try {
            DocumentBuilder documentBuilder= documentBuilderFactory.newDocumentBuilder();
            Document document= documentBuilder.parse(file);
            NodeList list= document.getElementsByTagName("Catalog").item(0).getChildNodes();
            SwitchElements(list);

        } catch (ParserConfigurationException | SAXException | IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static void SwitchElements(NodeList list) {
        for (int i = 0; i < list.getLength(); i++) {
            Node node= list.item(i);
            if(node.getNodeType()==Node.ELEMENT_NODE){
                switch(node.getNodeName()){
                    case "Book":
                        String att=node.getAttributes().getNamedItem("id").getNodeValue();
                        System.out.println(node.getNodeName()+" --> id="+att);
                        NodeList hijo= node.getChildNodes();
                        SwitchElements(hijo);
                        break;
                    case "Author":
                    case "Title":
                    case "Genre":
                    case "Price":
                    case "PublishDate":
                    case "Description":
                        System.out.println("\t"+node.getNodeName()+" :"+node.getTextContent());
                        break;

                }
            }
        }
    }
}
