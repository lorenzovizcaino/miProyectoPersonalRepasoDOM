/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package javaexceptions.exceptions;

/**
 *
 * @author maria
 */
public class NumeroNegativoException extends Exception {
    private int numero;

    public NumeroNegativoException(int num) {
        super("El numero debe ser mayor o igual que 0");
        this.numero = num;
    }
}
