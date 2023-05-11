import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.util.ArrayList;
public class PersonasHandlerNsMejorado extends DefaultHandler {
    private ArrayList<Personas> personas = new ArrayList();
    private ArrayList<Personas> personasNs = new ArrayList();
    private Personas persona;
    private StringBuilder buffer = new StringBuilder();
    public ArrayList<Personas> getVersiones() {
        return personas;
    }
    public ArrayList<Personas> getVersionesNs() {
        return personasNs;
    }
    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        super.startElement(uri, localName, qName, attributes);
        switch (localName) {
            case "persona":
                persona = new Personas();
                if (uri.equals("http://www.personas.com/active")) {
                    personasNs.add(persona);
                } else {
                    personas.add(persona);
                }
                persona.setId(Long.parseLong(attributes.getValue("id")));
                persona.setBorrado(Boolean.parseBoolean(attributes.getValue("borrado")));
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
        switch (localName) {
            case "nombre":
                persona.setNombre(buffer.toString());
                break;
            case "dni":
                persona.setDni(buffer.toString());
                break;
            case "edad":
                persona.setEdad(Integer.parseInt(buffer.toString()));
                break;
            case "salario":
                persona.setSalario(Float.parseFloat(buffer.toString()));
                break;
        }

    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        super.characters(ch, start, length);
        buffer.append(ch, start, length);
    }
}
