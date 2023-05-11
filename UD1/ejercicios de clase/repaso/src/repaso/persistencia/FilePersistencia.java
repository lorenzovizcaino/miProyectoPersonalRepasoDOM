package repaso.persistencia;

import repaso.piscina_v2.EjercicioPiscina;
import repaso.piscina_v2.Piscina;

import java.io.*;

public class FilePersistencia {
    public static void Write(Piscina piscina, String ruta){

        try(ObjectOutputStream objectOutputStream=new ObjectOutputStream(new FileOutputStream(ruta))
        ){
            objectOutputStream.writeObject(piscina);

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        
    }


    public static Piscina Read(String ruta) {
        Piscina piscina = null;
        try(ObjectInputStream objectInputStream=new ObjectInputStream(new FileInputStream(ruta))

                ){
            while(objectInputStream!=null){
                piscina=(Piscina)objectInputStream.readObject();
                
            }

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch(EOFException e){

        } catch (IOException e) {

            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return piscina;
    }


}
