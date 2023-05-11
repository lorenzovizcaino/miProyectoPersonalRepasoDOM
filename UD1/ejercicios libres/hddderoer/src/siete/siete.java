package siete;

import javax.swing.*;
import java.io.*;

/*
Vamos a realizar el mismo ejercicio pero con serialización, para ello, crea una simple clase Vehiculo con los atributos matricula,
marca, tamaño del deposito y modelo, con sus respectivos métodos get y el constructor se invocara con todos los atributos.

El atributo tamañoDeposito no se incluirá en el fichero (aun así debemos pedirlo), debemos indicarlo en la clase
(recuerda el uso de transient).

Recuerda que al usar la clase ObjectOutputStream, si vamos a añadir varios objetos en distintas ejecuciones,
debemos crear nuestra propia versión de ObjectOutputStream. (Serialización de objetos en Java)
 */
public class siete {
    public static File file = new File("siete.dat");

    public static void main(String[] args) {
        Vehiculo vehiculo1 = new Vehiculo("6823KRF", "Mazda", 45, "3");
        Vehiculo vehiculo2 = new Vehiculo("2934DDJ", "Volvo", 55, "S60");
        GuardarVehiculo(vehiculo1);
        GuardarVehiculo(vehiculo2);
        leerVehiculos();
    }

    private static void leerVehiculos() {
        Vehiculo vehiculo;
        String matricula, marca, modelo;
        double deposito;
        try (ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream(file))) {
            while (true) {
                vehiculo = (Vehiculo) objectInputStream.readObject();
                matricula = vehiculo.getMatricula();
                marca = vehiculo.getMarca();
                modelo = vehiculo.getModelo();
                deposito = vehiculo.getDeposito();
                JOptionPane.showMessageDialog(null, "El vehiculo tiene una matricula " + matricula + ", su marca es " + marca + " el tamaño del deposito es de " + deposito + " litros y su modelo es " + modelo);

            }

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (EOFException e) {

        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

    }

    private static void GuardarVehiculo(Vehiculo vehiculo) {
        MyObjectOutputStream myObjectOutputStream;
        ObjectOutputStream objectOutputStream;
        try {
            if (file.exists()) {
                myObjectOutputStream = new MyObjectOutputStream(new FileOutputStream(file, true));
                myObjectOutputStream.writeObject(vehiculo);
                myObjectOutputStream.close();

            } else {
                objectOutputStream = new ObjectOutputStream(new FileOutputStream(file));
                objectOutputStream.writeObject(vehiculo);
                objectOutputStream.close();

            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);


        }
    }
}
