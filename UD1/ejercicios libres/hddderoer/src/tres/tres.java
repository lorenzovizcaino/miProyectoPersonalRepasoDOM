package tres;

import javax.swing.*;
import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;

/*
Crea una aplicación que pida la ruta de dos ficheros de texto y de una ruta de destino (solo la ruta, sin fichero al final).
Debes copiar el contenido de los dos ficheros en uno, este tendrá el nombre de los dos ficheros separados por un guion bajo,
este se guardara en la ruta donde le hayamos indicado por teclado.

Para unir los ficheros en uno, crea un método donde le pases como parámetro todas las rutas. En este método, aparte de copiar
debe comprobar que si existe el fichero de destino, nos muestre un mensaje informándonos de si queremos sobrescribir el fichero.
Te recomiendo usar la clase File y JOptionPane.

Por ejemplo, si tengo un fichero A.txt con «ABC» como contenido, un fichero B.txt con «DEF» y una ruta de destino D:\,
el resultado sera un fichero llamado A_B.txt en la ruta D:\ con el contenido «ABCDEF».

Puedes crear submétodos para realizar la copia de ficheros, piensa también como podrias optimizar esta copia, si los ficheros tuvieran mucho contenido.

Recuerda que debes controlar las excepciones que puedan aparecer. En caso de error, mostrar una ventana de dialogo con información del error.
 */
public class tres {
    public static void main(String[] args) {
        String f1,f2, rutaDestino;
        f1= JOptionPane.showInputDialog("Ruta del fichero 1");
        f2= JOptionPane.showInputDialog("Ruta del fichero 2");
        rutaDestino= JOptionPane.showInputDialog("nombre de la ruta de destino de los ficheros");
        unirFicheros(f1,f2,rutaDestino);




    }

    private static void unirFicheros(String f1, String f2, String rutaDestino) {
        String f1Name, f2Name, nameFinal = "";
        String texto1, texto2, textoFinal, fichero;
        File file1=new File(f1);
        f1Name= file1.getName();
        File file2=new File(f2);
        f2Name= file2.getName();
        for(int i=0;i<f1Name.length();i++){
            if(f1Name.charAt(i)=='.'){
                break;
            }else{
                nameFinal+=f1Name.charAt(i);
            }
        }
        nameFinal+="_";
        nameFinal+=f2Name;
        texto1=leerFichero(f1);
        texto2=leerFichero(f2);
        textoFinal=texto1+texto2;
        fichero=rutaDestino+File.separator+nameFinal;
        escribirFichero(fichero, textoFinal);


    }

    private static void escribirFichero(String fichero, String textoFinal) {
        File file=new File(fichero);
        String pregunta="";
        if(file.exists()){
            while(!(pregunta.equalsIgnoreCase("s") || pregunta.equalsIgnoreCase("n"))){
                pregunta=JOptionPane.showInputDialog("El fichero ya existe, desea sobreescribirlo");
            }
            if(pregunta.equalsIgnoreCase("S")){
                escribe(fichero,textoFinal);
            }else{
                System.out.println("El fichero no se ha copiado");
            }


        }else{
            escribe(fichero,textoFinal);
        }

    }

    private static void escribe(String fichero, String textoFinal) {
        try(FileWriter fileWriter=new FileWriter(fichero)){
            fileWriter.write(textoFinal);
            System.out.println("El fichero se ha escrito correctamente");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static String leerFichero(String f1) {
        String texto="";
        int lee = 0;
        try( FileReader fileReader=new FileReader(f1)){
            while((lee=fileReader.read())!=-1){
                texto+=(char)lee;

            }

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return texto;
    }
}
