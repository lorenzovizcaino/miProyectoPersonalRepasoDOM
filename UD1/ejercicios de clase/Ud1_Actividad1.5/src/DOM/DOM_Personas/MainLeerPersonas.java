package DOM.DOM_Personas;


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
import java.util.ArrayList;

public class MainLeerPersonas {
    public static Personas persona;
    public static ArrayList<Personas> personas=new ArrayList<>();
    public static void main(String[] args) throws ParserConfigurationException, IOException, SAXException {
        final String  PERSONAS_TAG="personas";
        Path path= Paths.get("ficheros","personas.xml");
        File file=path.toFile();
        DocumentBuilderFactory documentBuilderFactory=DocumentBuilderFactory.newInstance();
        DocumentBuilder documentBuilder= documentBuilderFactory.newDocumentBuilder();
        Document document= documentBuilder.parse(file);
        NodeList list=document.getElementsByTagName(PERSONAS_TAG).item(0).getChildNodes();
        MiManejadorPersonas(list);
        for (Personas p:personas) {
            System.out.println(p);
        }




    }

    private static void MiManejadorPersonas(NodeList list) {
        final String PERSONA_TAG="persona";
        final String NOMBRE_TAG="nombre";
        final String DNI_TAG="dni";
        final String EDAD_TAG="edad";
        final String SALARIO_TAG="salario";
        final String PERSONA_ATT_ID = "id";
        final String PERSONA_ATT_BORRADO = "borrado";

        for(int i=0;i< list.getLength();i++){
            Node node= list.item(i);
            int numAtt=0;
            switch(node.getNodeName()){
                case PERSONA_TAG:
                    persona=new Personas();
                    personas.add(persona);
                    persona.setId(Long.parseLong(node.getAttributes().getNamedItem(PERSONA_ATT_ID).getNodeValue()));
                    numAtt=node.getAttributes().getLength();
                    if(numAtt>1){
                        persona.setBorrado(Boolean.parseBoolean(node.getAttributes().getNamedItem(PERSONA_ATT_BORRADO).getNodeValue()));
                    }
                    NodeList hijo=node.getChildNodes();
                    MiManejadorPersonas(hijo);
                    break;
                case NOMBRE_TAG:
                    persona.setNombre(node.getTextContent());
                    break;
                case DNI_TAG:
                    persona.setDni(node.getTextContent());
                    break;
                case EDAD_TAG:
                    persona.setEdad(Integer.parseInt(node.getTextContent()));
                    break;
                case SALARIO_TAG:
                    persona.setSalario(Float.parseFloat(node.getTextContent()));
                    break;

            }
        }
    }
}
