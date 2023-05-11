package cinco;

import javax.swing.*;
import java.io.*;

/*
Crea una aplicación que pida por teclado un número de números aleatorios enteros positivos y la ruta de un fichero.
Este fichero contendrá la cantidad de números aleatorios enteros positivos que se ha pedido por teclado.

Escribe los números usando un DataOutputStream y muestra por pantalla estos números leyéndolos con un DataInputStream.

El rango de los números aleatorios estará entre 0 y 100, incluyendo el 100.

Cada vez que ejecutemos la aplicación añadiremos números al fichero sin borrar los anteriores, es decir, si cuando creo
el fichero añado 10 números y después añado otros 10 al mismo, en el fichero habrá 20 números que serán mostrados por pantalla.
 */
public class cinco {
    public static void main(String[] args) {
        int cantidadNumeros=0;
        String rutaFichero;
        rutaFichero=JOptionPane.showInputDialog("Dame la ruta del fichero");
        File file=new File(rutaFichero);
        while(cantidadNumeros<1){
            try{
                cantidadNumeros=Integer.parseInt(JOptionPane.showInputDialog("Cuantos numeros desea añadir"));
            }catch(NumberFormatException e){
                System.out.println("Debe de introducir un numero entero mayor que 0");
            }
        }
        crearFichero(cantidadNumeros,file);
        leerFichero(file);




    }

    private static void leerFichero(File file) {
        int numero;
        try(DataInputStream dataInputStream=new DataInputStream(new FileInputStream(file))){
            while((numero=dataInputStream.readInt())!=-1){
                System.out.println(numero);
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (EOFException e){

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static void crearFichero(int cantidadNumeros, File file) {
        int numero;
        try(DataOutputStream dataOutputStream=new DataOutputStream(new FileOutputStream(file,true));

        ){
            for (int i=0;i<cantidadNumeros;i++){
                numero=(int)(Math.random()*100);
                dataOutputStream.writeInt(numero);
            }


        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
