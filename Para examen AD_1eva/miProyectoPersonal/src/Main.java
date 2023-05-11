import MiObjetoStream.MiObjectOutputStream;
import model.Alumno;
import org.w3c.dom.DOMImplementation;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

import javax.swing.*;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;



public class Main {

    public static Path path= Paths.get("ficheros","Alumnos.dat");
    public static File file=path.toFile();
    public static void main(String[] args) {
        menu();

    }

    private static void menu() {
        int op = -1;
        boolean bandera = false;
        while (op != 0 || bandera) {
            try {
                bandera = false;
                op = Integer.parseInt(JOptionPane.showInputDialog("MENU DE OPCIONES" +
                        "\n1.-Alta" +
                        "\n2.-Listado" +
                        "\n3.-Crear XML" +
                        "\n0.-SALIR"));

                switch (op) {
                    case 1:
                        alta();
                        break;
                    case 2:
                        listado();
                        break;
                    case 3:
                        crearXML();
                        //crearXmlModoMaria();
                        break;
                    case 0:
                        System.out.println("Hasta luego");
                        break;
                    default:
                        System.out.println("Opciones del 0-3");
                        bandera = true;
                }

            } catch (NumberFormatException e) {
                System.out.println("Solo se admiten numeros del 0-3");
            }


        }

    }

