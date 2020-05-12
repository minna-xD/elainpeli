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
public class Elain {
    
    final private String nimi;
    final private int koko;
    private int osat;
    private boolean valmis;
    
    public Elain (String nimi, int koko) {
        this.nimi = nimi;
        this.koko = koko;
        this.osat = 0;
        this.valmis = false;
    }
    
    public String getNimi() {
        return this.nimi;
    }
    
    public int getOsat() {
        return this.osat;
    }
    
    public int getKoko() {
        return this.koko;
    }
    
    public boolean kasvata() {
        if (!this.valmis) {
            this.osat++;
            return true;
        }
        return false;
    }    
    
    public boolean valmistuiko() {
        if (this.koko == this.osat && this.valmis == false) {
            this.valmis = true;
            return true;
        } else {
            return false;
        }
    }
    
    public boolean valmis() {
        return this.valmis;
    }
}
