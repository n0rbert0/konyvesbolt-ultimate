/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ultimate.gui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
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
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import ultimate.konyvesbolt.Felhasznalo;
import ultimate.sql.DAO;

/**
 *
 * @author Peták Norbert
 */

public class Regisztracio extends JDialog implements ActionListener  {
    
	private static final long serialVersionUID = 2072911996817368496L;

	private DAO dao = new DAO();
	
	private JTextField fNev_textfield = new JTextField(50);
	private JTextField pass_textfield = new JTextField(50);
        private JTextField email_textfield = new JTextField(50);
        private JTextField tn_textfield = new JTextField(50);
        private JSpinner irSzam = new JSpinner();
        private JTextField varos_textfield = new JTextField(50);
        private JTextField utca_textfield = new JTextField(50);
        private JSpinner hazSzam = new JSpinner();

	private JButton ok = new JButton("Ok");
        private JButton cancel = new JButton("Cancel");

    public Regisztracio() {
        super();

        this.setTitle("Regisztracio");
        
        JPanel settingPanel = createSettingPanel();//A beállításokat tartalmazó panel gyártása
        JPanel buttonPanel = createButtonPanel();//A gombokat tartalmazó panel gyártása
        JPanel dialogPanel = createDialogPanel(settingPanel, buttonPanel); //Az előző két panelt egy panelre rakjuk 

        getContentPane().add(dialogPanel); //A dialogPanelt rárakjuk a dialógusra
        pack(); //A dialógus megfelelő méretének beállítása (a tartalmazott elemek alapján)
        setLocationRelativeTo(null); //A dialógust a BookShopGUI-hoz képest rajzolja ki
        setVisible(true);//Dialogus megjelenítése
    }
    
    private JPanel createSettingPanel(){
        JPanel settingPanel = new JPanel();
        //A panel elrendezése mátrix, 4 sor és 2 oszlop, a cellak egyforma meretuek
        settingPanel.setLayout(new GridLayout(8,2));

    	settingPanel.add(new JLabel("Felhasználó név:"));
    	settingPanel.add(fNev_textfield);
    	
    	settingPanel.add(new JLabel("Jelszó:"));
    	settingPanel.add(pass_textfield);
    	
    	settingPanel.add(new JLabel("Email:"));
    	settingPanel.add(email_textfield);
    	
    	settingPanel.add(new JLabel("Teljes név"));
    	settingPanel.add(tn_textfield);
        
        settingPanel.add(new JLabel("Irányítószám:"));
        settingPanel.add(irSzam);
        
        settingPanel.add(new JLabel("Város"));
    	settingPanel.add(varos_textfield);
        
        settingPanel.add(new JLabel("Utca"));
    	settingPanel.add(utca_textfield);
        
        settingPanel.add(new JLabel("Házszám"));
        settingPanel.add(hazSzam);
    	
    	return settingPanel;
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
            		    "Felhasználó név megadása köelező!",
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
        	Felhasznalo f = new Felhasznalo();
        	f.setF_id(dao.maxFelhasznaloId()+1);
                f.setF_nev(fNev_textfield.getText());
                f.setPass(pass_textfield.getText());
                f.setEmail(email_textfield.getText());
                f.setTeljesnev(tn_textfield.getText());
                f.setIrSzam((Integer)irSzam.getValue());
                f.setVaros(varos_textfield.getText());
                f.setUtca(utca_textfield.getText());
                f.setHazszam((Integer)hazSzam.getValue());
        	//Ha az addCustomer false-t ad vissza akkor egy hibaüzenetet írunk ki egy error dialogra(JOptionPane.ERROR_MESSAGE)
             if(!dao.addFelhasznalo(f))
            	JOptionPane.showMessageDialog(null,
            		    "Varatlan hiba tortent az adatbazis feltoltese kozben!",
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
								System.out.println("E-mail elkuldve: " + email_textfield.getText());
							} catch (NoSuchProviderException e) {
								// Konzolon meg fog jelenni a stack trace, ha nem sikerül a küldés
								e.printStackTrace();
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
    
}
