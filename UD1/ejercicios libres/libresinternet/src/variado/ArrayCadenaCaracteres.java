package variado;

import java.io.*;

/*
Escribir un método que reciba un array de cadenas de caracteres y vuelque su contenido a un archivo cuyo nombre también
se recibirá por parámetro. Las cadenas quedarán separadas en el archivo por un asterisco.
 */
public class ArrayCadenaCaracteres {
    public static void main(String[] args) {
        File file=new File("cadena caracteres.txt");
        String texto="Lorem Ipsum es simplemente el texto de relleno de las imprentas y archivos de texto. " +
                "Lorem Ipsum ha sido el texto de relleno estándar de las industrias desde el año 1500, " +
                "cuando un impresor (N. del T. persona que se dedica a la imprenta) desconocido usó una galería de " +
                "textos y los mezcló de tal manera que logró hacer un libro de textos especimen. No sólo sobrevivió 500 " +
                "años, sino que tambien ingresó como texto de relleno en documentos electrónicos, quedando esencialmente " +
                "igual al original. Fue popularizado en los 60s con la creación de las hojas Letraset, las cuales contenian " +
                "pasajes de Lorem Ipsum, y más recientemente con software de autoedición, como por ejemplo Aldus PageMaker, " +
                "el cual incluye versiones de Lorem Ipsum.";
        String [] palabras=texto.split(" ");
        escribirFichero(file,palabras);
        leerFichero(file);
    }

    private static void leerFichero(File file) {
        try(DataInputStream dataInputStream=new DataInputStream(new FileInputStream(file))){
            while(dataInputStream!=null){
                System.out.print(dataInputStream.readUTF());
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (EOFException e){

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static void escribirFichero(File file, String[] palabras) {
        try(DataOutputStream dataOutputStream=new DataOutputStream(new FileOutputStream(file))){
            for(int i=0;i< palabras.length;i++){
                dataOutputStream.writeUTF(palabras[i]+"*");
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
