package ad.teis;


//import ad.teis.model.Persona;
//import ad.teis.persistencia.DataIOPersistencia;
//import ad.teis.persistencia.Ipersistencia;

import ad.teis.model.*;
import ad.teis.persistencia.*;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;


public class Main {
    public static final String PERSONA_FILE="persona.dat";
    public static final String PERSONA_FILE2="persona2.dat";
    public static final String PERSONA_FILE3="persona3.dat";
    public static final String PERSONA_FILE4="persona4.dat";

    public static final String PERSONA_FILE5="persona5.dat";


    public static void main(String[] args) {
        Persona p1=new Persona(1, "76912388C",47,2000);
        Persona p4=new Persona(2, "45879999S",27,1700);
        Persona p5=new Persona(3, "78452155D",33,1200);
        Persona p6=new Persona(4, "89562225D",55,3050);
        ArrayList<Persona> personas = new ArrayList<>();
        float nuevoSalario;
        boolean borrado;
        Persona p2,p3;

        Ipersistencia dataIOPersistencia=new DataIOPersistencia();
        dataIOPersistencia.escribirPersona(p1,PERSONA_FILE);
        p2=dataIOPersistencia.leerDatos(PERSONA_FILE);
        System.out.println("Persona con DATA I/O");
        System.out.println(p2.toString());

        System.out.println("Persona con RandomAccessFile");
        Ipersistencia randomAccessPersistencia=new RandomAccessPersistencia();

        if(!Files.exists(Paths.get(PERSONA_FILE2))){
            randomAccessPersistencia.escribirPersona(p1, PERSONA_FILE2);
            randomAccessPersistencia.escribirPersona(p4, PERSONA_FILE2);
            randomAccessPersistencia.escribirPersona(p5, PERSONA_FILE2);
            randomAccessPersistencia.escribirPersona(p6, PERSONA_FILE2);
        }

        p3=randomAccessPersistencia.leerDatos(PERSONA_FILE2);
        System.out.println(p3.toString());
        System.out.println();
        System.out.println("LISTADO DE PERSONAS");
        RandomAccessPersistencia.listarDatos(PERSONA_FILE2);
        System.out.println("BUSQUEDA DE UNA PERSONA POR POSICION");
        p3=randomAccessPersistencia.leerPersona(2,PERSONA_FILE2);
        System.out.println(p3.toString());
        System.out.println("AÑADIR UNA PERSONA EN UNA DETERMINADA POSICION");
        p3=RandomAccessPersistencia.add(2, PERSONA_FILE2,p6);
        RandomAccessPersistencia.listarDatos(PERSONA_FILE2);
        System.out.println("MODIFICAR EL SALARIO DE UNA DETERMINADA POSICION");
        nuevoSalario= RandomAccessPersistencia.sumarSalario(1,PERSONA_FILE2,500);
        System.out.println("Nuevo salario= " + nuevoSalario);
        RandomAccessPersistencia.listarDatos(PERSONA_FILE2);

        System.out.println("LISTAR UN ARRAYLIST");
        personas.add(p1);
        personas.add(p4);
        personas.add(p5);
        personas.add(p6);
        RandomAccessPersistencia.escribirPersonas(personas,PERSONA_FILE3);
        personas=null;
        personas=RandomAccessPersistencia.leerTodo(PERSONA_FILE3);
        System.out.println(personas.toString());

        System.out.println("ATRIBUTO BORRADO");
        ArrayList <Persona> personas5Atributos=new ArrayList<>();
        Persona p1b=new Persona(1, "76912388C",47,2000,false);
        Persona p4b=new Persona(2, "45879999S",27,1700,false);
        Persona p5b=new Persona(3, "78452155D",33,1200,false);
        Persona p6b=new Persona(4, "89562225D",55,3050,false);
        personas5Atributos.add(p1b);
        personas5Atributos.add(p4b);
        personas5Atributos.add(p5b);
        personas5Atributos.add(p6b);
        if(!Files.exists(Paths.get(PERSONA_FILE4))){
            RandomAccessPersistencia.escribirPersonas5Atributos(personas5Atributos, PERSONA_FILE4);
        }

        System.out.println("LISTADO DEL FICHERO CON PERSONAS DE 5 ATRIBUTOS");
        RandomAccessPersistencia.listarPersonas5Atributos(PERSONA_FILE4);

        borrado=RandomAccessPersistencia.borrar(1,PERSONA_FILE4,true);
        System.out.println("El registro ha sido borrado: " +borrado);
        System.out.println("LISTADO DEL FICHERO CON PERSONAS DE 5 ATRIBUTOS");
        RandomAccessPersistencia.listarPersonas5Atributos(PERSONA_FILE4);

        System.out.println("ATRIBUTO NOMBRE");
        ArrayList <Persona> personas6Atributos=new ArrayList<>();
        Persona p1c=new Persona(1, "76912388C","Antonio",47,2000,false);
        Persona p4c=new Persona(2, "45879999S","Luis",27,1700,false);
        Persona p5c=new Persona(3, "78452155D","Ana Gomez",33,1200,false);
        Persona p6c=new Persona(4, "89562225D","Maria Fernandez",55,3050,false);
        personas6Atributos.add(p1c);
        personas6Atributos.add(p4c);
        personas6Atributos.add(p5c);
        personas6Atributos.add(p6c);
        if(!Files.exists(Paths.get(PERSONA_FILE5))){
            RandomAccessPersistencia.escribirPersonas6Atributos(personas6Atributos, PERSONA_FILE5);
        }
        System.out.println("LISTADO DEL FICHERO CON PERSONAS DE 6 ATRIBUTOS");
        RandomAccessPersistencia.listarPersonas6Atributos(PERSONA_FILE5);




    }


}
