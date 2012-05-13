/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ultimate.gui;

import java.awt.*;
import java.awt.event.*;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.*;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.NoSuchProviderException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import ultimate.konyvesbolt.Felhasznalo;
import ultimate.sql.DAO;

/**
 *
 * @author Peták Norbert
 */

public class FelhasznaloUpdate extends JDialog implements ActionListener  {

	private DAO dao = new DAO();
	private Felhasznalo f;
        private JTextField fTipus_textfield = new JTextField();
	private JTextField fNev_textfield = new JTextField();
	private JPasswordField pass_textfield = new JPasswordField();
        private JTextField email_textfield = new JTextField();
        private JTextField tn_textfield = new JTextField();
        private JSpinner irSzam = new JSpinner();
        private JSpinner sorszam = new JSpinner();
        private JTextField varos_textfield = new JTextField();
        private JTextField utca_textfield = new JTextField();
        private JSpinner hazSzam = new JSpinner();

	private JButton ok = new JButton("Ok");
        private JButton cancel = new JButton("Cancel");

    public FelhasznaloUpdate(Felhasznalo f) {
        //proba
        super();
        this.setTitle("Adatok módosítása");
        this.f=f;
        
        JPanel settingPanel = createSettingPanel();//A beállításokat tartalmazó panel gyártása
        JPanel buttonPanel = createButtonPanel();//A gombokat tartalmazó panel gyártása
        JPanel dialogPanel = createDialogPanel(settingPanel, buttonPanel); //Az előző két panelt egy panelre rakjuk 
        getContentPane().add(dialogPanel); //A dialogPanelt rárakjuk a dialógusra
        pack(); //A dialógus megfelelő méretének beállítása (a tartalmazott elemek alapján)
        setSize(400,500);
        setLocationRelativeTo(null); //A dialógust a BookShopGUI-hoz képest rajzolja ki
        setVisible(true);//Dialogus megjelenítése
    }
    
    private JPanel createSettingPanel(){
        JPanel settingPanel = new JPanel();
        settingPanel.setLayout(new GridLayout(2,1));
        
        settingPanel.add(createSzemelyesAdatok());
        settingPanel.add(createSzallitasiAdatok());

    	return settingPanel;
    }
    
     private JPanel createSzemelyesAdatok(){ 

        JPanel szemelyesPanel = new JPanel();
        szemelyesPanel.setLayout(new GridLayout(9,2));
        
        szemelyesPanel.add(new JLabel("Személyes adatok"));
        szemelyesPanel.add(new JLabel(""));
        
        szemelyesPanel.add(new JSeparator());
        szemelyesPanel.add(new JSeparator());
        
        szemelyesPanel.add(new JLabel("  Felhasználó sorszám:"));
        sorszam.setValue(f.getF_id());
        sorszam.setEnabled(false);
        szemelyesPanel.add(sorszam);
        
        szemelyesPanel.add(new JLabel("  Felhasználó típus:"));
        if(f.getJog()==1){
           fTipus_textfield.setText("Admin");
           fTipus_textfield.setEnabled(false);
           szemelyesPanel.add(fTipus_textfield);      
        }else{
           fTipus_textfield.setText("Általános felhasználó");
           fTipus_textfield.setEnabled(false);
           szemelyesPanel.add(fTipus_textfield);
        }
        
        szemelyesPanel.add(new JLabel("  Felhasználó név:"));
        fNev_textfield.setText(f.getF_nev());
        fNev_textfield.setEnabled(false);
    	szemelyesPanel.add(fNev_textfield);
    	
    	szemelyesPanel.add(new JLabel("  Jelszó:"));
        pass_textfield.setText(f.getPass());
    	szemelyesPanel.add(pass_textfield);
    	
    	szemelyesPanel.add(new JLabel("  Email:"));
        email_textfield.setText(f.getEmail());
    	szemelyesPanel.add(email_textfield);
    	
    	szemelyesPanel.add(new JLabel("  Teljes név:"));
        tn_textfield.setText(f.getTeljesnev());
    	szemelyesPanel.add(tn_textfield);
        
        return szemelyesPanel;
     }
     
     private JPanel createSzallitasiAdatok(){
        JPanel szallitasiAdatok = new JPanel();
        szallitasiAdatok.setLayout(new GridLayout(7,2));
        
        szallitasiAdatok.add(new JLabel("Szallítási adatok"));
        szallitasiAdatok.add(new JLabel(""));
        
        szallitasiAdatok.add(new JSeparator());
        szallitasiAdatok.add(new JSeparator());
        
        szallitasiAdatok.add(new JLabel("  Irányítószám:"));
        irSzam.setValue(f.getIrSzam());
        szallitasiAdatok.add(irSzam);
        
        szallitasiAdatok.add(new JLabel("  Város"));
        varos_textfield.setText(f.getVaros());
    	szallitasiAdatok.add(varos_textfield);
        
        szallitasiAdatok.add(new JLabel("  Utca"));
        utca_textfield.setText(f.getUtca());
    	szallitasiAdatok.add(utca_textfield);
        
        szallitasiAdatok.add(new JLabel("  Házszám"));
        hazSzam.setValue(f.getHazszam());
        szallitasiAdatok.add(hazSzam);

        return szallitasiAdatok;
     }
     
    
    private JPanel createButtonPanel(){
    	JPanel buttonPanel = new JPanel();
        //A panel elrendezése folytonos, középre igazítva
        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        //Hozzáadjuk az ok gombot, és figyelünk rá
        buttonPanel.add(ok);        
        ok.addActionListener(this);
        //Hozzáadjuk a cancel gombot, és figyelünk rá
        buttonPanel.add(cancel);        
        cancel.addActionListener(this);
        return buttonPanel;
    }

