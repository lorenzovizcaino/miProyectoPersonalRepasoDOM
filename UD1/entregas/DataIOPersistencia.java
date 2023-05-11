/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ad.teis.persistencia;

import ad.teis.model.Persona;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

/**
 *
 * @author mfernandez
 */
public class DataIOPersistencia implements IPersistencia {


    @java.lang.Override
    public void escribirPersonas(ArrayList<Persona> personas, String ruta) {
        StringBuffer stringBuffer;
        for (Persona persona:personas) {
            try(DataOutputStream dataOutputStream=new DataOutputStream(new FileOutputStream(ruta,true))){
                dataOutputStream.writeLong(persona.getId());
                stringBuffer=new StringBuffer(persona.getDni());
                stringBuffer.setLength(persona.MAX_LENGTH_DNI);
                dataOutputStream.writeChars(stringBuffer.toString());
                stringBuffer=new StringBuffer(persona.getNombre());
                stringBuffer.setLength(persona.MAX_LENGTH_NOMBRE);
                dataOutputStream.writeChars(stringBuffer.toString());
                dataOutputStream.writeInt(persona.getEdad());
                dataOutputStream.writeFloat(persona.getSalario());
                dataOutputStream.writeBoolean(persona.isBorrado());
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        }


    @java.lang.Override
    public ArrayList<Persona> leerTodo(String ruta) {
        ArrayList<Persona> personas=new ArrayList<>();
        long id;
        String dni;
        String nombre;
        int edad;
        float salario;
        boolean borrado;
        StringBuffer stringBuffer;
        Persona persona=new Persona();
        try(DataInputStream dataInputStream=new DataInputStream(new FileInputStream(ruta))){
            while(true){
                id=dataInputStream.readLong();
                stringBuffer=new StringBuffer();
                for(int i=0;i<persona.MAX_LENGTH_DNI;i++){
                    stringBuffer.append(dataInputStream.readChar());

                }
                dni=stringBuffer.toString();
                stringBuffer.delete(0,stringBuffer.length());
                for (int i = 0; i < persona.MAX_LENGTH_NOMBRE; i++) {
                    stringBuffer.append(dataInputStream.readChar());
                }
                nombre=stringBuffer.toString();
                edad=dataInputStream.readInt();
                salario=dataInputStream.readFloat();
                borrado= dataInputStream.readBoolean();
                persona=new Persona(id, dni, edad,salario, nombre);
                persona.setBorrado(borrado);
                personas.add(persona);
            }

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (EOFException e){

        } catch (IOException e) {
            throw new RuntimeException(e);
        }


        return personas;
    }
}
