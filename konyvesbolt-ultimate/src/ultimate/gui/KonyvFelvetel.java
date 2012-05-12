/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ultimate.gui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import ultimate.konyvesbolt.Konyv;
import ultimate.sql.DAO;

/**
 *
 * @author Peták Norbert
 */
public class KonyvFelvetel extends JDialog implements ActionListener {

	private DAO dao = new DAO();
	
	private JTextField szerzo_textfield = new JTextField();
	private JTextField cim_textfield = new JTextField();
        private JTextField mufaj_textfield = new JTextField();
        private JSpinner ar = new JSpinner();
        private JSpinner db = new JSpinner();

	private JButton ok = new JButton("Ok");
        private JButton cancel = new JButton("Cancel");

    public KonyvFelvetel() {
        //proba
        super();
        this.setTitle("Könyv hozzáadása");
        
        JPanel settingPanel = createSettingPanel();//A beállításokat tartalmazó panel gyártása
        JPanel buttonPanel = createButtonPanel();//A gombokat tartalmazó panel gyártása
        JPanel dialogPanel = createDialogPanel(settingPanel, buttonPanel); //Az előző két panelt egy panelre rakjuk 
        getContentPane().add(dialogPanel); //A dialogPanelt rárakjuk a dialógusra
        pack(); //A dialógus megfelelő méretének beállítása (a tartalmazott elemek alapján)
        setSize(400,250);
        setLocationRelativeTo(null); //A dialógust a BookShopGUI-hoz képest rajzolja ki
        setVisible(true);//Dialogus megjelenítése
    }
    
    private JPanel createSettingPanel(){
        JPanel settingPanel = new JPanel();
        settingPanel.setLayout(new GridLayout(6,1));
        
        settingPanel.add(new JLabel("  Szerző:"));
    	settingPanel.add(szerzo_textfield);
    	
    	settingPanel.add(new JLabel("  Cím:"));
    	settingPanel.add(cim_textfield);
    		
    	settingPanel.add(new JLabel("  Műfaj:"));
    	settingPanel.add(mufaj_textfield);
        
        settingPanel.add(new JLabel("  Ár:"));
        settingPanel.add(ar);
        
        settingPanel.add(new JLabel("  Darabszám:"));
        settingPanel.add(db);

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
    
    @Override
  public void actionPerformed(ActionEvent e) {
        //Ha az ok gombot nyomták meg, akkor megpróbáljuk felvenni az ügyfelet
    	if(ok == e.getSource()) {
        	//Ha nem adtak meg nevet akkor egy hibaüzenetet írunk ki egy error dialogra(JOptionPane.ERROR_MESSAGE)
    		if(szerzo_textfield.getText().isEmpty()){
    			JOptionPane.showMessageDialog(null,
            		    "Add meg a szerző nevét!",
            		    "Hiba",
            		    JOptionPane.ERROR_MESSAGE);
    			return;
    		}
    		if(cim_textfield.getText().isEmpty()){
    			JOptionPane.showMessageDialog(null,
            		    "Add meg a könyv címét!",
            		    "Hiba",
            		    JOptionPane.ERROR_MESSAGE);
    			return;
    		}
                if(mufaj_textfield.getText().isEmpty()){
    			JOptionPane.showMessageDialog(null,
            		    "Add meg a könyv műfaját!",
            		    "Hiba",
            		    JOptionPane.ERROR_MESSAGE);
    			return;
    		}
                if(ar.getValue() == null){
    			JOptionPane.showMessageDialog(null,
            		    "Irányítószám megadása kötelező!",
            		    "Hiba",
            		    JOptionPane.ERROR_MESSAGE);
    			return;
    		}
                if(db.getValue() == null){
    			JOptionPane.showMessageDialog(null,
            		    "Irányítószám megadása kötelező!",
            		    "Hiba",
            		    JOptionPane.ERROR_MESSAGE);
    			return;
    		}
                
        	/*
                 * Valamiért így csak a könyv táblát tölti fel
                 * Konyv k = new Konyv();
        	k.setIsbn(dao.maxKonyvIsbn()+1);
                k.setSzerzo(szerzo_textfield.getText());
                k.setCim(cim_textfield.getText());
                k.setMufaj(mufaj_textfield.getText());
                k.setAr((Integer)ar.getValue());
                k.setDb((Integer)db.getValue());*/
             if(!dao.testKonyv(cim_textfield.getText(), (Integer)ar.getValue(),
                     (Integer)db.getValue(), mufaj_textfield.getText(), szerzo_textfield.getText()))
            	JOptionPane.showMessageDialog(null,
            		    "Váratlan hiba történt az adatbázis feltöltése közben!",
            		    "Hiba",
            		    JOptionPane.ERROR_MESSAGE);
             setVisible(false);
        }
        else if(cancel == e.getSource())
            setVisible(false);
    }
}
