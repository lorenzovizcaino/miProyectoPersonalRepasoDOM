package JavaIO;

import java.io.File;

public class JavaIO {
    public static void main(String[] args) {
        System.out.println("INFORMACI�N SOBRE EL FICHERO:");
        File f = new File("E:\\justificante.docx");
        if (f.exists()) {
            System.out.println("Nombre del fichero  : " + f.getName());
            System.out.println("Ruta                : " + f.getPath());
            System.out.println("Ruta absoluta       : " + f.getAbsolutePath());
            System.out.println("Se puede leer       : " + f.canRead());
            System.out.println("Se puede escribir   : " + f.canWrite());
            System.out.println("Tama�o              : " + f.length());
            System.out.println("Es un directorio    : " + f.isDirectory());
            System.out.println("Es un fichero       : " + f.isFile());
            System.out.println("Nombre del directorio padre: " + f.getParent());
        }

        verDirectorio();
        verDirectorio2();
        
    }

    private static void verDirectorio2() {
        String dir = ".";
        File f = new File(dir);
        String[] archivos = f.list();
        System.out.printf("Ficheros en el directorio actual: %d %n", archivos.length);
        for (int i = 0; i < archivos.length; i++) {
            File f2 = new File(f, archivos[i]);
            System.out.printf("Nombre: %s, es fichero?: %b, es directorio?: %b %n", archivos[i], f2.isFile(),
                    f2.isDirectory());
        }
    }

    private static void verDirectorio() {
        String dir = ".";
        File f = new File(dir);
        File[] listaficheros= f.listFiles();
        System.out.printf("Ficheros en el directorio actual: %d %n", listaficheros.length);
        for (int i = 0; i < listaficheros.length; i++) {
            File f2 = listaficheros[i];
            System.out.printf("Nombre: %s, es fichero?: %b, es directorio?: %b %n",
                    f2.getName(), f2.isFile(),f2.isDirectory());
        }
    }
}
