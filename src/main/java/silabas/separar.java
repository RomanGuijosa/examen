/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package silabas;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

/**
 *
 * @author roman
 */


public class separar {
    

    String[][] conversiones = {
        {"ch", "@"}, {"ll", "#"}, {"gue", "%e"}, {"gui", "%i"},
        {"qu", "&"}, {"rr", "$"}, {"ya", "|a"}, {"ye", "|e"}, {"yi", "|i"}, {"yo", "|o"}, {"yu", "|u"}
    };
    char[] abiertas = {'a', 'e', 'o'};
    char[] cerradas = {'i', 'u', 'y'};
    Pattern patron_vocal_n_s = Pattern.compile(".*(a|e|i|o|u|n|s)");
    char enye = 'ñ';

    public char[] getVocales() {
        int size = abiertas.length + cerradas.length;
        char[] vocales = new char[size];
        int i = 0;
        for (int j = 0; j < abiertas.length; j++) {
            vocales[i] = abiertas[j];
            i++;
        }
        for (int j = 0; j < cerradas.length; j++) {
            vocales[i] = cerradas[j];
            i++;
        }
        
        return vocales;
    }

    public char[] getTierrasRaras() {
        int size = getVocales().length + 1;
        char[] raras = new char[size];
        for (int i = 0; i < getVocales().length; i++) {
            raras[i] = abiertas[i];
        }
        raras[size] = enye;
        return raras;
    }

    public Pattern getPattern() {
        StringBuffer regex = new StringBuffer(getTierrasRaras()[0]);
        for (int i = 1; i < getTierrasRaras().length; i++) {
            regex.append("|").append(getTierrasRaras()[i]);
        }
        return Pattern.compile(regex.toString());
    }

    public ArrayList<String> silabear(String w) {
        w = format(w);
        int corte;
        String silaba;
        ArrayList<String> silabas = new ArrayList<String>();
        while (w.length() > 0) {
            corte = next_s(w) + 1;
            silaba = unformat(w.substring(0, corte));
            w = w.substring(corte);
            silabas.add(silaba);
        }
        return silabas;
    }

    public int next_s(String w) {
        int hacheIntercalada = 0;
        char[] a = w.toCharArray();

        if (a[0] == 's' && a[1] == 'u' && a[2] == 'b' && a[3] == 'r') {
            return 2;
        }

        int vocal = 0;
        boolean found = false;
        while (vocal < a.length && !found) {
            found = esVocal(a[vocal]);
            if (!found) {
                vocal++;
            }
        }

        
        if (ultimaVocal(vocal, a)) {
            return w.length() - 1;
        }

        int l1 = vocal + 1;
        if (a[l1] == 'h') {
            l1++;
            hacheIntercalada = 1;
        }

       
        if (l1 + 1 == a.length) {
            if (esVocal(a[l1]) && isHiato(a[vocal], a[l1])) {
                return vocal;
            } else {
                return l1 + hacheIntercalada;
            }
        }

        int l2 = l1 + 1;
        if (a[l2] == 'h') {
            l2++;
            hacheIntercalada = 1;
        }
        if (esConsonante(a[l1]) && esVocal(a[l2])) 
        {
            return vocal;
        } else if (esConsonante(a[l1]) && esConsonante(a[l2])) 
        {
            String[] cc = {"tr", "gr", "pr", "br", "bl", "fr", "fl", "cl", "dr", "pl"};
            char[] tokenchar = {a[l1], a[l2]};
            String token = new String(tokenchar).toLowerCase();
            for (String s : cc) {
                if (s.equals(token)) {
                    return vocal;
                }
            }
            if ("ns".equals(token)) {
                if (a.length > l2 + 1 && esConsonante(a[l2 + 1])) {
                    return l2; 
                }
            }
            return l1 + hacheIntercalada; 
        } else if (esVocal(a[l1])) 
        {
            if (isHiato(a[vocal], a[l1])) {
                return vocal + hacheIntercalada;
            } else {
                return vocal + next_s(w.substring(l1)) + 1 + hacheIntercalada;
            }
        }
        return 0;
    }

    public boolean ultimaVocal(int vocal, char[] a) {
        for (int i = vocal + 1; i < a.length; i++) {
            if (esVocal(a[i])) {
                return false;
            }
        }
        return true;
    }

    public boolean isHiato(char v1, char v2) {
        
        
        for (char c1 : abiertas) {
            if (c1 == v1) {
                for (char c2 : abiertas) {
                    if (c2 == v2) {
                        return true;
                    }
                }
            }
        }
        
        return (v1 == v2);
    }

    public boolean esVocal(Character l) {
        for (char c : getVocales()) {
            if (Character.toLowerCase(l) == c) {
                return true;
            }
        }
        return false;
    }

    public boolean esConsonante(char l) {
        return (!esVocal(l));
    }

    public String format(String w) {
        if (w == null) {
            w = "";
        }
        for (int i = 0; i < conversiones.length; i++) {
            w = w.replace(conversiones[i][0], conversiones[i][1]);
        }
        
        if (w.startsWith("cacah")) {
            w = w.replace("h", "¬");
        }
        return w;
    }

    public String unformat(String w) {
        if (w == null) {
            w = "";
        }
        for (int i = 0; i < conversiones.length; i++) {
            w = w.replace(conversiones[i][1], conversiones[i][0]);
        }
        
        w = w.replace("¬", "h");
        return w;
    }

   
    public int tonica(List<String> silabas) {
        if (silabas.size() == 1) {
            return 0;
        }

        int ret = -1;
       

        if (ret != -1) {
            return ret;
        }

        
        String ultima = silabas.get(silabas.size() - 1);
        if (patron_vocal_n_s.matcher(ultima).matches()) {
            return silabas.size() - 2; 
        } else {
            return silabas.size() - 1; 
        }
    }

    public int vocalTonica(String silaba) {
        char[] letras = silaba.toLowerCase().toCharArray();
        int ret = -1, j = 0;
        Pattern patron_vocales_todas = Pattern.compile("a|e|i|o|u");
        Pattern patron_vocales_abiertas = Pattern.compile("a|e|o");

        
        for (int i = 0; i < letras.length; i++) {
            StringBuffer sb = new StringBuffer();
            sb.append(letras[i]);
            if (patron_vocales_todas.matcher(sb).matches()) {
                j++;
                ret = i;
            }
        }
        if (j == 1) {
            return ret;
        }

        
        ret = -1;
        for (int i = 0; i < letras.length; i++) {
            StringBuffer sb = new StringBuffer();
            sb.append(letras[i]);
            
        }
        if (ret != -1) {
            return ret;
        }

       
        ret = -1;
        for (int i = 0; i < letras.length; i++) {
            StringBuffer sb = new StringBuffer();
            sb.append(letras[i]);
            if (patron_vocales_abiertas.matcher(sb).matches()) {
                ret = i;
            }
        }
        return ret;
    }
}
