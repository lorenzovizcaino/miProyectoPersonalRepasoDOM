/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.util.ArrayList;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;


/**
 *
 * @author maria
 */
@XmlRootElement(namespace = "http://www.milibreria.com", name = "bookshop")
@XmlAccessorType(XmlAccessType.PUBLIC_MEMBER)
public class Libreria {

    private ArrayList<Libro> ListaLibro;

    private String nombre;

    private String lugar;

    public ArrayList<Libro> getListaLibro() {
        return ListaLibro;
    }

    @XmlElementWrapper(name = "listaLibro")
    @XmlElement(name = "libro")
    public void setListaLibro(ArrayList<Libro> ListaLibro) {
        this.ListaLibro = ListaLibro;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setLugar(String lugar) {
        this.lugar = lugar;
    }

    public String getLugar() {
        return lugar;
    }

    public String getNombre() {
        return nombre;
    }

}
