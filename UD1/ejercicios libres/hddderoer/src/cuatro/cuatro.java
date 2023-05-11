package cuatro;

import java.io.*;

/*
Crea una aplicación que copie un fichero binario a otra localización.
En lugar de leer y escribir byte a byte, crea un array de bytes con el tamaño del fichero de origen (utiliza el método available()),
copia el contenido del fichero a este array y escribe a partir de este array.

Recuerda que debes controlar las excepciones que puedan aparecer. En caso de error, mostrar una ventana de dialogo con información del error.
 */
public class cuatro {
    public static void main(String[] args) {
        File file=new File("Captura.jpg");
        File file2=new File("C2.jpg");
        try(FileInputStream fileInputStream=new FileInputStream(file);
            FileOutputStream fileOutputStream=new FileOutputStream(file2)){
            byte [] array=new byte[fileInputStream.available()];
            array=fileInputStream.readAllBytes();
            fileOutputStream.write(array);

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


    }
}
