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

public class MainLeerPersonasNS {

    public static Personas persona;
    public static ArrayList<Personas> personas=new ArrayList<>();
    public static ArrayList<Personas> personasNS=new ArrayList<>();
    public static void main(String[] args) throws ParserConfigurationException, IOException, SAXException {
        final String  PERSONAS_TAG="personas";
        Path path= Paths.get("ficheros","personasns.xml");
        File file=path.toFile();
        DocumentBuilderFactory documentBuilderFactory=DocumentBuilderFactory.newInstance();
        documentBuilderFactory.setNamespaceAware(true);
        DocumentBuilder documentBuilder= documentBuilderFactory.newDocumentBuilder();

        Document document= documentBuilder.parse(file);
        NodeList list=document.getElementsByTagName(PERSONAS_TAG).item(0).getChildNodes();
        MiManejadorPersonas(list);
        System.out.println("LISTADO DE PERSONAS NO CUALIFICADAS");
        for (Personas p:personas) {
            System.out.println(p);
        }
        System.out.println("LISTADO DE PERSONAS CUALIFICADAS");
        for (Personas p:personasNS) {
            System.out.println(p);
        }



    }

    private static void MiManejadorPersonas(NodeList list) {
        final String PERSONA_TAG="persona";
        final String PERSONA_TAG_CUALIFICADA="a:persona";
        final String NOMBRE_TAG="nombre";
        final String DNI_TAG="dni";
        final String EDAD_TAG="edad";
        final String SALARIO_TAG="salario";
        final String PERSONA_ATT_ID = "id";
        final String PERSONA_ATT_BORRADO = "borrado";

        for(int i=0;i< list.getLength();i++){
            Node node= list.item(i);
            int numAtt=0;
            //System.out.println(node.getNamespaceURI());
            System.out.println(node.getLocalName());
            //System.out.println(node.getNodeName());
            if(node.getLocalName()!=null){
                switch(node.getLocalName()){
                    case PERSONA_TAG:

                        if(node.getNamespaceURI().equals("http://www.personas.com")) {
                            persona = new Personas();
                            personas.add(persona);
                        }
                        if(node.getNamespaceURI().equals("http://www.personas.com/active")){
                            persona=new Personas();
                            personasNS.add(persona);
                        }

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
}
