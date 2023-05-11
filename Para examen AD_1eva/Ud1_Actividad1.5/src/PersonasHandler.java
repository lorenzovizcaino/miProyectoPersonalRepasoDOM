import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.util.ArrayList;

public class PersonasHandler extends DefaultHandler {


    private ArrayList<Personas> personas = new ArrayList();
    private Personas persona;
    private StringBuffer stringBuffer = new StringBuffer();

    public ArrayList<Personas> getVersiones() {

        return personas;
    }
    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        super.startElement(uri, localName, qName, attributes);
        switch (qName){
            case "persona":
                persona=new Personas();
                personas.add(persona);
                persona.setId(Long.parseLong(attributes.getValue("id")));
                persona.setBorrado(Boolean.parseBoolean(attributes.getValue("borrado")));
                break;
            case "nombre":
            case "dni":
            case "edad":
            case "salario":
                stringBuffer.delete(0, stringBuffer.length());
                break;

        }
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        super.endElement(uri, localName, qName);
        switch(qName){
            case "nombre":
                persona.setNombre(stringBuffer.toString());
                break;
            case "dni":
                persona.setDni(stringBuffer.toString());
                break;
            case "edad":
                persona.setEdad(Integer.parseInt(stringBuffer.toString()));
                break;
            case "salario":
                persona.setSalario(Float.parseFloat(stringBuffer.toString()));
                break;
        }
    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        super.characters(ch, start, length);
        stringBuffer.append(ch, start, length);
    }
}