    private JPanel createDialogPanel(JPanel settingPanel, JPanel buttonPanel){
        JPanel dialogPanel = new JPanel();
        //A panel elrendezése BorderLayout
        dialogPanel.setLayout(new BorderLayout());
        //Középen lesz a settingPanel
        dialogPanel.add(settingPanel, BorderLayout.CENTER);
        //Alul pedig a gombok
        dialogPanel.add(buttonPanel, BorderLayout.SOUTH);
        return dialogPanel;
    }
    
  public void actionPerformed(ActionEvent e) {
        //Ha az ok gombot nyomták meg, akkor megpróbáljuk felvenni az ügyfelet
    	if(ok == e.getSource()) {
        	//Ha nem adtak meg nevet akkor egy hibaüzenetet írunk ki egy error dialogra(JOptionPane.ERROR_MESSAGE)
    		if(pass_textfield.getText().isEmpty()){
    			JOptionPane.showMessageDialog(null,
            		    "Jelszó megadása kötelező!",
            		    "Hiba",
            		    JOptionPane.ERROR_MESSAGE);
    			return;
    		}
                if(email_textfield.getText().isEmpty()){
    			JOptionPane.showMessageDialog(null,
            		    "Email cím megadása kötlező!",
            		    "Hiba",
            		    JOptionPane.ERROR_MESSAGE);
    			return;
    		}
                if(tn_textfield.getText().isEmpty()){
    			JOptionPane.showMessageDialog(null,
            		    "Teljes név megadása kötelező!",
            		    "Hiba",
            		    JOptionPane.ERROR_MESSAGE);
    			return;
    		}
                if(irSzam.getValue() == null){
    			JOptionPane.showMessageDialog(null,
            		    "Irányítószám megadása kötelező!",
            		    "Hiba",
            		    JOptionPane.ERROR_MESSAGE);
    			return;
    		}
                if(varos_textfield.getText().isEmpty()){
    			JOptionPane.showMessageDialog(null,
            		    "Város megadása kötelező!",
            		    "Hiba",
            		    JOptionPane.ERROR_MESSAGE);
    			return;
    		}
                if(utca_textfield.getText().isEmpty()){
    			JOptionPane.showMessageDialog(null,
            		    "Utca megadása kötelező!",
            		    "Hiba",
            		    JOptionPane.ERROR_MESSAGE);
    			return;
    		}
                if(hazSzam.getValue() == null){
    			JOptionPane.showMessageDialog(null,
            		    "Irányítószám megadása kötelező!",
            		    "Hiba",
            		    JOptionPane.ERROR_MESSAGE);
    			return;
    		}
                if(!isValidEmailAddress(email_textfield.getText())){
                    JOptionPane.showMessageDialog(null,
            		    "Valós email címet adj meg!",
            		    "Hiba",
            		    JOptionPane.ERROR_MESSAGE);
    			return;
                }
                /*if(dao.belepes(fNev_textfield.getText(),pass_textfield.getText()) != null){
                    JOptionPane.showMessageDialog(null,
            		    "Helytelen jelszó!\n Kérlek válassz másikat.",
            		    "Hiba",
            		    JOptionPane.ERROR_MESSAGE);
    			return;
                }*/
        	
                Felhasznalo felhasznalo = new Felhasznalo();
                felhasznalo.setF_id((Integer)sorszam.getValue());
                felhasznalo.setF_nev(fNev_textfield.getText());
                felhasznalo.setPass(pass_textfield.getText());
                felhasznalo.setEmail(email_textfield.getText());
                felhasznalo.setTeljesnev(tn_textfield.getText());
                felhasznalo.setJog(f.getJog());
                felhasznalo.setIrSzam((Integer)irSzam.getValue());
                felhasznalo.setVaros(varos_textfield.getText());
                felhasznalo.setUtca(utca_textfield.getText());
                felhasznalo.setHazszam((Integer)hazSzam.getValue());
                
             if(!dao.updateFelhasznalo(felhasznalo))
            	JOptionPane.showMessageDialog(null,
            		    "Váratlan hiba történt az adatbázis feltöltése közben!",
            		    "Hiba",
            		    JOptionPane.ERROR_MESSAGE);
            else {
            	JOptionPane.showMessageDialog(null,
            		    "Sikeres adatmódosítás!",
            		    "Információ",
            		    JOptionPane.INFORMATION_MESSAGE);
            	setVisible(false);
            }
        }
        else if(cancel == e.getSource())
            setVisible(false);
    }	
  
        public static boolean isValidEmailAddress(String email) {
            boolean result = true;
            try {
                InternetAddress emailAddr = new InternetAddress(email);
                emailAddr.validate();
            } catch (AddressException ex) {
                result = false;
            }
                 return result;
            }
    
}
