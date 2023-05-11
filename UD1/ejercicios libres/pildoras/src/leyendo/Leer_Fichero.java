package leyendo;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class Leer_Fichero {

    public void lee(){
        try(FileReader fileReader=new FileReader("archivo.txt")){
            int caracter;
            while((caracter= fileReader.read())!=-1){
                System.out.print((char)caracter);
            }

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
