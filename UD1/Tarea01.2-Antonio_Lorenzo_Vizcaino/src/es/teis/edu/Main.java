/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package es.teis.edu;

import es.teis.data.exceptions.LecturaException;
import es.teis.dataXML.IXMLService;
import es.teis.model.Partido;
import java.nio.file.Paths;
import java.util.ArrayList;

/**
 *
 * @author maria
 */
public class Main {

    private static String ELECCIONES_INPUT_FILE = Paths.get("src", "docs", "elecciones.xml").toString();
    private static String ELECCIONES_OUTPUT_FILE = Paths.get("src", "docs", "elecciones_output.dat").toString();

    private static float UMBRAL_PORCENTAJE = 3;

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

//completa aquí:
        ArrayList<Partido> partidos=new ArrayList<>();
        ArrayList<Partido> partidosDeFichero=new ArrayList<>();
        IXMLService ejemplo=new DOMXMLService();
        try {
            partidos=ejemplo.leerPartidos(ELECCIONES_INPUT_FILE,5);
        } catch (LecturaException e) {
            throw new RuntimeException(e);

        }
        System.out.println("PARTIDOS LEIDOS DEL ARCHIVO XML");
        mostrar(partidos);
        System.out.println();
        PartidoObjectPersistencia partidoObjectPersistencia=new PartidoObjectPersistencia();
        partidoObjectPersistencia.escribir(partidos, ELECCIONES_OUTPUT_FILE);
        partidosDeFichero=partidoObjectPersistencia.leerTodo(ELECCIONES_OUTPUT_FILE);
        System.out.println("PARTIDOS LEIDOS DEL FICHERO");
        mostrar(partidosDeFichero);
    }

    private static void mostrar(ArrayList<Partido> partidos) {
        System.out.println("Se han leído: ");
        for (Partido partido : partidos) {
            System.out.println(partido);

        }
    }

}
