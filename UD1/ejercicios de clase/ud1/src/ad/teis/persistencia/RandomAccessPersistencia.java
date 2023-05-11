package ad.teis.persistencia;

import ad.teis.model.Persona;

import java.io.EOFException;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;

/*
                bytes que ocupan los diferentes tipos de datos:
                boolean, byte -> 1 byte
                short -> 2 bytes
                int, float -> 4 bytes
                long, double -> 8 bytes
                String -> 2 bytes por cada caracter
 */
public class RandomAccessPersistencia implements Ipersistencia{

    public static void listarDatos(String personaFile2) {
        long tamanho=0;
        StringBuffer stringBuffer = null;
        try(RandomAccessFile randomAccessFile=new RandomAccessFile(personaFile2,"r")){
            tamanho= randomAccessFile.length();
            while(randomAccessFile.getFilePointer()<tamanho){
                   System.out.println("Posicion: "+randomAccessFile.getFilePointer());
                System.out.println("id: ............"+randomAccessFile.readLong());
                stringBuffer=new StringBuffer();
                for(int i=0;i<10;i++){
                    stringBuffer.append(randomAccessFile.readChar());
                }
                System.out.println("D.N.I. ........"+stringBuffer.toString().trim());
                System.out.println("Edad: ........."+randomAccessFile.readInt());
                System.out.println("Salario: ......"+randomAccessFile.readFloat());
                System.out.println();
                
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void escribirPersonas(ArrayList<Persona> personas, String personaFile3) {
        long posicion=0;
        try(RandomAccessFile randomAccessFile=new RandomAccessFile(personaFile3,"rw")){
            posicion=randomAccessFile.length();
            randomAccessFile.seek(posicion);
            for (Persona p:personas) {
                randomAccessFile.writeLong(p.getId());
                randomAccessFile.writeUTF(p.getDni());
                randomAccessFile.writeInt(p.getEdad());
                randomAccessFile.writeFloat(p.getSalario());
            }

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


    }

    public static ArrayList<Persona> leerTodo(String personaFile3) {
        ArrayList <Persona> personas=new ArrayList<>();
        long id;
        String dni="";
        int edad;
        float salario;
        Persona p=null;
        try(RandomAccessFile randomAccessFile=new RandomAccessFile(personaFile3,"r")){
            while(true){
                id= randomAccessFile.readLong();
                dni=randomAccessFile.readUTF();
                edad= randomAccessFile.readInt();
                salario=randomAccessFile.readFloat();
                p=new Persona(id, dni, edad, salario);
                personas.add(p);
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (EOFException e){

        } catch (IOException e) {
            throw new RuntimeException(e);
        }


        return personas;
    }

    public static Persona add(int i, String personaFile2, Persona persona) {

        try(RandomAccessFile randomAccessFile=new RandomAccessFile(personaFile2,"rw")){
            randomAccessFile.seek((i*36)-36);
            randomAccessFile.writeLong(persona.getId());
            StringBuffer stringBuffer=new StringBuffer(persona.getDni());
            stringBuffer.setLength(10);
            randomAccessFile.writeChars(stringBuffer.toString());
            randomAccessFile.writeInt(persona.getEdad());
            randomAccessFile.writeFloat(persona.getSalario());

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return persona;
    }

    public static float sumarSalario(int posicion, String fichero, float incSalario) {
        float salario;
        float nuevoSalario;
        try(RandomAccessFile randomAccessFile=new RandomAccessFile(fichero,"rw")){
            // ponemos -4 en vez de -36 por que queremos posicionarnos en el salario de ese registro, no en el id
            randomAccessFile.seek((posicion*36)-4);
            salario=randomAccessFile.readFloat();
            nuevoSalario=salario+incSalario;
            randomAccessFile.seek(randomAccessFile.getFilePointer()-4);
            randomAccessFile.writeFloat(nuevoSalario);
            return nuevoSalario;

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void escribirPersonas5Atributos(ArrayList<Persona> personas5Atributos, String personaFile4) {
        long posicion=0;
        try(RandomAccessFile randomAccessFile=new RandomAccessFile(personaFile4,"rw")){
            posicion=randomAccessFile.length();
            randomAccessFile.seek(posicion);
            for (Persona p:personas5Atributos) {
                randomAccessFile.writeLong(p.getId());
                randomAccessFile.writeUTF(p.getDni());
                randomAccessFile.writeInt(p.getEdad());
                randomAccessFile.writeFloat(p.getSalario());
                randomAccessFile.writeBoolean(p.isBorrado());

            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void listarPersonas5Atributos(String personaFile4) {
        try(RandomAccessFile randomAccessFile=new RandomAccessFile(personaFile4,"r")){
            while(true){
                System.out.println(randomAccessFile.getFilePointer());
                System.out.println("Id Persona "+randomAccessFile.readLong());
                System.out.println("DNI Persona "+randomAccessFile.readUTF());
                System.out.println("Edad Persona "+randomAccessFile.readInt());
                System.out.println("Salario Persona "+randomAccessFile.readFloat());
                System.out.println("Borrado "+randomAccessFile.readBoolean());
                System.out.println();
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch(EOFException e){

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static boolean borrar(int posicion, String personaFile4, boolean b) {
        boolean retorno=false;

        try(RandomAccessFile randomAccessFile=new RandomAccessFile(personaFile4,"rw")){
            //se pone -1 en vez de 28 (longitud en bytes de cada registro al usar el writeUtf) porque nos queremos posicionar
            // en el campo de borrado y no en el de id (que es el principio del registro).
            randomAccessFile.seek((posicion*28)-1);
            randomAccessFile.writeBoolean(b);
            retorno=true;

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return retorno;
    }

    public static void escribirPersonas6Atributos(ArrayList<Persona> personas6Atributos, String personaFile5) {
        long posicion=0;
        StringBuffer stringBuffer;
        try(RandomAccessFile randomAccessFile=new RandomAccessFile(personaFile5,"rw")){
            posicion=randomAccessFile.length();
            randomAccessFile.seek(posicion);
            for (Persona p:personas6Atributos) {
                randomAccessFile.writeLong(p.getId());

                stringBuffer=new StringBuffer(p.getNombre());
                stringBuffer.setLength(30);
                randomAccessFile.writeChars(stringBuffer.toString());

                //randomAccessFile.writeUTF(p.getNombre()); no se  debe de utilizar  writeUTF ya sea asociandole tamaño o sin asociarleselo
                // ya que no genera registros de igual tamaño de bytes para posteriormente poder recorrerlo

                stringBuffer=new StringBuffer(p.getDni());
                stringBuffer.setLength(9);
                randomAccessFile.writeChars(stringBuffer.toString());

                randomAccessFile.writeInt(p.getEdad());
                randomAccessFile.writeFloat(p.getSalario());
                randomAccessFile.writeBoolean(p.isBorrado());

            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void listarPersonas6Atributos(String personaFile5) {
        StringBuffer stringBuffer;
        try(RandomAccessFile randomAccessFile=new RandomAccessFile(personaFile5,"r")){
            while(true){
                System.out.println(randomAccessFile.getFilePointer());
                System.out.println("Id Persona "+randomAccessFile.readLong());
                stringBuffer=new StringBuffer();
                for (int i = 0; i < 30; i++) {
                    stringBuffer.append(randomAccessFile.readChar());

                }
                System.out.println("Nombre Persona "+stringBuffer.toString().trim());

                stringBuffer=new StringBuffer();
                for (int i = 0; i < 9; i++) {
                    stringBuffer.append(randomAccessFile.readChar());
                }
                System.out.println("DNI Persona "+stringBuffer.toString().trim());
                System.out.println("Edad Persona "+randomAccessFile.readInt());
                System.out.println("Salario Persona "+randomAccessFile.readFloat());
                System.out.println("Borrado "+randomAccessFile.readBoolean());
                System.out.println();
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch(EOFException e){

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    @Override
    public void escribirPersona(Persona persona, String ruta) {
        long posicion;
        try (RandomAccessFile randomAccessFile=new RandomAccessFile(ruta,"rw");){
            posicion=randomAccessFile.length();
            randomAccessFile.seek(posicion);
            randomAccessFile.writeLong(persona.getId());
            //randomAccessFile.writeUTF(persona.getDni());

            StringBuffer stringBuffer=new StringBuffer(persona.getDni());
            stringBuffer.setLength(10);
            randomAccessFile.writeChars(stringBuffer.toString());

            randomAccessFile.writeInt(persona.getEdad());
            randomAccessFile.writeFloat(persona.getSalario());
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Persona leerDatos(String ruta) {
        long id;
        String dni="";
        int edad;
        float salario;
        Persona p=null;
        StringBuffer stringBuffer=new StringBuffer();
        try(RandomAccessFile randomAccessFile=new RandomAccessFile(ruta,"r")){
            id=randomAccessFile.readLong();

            //dni=randomAccessFile.readUTF();
            for(int i=0;i<10;i++){
                stringBuffer.append(randomAccessFile.readChar());

            }


            edad= randomAccessFile.readInt();
            salario= randomAccessFile.readFloat();
            p=new Persona(id, stringBuffer.toString(),edad,salario);


        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (EOFException e){

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return p;
    }
   public Persona leerPersona(int posicion, String ruta){
        Persona persona;
        long id;
        int edad;
        float salario;
        StringBuffer stringBuffer=new StringBuffer();
        try(RandomAccessFile randomAccessFile=new RandomAccessFile(ruta,"r")){
            randomAccessFile.seek((36*posicion)-36);

            id=randomAccessFile.readLong();
            for(int i=0;i<10;i++){
                stringBuffer.append(randomAccessFile.readChar());
            }
            edad= randomAccessFile.readInt();
            salario= randomAccessFile.readFloat();
            persona=new Persona(id,stringBuffer.toString(),edad,salario);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
       return persona;
   }
}
