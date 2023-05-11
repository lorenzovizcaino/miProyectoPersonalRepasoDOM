package FileWriter_FileReader;

import java.io.*;

public class ConBuffer2_TryWithResources {
    public static void main(String[] args) {
        try (BufferedWriter bufferedWriter=new BufferedWriter(new FileWriter(new File("pruebas.txt")));
             BufferedReader bufferedReader=new BufferedReader(new FileReader(new File("pruebas.txt")));){

            bufferedWriter.write("Es un hecho establecido hace demasiado tiempo que un lector se distraerá con " +
                    "\nel contenido del texto de un sitio mientras que mira su diseño. El punto de usar Lorem Ipsum es" +
                    "\nque tiene una distribución más o menos normal de las letras, al contrario de usar textos como por ejemplo " +
                    "\nContenido aquí, contenido aquí. Estos textos hacen parecerlo un español que se puede leer. " +
                    "\nMuchos paquetes de autoedición y editores de páginas web usan el Lorem Ipsum como su texto por defecto," +
                    "\ny al hacer una búsqueda de \"Lorem Ipsum\" va a dar por resultado muchos sitios web que usan este texto si se " +
                    "\nencuentran en estado de desarrollo. Muchas versiones han evolucionado a través de los años, algunas veces por accidente," +
                    "\notras veces a propósito (por ejemplo insertándole humor y cosas por el estilo).");
            bufferedWriter.flush();


            String linea;
            while((linea=bufferedReader.readLine())!=null){
                System.out.println(linea);
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
