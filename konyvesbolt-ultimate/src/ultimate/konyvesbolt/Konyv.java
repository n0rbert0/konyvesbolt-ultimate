/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ultimate.konyvesbolt;

/**
 *
 * @author Gere
 */
public class Konyv {
    
     
    private String szerzo;
    private String cim;
    private int isbn;
    private String mufaj;
    private int ar;     //ára
    private int db;
    private String kep;
    private String leiras;

    //Getters: 
    
     public int getDb() {
        return db;
    }

    public int getAr() {
        return ar;
    }

    public String getCim() {
        return cim;
    }

    public int getIsbn() {
        return isbn;
    }

    public String getMufaj() {
        return mufaj;
    }

    public String getSzerzo() {
        return szerzo;
    }

    public String getKep() {
        return kep;
    }

    public String getLeiras() {
        return leiras;
    }
    
    //Setters: 

    public void setDb(int db) {
        this.db = db;
    }
    
    public void setAr(int ar) {
        this.ar = ar;
    }

    public void setCim(String cim) {
        this.cim = cim;
    }

    public void setIsbn(int isbn) {
        this.isbn = isbn;
    }

    public void setMufaj(String mufaj) {
        this.mufaj = mufaj;
    }

    public void setSzerzo(String szerzo) {
        this.szerzo = szerzo;
    }

    public void setKep(String kep) {
        this.kep = kep;
    }

    public void setLeiras(String leiras) {
        this.leiras = leiras;
    }

    @Override
    public String toString() {
        return " {SZERZO=" + szerzo + ", CIM=" + cim + ", ISBN=" + isbn + ", MUFAJ=" + mufaj + ", AR=" + ar + ", DB=" + db + ", KEP=" + kep + ", LEIRAS=" + leiras +"}\n";
    }

   
    
}//class
