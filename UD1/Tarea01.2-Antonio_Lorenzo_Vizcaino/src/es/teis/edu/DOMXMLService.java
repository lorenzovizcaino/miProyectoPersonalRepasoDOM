package es.teis.edu;

import es.teis.data.exceptions.LecturaException;
import es.teis.dataXML.IXMLService;
import es.teis.model.Partido;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.ArrayList;

public class DOMXMLService implements IXMLService {

    public static Partido partido;
    public static ArrayList<Partido> partidosService=new ArrayList<>();
    @Override
    public ArrayList<Partido> leerPartidos(String ruta, float umbral) throws LecturaException {


        DocumentBuilderFactory documentBuilderFactory=DocumentBuilderFactory.newInstance();
        try {
            DocumentBuilder documentBuilder= documentBuilderFactory.newDocumentBuilder();
            Document document= documentBuilder.parse(ruta);
            NodeList list= document.getElementsByTagName("escrutinio_sitio").item(0).getChildNodes();
            SwitchElements(list,umbral);

        } catch (ParserConfigurationException | SAXException | IOException e) {

            LecturaException miExcepcion=new LecturaException(e.getMessage(), ruta);
            throw miExcepcion;

        }



        return partidosService;
    }

    private void SwitchElements(NodeList list, float umbral) {
        long id = 0;
        String nombre = "";
        int votos = 0;
        float porcentaje = 0;
        for (int i = 0; i < list.getLength(); i++) {
            Node node= list.item(i);
            if(node.getNodeType()==Node.ELEMENT_NODE){
                switch(node.getNodeName()){
                    case "resultados":
                        NodeList hijo= node.getChildNodes();
                        SwitchElements(hijo, umbral);
                        break;
                    case "partido":
                        partido=new Partido(id,nombre,votos,porcentaje);
                        String att=node.getAttributes().getNamedItem("id").getNodeValue();
                        id= Long.parseLong(att);
                        partido.setId(id);
                        NodeList hijo2= node.getChildNodes();
                        SwitchElements(hijo2, umbral);
                        break;
                    case "nombre":
                        nombre=node.getTextContent();
                        partido.setNombre(nombre);
                        break;
                    case "votos_numero":
                        votos= Integer.parseInt(node.getTextContent());
                        partido.setVotos(votos);
                        break;
                    case "votos_porciento":
                        porcentaje= Float.parseFloat(node.getTextContent());
                        partido.setPorcentaje(porcentaje);
                        break;

                }
                if(porcentaje>umbral){
                    partidosService.add(partido);
                }
            }
        }

    }
}
