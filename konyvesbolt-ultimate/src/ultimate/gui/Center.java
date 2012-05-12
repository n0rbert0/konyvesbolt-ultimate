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
        removeAll();
        
        konyvleiras elso = new konyvleiras(350, 150, 120, 150, new Dimension(350,310));
        konyvleiras masodik = new konyvleiras(350, 150, 120, 150, new Dimension(350,310));
        konyvleiras harmadik = new konyvleiras(350, 150, 120, 150, new Dimension(350,310));
        konyvleiras negyedik = new konyvleiras(350, 150, 120, 150, new Dimension(350,310));

        add(elso);
        add(masodik);
        add(harmadik);
        add(negyedik);
        
        egykonyv();
    }
    
    public void egykonyv()
    {
        removeAll();  
        JPanel fal = new JPanel();
        fal.setLayout(new BorderLayout());
        konyvleiras alma = new konyvleiras(700, 280, 250, 250, new Dimension(700,820));
        fal.add(alma, BorderLayout.CENTER);
        
        JPanel fal2 = new JPanel(new BorderLayout());
        JButton gombocska = new JButton("Kosárba");

        fal2.add(gombocska, BorderLayout.EAST);
        
        fal.add(fal2, BorderLayout.NORTH);
        add(fal);
    }
    
    
    private class konyvleiras extends TopList{
    
        
        konyvleiras(int a, int b, int c, int d, Dimension dimenzio){

            init(1, dimenzio, c, d, a, b);
                     
        }
       
        @Override
        protected JPanel Lista()
        {
            JPanel alaplabel = new JPanel(new GridLayout(2,1));
 
            JPanel label = new JPanel(new BorderLayout());
            JButton gomb = new JButton();        
            gomb.add(LoadImage("101751F.gif",kepx,kepy));

            label.setPreferredSize(new Dimension(keretx,kerety));
            label.add(gomb, BorderLayout.WEST);
        
            JTextArea text = new JTextArea("Királyok csatája" + "\n" + "George Martin");
            text.setEnabled(false);
            label.add (text, BorderLayout.CENTER);
            
            JTextArea leiras = new JTextArea("ewrztrewt43265rewgwezr35zhehgwhj2tewzhweht\newrthzwteh\n");
            leiras.setEnabled(false);
            
            alaplabel.add(label);
            alaplabel.add(leiras);
            
        
        return alaplabel;
        
        }
    
    }
    
    private class kosar extends JPanel{}
}
