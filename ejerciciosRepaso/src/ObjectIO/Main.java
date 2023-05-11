package ObjectIO;

import model.Alumno;

import javax.swing.*;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

public class Main {
    public static Path path= Paths.get("ficheros","ObjectIOAlumnos.dat");
    public static Path pathTemporal= Paths.get("ficheros","ObjectIOAlumnosTMP.tmp");
    public static File file=path.toFile();
    public static File fileTMP=pathTemporal.toFile();

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
                        "\n3.-Consulta" +
                        "\n4.-Baja"+
                        "\n0.-SALIR"));

                switch (op) {
                    case 1:
                        alta();
                        break;
                    case 2:
                        listado();
                        break;
                    case 3:
                        Consulta();
                        break;
                    case 4:
                        baja();
                        break;
                    case 0:
                        System.out.println("Hasta luego");
                        break;
                    default:
                        System.out.println("Opciones del 0-4");
                        bandera = true;
                }

            } catch (NumberFormatException e) {
                System.out.println("Solo se admiten numeros del 0-4");
            }


        }

    }

    private static void baja() {
        ArrayList<Alumno> alumnos = new ArrayList<>();
        Alumno alumno;
        boolean encontrado=false;
        String idAlumnoBorrar = JOptionPane.showInputDialog("ID del alumno a borrar (formato AL99");
        try (ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream(file))
             ) {
            while (objectInputStream != null) {
                alumno = (Alumno) objectInputStream.readObject();
                if(!alumno.getId().equalsIgnoreCase(idAlumnoBorrar)){
                    alumnos.add(alumno);
                }else{
                    encontrado=true;
                }
            }


        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (EOFException e){

        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        if(encontrado){
            try(ObjectOutputStream objectOutputStream=new ObjectOutputStream(new FileOutputStream(fileTMP));
                MyObjectOutputStream myObjectOutputStream=new MyObjectOutputStream(new FileOutputStream(fileTMP,true))){
                for (Alumno a:alumnos) {
                    if(fileTMP.exists()){
                        myObjectOutputStream.writeObject(a);
                    }else{
                        objectOutputStream.writeObject(a);
                    }

                }

            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

            try {
                Files.delete(path);
                fileTMP.renameTo(file);
               // Files.delete(pathTemporal);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

        }





    }

    private static void Consulta() {
        Alumno alumno;
        boolean encontrado = false;
        String idAConsultar = JOptionPane.showInputDialog("ID del alumno a consultar (formato AL99)");
        try (ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream(file))) {
            while (objectInputStream != null) {
                alumno = (Alumno) objectInputStream.readObject();
                if (alumno.getId().equalsIgnoreCase(idAConsultar)) {
                    System.out.println("Los datos del alumno con id " + idAConsultar + " son:\n" + alumno.toString());
                    encontrado = true;
                    break;
                }

            }



        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }catch (EOFException e){

        }catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        if (!encontrado) {
            System.out.println("No existe ningun alumno con el id: " + idAConsultar);
        }


    }

    private static void listado() {

        Alumno alumno;

        try(ObjectInputStream objectInputStream=new ObjectInputStream(new FileInputStream(file))){
            while(objectInputStream!=null){
                alumno=(Alumno)objectInputStream.readObject();
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
        Alumno alumno1=new Alumno("AL01", "Antonio","76912388C",47,"2DAM",false);
        Alumno alumno2=new Alumno("AL02", "Luis","76915688C",44,"2DAM",true);
        Alumno alumno3=new Alumno("AL03", "Ana","76913488F",57,"2DAM",false);
        Alumno alumno4=new Alumno("AL04", "Nuria","66912458K",27,"2DAM",true);
        ArrayList<Alumno> alumnos=new ArrayList<>();
        alumnos.add(alumno1);
        alumnos.add(alumno2);
        alumnos.add(alumno3);
        alumnos.add(alumno4);
        try(ObjectOutputStream objectOutputStream=new ObjectOutputStream(new FileOutputStream(file));
            MyObjectOutputStream myObjectOutputStream=new MyObjectOutputStream(new FileOutputStream(file,true))){
            for (Alumno a:alumnos) {
                if(file.exists()){
                    myObjectOutputStream.writeObject(a);
                }else{
                    objectOutputStream.writeObject(a);
                }

            }

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
