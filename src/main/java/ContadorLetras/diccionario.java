/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ContadorLetras;

import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 *
 * @author roman
 */
public class diccionario {
    
    
        
        public Map contador (String name){
        Map<String, Long> charCount = name.codePoints().mapToObj(Character::toString)
        .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
        return (charCount);
        }
}
