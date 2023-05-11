package JavaNIO;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.FileTime;

public class JavaNIO {
    public static void main(String[] args) throws IOException {
        Path directorio=Paths.get("ficheros");
        Path directorio2=Paths.get("ficheros2");
        Path fichero= Paths.get("ficheros","pruebas.txt");

        //CREAR FICHERO Y DIRECTORIO
        if(!Files.exists(directorio)){
            Files.createDirectory(directorio);
            Files.createFile(fichero);
        }

        //LEER FICHERO CONVIRTIENDOLO A UN String
        String c=Files.readString(fichero);
        System.out.println(c);


        if(Files.notExists(directorio2)){
            Files.createDirectory(directorio2);

        }
//        MOVER ARCHIVO
//        Path origen=fichero;
//        Path destino=Paths.get(directorio2.toString(), "pruebas.txt");
//        Files.move(origen,destino);
//        Files.delete(directorio);

//        COPIAR ARCHIVO
//        Files.copy(destino,origen);

        //INFO DEL FICHERO
        System.out.println("Nombre: " + fichero.getFileName());
        System.out.println("Ruta absoluta: " + fichero.toAbsolutePath());

        FileTime ftime = Files.getLastModifiedTime(fichero);
        System.out.println("Ultima modificacion: " + ftime);

        long bytes = Files.size(fichero);
        System.out.println("Tamaño(KB): " + bytes+"/1024");










    }
}
