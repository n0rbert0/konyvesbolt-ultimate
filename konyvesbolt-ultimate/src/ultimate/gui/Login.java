/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ultimate.gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;
import javax.swing.*;
import javax.swing.border.LineBorder;
import ultimate.konyvesbolt.Konyv;
import ultimate.sql.DAO;

/**
 *
 * @author lorda
 */
public class Login extends JPanel implements ActionListener{
    
    private DAO dao = new DAO();
    
    JTextField kereses;
    JTextField login;
    JPasswordField pass;
    
    private JButton belepes;
    private JButton keres;
    private JButton reg;
    
    Login()
    {
    JPanel fal = new JPanel(new BorderLayout());
    //fal.setBorder(LineBorder.createBlackLineBorder());
    fal.setPreferredSize(new Dimension(1150,35));
    kereses = new JTextField("keresés");
    kereses.setPreferredSize(new Dimension(500,30));
    
    keres = new JButton("Keres");
    keres.addActionListener(this);
    
    reg = new JButton("Regisztráció");
    reg.addActionListener(this);
    
    login = new JTextField("login");
    login.setPreferredSize(new Dimension(150,30));
    pass = new JPasswordField();
    pass.setPreferredSize(new Dimension(150,30));
    
    belepes = new JButton("Belépés");
    belepes.addActionListener(this);
    
    JPanel falacska = new JPanel(new BorderLayout());
    JPanel falacska2 = new JPanel(new BorderLayout());
    
    falacska.add(kereses, BorderLayout.WEST);
    falacska.add(keres, BorderLayout.CENTER);
    falacska.add(reg,BorderLayout.EAST);
    
    falacska2.add(login, BorderLayout.WEST);
    falacska2.add(pass, BorderLayout.CENTER);    
    falacska2.add(belepes, BorderLayout.EAST);
    fal.add(falacska, BorderLayout.WEST);
    fal.add(falacska2, BorderLayout.EAST);
    add(fal);
    }
    
    public void actionPerformed(ActionEvent e) {
    
        if(reg == e.getSource()) {
          new Regisztracio();
        }
        if(belepes == e.getSource()){
            if((!pass.getText().isEmpty()) && (!login.getText().isEmpty()) ){
    		if(dao.belepes(login.getText(), pass.getText())){
                    JOptionPane.showMessageDialog(null,
            		    "Sikeres bejelentkezés!",
            		    "Információ",
            		    JOptionPane.INFORMATION_MESSAGE);
                }else{
                    JOptionPane.showMessageDialog(null,
            		    "Hibás felhasználónév vagy jelszó!",
            		    "Hiba",
            		    JOptionPane.ERROR_MESSAGE);
                }
            }
        }
        if(keres == e.getSource()){
            if((!kereses.getText().isEmpty()) && (dao.existsKonyv(kereses.getText()).size() != 0) ){
                JOptionPane.showMessageDialog(null,
            		    dao.existsKonyv(kereses.getText()).size() + "db könyvet találtam!\n" + dao.existsKonyv(kereses.getText()).toString() ,
            		    "Információ",
            		    JOptionPane.INFORMATION_MESSAGE);
            }else{
                JOptionPane.showMessageDialog(null,
            		    "Nincs ilyen könyv!",
            		    "Hiba",
            		    JOptionPane.ERROR_MESSAGE);
            }
        }
    }
}
