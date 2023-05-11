package seis;

import javax.swing.*;
import java.io.*;

/*
 Crea una aplicación que almacene los datos básicos de un vehículo como la matricula(String), marca (String),
 tamaño de deposito (double) y modelo (String) en ese orden y de uno en uno usando la clase DataInputStream.

Los datos anteriores datos se pedirán por teclado y se irán añadiendo al fichero (no se sobrescriben los datos)
cada vez que ejecutemos la aplicación.

El fichero siempre sera el mismo, en todos los casos.

Muestra todos los datos de cada coche en un cuadro de dialogo, es decir, si tenemos 3 vehículos mostrara
3 cuadros de dialogo con sus respectivos datos.
 */
public class Seis {
    public static void main(String[] args) {
        File file=new File("seis.dat");
        String matricula= JOptionPane.showInputDialog("Matricula del vehiculo");
        String marca=JOptionPane.showInputDialog("Marca del vehiculo");
        double deposito=Double.parseDouble(JOptionPane.showInputDialog("Tamaño del deposito"));
        String modelo=JOptionPane.showInputDialog("Modelo del vehiculo");
        guardarDatos(file, matricula, marca, deposito,modelo);
        leerDatos(file);

    }

    private static void leerDatos(File file) {
        String matricula, marca, modelo;
        double deposito;
        try(DataInputStream dataInputStream=new DataInputStream(new FileInputStream(file))){
            while((matricula=dataInputStream.readUTF())!=null){
                marca=dataInputStream.readUTF();
                deposito=dataInputStream.readDouble();
                modelo=dataInputStream.readUTF();
                JOptionPane.showMessageDialog(null,"El vehiculo tiene una matricula "+matricula+", su marca es "+marca+" el tamaño del deposito es de "+deposito+" litros y su modelo es "+modelo);
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (EOFException e){

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static void guardarDatos(File file, String matricula, String marca, double deposito, String modelo) {
        try(DataOutputStream dataOutputStream=new DataOutputStream(new FileOutputStream(file,true))){
            dataOutputStream.writeUTF(matricula);
            dataOutputStream.writeUTF(marca);
            dataOutputStream.writeDouble(deposito);
            dataOutputStream.writeUTF(modelo);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
