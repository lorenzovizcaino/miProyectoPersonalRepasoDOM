/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package ad.teis.tarea;

import ad.teis.model.Persona;
import ad.teis.persistencia.RandomAccessPersistencia;

import java.io.EOFException;
import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.stream.Collectors;

import static java.nio.file.StandardCopyOption.REPLACE_EXISTING;

/**
 *
 * @author mfernandez
 */
public class Tarea01_1 {

    public static final String PERSONAS_FILE = Paths.get("Tarea01.1-Enunciado-master","src", "docs", "personasConBorrados.dat").toString();
    private static final String PERSONAS_FILE_BK = Paths.get("Tarea01.1-Enunciado-master","src", "docs", "personasConBorrados.dat.bk").toString();
    private static final String PERSONAS_FILE_DESTINO = Paths.get("Tarea01.1-Enunciado-master","src", "docs","destinoRandom.dat.").toString();
    private static final int LONG_BYTES_PERSONA=235;
    private static final int OFFSET_BORRADO = 1;

    private static void cribarBorrados() {
        //OJO HE AÑADIDO LO DE "Tarea01.1-Enunciado-master"  A LOS files PARA PODER TRABAJAR CON INTELLIJ QUITARLO ANTES DE ENTREGAR
        Path pathorigen=Paths.get(PERSONAS_FILE);
        Path pathdestino=Paths.get(PERSONAS_FILE_BK);
        ArrayList<Persona> personas=new ArrayList<>();
        int posicion=0;
        long id;
        String dni;
        String nombre;
        int edad;
        float salario;
        boolean borrado = false;
        Persona persona;

        /*
        CUANTO OCUPA CADA TIPO DE DATO:
        int --> 4 bytes
        String --> 2 bytes por caracter
        double --> 8 bytes
        short --> 2 bytes
        byte --> 1 byte
        long --> 8 bytes
        float --> 4 bytes
        boolean --> 1 byte
         */

        StringBuffer stringBuffer;
        if(Files.exists(pathorigen)){
            try {
                Files.copy(pathorigen,pathdestino,REPLACE_EXISTING);
                RandomAccessFile randomAccessFile=new RandomAccessFile(PERSONAS_FILE,"r");
                while(true){
                    randomAccessFile.seek(randomAccessFile.getFilePointer()+(LONG_BYTES_PERSONA-OFFSET_BORRADO));
                    if(randomAccessFile.readBoolean()==false){
                        randomAccessFile.seek(randomAccessFile.getFilePointer()-LONG_BYTES_PERSONA);
                        id=randomAccessFile.readLong();
                        stringBuffer=new StringBuffer();
                        for(int i=0;i<9;i++){
                            stringBuffer.append(randomAccessFile.readChar());
                        }
                        dni=stringBuffer.toString();

                        stringBuffer=new StringBuffer();
                        for(int i=0;i<100;i++){
                            stringBuffer.append(randomAccessFile.readChar());
                        }
                        nombre=stringBuffer.toString();
                        edad=randomAccessFile.readInt();
                        salario=randomAccessFile.readFloat();
                        borrado=randomAccessFile.readBoolean();
                        persona=new Persona(id,dni,edad,salario,nombre);
                        persona.setBorrado(borrado);
                        personas.add(persona);
                    }
                }


            } catch (EOFException e){

            } catch (IOException e) {
                e.printStackTrace();
                System.out.println("Ha ocurrido una excepción: " + e.getMessage());
            }
            RandomAccessPersistencia randomAccessPersistencia = new RandomAccessPersistencia();
            randomAccessPersistencia.escribirPersonas(personas,PERSONAS_FILE_DESTINO);
            try {
                Files.delete(pathdestino);
            } catch (IOException e) {
                e.printStackTrace();
                System.out.println("Ha ocurrido una excepción: " + e.getMessage());
            }

        }else{
            System.out.println("El fichero no existe");
        }
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        ArrayList<Persona> personasRecuperadas = new ArrayList<>();
        RandomAccessPersistencia random = new RandomAccessPersistencia();

        cribarBorrados();
        personasRecuperadas = random.leerTodo(PERSONAS_FILE_DESTINO);
        int contador = 1;
        for (Persona p : personasRecuperadas) {
            System.out.println("Persona recuperada " + contador + ": " + p);
            contador++;
        }




    }

}
