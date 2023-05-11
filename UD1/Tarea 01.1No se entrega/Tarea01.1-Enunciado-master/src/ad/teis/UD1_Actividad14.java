/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package ad.teis;

import ad.teis.model.Persona;
import ad.teis.persistencia.RandomAccessPersistencia;
import java.util.ArrayList;

/**
 *
 * @author mfernandez
 */
public class UD1_Actividad14 {

    private static final String PERSONA_FILE = "personaRandom235.dat";

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        ArrayList<Persona> personasRecuperadas = new ArrayList<>();

        ArrayList<Persona> personas = crearPersonas();
        RandomAccessPersistencia random = new RandomAccessPersistencia();

        random.escribirPersonas(personas, PERSONA_FILE);
        personasRecuperadas = random.leerTodo(PERSONA_FILE);

        int contador = 1;
        for (Persona p : personasRecuperadas) {
            System.out.println("Persona recuperada " + contador + ": " + p);
            contador++;
        }

        //Leer persona
        int pos = 3;
        Persona personaRecuperada = random.leerPersona(pos, PERSONA_FILE);
        if (personaRecuperada != null) {
            System.out.println("La persona en la posición: " + pos + " es: " + personaRecuperada);
        } else {
            System.out.println("La persona en la posición: " + pos + " es null. Puede que no haya objetos de tipo Persona en esa posición.");
        }

        //Add persona
        Persona personaZ = new Persona(100, "12345678Z", 23, 10000.5f, "Paco Äman");

        pos = 4;
        Persona personaRecuperadaZ = random.add(pos, PERSONA_FILE, personaZ);
        if (personaRecuperadaZ != null) {
            System.out.println("La persona añadida en la posición: " + pos + " es: " + personaRecuperadaZ);
        } else {
            System.out.println("No se ha añadido la persona en la posición: " + pos);
        }

        //Sumar salario
        pos = 2;
        random.sumarSalario(pos, PERSONA_FILE, 500.5f);
        personaRecuperada = random.leerPersona(pos, PERSONA_FILE);
        System.out.println("La persona con incremento de salario: " + pos + " es: " + personaRecuperada);

        //Borrar persona
        random.borrar(5, PERSONA_FILE, true);

        personasRecuperadas = random.leerTodo(PERSONA_FILE);

        contador = 1;
        for (Persona p : personasRecuperadas) {
            System.out.println("Persona recuperada " + contador + ": " + p);
            contador++;
        }

    }

    private static ArrayList<Persona> crearPersonas() {
        ArrayList<Persona> personas = new ArrayList<>();

        Persona personaA = new Persona(1, "12345678A", 18, 20000.65f, "María");
        Persona personaB = new Persona(2, "12345678B", 12, 30000.65f, "Juan");
        //https://support.microsoft.com/en-us/office/insert-ascii-or-unicode-latin-based-symbols-and-characters-d13f58d3-7bcb-44a7-a4d5-972ee12e50e0
        Persona personaC = new Persona(3, "12345678C", 22, 40000.65f, "Äl"); //ALT+142
        personas.add(personaA);
        personas.add(personaB);
        personas.add(personaC);

        return personas;
    }
}
