/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ultimate.konyvesbolt;

/**
 *
 * @author Peták Norbert
 */
public class Felhasznalo {
    
    private int f_id;
    private String f_nev;
    private String pass;
    private String email;
    private String teljesnev;
    private int jog;
    
    private int irSzam;
    private String varos;
    private String utca;
    private int hazszam;

    public String getEmail() {
        return email;
    }

    public int getF_id() {
        return f_id;
    }

    public String getF_nev() {
        return f_nev;
    }

    public int getHazszam() {
        return hazszam;
    }

    public int getIrSzam() {
        return irSzam;
    }

    public int getJog() {
        return jog;
    }

    public String getPass() {
        return pass;
    }

    public String getTeljesnev() {
        return teljesnev;
    }

    public String getUtca() {
        return utca;
    }

    public String getVaros() {
        return varos;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setF_id(int f_id) {
        this.f_id = f_id;
    }

    public void setF_nev(String f_nev) {
        this.f_nev = f_nev;
    }

    public void setHazszam(int hazszam) {
        this.hazszam = hazszam;
    }

    public void setIrSzam(int irSzam) {
        this.irSzam = irSzam;
    }

    public void setJog(int jog) {
        this.jog = jog;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public void setTeljesnev(String teljesnev) {
        this.teljesnev = teljesnev;
    }

    public void setUtca(String utca) {
        this.utca = utca;
    }

    public void setVaros(String varos) {
        this.varos = varos;
    }

    @Override
    public String toString() {
        return " {F_ID=" + f_id + ", F_NEV=" + f_nev + ", PASS=" + pass + ", EMAIL=" + email +
                ", TELJESNEV=" + teljesnev + ", JOG=" + jog + ", IRSZAM=" + irSzam +
                ", VAROS=" + varos + ", UTCA=" + utca + ", HAZSZAM=" + hazszam + "}";
    }

    

}
