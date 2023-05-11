package escribiendo;

import java.io.FileWriter;
import java.io.IOException;

public class Escribir_Fichero {
    public static void main(String[] args) {
        try(FileWriter fileWriter=new FileWriter("escribirFichero.txt")){
            fileWriter.write("Las cosas de todos lo dias");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
