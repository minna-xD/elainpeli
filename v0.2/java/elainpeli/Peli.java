/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package elainpeli;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Random;

/**
 *
 * @author Minna
 */
public class Peli {

    private HashMap<Integer, Elain> elaimet;
    private ArrayList<Pelaaja> pelaajat;
    private int pelivuoro;

    public Peli() {
        pelivuoro = 0;
        pelaajat = new ArrayList<>();
        elaimet = new HashMap<>();
        this.elaintenLuonti();
    }

    private void elaintenLuonti() {
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
    }

    public Pelaaja lisaaPelaaja(String nimi) {
        Pelaaja pelaaja = new Pelaaja(nimi);
        pelaajat.add(pelaaja);
        return pelaaja;
    }

    public ArrayList<String> pistetilanne() {
        ArrayList<String> tilanne = new ArrayList<>();
        for (Pelaaja pelaaja : pelaajat) {
            String paate = " pistettä";
            if (pelaaja.getPisteet() == 1) {
                paate = " piste";
            }
            tilanne.add(pelaaja.getNimi() + ": " + pelaaja.getPisteet() + paate);
        }
        return tilanne;
    }

    public int nopanHeitto() {
        Random arpa = new Random();
        int luku = arpa.nextInt(6) + 1;
        
        return luku;
    }

    private int pelaajaIndeksiVuorossa() {
        return pelivuoro % pelaajat.size();
    }
    
    public String pelaajaVuorossa() {
        return pelaajat.get(pelaajaIndeksiVuorossa()).getNimi();
    }
    
    public void seuraavaPelaaja() {
        pelivuoro++;
    }

    public boolean peliLoppu() {
        int valmiita = 0;
        for (int i : elaimet.keySet()) {
            if (elaimet.get(i).valmis()) {
                valmiita++;
            }
        }
        if (valmiita == 5) {
            return true;
        } else {
            return false;
        }
    }

    public String voittaja() {
        Collections.sort(pelaajat);
        return pelaajat.get(0).getNimi();
    }

    public ArrayList<Pelaaja> getPelaajat() {
        return this.pelaajat;
    }

    public boolean elaimenValinta(int i) {
        return elaimet.get(i).kasvata();
    }

    public int elaimenKoko(int i) {
        return elaimet.get(i).getKoko();
    }

    public int elaimenOsat(int i) {
        return elaimet.get(i).getOsat();
    }

    public boolean elainValmistui(int i) {
        return elaimet.get(i).valmistuiko();
    }
    
    public void pelaajallePiste() {
        pelaajat.get(pelaajaIndeksiVuorossa()).lisaaPisteita();
    }
}
