/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package silabas;

import java.util.Scanner;

/**
 *
 * @author roman
 */
public class main {
 
    public static void main(String[] args) {
        Scanner teclado = new Scanner (System.in);
        separar sep = new separar ();
  
        System.out.println("Ingrese una palabra porfavor");    
        String a = teclado.nextLine();
        System.out.println(sep.silabear(a));
    }
}
