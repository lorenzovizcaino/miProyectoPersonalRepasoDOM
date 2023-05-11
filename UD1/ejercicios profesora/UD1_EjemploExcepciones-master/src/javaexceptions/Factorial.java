/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package javaexceptions;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import javaexceptions.exceptions.NumeroNegativoException;

/**
 *
 * @author maria
 */
public class Factorial {

    /***
     * 
     * @param n entero del que se calculará el factorial
     * @return el factorial del número entero como parámetro de entrada
     * @throws NumeroNegativoException 
     */
   private static long Factorial(int n) throws NumeroNegativoException {
        if (n <= 0) {
            throw new NumeroNegativoException(n);
        }
        int f = 1;
        for (int i = 1; i <= n; i++) {
            f *= i;
        }
        return f;
    }

    public static void main(String args[]) {
        int i;
        BufferedReader entrada = new BufferedReader(
                new InputStreamReader(System.in));
        System.out.println("Introduzca un número para calcular el factorial");
        String s;
        try {
            s = entrada.readLine();
//Convertir la entrada en entero
            i = Integer.parseInt(s);
            System.out.println("Factorial de " + i + ": " + Factorial(i));
        } catch (NumeroNegativoException e) {
            System.err.println(e.getMessage());
        } catch (IOException e) {
        } catch (NumberFormatException e) { //Se lanza la cadena s no se puede convertir a entero
            System.err.println("Formato erroneo");
        }
    }
}
