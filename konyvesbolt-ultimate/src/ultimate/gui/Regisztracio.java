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

public class Regisztracio extends JDialog implements ActionListener  {

	private DAO dao = new DAO();
	
	private JTextField fNev_textfield = new JTextField();
	private JPasswordField pass_textfield = new JPasswordField();
        private JTextField email_textfield = new JTextField();
        private JTextField tn_textfield = new JTextField();
        private JSpinner irSzam = new JSpinner();
        private JTextField varos_textfield = new JTextField();
        private JTextField utca_textfield = new JTextField();
        private JSpinner hazSzam = new JSpinner();

	private JButton ok = new JButton("Ok");
        private JButton cancel = new JButton("Cancel");

    public Regisztracio() {
        //proba
        super();
        this.setTitle("Regisztráció");
        
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
        szemelyesPanel.setLayout(new GridLayout(7,2));
        
        szemelyesPanel.add(new JLabel("Személyes adatok"));
        szemelyesPanel.add(new JLabel(""));
        
        szemelyesPanel.add(new JSeparator());
        szemelyesPanel.add(new JSeparator());
        szemelyesPanel.add(new JLabel("  Felhasználó név:"));
    	szemelyesPanel.add(fNev_textfield);
    	
    	szemelyesPanel.add(new JLabel("  Jelszó:"));
    	szemelyesPanel.add(pass_textfield);
    	
    	szemelyesPanel.add(new JLabel("  Email:"));
    	szemelyesPanel.add(email_textfield);
    	
    	szemelyesPanel.add(new JLabel("  Teljes név:"));
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
        szallitasiAdatok.add(irSzam);
        
        szallitasiAdatok.add(new JLabel("  Város"));
    	szallitasiAdatok.add(varos_textfield);
        
        szallitasiAdatok.add(new JLabel("  Utca"));
    	szallitasiAdatok.add(utca_textfield);
        
        szallitasiAdatok.add(new JLabel("  Házszám"));
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
    		if(fNev_textfield.getText().isEmpty()){
    			JOptionPane.showMessageDialog(null,
            		    "Felhasználó név megadása kötelező!",
            		    "Hiba",
            		    JOptionPane.ERROR_MESSAGE);
    			return;
    		}
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
                if((Integer)irSzam.getValue() > 9999 || 0 >= (Integer)irSzam.getValue() ){
    			JOptionPane.showMessageDialog(null,
            		    "Irányítószám helytelen!",
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
                if((Integer)hazSzam.getValue() > 9999 || 0 >= (Integer)hazSzam.getValue() ){
    			JOptionPane.showMessageDialog(null,
            		    "Házszám helytelen!",
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
                if(dao.belepes(fNev_textfield.getText(),pass_textfield.getText()) != null){
                    JOptionPane.showMessageDialog(null,
            		    "Ilyen felhasználónév már létezik!\n Kérlek válassz másikat.",
            		    "Hiba",
            		    JOptionPane.ERROR_MESSAGE);
    			return;
                }
        	
             if(!dao.testFelhasznalo(fNev_textfield.getText(), pass_textfield.getText(),
                     email_textfield.getText(), tn_textfield.getText(), 2,
                     (Integer)irSzam.getValue(), varos_textfield.getText(), utca_textfield.getText(),
                     (Integer)hazSzam.getValue()))
            	JOptionPane.showMessageDialog(null,
            		    "Váratlan hiba történt az adatbázis feltöltése közben!",
            		    "Hiba",
            		    JOptionPane.ERROR_MESSAGE);
            else {
            	//Küldünk egy értesítő e-mailt a felhasználónak a sikeres felvételről
            	//Ehhez a javax.mail API-t haszáljuk, amely megvaósítása a mail.jar-ban található
            	//Mivel az e-mail küldés sokáig eltarthat, egy külön szálon indítjuk, a háttérben
            	if (!email_textfield.getText().isEmpty()) {
	            	Thread senMailThread = new Thread(new Runnable() {
	
						@Override
						public void run() {
							//SMTP kapcsolódási paraméterek
							String host = "smtp.gmail.com";
							int port = 465;
							String username = "konyvesbolt.ultimate";
							String password = "konyvesbolt.pass";
					 
							Properties props = new Properties();
							props.put("mail.smtps.auth", "true");
					 
							Session session = Session.getInstance(props);
							try {
								//Egy új e-mail üzenetet ábrázoló osztály
							    Message msg = new MimeMessage(session);
							    //Beállítjuk az e-mail feladóját
							    msg.setFrom(new InternetAddress("konyvesbolt.ultimate@gmail.com"));
								//Megadjuk az új ügyfél e-mail címét a levél címzettjeként
								msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(email_textfield.getText()));
								//Beállítjuk a levél tárgyát
								msg.setSubject("Konyvesbolt-ultiamte regisztrációs e-mail");
								//Illetve a levél tartalmát
								msg.setContent("Kedves " + tn_textfield.getText() + 
										",\nMostantól tagja vagy a Könyvesbolt-ultimate közösségének...\n\nKönyvesbolt-ultimate Team", "text/plain");
														  
								Transport t = session.getTransport("smtps");
                                try {
                                    t.connect(InetAddress.getByName(host).getCanonicalHostName(), port, username, password);
                                } catch (UnknownHostException ex) {
                                    Logger.getLogger(Regisztracio.class.getName()).log(Level.SEVERE, null, ex);
                                }
								//Elküldjük az üzenetet a property-ben megadott szerver segítségével
								t.sendMessage(msg, msg.getAllRecipients());
								t.close();
								JOptionPane.showMessageDialog(null,
                                                                    "Regisztrációs email elküldve!",
                                                                    "Információ",
                                                                    JOptionPane.INFORMATION_MESSAGE);
                        				} catch (NoSuchProviderException e) {
								JOptionPane.showMessageDialog(null,
                                                                    "Regisztrációs email-t nem sikerült elküldeni!\n Valószínűleg rossz email címet adtál meg.",
                                                                    "Hiba",
                                                                    JOptionPane.ERROR_MESSAGE);
							} catch (MessagingException e) {
								// Konzolon meg fog jelenni a stack trace, ha nem sikerül a küldés
								e.printStackTrace();				 
							}
						}            		
	            	});
	            	
	            	//Elindítjuk a szálat, amely a run metódust egy külön szálon végrehajtva elküldi az e-mailt
	            	senMailThread.start();
            	}
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
