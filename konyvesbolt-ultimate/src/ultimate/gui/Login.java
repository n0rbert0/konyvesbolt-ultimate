/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ultimate.gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;

/**
 *
 * @author lorda
 */
public class Login extends JPanel{
    
    Login()
    {
    JPanel fal = new JPanel(new BorderLayout());
    //fal.setBorder(LineBorder.createBlackLineBorder());
    fal.setPreferredSize(new Dimension(1150,35));
    JTextField kereses = new JTextField("keresés");
    kereses.setPreferredSize(new Dimension(500,30));
    JButton keres = new JButton("Keres");
    JTextField login = new JTextField("login");
    login.setPreferredSize(new Dimension(150,30));
    JTextField pass = new JTextField("pass");
    pass.setPreferredSize(new Dimension(150,30));
    JButton belepes = new JButton("Belépés");
    
    JPanel falacska = new JPanel(new BorderLayout());
    JPanel falacska2 = new JPanel(new BorderLayout());
    
    falacska.add(kereses, BorderLayout.WEST);
    falacska.add(keres, BorderLayout.CENTER);
    
    falacska2.add(login, BorderLayout.WEST);
    falacska2.add(pass, BorderLayout.CENTER);    
    falacska2.add(belepes, BorderLayout.EAST);
    fal.add(falacska, BorderLayout.WEST);
    fal.add(falacska2, BorderLayout.EAST);
    add(fal);
    }
}
