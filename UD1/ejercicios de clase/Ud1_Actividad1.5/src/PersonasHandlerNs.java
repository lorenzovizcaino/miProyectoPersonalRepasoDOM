import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.util.ArrayList;

public class PersonasHandlerNs extends DefaultHandler {


    private ArrayList<Personas> personas = new ArrayList();
    private ArrayList<Personas> personasNs = new ArrayList();
    private Personas persona,personaNs;
    private StringBuilder buffer = new StringBuilder();
    boolean bandera;

    public ArrayList<Personas> getVersiones() {

        return personas;
    }
    public ArrayList<Personas> getVersionesNs() {

        return personasNs;
    }


    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        super.startElement(uri, localName, qName, attributes);
        switch (localName){
            case "persona":
                if(uri.equals("http://www.personas.com/active")){
                    personaNs=new Personas();
                    personasNs.add(personaNs);
                    personaNs.setId(Long.parseLong(attributes.getValue("id")));
                    personaNs.setBorrado(Boolean.parseBoolean(attributes.getValue("borrado")));
                    bandera=true;

                }else{
                    persona=new Personas();
                    personas.add(persona);
                    persona.setId(Long.parseLong(attributes.getValue("id")));
                    persona.setBorrado(Boolean.parseBoolean(attributes.getValue("borrado")));
                    bandera=false;
                }

                break;
            case "nombre":
            case "dni":
            case "edad":
            case "salario":
                buffer.delete(0, buffer.length());
                break;

        }
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        super.endElement(uri, localName, qName);
        switch(localName){
            case "nombre":
                if(bandera){
                    personaNs.setNombre(buffer.toString());
                }else{
                    persona.setNombre(buffer.toString());
                }
                break;
            case "dni":
                if(bandera){
                    personaNs.setDni(buffer.toString());
                }else{
                    persona.setDni(buffer.toString());
                }
                break;
            case "edad":
                if(bandera){
                    personaNs.setEdad(Integer.parseInt(buffer.toString()));
                }else{
                    persona.setEdad(Integer.parseInt(buffer.toString()));
                }

                break;
            case "salario":
                if(bandera){
                    personaNs.setSalario(Float.parseFloat(buffer.toString()));
                }else{
                    persona.setSalario(Float.parseFloat(buffer.toString()));
                }

                break;
        }
    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        super.characters(ch, start, length);
        buffer.append(ch, start, length);
    }
}

