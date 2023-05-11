package repaso;

import javax.swing.*;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Ejercicio_Piscina {
    public static void main(String[] args) {
        int aforo;
        int op=-1;
        aforo=CalcularAforo();
        String[] [] HojaReservas=new String[6][aforo];
        //HAY QUE RELLENAR LA MATRIZ CON ALGUN String, POR QUE SINO NO SE PUEDE PREGUNTAR CON EL equals SI LA POSICION ESTA VACIA.
        RellenarMatriz(HojaReservas);
        boolean hueco, dniNoRepetido;
        String dni;
        while(op!=8){
            op=menu();
            switch (op){
                case 1:
                case 2:
                case 3:
                case 4:
                case 5:
                case 6:
                    //SE COMPRUEBA SI EN LA OPCION ELEGIDA POR EL USUARIO QUEDA ALGUN HUECO LIBRE
                    hueco=comprobarHueco(HojaReservas,op);
                    if(hueco){
                        //SI HAY HUECO SE SOLICITA DNI
                        dni=SolicitarDni();
                        //SE COMPRUEBA QUE NO EXISTE ESE DNI YA VALIDADO EN TODA LA MATRIZ
                        dniNoRepetido=comprobarDniRepetido(HojaReservas,dni);
                        if(dniNoRepetido){
                            //SI NO ESTA REPETIDO SE REALIZA LA RESERVA
                            reservar(HojaReservas, op, dni);

                        }else{
                            System.out.println("Ese DNI ya esta registrado para el dia de hoy. " +
                                    "\nNo se puede reservar mas de una vez por dia");
                        }
                    }else{
                        System.out.println("En esa franja horaria no quedan plazas disponibles");
                    }
                    break;
                case 7:
                    VerReservasPiscina(HojaReservas);
                    break;
                case 8:
                    System.out.println("Hasta Luego");
                    break;

            }
        }



    }

    private static void VerReservasPiscina(String[][] hojaReservas) {
        //ESTE METODO IMPRIME EN FORMATO DE TABLA LA SITUACION ACTUAL DE LAS RESERVAS EN LA PISCINA
        for(int i=0;i< hojaReservas.length;i++){
            for(int j=0;j<hojaReservas[0].length;j++){
                System.out.print(hojaReservas[i][j]+" ");
            }
            System.out.println();
        }
    }

    private static void RellenarMatriz(String[][] hojaReservas) {
        //ESTE METODO RELLENA CON "VACIO" TODAS LAS POSICIONES DE LA MATRIZ
        for(int i=0;i< hojaReservas.length;i++){
            for(int j=0;j<hojaReservas[0].length;j++){
                hojaReservas[i][j]="VACIO";
            }
        }
    }

    private static boolean comprobarDniRepetido(String[][] hojaReservas, String dni) {
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

    private static String SolicitarDni() {
        //ESTE METODO SOLICITA EL DNI HASTA QUE ES METE UNO CORRECTO, COMPROBADO CON EL METODO --> ComprobarDni(dni)
        String dni = null;
        boolean pedirDni=true, dniOk;
        while(pedirDni){
            dni=JOptionPane.showInputDialog("Introduzca DNI para formalizar la reserva").toUpperCase();
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

    private static boolean ComprobarDni(String dni) {
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

    private static boolean comprobarHueco(String[][] hojaReservas, int op) {
        //ESTE METODO COMPRUEBA QUE QUEDAN PLAZAS LIBRES EN LA PISCINA EN EL HORARIO ELEGIDO POR EL USUARIO
        for(int j=0;j< hojaReservas[0].length;j++) {
            if (hojaReservas[op - 1] [j]== "VACIO") {
                return true;
            }
        }
        return  false;
    }

    private static int CalcularAforo() {int longitudPiscina, longitudParcela, anchuraPiscina, anchuraParcela;
        //ESTE METODO CALCULA EL AFORO CORRECTO SEGUN LAS ESPECIFICACIONES DEL ENUNCIADO
        int aforoPiscina, aforoParcela,aforo=10;
        longitudPiscina=comprobarEntero("Longitud de la Piscina","Ha ocurrido una excepción. Solo se permiten enteros positivos");
        anchuraPiscina=comprobarEntero("Anchura de la Piscina","Ha ocurrido una excepción. Solo se permiten enteros positivos");
        longitudParcela=comprobarEntero("Longitud de la Parcela","Ha ocurrido una excepción. Solo se permiten enteros positivos");
        anchuraParcela=comprobarEntero("Anchura de la Parcela","Ha ocurrido una excepción. Solo se permiten enteros positivos");
        aforoPiscina=(longitudPiscina*anchuraPiscina)/2;
        aforoParcela=(longitudParcela*anchuraParcela)/2;

        if(aforoPiscina>=aforoParcela){
            aforo=aforoParcela;
        }else{
            aforo=aforoPiscina;
        }
        System.out.println("El maximo de aforo es de "+aforo+" personas");
        return aforo;

    }

    private static void reservar(String [][] HojaReservas, int op, String dni) {
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

    private static int menu() {
        //ESTE METODO ES UN MENU DE OPCIONES
        int op=-1;
        while(op<1 || op>8){
            op=comprobarEntero("Introduce la franja Horaria deseada" +
                    "\n1.- 08:00h - 10:00h" +
                    "\n2.- 10:00h - 12:00h" +
                    "\n3.- 12:00h - 14:00h" +
                    "\n4.- 14:00h - 16:00h" +
                    "\n5.- 16:00h - 18:00h" +
                    "\n6.- 18:00h - 20:00h" +
                    "\n7.- Ver Reservas Piscina"+
                    "\n8.- SALIR",
                    "Solo se permiten numeros (1-8)");
            if(op<1||op>8){
                System.out.println("Solo se permiten numeros (1-8)");
            }
        }

        return op;
    }

    private static int comprobarEntero(String cadenaTexto, String error) {
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
