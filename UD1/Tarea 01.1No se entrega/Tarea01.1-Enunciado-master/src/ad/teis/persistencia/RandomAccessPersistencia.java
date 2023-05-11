/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ad.teis.persistencia;

import ad.teis.model.Persona;
import java.io.EOFException;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author mfernandez
 */
public class RandomAccessPersistencia implements IPersistencia {

    private static final int LONG_BYTES_PERSONA = 35 + RandomAccessPersistencia.MAX_LENGTH_NOMBRE * 2;
    private static final int OFFSET_BORRADO = 1; 
    private static final int OFFSET_SALARIO = 4;
    private static final int MAX_LENGTH_NOMBRE = 100; //caracteres
    private static final int MAX_LENGTH_DNI = 9; //caracteres
 

    @Override
    public void escribirPersona(Persona persona, String ruta) {

        try (RandomAccessFile randomAccessFile = new RandomAccessFile(ruta, "rw");) {
            randomAccessFile.writeLong(persona.getId());
            StringBuilder stringBuilder = new StringBuilder(persona.getDni());
            stringBuilder.setLength(MAX_LENGTH_DNI);
            randomAccessFile.writeChars(stringBuilder.toString());

            stringBuilder = new StringBuilder(persona.getNombre());
            stringBuilder.setLength(MAX_LENGTH_NOMBRE);
            randomAccessFile.writeChars(stringBuilder.toString());

            randomAccessFile.writeInt(persona.getEdad());
            randomAccessFile.writeFloat(persona.getSalario());
            randomAccessFile.writeBoolean(persona.isBorrado());

        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
            System.out.println("Se ha producido una excepción: " + ex.getMessage());
        } catch (IOException ex) {
            ex.printStackTrace();
            System.out.println("Se ha producido una excepción: " + ex.getMessage());
        }
    }

    @Override
    public Persona leerDatos(String ruta) {

        long id = 0;
        String dni = "", nombre = "";
        int edad = 0;
        float salario = 0;
        StringBuilder stringBuilder = new StringBuilder();
        Persona persona = null;
        boolean borrado = false;
        try (RandomAccessFile randomAccessFile = new RandomAccessFile(ruta, "r");) {

            id = randomAccessFile.readLong();
            for (int i = 0; i <= (MAX_LENGTH_DNI -1); i++) {
                stringBuilder.append(randomAccessFile.readChar());
            }

            dni = stringBuilder.toString();

            stringBuilder = new StringBuilder();
            for (int i = 0; i < MAX_LENGTH_NOMBRE; i++) {
                stringBuilder.append(randomAccessFile.readChar());
            }
            nombre = stringBuilder.toString();

            edad = randomAccessFile.readInt();
            salario = randomAccessFile.readFloat();

            borrado = randomAccessFile.readBoolean();

            persona = new Persona(id, dni, edad, salario, nombre);
            persona.setBorrado(borrado);

        } catch (IOException ex) {
            ex.printStackTrace();
            System.out.println("Se ha producido una excepción: " + ex.getMessage());
        }
        return persona;

    }

    public void escribirPersonas(ArrayList<Persona> personas, String ruta) {
        long longitudBytes = 0;

        if (personas != null) {
            try (
                     RandomAccessFile randomAccessFile = new RandomAccessFile(ruta, "rw");) {

                longitudBytes = randomAccessFile.length();
                randomAccessFile.seek(longitudBytes);
                for (Persona persona : personas) {

                    randomAccessFile.writeLong(persona.getId());
                    StringBuilder stringBuilder = new StringBuilder(persona.getDni());
                    stringBuilder.setLength(MAX_LENGTH_DNI);
                    randomAccessFile.writeChars(stringBuilder.toString());

                    stringBuilder = new StringBuilder(persona.getNombre());
                    stringBuilder.setLength(MAX_LENGTH_NOMBRE);
                    randomAccessFile.writeChars(stringBuilder.toString());

                    randomAccessFile.writeInt(persona.getEdad());
                    randomAccessFile.writeFloat(persona.getSalario());

                    randomAccessFile.writeBoolean(persona.isBorrado());

                }

            } catch (FileNotFoundException ex) {
                ex.printStackTrace();
                System.out.println("Se ha producido una excepción: " + ex.getMessage());
            } catch (IOException ex) {
                ex.printStackTrace();
                System.out.println("Se ha producido una excepción: " + ex.getMessage());
            }
        }

    }

