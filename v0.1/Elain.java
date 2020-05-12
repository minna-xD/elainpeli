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
public class Elain {
    
    private String nimi;
    private int koko;
    private int osia;
    private boolean valmis;
    
    public Elain(String nimi, int koko) {
        this.nimi = nimi;
        this.koko = koko;
        this.osia = 0;
        this.valmis = false;
    }
    
    public String getNimi() {
        return this.nimi;
    }
    public int getKoko() {
        return this.koko;
    }
    public int getOsat() {
        return this.osia;
    }
    public boolean onkoValmis() {
        return valmis;
    }
    public boolean tulikoValmiiksi() {
        if (this.valmis == false && this.osia == this.koko) {
            this.valmis = true;
            return true;
        } else {
            return false;
        }
    }
    
    public boolean kasvata() {
        if (this.valmis == true) {
            System.out.println("Eläin on jo valmis. Ei pisteitä!");
            return false;
        }
        if (this.osia < this.koko) {
            this.osia++;
            return tulikoValmiiksi();
        } else {
            return false;
        }        
    }
  
    @Override
    public String toString() {
        // Piirretään eläimen osat JATKA
        if (this.osia == 0 ) {
            return "";
        }
        String keho = "";
        for (int i = 0; i < this.osia-1; i++) {
            keho += "\n" + piirraKeho();
        }
        return piirraPaa() + keho;
        }
    
    private String piirraPaa() {
        return "**    **\n" +
               "********\n" +
               "* O  O *\n" +
               "*  ..  *\n" +
               "*  __  *\n" +
               "********";

    }
    private String piirraKeho() {
        return "********\n" +
               "*      *\n" +
               "*      *\n" +
               "********";
        
    }
    
}
