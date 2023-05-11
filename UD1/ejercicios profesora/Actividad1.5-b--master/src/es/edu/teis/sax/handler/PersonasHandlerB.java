/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package es.edu.teis.sax.handler;

import es.edu.teis.sax.model.Persona;
import java.util.ArrayList;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

/**
 *
 * @author mfernandez
 */
public class PersonasHandlerB extends DefaultHandler {

    private static final String PERSONA_TAG = "persona";
    private static final String PERSONA_EDAD_TAG = "edad";
    private static final String PERSONA_SALARIO_TAG = "salario";
    private static final String PERSONA_DNI_TAG = "dni";
    private static final String PERSONA_NOMBRE_TAG = "nombre";
    private static final String PERSONA_ATT_ID = "id";
    private static final String PERSONA_ATT_BORRADO = "borrado";
    
    private static final String PERSONA_URI_NS_DEFAULT="http://www.personas.com";
    private static final String PERSONA_URI_NS_ACTIVE= "http://www.personas.com/active";

    private Persona persona = null;
    private StringBuilder sb = new StringBuilder();
    private ArrayList<Persona> personasNA = new ArrayList<>();
    private ArrayList<Persona> personasActivas = new ArrayList<>();

    public ArrayList<Persona> getPersonasNA() {
        return personasNA;
    }
    public ArrayList<Persona> getPersonasActivas() {
        return personasActivas;
    }
    

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        sb.delete(0, sb.length());
        sb.append(ch, start, length);
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        switch (localName) {
            case PERSONA_NOMBRE_TAG:
                persona.setNombre(sb.toString());
                break;

            case PERSONA_DNI_TAG:
                persona.setDni(sb.toString());
                break;

            case PERSONA_EDAD_TAG:
                persona.setEdad(Integer.parseInt(sb.toString()));
                break;

            case PERSONA_SALARIO_TAG:
                persona.setSalario(Float.parseFloat(sb.toString()));
                break;

            case PERSONA_TAG:
                if(PERSONA_URI_NS_DEFAULT.equals(uri)){
                     personasNA.add(persona);
                }
                else if(PERSONA_URI_NS_ACTIVE.equals(uri)){
                    personasActivas.add(persona);
                }               
                break;
           
        }
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        long id;
        String borradoString = null;

        switch (localName) {
            case PERSONA_TAG:
                persona = new Persona();
                id = Long.parseLong(attributes.getValue(PERSONA_ATT_ID));
                persona.setId(id);
                borradoString = attributes.getValue(PERSONA_ATT_BORRADO);
                if (borradoString != null) {
                    persona.setBorrado(Boolean.parseBoolean(borradoString));
                }

                break;
        }
    }

}
