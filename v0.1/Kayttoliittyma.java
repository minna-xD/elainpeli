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
import java.util.Random;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.HashMap;

public class Kayttoliittyma {

    private Scanner lukija;
    private HashMap<Integer, Elain> elaimet = new HashMap<>();
    private ArrayList<Pelaaja> pelaajat = new ArrayList<>();
    private int pelivuorossa;

    public Kayttoliittyma(Scanner lukija, HashMap<Integer, Elain> elaimet) {
        this.lukija = lukija;
        this.elaimet = elaimet;
        this.pelaajat = pelaajat;
        this.pelivuorossa = 0;
    }

    public void kaynnista() {
        pelaajienLisays();
        while (!peliLoppu()) {
            System.out.println("\nPelivuorossa " + pelaajat.get(pelivuorossa).nimi());
            System.out.println("1: Heitä noppaa\n" +
                               "2: Näytä pistetilanne");
            tulostaElainlista();
            System.out.print("> ");
            int valinta = Integer.valueOf(lukija.nextLine());
            switch(valinta) {
                case 1: nopanheitto(); break;
                case 2: pistetilanne(); break;
            }
            pelivuoro();
        }
        System.out.println("*~*~* " + voittaja() + " voitti! *~*~*");
        
    }

    private void pelaajienLisays() {
        /* Annetaan pelaajien nimet, max 4 */
        for (int i = 0; i < 4; i++) {
            System.out.println("Anna pelaajan nimi (paina Enter, jos haluat lopettaa):");
            String pelaaja = lukija.nextLine();
            if (pelaaja.isBlank()) {
                break;
            }
            pelaajat.add(new Pelaaja(pelaaja));
        }
    }

    private void nopanheitto() {
        /* Nopanheitto, nopan luku vastaa eläimen osien määrää. 6 = mikä vain */
        Random arpa = new Random();
        int luku = arpa.nextInt(6) + 1;
        switch (luku) {
            case 1: System.out.print(luku + " - Sammakko! ");
                    kasvataElainta(elaimet.get(luku));
                    break;
            case 2: System.out.print(luku + " - Ankka! ");
                    kasvataElainta(elaimet.get(luku));
                    break;
            case 3: System.out.print(luku + " - Possu! ");
                    kasvataElainta(elaimet.get(luku));
                    break;
            case 4: System.out.print(luku + " - Lehmä! ");
                    kasvataElainta(elaimet.get(luku));
                    break;
            case 5: System.out.print(luku + " - Hevonen! ");
                    kasvataElainta(elaimet.get(luku));
                    break;
            case 6: System.out.print(luku + " - Valitse mikä vain eläin!\n");
                    tulostaElainvalinta();
                    int valinta = Integer.valueOf(lukija.nextLine());
                    kasvataElainta(elaimet.get(valinta));
                    break;
        }
        System.out.println("");
    }
    private void pistetilanne() {
        // "piste(ttä)" nätimmäksi
        for (Pelaaja pelaaja : pelaajat) {
            System.out.println(pelaaja.nimi() + ": " + pelaaja.pisteet() + " piste(ttä)");
        }
        System.out.println("");
    }
    
    private void pelivuoro() {
        this.pelivuorossa++;
        if (this.pelivuorossa == pelaajat.size()) {
            this.pelivuorossa = 0;
        }
    }

    public void tulostaElainlista() {
        for (int luku : this.elaimet.keySet()) {
            System.out.println("   " + elaimet.get(luku).getOsat() 
                               + "/" 
                               + elaimet.get(luku).getKoko() 
                               + " " 
                               + elaimet.get(luku).getNimi()
                               );
        }
    }
    public void tulostaElainvalinta() {
        for (int luku : this.elaimet.keySet()) {
            System.out.println("   " + luku + ": " + elaimet.get(luku).getNimi() + "\t"
                               + elaimet.get(luku).getOsat() + "/" + elaimet.get(luku).getKoko());
        }
    }
    
    private void kasvataElainta(Elain elain) {
        if (elain.kasvata()) {
            System.out.print("Onnea! Sait pisteen.");
            pelaajat.get(this.pelivuorossa).lisaaPisteita();
        }
    }

    
    private boolean peliLoppu() {
        for (int i : elaimet.keySet()) {
            if (elaimet.get(i).onkoValmis() == false) {
                return false;
            }
        }
        return true;
    }
    
    
    // KORJAA ARRAYINDEXOUTOFBOUNDS
    private String voittaja() {
        int paras = pelaajat.get(0).pisteet();
        Pelaaja voittaja = pelaajat.get(0);
        for (Pelaaja pelaaja : pelaajat) {
            if (pelaaja.pisteet() > paras) {
                paras = pelaaja.pisteet();
                voittaja = pelaaja;
            }
        }
        return voittaja.nimi();
    }
}
