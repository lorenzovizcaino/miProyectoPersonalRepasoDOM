package ad.teis.persistencia;

import ad.teis.model.Persona;

import java.io.*;

public class DataIOPersistencia implements Ipersistencia {

    @Override
    public void escribirPersona(Persona persona, String ruta) {
        if(persona!=null){
            try(DataOutputStream dataOutputStream=new DataOutputStream(new FileOutputStream(ruta))){
                dataOutputStream.writeLong(persona.getId());
                dataOutputStream.writeChars(persona.getDni());
                dataOutputStream.writeUTF(persona.getDni());
                dataOutputStream.writeInt(persona.getEdad());
                dataOutputStream.writeFloat(persona.getSalario());
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

    }

    @Override
    public Persona leerDatos(String ruta) {
        Persona p;
        long id;
        String dni = "";
        StringBuilder stringBuilder=new StringBuilder();
        String dni2;
        int edad;
        float salario;
        try(DataInputStream dataInputStream=new DataInputStream(new FileInputStream(ruta))){
            id=dataInputStream.readLong();
            System.out.println("ID............ "+id);


            for(int i=0;i<9;i++){
                //ESTUDIAR StringBuilder y StringBuffer
                stringBuilder.append(dataInputStream.readChar());
            }
            System.out.println("D.N.I......... "+stringBuilder.toString());
            dni2=dataInputStream.readUTF();
            System.out.println("D.N.I......... "+dni2);
            edad=dataInputStream.readInt();
            System.out.println("Edad ......... "+edad);
            salario=dataInputStream.readFloat();
            System.out.println("Salario ...... "+salario);

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        p=new Persona(id,stringBuilder.toString(),edad,salario);
        return p;
    }

    @Override
    public Persona leerPersona(int posicion, String ruta) {
        return null;
    }
}
