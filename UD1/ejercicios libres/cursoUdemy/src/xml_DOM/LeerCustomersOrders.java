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

public class LeerCustomersOrders {
    public static void main(String[] args) {
        Path path=Paths.get("ficheros","TestConfigInNamespace.xml");
        File file=path.toFile();
        try {
            //PRIMEROS PASOS DE CONFIGURACION
            DocumentBuilderFactory documentBuilderFactory=DocumentBuilderFactory.newInstance();
            DocumentBuilder  documentBuilder= documentBuilderFactory.newDocumentBuilder();
            Document document= documentBuilder.parse(file);

            //TRATAMOS EL FICHERO
            NodeList listaInicial=document.getElementsByTagName("Tests").item(0).getChildNodes();
            switchElement(listaInicial);

        } catch (ParserConfigurationException | SAXException | IOException e) {
            throw new RuntimeException(e);
        }

    }

    private static void switchElement(NodeList list){
        for (int i = 0; i < list.getLength(); i++) {
            Node node=list.item(i);
            if(node.getNodeType()==Node.ELEMENT_NODE){
                switch(node.getNodeName()){
                    case "Test":
                        String atributo=node.getAttributes().getNamedItem("TestId").getNodeValue();
                        String atributo2=node.getAttributes().getNamedItem("TestType").getNodeValue();
                        System.out.println(node.getNodeName()+"\t-\t"+atributo+"\t-\t"+atributo2);
                        NodeList listaHijos= node.getChildNodes();
                        switchElement(listaHijos);
                        break;
                    case "Name":
                    case "CommandLine":
                    case "Input":
                    case "Output":
                        System.out.println("\t"+node.getNodeName()+"-->"+node.getTextContent());
                        break;


                }

            }


        }
    }
}
