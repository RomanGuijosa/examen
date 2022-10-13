/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package contrasena;

import java.util.Scanner;

/**
 *
 * @author roman
 */
public class main {
    public static void main(String[] args) {
        String entrada;
        
        char[ ] signos= {'*', '_', '-', '¿', '¡', '?', '#', '$' };
        
        int mayus=0;
        int numeros=0;
        int caracter=0;
        int letras=0;
        int espacio=0;
        int sig=0;
        int contadornumero=0;
        int total=0;
        
        String cont_mayus="";
        String cont_numeros="";
        String cont_caracter="";
        String cont_espacio="";
        String cont_sig="";
        
        Scanner teclado = new Scanner (System.in);
        
        
        System.out.println("Ingrese contraseña nueva");
        entrada = teclado.nextLine();
        
        for (int i = 0; i < entrada.length(); i++) {
            total++;
            for (int j = 0; j < signos.length; j++) {
                if (entrada.charAt(i) == signos [j]) {
                    sig++;
                }
            }
            if (Character.isSpaceChar(entrada.charAt(i))) {
                espacio++;
            }
            if (Character.isLetter(entrada.charAt(i))) {
                letras++;
            }
            
            
            if (Character.isUpperCase(entrada.charAt(i))) {
                mayus++;
                mayus+=entrada.charAt(i);
            }
            if (Character.isDigit(entrada.charAt(i))) {
                numeros++;
                cont_numeros+=entrada.charAt(i);
                for (int j = i; j < cont_numeros.length(); j++) {
                    if (entrada.charAt(i)==entrada.charAt(j)) {
                     contadornumero++;
                        if (contadornumero>=1) {
                            break;
                        }
                    }
                }
            }
            
        }
       
        
        if (total<7) {
            System.out.println("LA CONTRASENA DEBE CONTENER ENTRE 8 Y 15 CARACTERES");
        }
        if (total>15) {
            System.out.println("LA CONTRASENA DEBE CONTENER ENTRE 8 Y 15 CARACTERES");
        }
        if (espacio>0) {
            System.out.println("NO DEBE CONTENER ESPACIOS");
        }
        if (mayus<2) {
            System.out.println("LA CONTRASENA DEBE CONTENER AL MENOS 2 MAYUSCULAS");
        }
        if (sig<1) {
            System.out.println("LA CONTRASENA DEBE CONTENER AL MENOS 1 CARACTER DE LOS SIGUIENTES: (* _ - ¿ ¡ ? # $) ");
        }
        if (contadornumero>=2) {
            System.out.println("LA CONTRASENA NO DEBE CONTENER NUMEROS REPETIDOS");
        }
        if (numeros<3) {
            System.out.println("LA CONTRASENA DEBE CONTENER AL MENOS 3 NUMEROS");
        }
        
       
        
    
        
        
    }
}
