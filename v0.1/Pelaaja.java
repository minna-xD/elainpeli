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
public class Pelaaja {
    private String nimi;
    private int pisteet;
    
    public Pelaaja(String nimi) {
        this.nimi = nimi;
        this.pisteet = 0;
    }
    
    public void lisaaPisteita() {
        this.pisteet++;
    }
    public int pisteet() {
        return this.pisteet;
    }
    public String nimi() {
        return this.nimi;
    }
}
