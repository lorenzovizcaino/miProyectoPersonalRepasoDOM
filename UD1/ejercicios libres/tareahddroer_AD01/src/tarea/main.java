package tarea;

import javax.swing.*;
import java.io.FileNotFoundException;
import java.io.IOException;

public class main {
    public static void main(String[] args) {
        menu();
    }

    private static void menu() {
        int op=0;
        boolean bandera=false;

        while(op!=6 || bandera){
            try{
                bandera=false;
                op=Integer.parseInt(JOptionPane.showInputDialog("MENU DE OPCIONES" +
                        "\n1.- Alta de empleados" +
                        "\n2.- Baja de empleados" +
                        "\n3.- Listado de empleados" +
                        "\n4.- Consulta por posicion del empleado" +
                        "\n5.- Modificacion de un empleado" +
                        "\n6.- Salir"));

                switch(op){
                    case 1:
                        Operaciones.AltaEmpleados();
                        break;
                    case 2:
                        Operaciones.BajaEmpleados();
                        break;
                    case 3:
                        Operaciones.ListarEmpleados();
                        break;
                    case 4:
                        Operaciones.ConsultaPorPosicion();
                        break;
                    case 5:
                        Operaciones.ModificacionEmpleado();
                        break;
                    case 6:
                        System.out.println("Hasta Luego");
                }

            }catch(NumberFormatException e){
                bandera=true;
                System.out.println("Solo se admiten numeros del 1-6");
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }


        }




    }
}
