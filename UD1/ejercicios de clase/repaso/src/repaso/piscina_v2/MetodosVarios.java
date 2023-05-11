package repaso.piscina_v2;

import javax.swing.*;

public class MetodosVarios {
    public static int comprobarEntero(String cadenaTexto, String error) {
        //ESTE METODO COMPRUEBA QUE EL NUMERO ES UN ENTERO
        boolean bandera = true;
        int number=0;
        while (bandera) {
            try {
                bandera = false;
                number = Integer.parseInt(JOptionPane.showInputDialog(cadenaTexto));
                if (number<1){
                    bandera=true;
                    System.out.println(error);
                }

            } catch (NumberFormatException e) {
                bandera = true;
                System.out.println(error);
            }
        }
        return number;
    }
}
