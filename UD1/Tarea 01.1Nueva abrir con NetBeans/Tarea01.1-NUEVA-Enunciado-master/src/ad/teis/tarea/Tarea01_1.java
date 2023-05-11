/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package ad.teis.tarea;

import ad.teis.model.Persona;
import ad.teis.persistencia.DataIOPersistencia;
import ad.teis.persistencia.IPersistencia;
import java.io.IOException;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author mfernandez
 * PARA QUE VUELVA A FUNCIONAR A LA SEGUNDA VEZ HAY QUE COPIAR EL ARCHIVO origen.dat.bk DE docs.borrados A docs RENOMBRANDOLO A origen.dat
 */
public class Tarea01_1 {

    public static final Path PERSONAS_ORIGEN_PATH = Paths.get("src", "docs", "origen.dat");
    private static final Path PERSONAS_ORIGEN_COPIA_PATH = Paths.get("src", "docs", "borrados", "origen.dat.bk");
    private static final Path PERSONAS_DESTINO_PATH = Paths.get("src", "docs", "destino.dat.");

    private static ArrayList<Persona> filtrarPersonas(ArrayList<Persona> personas) {
        ArrayList<Persona> persona2=new ArrayList<>();
        for (Persona p:personas) {
            if(p.isBorrado()==false){
                persona2.add(p);
                
            }
        }
        return persona2;
        
    }

    private static void cribarBorrados() {
        ArrayList<Persona> personas = new ArrayList<>();
        ArrayList<Persona> personas2 = new ArrayList<>();
        IPersistencia diop = new DataIOPersistencia();
        personas = diop.leerTodo(PERSONAS_ORIGEN_PATH.toString());
        personas2=filtrarPersonas(personas);
        if(Files.exists(PERSONAS_DESTINO_PATH)){
            try {
                Files.delete(PERSONAS_DESTINO_PATH);
            } catch (IOException ex) {
                System.out.println(ex.getMessage());
            }
        }
        diop.escribirPersonas(personas2, PERSONAS_DESTINO_PATH.toString());
        
    

    

            


}

/**
 * @param args the command line arguments
 */
public static void main(String[] args) {

        try {
            ArrayList<Persona> personasRecuperadas = new ArrayList<>();
            IPersistencia daop = new DataIOPersistencia();
            if (Files.exists(PERSONAS_ORIGEN_PATH)) {
                
                cribarBorrados();
                personasRecuperadas = daop.leerTodo(PERSONAS_DESTINO_PATH.toString());
                int contador = 1;
                for (Persona p : personasRecuperadas) {
                    System.out.println("Persona recuperada " + contador + ": " + p);
                    contador++;
                }
                
            } else {
                System.out.println("No existe el fichero en la ruta: " + PERSONAS_ORIGEN_PATH);
            }
            
            Files.move(PERSONAS_ORIGEN_PATH, PERSONAS_ORIGEN_COPIA_PATH);
        } catch (IOException ex) {
            ex.getMessage();
        }

    }
}
