import MiObjetoStream.MiObjectOutputStream;
import model.Alumno;
import org.w3c.dom.DOMImplementation;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

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

    private static void crearXML()  {
        Alumno alumno;
        Document document = null;

        try {
            DocumentBuilderFactory factory=DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            DOMImplementation implementation=builder.getDOMImplementation();
            document=implementation.createDocument(null,null,null);
            document.setXmlVersion("1.0");
            document.setXmlStandalone(true);

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


