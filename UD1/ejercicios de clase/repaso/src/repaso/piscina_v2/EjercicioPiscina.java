package repaso.piscina_v2;

import repaso.config.Config;
import repaso.persistencia.FilePersistencia;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

public class EjercicioPiscina {
    private static final String CONFIG_FILE="Piscina.config";
    private static final String PISCINAS_FILE="miPiscina.dat";


    public static void main(String[] args) {


        int aforo;
        int op=-1;
        Piscina p;
        Path path=Paths.get(PISCINAS_FILE);
        crearConfigFile(); //SE CREA EL ARCHIVO DE CONFIGURACION SI NO EXISTIESE
        Config.leerConfig2(CONFIG_FILE,"persistencia"); //SE BUSCA EN EL ARCHIVO DE CONFIGURACION LA CLAVE "xxxx"
        if(Files.exists(path)){
            p=FilePersistencia.Read(PISCINAS_FILE);
            if(p==null){
                p=pedirDatos();
            }
        }else{
            p=pedirDatos();
        }


        FilePersistencia.Write(p,PISCINAS_FILE);//SE GUARDA LA PISCINA EN EL FICHERO
        p=FilePersistencia.Read(PISCINAS_FILE); //SE LEE LA PISCINA DEL FICHERO DEVOLVIENDO LA PISCINA
        System.out.println("CARACTERISTICAS DE LA PISCINA");
        System.out.println(p.toString());
        aforo=p.CalcularAforo();
        System.out.println("El aforo de la piscina es de "+aforo+" personas");

        String[] [] HojaReservas=new String[6][aforo];
        Reservas.RellenarMatriz(HojaReservas);
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
                    hueco=Reservas.comprobarHueco(HojaReservas,op);
                    if(hueco){
                        //SI HAY HUECO SE SOLICITA DNI
                        dni=Reservas.SolicitarDni();
                        //SE COMPRUEBA QUE NO EXISTE ESE DNI YA VALIDADO EN TODA LA MATRIZ
                        dniNoRepetido=Reservas.comprobarDniRepetido(HojaReservas,dni);
                        if(dniNoRepetido){
                            //SI NO ESTA REPETIDO SE REALIZA LA RESERVA
                            Reservas.reservar(HojaReservas, op, dni);

                        }else{
                            System.out.println("Ese DNI ya esta registrado para el dia de hoy. " +
                                    "\nNo se puede reservar mas de una vez por dia");
                        }
                    }else{
                        System.out.println("En esa franja horaria no quedan plazas disponibles");
                    }
                    break;
                case 7:
                    Reservas.VerReservasPiscina(HojaReservas);
                    break;
                case 8:
                    System.out.println("Hasta Luego");
                    break;

            }
        }


    }

    private static Piscina pedirDatos() {
        int longitudPiscina;
        int longitudParcela;
        int anchuraPiscina;
        int anchuraParcela;
        longitudPiscina=MetodosVarios.comprobarEntero("Longitud de la Piscina","Ha ocurrido una excepción. Solo se permiten enteros positivos");
        anchuraPiscina=MetodosVarios.comprobarEntero("Anchura de la Piscina","Ha ocurrido una excepción. Solo se permiten enteros positivos");
        longitudParcela=MetodosVarios.comprobarEntero("Longitud de la Parcela","Ha ocurrido una excepción. Solo se permiten enteros positivos");
        anchuraParcela=MetodosVarios.comprobarEntero("Anchura de la Parcela","Ha ocurrido una excepción. Solo se permiten enteros positivos");
        Piscina miPiscina=new Piscina(longitudPiscina,anchuraPiscina,longitudParcela,anchuraParcela);
        return miPiscina;
    }


    private static int menu() {
        //ESTE METODO ES UN MENU DE OPCIONES
        int op=-1;
        while(op<1 || op>8){
            op=MetodosVarios.comprobarEntero("Introduce la franja Horaria deseada" +
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

    private static void  crearConfigFile(){
        if(!Files.exists(Paths.get(CONFIG_FILE))){
            HashMap<String,String>mapa=new HashMap<>();
            mapa.put("start","true");
            mapa.put("persistencia", "true");
            mapa.put("max_franjas","4");

            Config.crearConfigFile(mapa,CONFIG_FILE);
        }
    }


}
