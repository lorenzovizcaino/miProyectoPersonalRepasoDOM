/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package es.udc.teis;

import es.udc.teis.model.Version;
import java.io.File;
import java.nio.file.Paths;
import java.util.ArrayList;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 *
 * @author maria
 */
public class EjemploDOMLeerNS {

    private static final String VERSION_TAG = "version";
    private static final String VERSION_Q_TAG = "v:version";
    private static final String VERSION_NOMBRE_TAG = "nombre";
    private static final String VERSION_API_TAG = "api";
    private static final String VERSION_ATT_NUMERO = "numero";
    
    private static final String VERSIONS_NS_URI = "http://www.dom.com/versions";

    private static final String VERSIONES_INPUT_FILE = Paths.get("src", "docs", "versiones_ns.xml").toString();

    public static void main(String[] args) {

        ArrayList<Version> versionsNotQualif = null;
        ArrayList<Version> versionsQualif = null;
        int contador = 1;

        try {
            File inputFile = new File(VERSIONES_INPUT_FILE);

            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
             dbFactory.setNamespaceAware(true);
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(inputFile);
            //elimina hijos con texto vacío y fusiona en un único nodo de texto varios adyacentes.
            doc.getDocumentElement().normalize();

            System.out.println("Root element :"
                    + doc.getDocumentElement().getNodeName());

            NodeList nList = doc.getElementsByTagName(VERSION_TAG);
            
           

            System.out.println("----------------------------");
           versionsNotQualif =  toVersionList(nList);
            
          
           NodeList nListQ = doc.getElementsByTagNameNS(VERSIONS_NS_URI, VERSION_TAG);
           // nListQ = doc.getElementsByTagName(VERSION_Q_TAG);
           
            versionsQualif= toVersionList(nListQ);

            for (Version v : versionsNotQualif) {
                System.out.println("Version no calificada: " + contador + " " + v);
                contador++;
            }
            for (Version v : versionsQualif) {
                System.out.println("Version calificada: " + contador + " " + v);
                contador++;
            }
            

        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("Ha ocurrido una exception: " + e.getMessage());
        }
    }

    private static  ArrayList<Version> toVersionList(NodeList nList) {

        double numero = 0;
        int api = 0;
        String nombre;
        Version version = null;
        ArrayList<Version> versions = new ArrayList<>();
        
        
        for (int i = 0; i < nList.getLength(); i++) {
            Node nNode = nList.item(i);

            if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                Element eElement = (Element) nNode;

                numero = Double.parseDouble(eElement.getAttribute(VERSION_ATT_NUMERO));
                nombre = eElement.getElementsByTagName(VERSION_NOMBRE_TAG).item(0).getTextContent();
                api = Integer.parseInt(eElement.getElementsByTagName(VERSION_API_TAG).item(0).getTextContent());

                version = new Version(numero, nombre, api);

                versions.add(version);
            }
        }
        return versions;
    }

}
