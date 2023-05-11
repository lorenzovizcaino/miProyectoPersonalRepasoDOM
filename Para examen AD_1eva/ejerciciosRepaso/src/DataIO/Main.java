package DataIO;

import model.Alumno;

import javax.swing.*;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

public class Main {
    public static Path path= Paths.get("ficheros","DataIOAlumnos.dat");
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
        final String FILE_TMP= Paths.get("ficheros", "AlumnoTemporal.tmp").toString();
        File fileTmp=Paths.get(FILE_TMP).toFile();
        String id;
        String nombre;
        String dni;
        int edad;
        String curso;
        boolean repite;
        Alumno alumno;
        boolean encontrado=false;
        String idBaja=JOptionPane.showInputDialog("Id a dar de Baja");
        try(DataInputStream dataInputStream=new DataInputStream(new FileInputStream(file));
            DataOutputStream dataOutputStream=new DataOutputStream(new FileOutputStream(FILE_TMP))){
            while(true){
                id=dataInputStream.readUTF();
                nombre=dataInputStream.readUTF();
                dni= dataInputStream.readUTF();
                edad=dataInputStream.readInt();
                curso= dataInputStream.readUTF();
                repite=dataInputStream.readBoolean();
                if(!id.equalsIgnoreCase(idBaja)){
                    alumno=new Alumno(id,nombre,dni,edad,curso,repite);
                    dataOutputStream.writeUTF(alumno.getId());
                    dataOutputStream.writeUTF(alumno.getNombre());
                    dataOutputStream.writeUTF(alumno.getDni());
                    dataOutputStream.writeInt(alumno.getEdad());
                    dataOutputStream.writeUTF(alumno.getCurso());
                    dataOutputStream.writeBoolean(alumno.isRepite());

                }else{
                    encontrado=true;
                }

            }

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (EOFException e){

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        if(encontrado==false){
            System.out.println("No se ha encontrado ningun registro con ese identificador");
        }
        try {
            Files.delete(file.toPath());
            fileTmp.renameTo(file);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


    }

    private static void Consulta() {
        String id;
        String nombre;
        String dni;
        int edad;
        String curso;
        boolean repite;
        String idBuscada=JOptionPane.showInputDialog("ID a consultar");
        Alumno alumno;
        try(DataInputStream dataInputStream=new DataInputStream(new FileInputStream(file))){
            while (true){
                id=dataInputStream.readUTF();
                nombre=dataInputStream.readUTF();
                dni= dataInputStream.readUTF();
                edad=dataInputStream.readInt();
                curso= dataInputStream.readUTF();
                repite=dataInputStream.readBoolean();
                if(id.equalsIgnoreCase(idBuscada)){
                    alumno=new Alumno(id, nombre, dni, edad, curso, repite);
                    System.out.println("El alumno buscado es: ");
                    System.out.println(alumno.toString());
                    break;
                }

            }

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (EOFException e){
            System.out.println("La id introducida no se encuentra en la base de datos");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static void listado() {
        String id;
        String nombre;
        String dni;
        int edad;
        String curso;
        boolean repite;
        Alumno alumno;
        ArrayList <Alumno> alumnos=new ArrayList<>();
        try(DataInputStream dataInputStream=new DataInputStream(new FileInputStream(file))){
            while(true){
                id=dataInputStream.readUTF();
                nombre=dataInputStream.readUTF();
                dni= dataInputStream.readUTF();
                edad=dataInputStream.readInt();
                curso= dataInputStream.readUTF();
                repite=dataInputStream.readBoolean();
                alumno=new Alumno(id, nombre, dni, edad, curso, repite);
                alumnos.add(alumno);

            }

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (EOFException e){
            for (Alumno a:alumnos) {
                System.out.println(a.toString());
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    private static void alta() {
        Alumno alumno1=new Alumno("AL01", "Antonio","76912388C",47,"2DAM",false);
        Alumno alumno2=new Alumno("AL02", "Luis","76915688C",44,"2DAM",true);
        Alumno alumno3=new Alumno("AL03", "Ana","76913488F",57,"2DAM",false);
        Alumno alumno4=new Alumno("AL04", "Nuria","66912458K",27,"2DAM",true);
        ArrayList <Alumno> alumnos=new ArrayList<>();
        alumnos.add(alumno1);
        alumnos.add(alumno2);
        alumnos.add(alumno3);
        alumnos.add(alumno4);
        try(DataOutputStream dataOutputStream=new DataOutputStream(new FileOutputStream(file))){
            for (Alumno a:alumnos) {
                dataOutputStream.writeUTF(a.getId());
                dataOutputStream.writeUTF(a.getNombre());
                dataOutputStream.writeUTF(a.getDni());
                dataOutputStream.writeInt(a.getEdad());
                dataOutputStream.writeUTF(a.getCurso());
                dataOutputStream.writeBoolean(a.isRepite());
            }

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


    }
}
