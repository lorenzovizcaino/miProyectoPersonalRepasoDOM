package dos;

import javax.swing.*;
import java.io.*;

/*
Crea una aplicación donde pidamos la ruta de un fichero por teclado y un texto que queramos
a escribir en el fichero. Deberás mostrar por pantalla el mismo texto pero variando entre mayúsculas
y minúsculas, es decir, si escribo «Bienvenido» deberá devolver «bIENVENIDO».
Si se escribe cualquier otro carácter, se quedara tal y como se escribió.

Deberás crear un método para escribir en el fichero el texto introducido y otro para mostrar
 el contenido en mayúsculas.

IMPORTANTE: cuando pidas por teclado una ruta con JOptionPane, no es necesario que insertes
caracteres de escape.
 */
public class dos {
    public static void main(String[] args) {
        String rutaFichero= JOptionPane.showInputDialog("Nombre del fichero");
        String texto=JOptionPane.showInputDialog("Texto");
        escribir(rutaFichero, texto);
        mostrar(rutaFichero);

    }

    private static void mostrar(String rutaFichero) {
        String cadena="";
        int caracter;
        try(FileReader fileReader=new FileReader(rutaFichero)){
            while((caracter=fileReader.read())!=-1){
                if(caracter>65 && caracter<91){
                    cadena+=(char)(caracter+32);
                }
                else if(caracter>96 && caracter<123){
                    cadena+=(char)(caracter-32);
                }else{
                    cadena+=(char)(caracter);
                }
            }

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        System.out.println(cadena);
    }

    private static void escribir(String rutaFichero, String texto) {

        try(FileWriter fileWriter=new FileWriter(rutaFichero)
        ){
          fileWriter.write(texto);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
