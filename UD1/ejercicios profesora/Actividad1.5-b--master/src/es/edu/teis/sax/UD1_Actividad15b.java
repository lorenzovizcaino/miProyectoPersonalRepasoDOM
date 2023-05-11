/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package es.edu.teis.sax;

import es.edu.teis.sax.handler.PersonasHandlerA;
import es.edu.teis.sax.handler.PersonasHandlerB;
import es.edu.teis.sax.model.Persona;
import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import org.xml.sax.SAXException;

/**
 *
 * @author mfernandez
 */
public class UD1_Actividad15b {
    
    public static void main(String[] args) {
         try {
            SAXParserFactory saxParserFactory = SAXParserFactory.newInstance();
            saxParserFactory.setNamespaceAware(true);
            SAXParser saxParser = saxParserFactory.newSAXParser();

            File file = new File(Paths.get("src", "docs", "personas_ns.xml").toString());
            PersonasHandlerB handler = new PersonasHandlerB();
            saxParser.parse(file, handler);

            ArrayList<Persona> personas = handler.getPersonasNA();

            for (Persona p : personas) {
                System.out.println("Persona NO activa: " + p);
            }
            
            ArrayList<Persona> personasActivas = handler.getPersonasActivas();

            for (Persona p : personasActivas) {
                System.out.println("Persona activa: " + p);
            }
        } catch (ParserConfigurationException ex) {
            ex.printStackTrace();
            System.err.println("Ha ocurrido una excepción: " + ex.getMessage());
        } catch (SAXException ex) {
            ex.printStackTrace();
            System.err.println("Ha ocurrido una excepción: " + ex.getMessage());

        } catch (IOException ex) {
            ex.printStackTrace();
            System.err.println("Ha ocurrido una excepción: " + ex.getMessage());
        }
    }
}
