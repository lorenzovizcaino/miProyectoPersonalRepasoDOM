package repaso.config;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class Config {
    private static final String SEPARATOR="=";
    public static void  crearConfigFile(HashMap<String, String> mapa, String rutaFichero){
        try (
                FileWriter fileWriter=new FileWriter((rutaFichero));
                BufferedWriter bufferedWriter=new BufferedWriter(fileWriter);
        ){
         for (Map.Entry<String, String> entry: mapa.entrySet()){
             String key= entry.getKey();
             String val= entry.getValue();

             bufferedWriter.write(key+SEPARATOR+val);
             bufferedWriter.newLine();
         }

        } catch (IOException e) {
            System.out.println("Ha ocurrido una excepcion" + e.getMessage());
        }

    }
    public static void leerConfig2(String config_File, String texto) {
        String linea;
        String array[];
        boolean bandera=true;

        try(
                FileReader fileReader=new FileReader(config_File);
                BufferedReader bufferedReader=new BufferedReader(fileReader);
        ){
            while((linea=bufferedReader.readLine())!=null) {
                array=linea.split(SEPARATOR);
                if (array[0].equalsIgnoreCase(texto)){
                    System.out.println(array[0]+" = "+ array[1]);
                    bandera=false;
                    break;
                }
            }
            if(bandera){
                System.out.println("CLAVE   "+"VALOR");
                System.out.println("null");
            }

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void leerConfig(String config_file, String texto) {
        HashMap<String, String> map = new HashMap<>();
        String linea, clave="", valor="";
        char caracter;
        boolean bandera=true;
        try (
                FileReader fileReader=new FileReader(config_file);
                BufferedReader bufferedReader=new BufferedReader(fileReader);
        ){
            while((linea=bufferedReader.readLine())!=null){
                for(int i=0;i<linea.length();i++){
                    caracter=linea.charAt(i);
                    if(bandera){
                        if (caracter=='='){
                            bandera=false;
                            //i++;
                        }else{
                            clave+=caracter;
                        }
                    }else{
                        valor+=caracter;
                    }
                }
                map.put(clave,valor);
                bandera=true;
                clave="";
                valor="";
            }
            bandera=true;//inicializamos la bandera a true para utilizar el boolean en otra mision
            //diferente a la que estaba hasta aqui
            for(Map.Entry<String,String> entry: map.entrySet()){
                if(entry.getKey().equalsIgnoreCase(texto)){
                    System.out.println("CLAVE   "+"VALOR");
                    System.out.println(entry.getKey()+"="+entry.getValue());
                    bandera=false;
                }
            }
            if(bandera){
                System.out.println("CLAVE   "+"VALOR");
                System.out.println("null");
            }
        }catch(IOException e){
            System.out.println(e.getMessage());
        }
    }
}
