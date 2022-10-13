/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Project/Maven2/JavaApp/src/main/java/${packagePath}/${mainClassName}.java to edit this template
 */

package ContadorLetras;
import java.util.Arrays; 
import java.util.Map;
import java.util.Scanner;
import java.util.function.Function;
import java.util.stream.Collectors;


/**
 *
 * @author roman
 */
public class Contador {

    public static void main(String[] args) {
        Scanner teclado = new Scanner (System.in);
        String nombre;
        diccionario cont = new diccionario ();
        
        System.out.println("Digite una palabra: ");
        nombre = teclado.nextLine();
        nombre = nombre.toLowerCase();
        System.out.println(cont.contador(nombre));
        
        
        char tempArray[] = nombre.toCharArray();
        Arrays.sort(tempArray);
        
        System.out.println(tempArray);
          
    }
}
