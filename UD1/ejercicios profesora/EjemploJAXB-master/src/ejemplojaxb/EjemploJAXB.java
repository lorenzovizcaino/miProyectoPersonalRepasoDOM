/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package ejemplojaxb;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import model.Libreria;
import model.Libro;

/**
 *
 * @author maria
 */
public class EjemploJAXB {
    
    
    private static Libreria crearLibreria(){
          // Lista de LIbros
        ArrayList<Libro> libroLista = new ArrayList<Libro>();
        // Creamos varios libros
        Libro libro1 = new Libro();
        libro1.setIsbn("978-0060554736");
        libro1.setNombre("The Game");
        libro1.setAutor("Neil Strauss");
        libro1.setEditorial("Harpercollins");
        
        
        libroLista.add(libro1);
        Libro libro2 = new Libro();
        libro2.setIsbn("978-3832180577");
        libro2.setNombre("Feuchtgebiete");
        libro2.setAutor("Charlotte Roche");
        libro2.setEditorial("Dumont Buchverlag");
        libroLista.add(libro2);
        
        
        // Se crea La libreria y se le asigna la lista de libros
        Libreria libreria = new Libreria();
        libreria.setNombre("Libreria sin limite");
        libreria.setLugar("Barrio Obrero");
        libreria.setListaLibro(libroLista);
        
        return libreria;
    }
    

    private static final String XML_FILE = "libreria-jaxb.xml";

    public static void main(String[] args) throws JAXBException, IOException {
        
        Libreria libreria = crearLibreria();
      
        // Creamos un contexto de la clase JAXB y lo intanciamos
        JAXBContext context = JAXBContext.newInstance(Libreria.class);
        
        Marshaller m = context.createMarshaller();
        m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
        
        // Lo creamos y mostramos en consola con system out
        m.marshal(libreria, System.out);
        
        // Lo creamos y escribimos en el archivo
        m.marshal(libreria, new File(XML_FILE));
        
        // Leemos el XML creado anteriormente  
     
        
        Unmarshaller um = context.createUnmarshaller();
        //Leemos el fichero recién creado:
        System.out.println("Leyendo fichero XML... " + XML_FILE);
        
        Libreria libreriaRecuperada = (Libreria) um.unmarshal(new FileReader(XML_FILE));
        
        ArrayList<Libro> lista = libreriaRecuperada.getListaLibro();
        for (Libro libro : lista) {
            System.out.println("Libro: " + libro.getNombre() + " de " + libro.getAutor());
        }
    }
    
}
