package uno;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/*
Crea un fichero de texto con el nombre y contenido que tu quieras.
Ahora crea una aplicación que lea este fichero de texto carácter a carácter y muestre su contenido por pantalla  sin espacios.
Por ejemplo, si un fichero tiene el siguiente texto «Esto es una prueba», deberá mostrar «Estoesunaprueba».

Captura las excepciones que veas necesario.

 */
public class uno {
    public static void main(String[] args) {
        String texto="La casa de papel tenia menos papel del necesario";
        String nombreFichero="Fichero.txt";
        try(FileWriter fileWriter=new FileWriter(nombreFichero)){
            fileWriter.write(texto);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        leerFichero(nombreFichero);

    }

    private static void leerFichero(String nombreFichero) {
        int lee;
        try(FileReader fileReader=new FileReader(nombreFichero)) {
            while((lee=fileReader.read())!=-1){
                if(!(lee==' ')){
                    System.out.print((char)lee);
                }
            }

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
