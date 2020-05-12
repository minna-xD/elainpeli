package elainpeli;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Minna
 */
import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;

public class Peli {

    public static void main(String[] args) {

        Kayttoliittyma ui = new Kayttoliittyma(new Scanner(System.in), elaintenLuonti());
        
        ui.kaynnista();
    }
    
    private static HashMap<Integer, Elain> elaintenLuonti() {
        HashMap<Integer, Elain> elaimet = new HashMap<>();
        Elain sammakko = new Elain("Sammakko", 1);
        Elain ankka = new Elain("Ankka", 2);
        Elain possu = new Elain("Possu", 3);
        Elain lehma = new Elain("Lehmä", 4);
        Elain hevonen = new Elain("Hevonen", 5);
        elaimet.put(sammakko.getKoko(), sammakko);
        elaimet.put(ankka.getKoko(), ankka); 
        elaimet.put(possu.getKoko(), possu);
        elaimet.put(lehma.getKoko(), lehma); 
        elaimet.put(hevonen.getKoko(), hevonen);
        return elaimet;
    }
}
