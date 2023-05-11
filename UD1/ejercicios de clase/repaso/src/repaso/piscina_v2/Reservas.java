package repaso.piscina_v2;

import javax.swing.*;

public class Reservas {

    public static void RellenarMatriz(String[][] hojaReservas) {
        //ESTE METODO RELLENA CON "VACIO" TODAS LAS POSICIONES DE LA MATRIZ
        for(int i=0;i< hojaReservas.length;i++){
            for(int j=0;j<hojaReservas[0].length;j++){
                hojaReservas[i][j]="VACIO";
            }
        }
    }

    public static void VerReservasPiscina(String[][] hojaReservas) {
        //ESTE METODO IMPRIME EN FORMATO DE TABLA LA SITUACION ACTUAL DE LAS RESERVAS EN LA PISCINA
        for(int i=0;i< hojaReservas.length;i++){
            for(int j=0;j<hojaReservas[0].length;j++){
                System.out.print(hojaReservas[i][j]+" ");
            }
            System.out.println();
        }
    }

    public static boolean comprobarHueco(String[][] hojaReservas, int op) {
        //ESTE METODO COMPRUEBA QUE QUEDAN PLAZAS LIBRES EN LA PISCINA EN EL HORARIO ELEGIDO POR EL USUARIO
        for(int j=0;j< hojaReservas[0].length;j++) {
            if (hojaReservas[op - 1] [j]== "VACIO") {
                return true;
            }
        }
        return  false;
    }

    public static String SolicitarDni() {
        //ESTE METODO SOLICITA EL DNI HASTA QUE ES METE UNO CORRECTO, COMPROBADO CON EL METODO --> ComprobarDni(dni)
        String dni = null;
        boolean pedirDni=true, dniOk;
        while(pedirDni){
            dni= JOptionPane.showInputDialog("Introduzca DNI para formalizar la reserva").toUpperCase();
            dniOk=ComprobarDni(dni);
            if (dniOk){
                System.out.println("DNI OK");
                pedirDni=false;
            }
            else{
                System.out.println("El DNI no es valido");
            }
        }
        return dni;

    }

    public static boolean ComprobarDni(String dni) {
        //LAS DIFERENTES COMPROBACIONES PARA VER QUE EL DNI INTRODUCIDO ES CORRECTO
        char [] letrasDni={'T','R','W','A','G','M','Y','F','P','D','X','B','N','J','Z','S','Q','V','H','L','C','K','E'};
        char letra;
        String numerosDni;
        int numeroDni, resto;
        //1ª COMPROBACION: LA LONGITUD DEL DNI
        if(dni.length()!=9){
            System.out.println("El DNI debe de tener 9 caracteres");
            return false;
        }
        //2ª COMPROBACION: QUE LOS 8 PRIMEROS CARACTERES SEAN NUMEROS
        numerosDni=dni.substring(0,8);
        for(int i=0;i<numerosDni.length();i++){
            if(numerosDni.charAt(i)<48 || numerosDni.charAt(i)>57){
                System.out.println("Los primeros 8 caracteres deben de ser numeros");
                return false;
            }
        }
        //3ª COMPROBACION: QUE EL ULTIMO CARACTER SE CORRESPONDA CON LA LETRA CORRECTA DEL DNI
        numeroDni=Integer.parseInt(numerosDni);
        resto=numeroDni%23;
        letra=dni.toUpperCase().charAt(8);
        if(letra!=letrasDni[resto]){
            System.out.println("La letra del DNI no es correcta");
            return false;
        }
        return true;

    }

    public static boolean comprobarDniRepetido(String[][] hojaReservas, String dni) {
        //ESTE METODO COMPRUEBA QUE EL DNI INTRODUCIDO NO ESTA YA REGISTRADO EN LA MATRIZ
        for(int i=0;i< hojaReservas.length;i++){
            for(int j=0;j<hojaReservas[0].length;j++){
                if(hojaReservas[i][j].equals(dni)){
                    return false;
                }
            }
        }
        return true;
    }

    public static void reservar(String [][] HojaReservas, int op, String dni) {
        //ESTE METODO INSERTA EN LA MATRIZ EL DNI DEL USUARIO SI HAY PLAZA EN EL HORARIO SOLICITADO,
        // SINO ES ASI INDICA QUE ESTA LLENA Y NO LO REGISTRA
        boolean bandera=true;
        for(int j=0;j< HojaReservas[0].length;j++){
            if(HojaReservas[op-1][j]=="VACIO"){
                HojaReservas[op-1][j]=dni;
                System.out.println("DNI registrado correctamente");
                bandera=false;
                break;
            }
        }
        if(bandera){
            System.out.println("Piscina llena, no se ha registrado en ese horario");
        }
    }
}
