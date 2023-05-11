/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package javaexceptions;

/**
 *
 * @author maria
 */
public class MainArithExcTryCatch {

    public static void main(String args[]) {
        int d, a;
        try {
            d = 0;
            a = 42 / d;
            System.out.println("Mensaje.");

        } catch (ArithmeticException ae) {
            System.out.println("División por cero");
        }
    }
}
