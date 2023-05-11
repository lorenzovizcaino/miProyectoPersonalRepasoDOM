package FileWriter_FileReader;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class SinBuffer {
    public static void main(String[] args) {
        String cadena="Es un hecho establecido hace demasiado tiempo que un lector se distraerá con " +
                "\nel contenido del texto de un sitio mientras que mira su diseño. El punto de usar Lorem Ipsum es" +
                "\nque tiene una distribución más o menos normal de las letras, al contrario de usar textos como por ejemplo " +
                "\nContenido aquí, contenido aquí. Estos textos hacen parecerlo un español que se puede leer. " +
                "\nMuchos paquetes de autoedición y editores de páginas web usan el Lorem Ipsum como su texto por defecto," +
                "\ny al hacer una búsqueda de \"Lorem Ipsum\" va a dar por resultado muchos sitios web que usan este texto si se " +
                "\nencuentran en estado de desarrollo. Muchas versiones han evolucionado a través de los años, algunas veces por accidente," +
                "\notras veces a propósito (por ejemplo insertándole humor y cosas por el estilo).";
        try {
            FileWriter fileWriter= new FileWriter(new File("pruebasSinBuffer.txt"));
            fileWriter.write(cadena);
            fileWriter.flush();
            fileWriter.close();

            FileReader fileReader=new FileReader(new File("pruebasSinBuffer.txt"));
            int c;
            while((c=fileReader.read())!=-1){
                System.out.print((char)c);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
