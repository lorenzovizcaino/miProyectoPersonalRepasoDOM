package variado;

import java.io.*;

//Escribe un programa que escriba los 100 primeros números naturales en un archivo numNaturales.txt.


public class NumerosNaturales {
    public static File file=new File("numNaturales.txt");
    public static void main(String[] args) {
        escribirFichero();
        leerFichero();


    }


    private static void leerFichero() {
        int num=0;
        int suma=0;
        try(FileReader fileReader=new FileReader(file)){
            while ((num=fileReader.read())!=-1){
                System.out.println(num);
                suma+=num;
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        System.out.println("La suma de los numeros es "+suma);
    }

    private static void escribirFichero() {
        int num=0;

        try(FileWriter fileWriter=new FileWriter(file)){
            for(int i=0;i<100;i++){
                num++;
                fileWriter.write(num);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
