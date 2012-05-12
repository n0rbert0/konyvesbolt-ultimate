/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ultimate.gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.border.LineBorder;

/**
 *
 * @author lorda
 */
public class Center extends JPanel{
    
    Center()
    {
        setPreferredSize(new Dimension(700,620));
        init();
    }
    
    public void init()
    {
        GridLayout layout = new GridLayout(2,2);
        setLayout(layout);
        konyvleiras elso = new konyvleiras();
        JButton masodik = new JButton();
        JButton harmadik = new JButton();
        JButton negyedik = new JButton();
        add(elso);
        add(masodik);
        add(harmadik);
        add(negyedik);
    }
    
    private class konyvleiras extends TopList{
    
        
        konyvleiras(){

            init(1, new Dimension(350,310), 75, 100, 350, 150);
                     
        }
       
        @Override
        protected JPanel Lista()
        {
            setLayout(new GridLayout(2,1));
            JPanel label = new JPanel(new BorderLayout());
            JButton gomb = new JButton();        
            gomb.add(LoadImage("101751F.gif",kepx,kepy));
            label.setBorder(LineBorder.createBlackLineBorder());
            label.setPreferredSize(new Dimension(keretx,kerety));
            label.add(gomb, BorderLayout.WEST);
        
            JTextArea text = new JTextArea("Királyok csatája" + "\n" + "George Martin");
            text.setEnabled(false);
            label.add (text, BorderLayout.CENTER);
        
        return label;
        
        }
    
    }
    
    private class kosar extends JPanel{}
}