    private static void crearXmlModoMaria() {
        final String ALUMNOS_TAG="Alumnos";
        final String ALUMNO_TAG="Alumno";
        final String ALUMNO_Q_TAG="a:Alumno";
        final String ALUMNO_ID_TAG="id";
        final String ALUMNO_NOMBRE_TAG="Nombre";
        final String ALUMNO_DNI_TAG="Dni";
        final String ALUMNO_EDAD_TAG="Edad";
        final String ALUMNO_CURSO_TAG="Curso";
        final String ALUMNO_REPITE_TAG="Repite";
        final String ALUMNOS_NS_URI = "http://www.alumnos.com";
        final String ALUMNOS_NS_URI_PREFIX = "a";
        final String ALUMNOS_OUTPUT_FILE = Paths.get("ficheros", "alumnos_ns.xml").toString();
        Alumno alumno;
        boolean par;
        int contador=1;
        Document document=null;

        try (ObjectInputStream objectInputStream=new ObjectInputStream(new FileInputStream(file))){
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder documentBuilder = factory.newDocumentBuilder();
            DOMImplementation implementation = documentBuilder.getDOMImplementation();
            document = implementation.createDocument(null, ALUMNOS_TAG, null);
            Element root = document.getDocumentElement();
            root.setAttribute("xmlns:"+ALUMNOS_NS_URI_PREFIX, ALUMNOS_NS_URI);

            while(true){
                alumno=(Alumno) objectInputStream.readObject();
                par=(contador % 2 == 0);
                Element eAlumno = null;
                if (par) {
                    eAlumno = document.createElement(ALUMNO_TAG);
                } else {
                    eAlumno = document.createElementNS(ALUMNOS_NS_URI, ALUMNO_Q_TAG);
                }
                eAlumno.setAttribute(ALUMNO_ID_TAG, String.valueOf(alumno.getId()));
                addElementConTexto(document, eAlumno, ALUMNO_NOMBRE_TAG, alumno.getNombre());
                addElementConTexto(document, eAlumno, ALUMNO_DNI_TAG, alumno.getDni());
                addElementConTexto(document, eAlumno, ALUMNO_EDAD_TAG, String.valueOf(alumno.getEdad()));
                addElementConTexto(document, eAlumno, ALUMNO_CURSO_TAG, alumno.getCurso());
                addElementConTexto(document, eAlumno, ALUMNO_REPITE_TAG, String.valueOf(alumno.isRepite()));
                root.appendChild(eAlumno);
                contador++;
            }



        } catch (ParserConfigurationException e) {
            throw new RuntimeException(e);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (EOFException e){

        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        try {
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            transformerFactory.setAttribute("indent-number", 4);
            Transformer transformer = transformerFactory.newTransformer();
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            //Si se quisiera añadir el <!DOCTYPE>:
            // transformer.setOutputProperty(OutputKeys.DOCTYPE_SYSTEM, docType.getSystemId());
            Source origen = new DOMSource(document);
            Result destino = new StreamResult(ALUMNOS_OUTPUT_FILE);
            transformer.transform(origen, destino);

        } catch (TransformerConfigurationException e) {
            throw new RuntimeException(e);
        } catch (TransformerException e) {
            throw new RuntimeException(e);
        }


    }

    private static void addElementConTexto(Document document, Node padre, String tag, String text) {
        Node node = document.createElement(tag);
        Node nodeTexto = document.createTextNode(text);
        padre.appendChild(node);
        node.appendChild(nodeTexto);

    }

    private static void crearXML()  {

        Alumno alumno;
        Document document = null;

        try {
            DocumentBuilderFactory factory=DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            DOMImplementation implementation=builder.getDOMImplementation();
            document=implementation.createDocument(null,null,null);


            Element alumnos=document.createElement("Alumnos");
            ObjectInputStream objectInputStream=new ObjectInputStream(new FileInputStream(file));
            document.appendChild(alumnos);
            while (objectInputStream!=null){
                alumno= (Alumno) objectInputStream.readObject();
                Element eAlumno=document.createElement("Alumno");
                eAlumno.setAttribute("id",alumno.getId());
                Element eNombre=document.createElement("Nombre");
                eNombre.setTextContent(alumno.getNombre());
                Element eDNI= document.createElement("DNI");
                eDNI.setTextContent(alumno.getDni());
                Element eEdad=document.createElement("Edad");
                eEdad.setTextContent(String.valueOf(alumno.getEdad()));
                Element eCurso=document.createElement("Curso");
                eCurso.setTextContent(alumno.getCurso());
                Element eRepite=document.createElement("Repite");
                eRepite.setTextContent(String.valueOf(alumno.isRepite()));
                eAlumno.appendChild(eNombre);
                eAlumno.appendChild(eDNI);
                eAlumno.appendChild(eEdad);
                eAlumno.appendChild(eCurso);
                eAlumno.appendChild(eRepite);
                alumnos.appendChild(eAlumno);

            }



        } catch (ParserConfigurationException e) {
            throw new RuntimeException(e);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (EOFException e){

            try {
                Source origen=new DOMSource(document);
                Result destino=new StreamResult(new File("ficheros/alumnos.xml"));
                TransformerFactory transformerFactory=TransformerFactory.newInstance();
                transformerFactory.setAttribute("indent-number", 4);
                Transformer transformer= transformerFactory.newTransformer();
                transformer.setOutputProperty(OutputKeys.INDENT, "yes");
                transformer.transform(origen,destino);
            } catch (TransformerConfigurationException ex) {
                throw new RuntimeException(ex);
            } catch (TransformerException ex) {
                throw new RuntimeException(ex);
            }


        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }


    }

    private static void listado() {
        Alumno alumno;

        try {
            ObjectInputStream objectInputStream=new ObjectInputStream(new FileInputStream(file));
            while(objectInputStream!=null){
                alumno= (Alumno) objectInputStream.readObject();
                System.out.println(alumno.toString());
            }
        } catch (EOFException e){
            

        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    private static void alta() {
        boolean bandera = true;
        String rep;
        String id = JOptionPane.showInputDialog("Identificador del alumno");
        String nombre = JOptionPane.showInputDialog("Nombre del alumno");
        String dni = JOptionPane.showInputDialog("DNI del alumno");
        int edad = Integer.parseInt(JOptionPane.showInputDialog("Edad del alumno"));
        String curso = JOptionPane.showInputDialog("Curso del alumno");
        boolean repite=false;
        while (bandera) {
            rep = JOptionPane.showInputDialog("¿El alumno es repetidor (S/N)?");
            if (rep.equalsIgnoreCase("s")) {
                repite = true;
                bandera = false;
            } else if (rep.equalsIgnoreCase("n")) {
                repite = false;
                bandera = false;
            }

        }
        Alumno alumno=new Alumno(id,nombre,dni,edad,curso,repite);
        GuardarAlumnoFichero(alumno);

    }

    private static void GuardarAlumnoFichero(Alumno alumno) {

        try {
            if(file.exists()){
                MiObjectOutputStream miObjectOutputStream=new MiObjectOutputStream(new FileOutputStream(file,true));
                miObjectOutputStream.writeObject(alumno);
            }else {
                ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream(file));
                objectOutputStream.writeObject(alumno);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


}