    public ArrayList<Persona> leerTodo(String ruta) {
        long id;
        String dni = "", nombre = "";
        int edad;
        float salario;
        StringBuilder stringBuilder =null;
        Persona persona = null;
        boolean borrado = false;
        ArrayList<Persona> personas = new ArrayList<>();
        try (
                 RandomAccessFile randomAccessFile = new RandomAccessFile(ruta, "r");) {

            do {

                id = randomAccessFile.readLong();

                stringBuilder = new StringBuilder();
                for (int i = 0; i <= (MAX_LENGTH_DNI -1); i++) {
                    stringBuilder.append(randomAccessFile.readChar());
                }

                dni = stringBuilder.toString();

                stringBuilder = new StringBuilder();
                for (int i = 0; i < MAX_LENGTH_NOMBRE; i++) {
                    stringBuilder.append(randomAccessFile.readChar());
                }

                nombre = stringBuilder.toString();

                edad = randomAccessFile.readInt();

                salario = randomAccessFile.readFloat();

                borrado = randomAccessFile.readBoolean();

                persona = new Persona(id, dni, edad, salario, nombre);
                persona.setBorrado(borrado);

                personas.add(persona);

            } while (randomAccessFile.getFilePointer() < randomAccessFile.length());

        } catch (IOException ex) {
            ex.printStackTrace();
            System.out.println("Se ha producido una excepción: " + ex.getMessage());
        }
        return personas;

    }

    public Persona leerPersona(int posicion, String ruta) {
        long id = 0;
        String dni = "", nombre = "";
        int edad = 0;
        float salario = 0;
        StringBuilder stringBuilder = new StringBuilder();
        Persona persona = null;
        boolean borrado = false;

        try (
                 RandomAccessFile randomAccessFile = new RandomAccessFile(ruta, "r");) {

            randomAccessFile.seek(converToBytePosition(posicion));
            id = randomAccessFile.readLong();
            for (int i = 0; i <= (MAX_LENGTH_DNI -1); i++) {
                stringBuilder.append(randomAccessFile.readChar());
            }

            dni = stringBuilder.toString();
            stringBuilder = new StringBuilder();
            for (int i = 0; i < MAX_LENGTH_NOMBRE; i++) {
                stringBuilder.append(randomAccessFile.readChar());
            }

            nombre = stringBuilder.toString();

            edad = randomAccessFile.readInt();
            salario = randomAccessFile.readFloat();

            borrado = randomAccessFile.readBoolean();

            persona = new Persona(id, dni, edad, salario, nombre);
            persona.setBorrado(borrado);

        } catch (EOFException ex) {
            ex.printStackTrace();
            System.out.println("Se ha producido una excepción: " + ex.getMessage());

        } catch (IOException ex) {
            ex.printStackTrace();
            System.out.println("Se ha producido una excepción: " + ex.getMessage());
        }

        return persona;
    }

    private long converToBytePosition(int posicion) {
        if (posicion == 0) {
            return posicion;
        } else {
            return LONG_BYTES_PERSONA * (posicion - 1);
        }
    }

    public Persona add(int posicion, String ruta, Persona persona) {
        try (
                 RandomAccessFile randomAccessFile = new RandomAccessFile(ruta, "rw");) {

            randomAccessFile.seek(converToBytePosition(posicion));

            randomAccessFile.writeLong(persona.getId());
            StringBuilder stringBuilder = new StringBuilder(persona.getDni());
            stringBuilder.setLength(MAX_LENGTH_DNI);
            randomAccessFile.writeChars(stringBuilder.toString());
           

            stringBuilder = new StringBuilder(persona.getNombre());
            stringBuilder.setLength(MAX_LENGTH_NOMBRE);
            randomAccessFile.writeChars(stringBuilder.toString());

            randomAccessFile.writeInt(persona.getEdad());
            randomAccessFile.writeFloat(persona.getSalario());
            randomAccessFile.writeBoolean(persona.isBorrado());

        } catch (FileNotFoundException ex) {
            persona = null;
            ex.printStackTrace();
            System.out.println("Se ha producido una excepción: " + ex.getMessage());
        } catch (IOException ex) {
            persona = null;
            ex.printStackTrace();
            System.out.println("Se ha producido una excepción: " + ex.getMessage());
        }
        return persona;
    }

    public float sumarSalario(int posicion, String ruta, float incremento) {

        float salario = 0;
        try (RandomAccessFile randomAccessFile = new RandomAccessFile(ruta, "rw");) {

            randomAccessFile.seek(posicion * LONG_BYTES_PERSONA - OFFSET_BORRADO - OFFSET_SALARIO);
//Nos posicionamos al final de la persona que ocupa la posicion que viene por parámetro
            salario = randomAccessFile.readFloat();

            salario += incremento;
            randomAccessFile.seek(randomAccessFile.getFilePointer() - OFFSET_SALARIO);
            randomAccessFile.writeFloat(salario);

        } catch (IOException ex) {
            ex.printStackTrace();
            System.out.println("Se ha producido una excepción: " + ex.getMessage());
        }
        return salario;
    }

    public boolean borrar(int posicion, String ruta, boolean borrado) {
        boolean exito = false;
        try (RandomAccessFile randomAccessFile = new RandomAccessFile(ruta, "rw");) {

            randomAccessFile.seek(posicion * LONG_BYTES_PERSONA - OFFSET_BORRADO);
            randomAccessFile.writeBoolean(borrado);

            exito = true;

        } catch (IOException ex) {
            ex.printStackTrace();
            System.out.println("Se ha producido una excepción: " + ex.getMessage());
        }
        return exito;
    }
}
