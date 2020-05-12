/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package elainpeli;

/**
 *
 * @author Minna
 */
public class Pelaaja implements Comparable<Pelaaja> {
    
    private String nimi;
    private int pisteet;
    
    public Pelaaja(String nimi) {
        this.nimi = nimi;
        this.pisteet = 0;
    }
    
    public String getNimi() {
        return this.nimi;
    }
    
    public int getPisteet() {
        return this.pisteet;
    }
    
    public void lisaaPisteita() {
        this.pisteet++;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        return hash;
    }

    @Override
    public int compareTo(Pelaaja pelaaja) {
        return  pelaaja.pisteet - this.pisteet;
    }
    
    
}
