package es.teis.edu;

import es.teis.data.IPersistencia;
import es.teis.model.Partido;

import java.io.*;
import java.util.ArrayList;

public class PartidoObjectPersistencia implements IPersistencia {


    @Override
    public void escribir(ArrayList<Partido> partidos, String ruta) {
        try(ObjectOutputStream objectOutputStream=new ObjectOutputStream(new FileOutputStream(ruta))){
            for (Partido p:partidos) {
                if(p instanceof Partido){
                    objectOutputStream.writeObject(p);
                }
            }

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public ArrayList<Partido> leerTodo(String ruta) {
        Partido partido;
        ArrayList<Partido> partidoLeerTodo=new ArrayList<>();
        try(ObjectInputStream objectInputStream=new ObjectInputStream(new FileInputStream(ruta))) {
            while(true){
                partido=(Partido) objectInputStream.readObject();
                partidoLeerTodo.add(partido);
            }

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (EOFException e){

        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return partidoLeerTodo;
    }
}
