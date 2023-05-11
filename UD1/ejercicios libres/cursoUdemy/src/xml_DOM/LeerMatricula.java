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

public class LeerMatricula {
    public static void main(String[] args) {
        Path path= Paths.get("ficheros","matricula.xml");
        File file=path.toFile();

        try {
            DocumentBuilderFactory documentBuilderFactory=DocumentBuilderFactory.newInstance();
            DocumentBuilder documentBuilder= documentBuilderFactory.newDocumentBuilder();
            Document document= documentBuilder.parse(file);
            NodeList list= document.getElementsByTagName("matricula").item(0).getChildNodes();
            SwicthElements(list);
        } catch (ParserConfigurationException | SAXException | IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static void SwicthElements(NodeList list) {
        for (int i = 0; i < list.getLength(); i++) {
            Node node=list.item(i);
            if(node.getNodeType()==Node.ELEMENT_NODE){
                switch (node.getNodeName()){
                    case "personal":
                    case "pago":
                        System.out.println(node.getNodeName());
                        NodeList hijo= node.getChildNodes();
                        SwicthElements(hijo);


                        break;
                    case "dni":
                    case "nombre":
                    case "titulacion":
                    case "curso_academico":
                    case "tipo_matricula":
                        System.out.println("\t"+node.getNodeName()+": "+node.getTextContent());
                        break;
                    case "domicilios":
                        System.out.println("\t"+node.getNodeName());
                        hijo= node.getChildNodes();
                        SwicthElements(hijo);
                        break;
                    case "domicilio":
                        String atributo=node.getAttributes().item(0).toString();
                        System.out.println("\t\t"+node.getNodeName()+"-->"+atributo);
                        hijo= node.getChildNodes();
                        SwicthElements(hijo);
                        break;
                    case "nombre_domicilio":
                        System.out.println("\t\t\t"+node.getNodeName()+": "+node.getTextContent());
                        break;
                }
            }
        }
    }
}
