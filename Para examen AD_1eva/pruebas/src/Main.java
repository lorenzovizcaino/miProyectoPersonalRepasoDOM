import model.Coches;

import java.io.*;
import java.lang.reflect.Array;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.ArrayList;

public class Main {
    public static ArrayList<Coches> coches=new ArrayList<Coches>();
    //public static File file=new File(".\\ficheros\\coches.dat");
    public static Path path= Paths.get("ficheros","coches.dat");
    public static File file=path.toFile();
    public static final int LONGITUD_MATRICULA=7;
    public static final int LONGITUD_MARCA=20;
    public static final int LONGITUD_FECHA_VENTA=10;
    public static final int LONGITUD_REGISTRO=79;


    public static void main(String[] args) {
        CrearCoches();
        MeterEnFichero();
        LeerElFichero();
        //buscarPorMatricula("7845WES");
        ModificarCaballosPorMatricula("7745WES",40);
        LeerElFichero();
    }

    private static void ModificarCaballosPorMatricula(String matricula, int caballos) {
        String matriculaFichero;
        StringBuffer stringBuffer;
        boolean encontrado=false;
        try(RandomAccessFile randomAccessFile=new RandomAccessFile(file,"rw")){
            while (true){
                stringBuffer=new StringBuffer();
                for (int i = 0; i < LONGITUD_MATRICULA; i++) {
                    stringBuffer.append(randomAccessFile.readChar());
                }
                matriculaFichero=stringBuffer.toString();
                if(matriculaFichero.equalsIgnoreCase(matricula)){
                    randomAccessFile.seek(randomAccessFile.getFilePointer()+40);
                    randomAccessFile.writeInt(caballos);
                    System.out.println("registro modificado");
                    encontrado=true;
                    break;
                }
                randomAccessFile.seek(randomAccessFile.getFilePointer()+65);
            }



        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (EOFException e){
            if(!encontrado){
                System.out.println("No existe esa matricula en el fichero");
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static void buscarPorMatricula(String matricula) {
        String matriculaFichero;
        String marca;
        int caballos;
        LocalDate fechaVenta;
        boolean climatizador;
        Coches coche;
        int posicion;
        StringBuffer stringBuffer;
        boolean encontrado=false;
        try(RandomAccessFile randomAccessFile=new RandomAccessFile(file,"r")){
            while(true){
                stringBuffer=new StringBuffer();
                for (int i = 0; i <LONGITUD_MATRICULA ; i++) {

                    stringBuffer.append(randomAccessFile.readChar());
                }
                matriculaFichero=stringBuffer.toString();
                if(matriculaFichero.equalsIgnoreCase(matricula)){
                    encontrado=true;
                    stringBuffer=new StringBuffer();
                    for (int i = 0; i <LONGITUD_MARCA; i++) {
                        stringBuffer.append(randomAccessFile.readChar());
                    }
                    marca=stringBuffer.toString().trim();
                    stringBuffer=new StringBuffer();
                    caballos=randomAccessFile.readInt();
                    for (int i = 0; i <LONGITUD_FECHA_VENTA; i++) {
                        stringBuffer.append(randomAccessFile.readChar());
                    }
                    fechaVenta=LocalDate.parse(stringBuffer.toString().trim());
                    stringBuffer=new StringBuffer();
                    climatizador=randomAccessFile.readBoolean();
                    coche=new Coches(matricula, marca, caballos, fechaVenta, climatizador);
                    System.out.println("El coche buscado es: "+coche);
                    break;
                }
                stringBuffer.delete(0,stringBuffer.length());

                //para empezar a leer de nuevo tenemos que posicionarnos en el byte anterior al que queremos leer

                randomAccessFile.seek(randomAccessFile.getFilePointer()+65);

            }

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (EOFException e){
            if(encontrado==false){
                System.out.println("No existe esa matricula en el fichero");
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static void LeerElFichero() {
        ArrayList <Coches> cochesFichero=new ArrayList<>();
        Coches coche;
        String matricula;
        String marca;
        int caballos;
        LocalDate fechaVenta;
        boolean climatizador;
        StringBuffer stringBuffer=new StringBuffer();
        try(RandomAccessFile randomAccessFile=new RandomAccessFile(file,"r")){
            while(true){
                for(int i=0;i<LONGITUD_MATRICULA;i++){
                    stringBuffer.append(randomAccessFile.readChar());
                }
                matricula=stringBuffer.toString().trim();
                stringBuffer=new StringBuffer();
                for (int i = 0; i <LONGITUD_MARCA; i++) {
                    stringBuffer.append(randomAccessFile.readChar());
                }
                marca=stringBuffer.toString().trim();
                stringBuffer=new StringBuffer();
                caballos=randomAccessFile.readInt();
                for (int i = 0; i <LONGITUD_FECHA_VENTA; i++) {
                    stringBuffer.append(randomAccessFile.readChar());
                }
                fechaVenta=LocalDate.parse(stringBuffer.toString().trim());
                stringBuffer=new StringBuffer();
                climatizador=randomAccessFile.readBoolean();
                coche=new Coches(matricula, marca, caballos, fechaVenta, climatizador);
                cochesFichero.add(coche);
            }

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (EOFException e){

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        for (Coches c:cochesFichero) {
            System.out.println(c);
        }
    }

    private static void MeterEnFichero() {
        StringBuffer stringBuffer;

        try(RandomAccessFile randomAccessFile=new RandomAccessFile(file,"rw")){
            for (Coches c:coches) {

                stringBuffer=new StringBuffer(c.getMatricula());
                stringBuffer.setLength(LONGITUD_MATRICULA);
                randomAccessFile.writeChars(stringBuffer.toString());
                stringBuffer.delete(0,stringBuffer.length());

                stringBuffer=new StringBuffer(c.getMarca());
                stringBuffer.setLength(LONGITUD_MARCA);
                randomAccessFile.writeChars(stringBuffer.toString());
                stringBuffer.delete(0,stringBuffer.length());
                randomAccessFile.writeInt(c.getCaballos());
                stringBuffer=new StringBuffer(c.getFechaVenta().toString());
                stringBuffer.setLength(LONGITUD_FECHA_VENTA);
                randomAccessFile.writeChars(stringBuffer.toString());
                stringBuffer.delete(0,stringBuffer.length());
                randomAccessFile.writeBoolean(c.isClimatizador());


            }

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


    }

    private static void CrearCoches() {

        String date="2020-03-12";
        Coches coche1=new Coches("2345KRF","Mazda",150,LocalDate.parse(date) , true);
        Coches coche2=new Coches("4518POE","Renault",140,LocalDate.parse(date) , false);
        Coches coche3=new Coches("4587DFD","Seat",180,LocalDate.parse(date) , false);
        Coches coche4=new Coches("7845WES","Volvo",120,LocalDate.parse(date) , true);
        coches.add(coche1);
        coches.add(coche2);
        coches.add(coche3);
        coches.add(coche4);


    }
}
