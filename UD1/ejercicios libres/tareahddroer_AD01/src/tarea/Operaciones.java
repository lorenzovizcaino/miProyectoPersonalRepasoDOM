package tarea;

import javax.swing.*;
import java.io.EOFException;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Operaciones {
    public static final String FICHERO_EMPLEADOS="Empleados.dat";
    public static void AltaEmpleados() {

        int codigo;
        String nombre;
        String direccion;
        float salario;
        float comision;
        long posicion=0;
        boolean bandera=false;
        StringBuffer stringBuffer;
        if(Files.exists(Paths.get(FICHERO_EMPLEADOS))){
            bandera=true;
        }
        try(RandomAccessFile randomAccessFile=new RandomAccessFile(FICHERO_EMPLEADOS,"rw");){
            if(bandera){
                posicion=randomAccessFile.length()-72;
                randomAccessFile.seek(posicion);
                codigo=randomAccessFile.readInt()+1;

            }else{
                codigo=1;
            }
            nombre=JOptionPane.showInputDialog("Nombre del empleado");
            direccion=JOptionPane.showInputDialog("Direccion del empleado");
            salario=Float.parseFloat(JOptionPane.showInputDialog("Salario del empleado"));
            comision=Float.parseFloat(JOptionPane.showInputDialog("Comision del empleado"));
            posicion=randomAccessFile.length();
            randomAccessFile.seek(posicion);
            randomAccessFile.writeInt(codigo);

            stringBuffer=new StringBuffer(nombre);
            stringBuffer.setLength(15);
            randomAccessFile.writeChars(stringBuffer.toString());

            stringBuffer=new StringBuffer(direccion);
            stringBuffer.setLength(15);
            randomAccessFile.writeChars(stringBuffer.toString());

            randomAccessFile.writeFloat(salario);
            randomAccessFile.writeFloat(comision);

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    public static void ListarEmpleados() {
        StringBuffer stringBuffer;
        try(RandomAccessFile randomAccessFile=new RandomAccessFile(FICHERO_EMPLEADOS,"r")){
            while (randomAccessFile!=null){
                leerRegistro(randomAccessFile);

            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (EOFException e){

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void ConsultaPorPosicion() throws IOException {
        int posicion;
        posicion=Integer.parseInt(JOptionPane.showInputDialog("Posicion a consultar"));
        try(RandomAccessFile randomAccessFile=new RandomAccessFile(FICHERO_EMPLEADOS,"r")){
            randomAccessFile.seek((posicion*72)-72);
            leerRegistro(randomAccessFile);
        }
    }

    private static void leerRegistro(RandomAccessFile randomAccessFile) throws IOException {
        StringBuffer stringBuffer;
        System.out.print(randomAccessFile.readInt()+"  ");
        stringBuffer=new StringBuffer();
        for(int i=0;i<15;i++){
            stringBuffer.append(randomAccessFile.readChar());
        }
        System.out.print(stringBuffer.toString().trim()+"  ");
        stringBuffer=new StringBuffer();
        for(int i=0;i<15;i++){
            stringBuffer.append(randomAccessFile.readChar());
        }
        System.out.print(stringBuffer.toString().trim()+"  ");
        System.out.print(randomAccessFile.readFloat()+"  ");
        System.out.print(randomAccessFile.readFloat());
        System.out.println();
    }


    public static void ModificacionEmpleado() throws IOException {
        String nombre;
        String direccion;
        float salario;
        float comision;
        StringBuffer stringBuffer;
        int posicion=Integer.parseInt(JOptionPane.showInputDialog("Posicion del empleado a modificar"));
        try(RandomAccessFile randomAccessFile=new RandomAccessFile(FICHERO_EMPLEADOS,"rw")){
            randomAccessFile.seek((posicion*72)-68);//se pone 68 y no 72 por que ya no se sobreescribe le id que coincide con la posicion
            nombre=JOptionPane.showInputDialog("Nombre del empleado modificado");
            stringBuffer=new StringBuffer(nombre);
            stringBuffer.setLength(15);
            randomAccessFile.writeChars(stringBuffer.toString());
            direccion=JOptionPane.showInputDialog("Direccion del empleado modificada");
            stringBuffer=new StringBuffer(direccion);
            stringBuffer.setLength(15);
            randomAccessFile.writeChars(stringBuffer.toString());
            salario=Float.parseFloat(JOptionPane.showInputDialog("Salario del empleado modificado"));
            randomAccessFile.writeFloat(salario);
            comision=Float.parseFloat(JOptionPane.showInputDialog("Comision del empleado modificada"));
            randomAccessFile.writeFloat(comision);
        }
    }

    public static void BajaEmpleados() {
        int posicion=Integer.parseInt(JOptionPane.showInputDialog("posicion del empleado a dar de baja"));
    }
}
