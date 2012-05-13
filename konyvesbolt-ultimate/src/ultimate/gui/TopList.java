/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ultimate.gui;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import ultimate.konyvesbolt.Konyv;
import ultimate.sql.DAO;

/**
 *
 * @author lorda
 */
public class TopList extends JPanel implements ActionListener {
    
    protected int m_db,i=1;
    protected Dimension meret;
    protected int kepx, kepy;
    protected int keretx, kerety;
    
    private Map<Integer, JButton> gombMap = new HashMap<Integer, JButton>();
    private Map<Integer, String> kep = new HashMap<Integer, String>();
    private Map<Integer, String> cim = new HashMap<Integer, String>();
    private Map<Integer, String> szerzo = new HashMap<Integer, String>();
    private Map<Integer, String> leiras = new HashMap<Integer, String>();
        
    public TopList() {
         init(6, new Dimension(300,1000), 75, 100, 300, 97);   
            
    }
    
    public void init(int a, Dimension b, int c, int d, int e, int f)
    {
        m_db = a;
        meret = b;
        kepx = c;
        kepy = d;
        keretx = e;
        kerety = f;
        removeAll();            
        JPanel fal = new JPanel();        
        fal.setPreferredSize(meret);
        gombMap.clear();
        for(int i=0; i<m_db; i++)
        fal.add(Lista());
        add(fal);
            
    }
    
    protected JPanel Lista()
    {    
        JPanel label = new JPanel(new BorderLayout());
        JButton gomb = new JButton();
        Random generator = new Random();       
        Map<Integer, Konyv> konyv;
        DAO dao = new DAO();       
        
        konyv = dao.getKonyv();
        
        int random = generator.nextInt( dao.maxKonyvIsbn() ) + 1;

        kep.put(i,konyv.get(random).getKep());
        cim.put(i,konyv.get(random).getCim());
        szerzo.put(i,konyv.get(random).getSzerzo());
        leiras.put(i,konyv.get(random).getLeiras());
        
        gomb.add(LoadImage(kep.get(i),kepx,kepy));
        gomb.addActionListener(this);
        label.setBorder(LineBorder.createBlackLineBorder());
        label.setPreferredSize(new Dimension(keretx,kerety));
        label.add(gomb, BorderLayout.WEST);
        
        JTextArea text = new JTextArea(cim.get(i) + "\n" + szerzo.get(i));
        text.setEnabled(false);
        label.add (text, BorderLayout.CENTER);
        
        gombMap.put(i,gomb);
        
        i++;
        return label;
    }
    
    protected JLabel LoadImage(String name, int width, int height){
        Image image = null;
        
        try {
            File file = new File(MainWindow.m_path + name);
            image  = ImageIO.read(file);
            
        } catch (IOException ex) {
            Logger.getLogger(TopList.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        JLabel label = new JLabel(new ImageIcon(resizeImage(image,width,height)));
        label.setPreferredSize(new Dimension(width,height));

        return label;
    }   
    
    protected static BufferedImage resizeImage(final Image image, int width, int height) {
        final BufferedImage bufferedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        final Graphics2D graphics2D = bufferedImage.createGraphics();
        graphics2D.setComposite(AlphaComposite.Src);
        graphics2D.drawImage(image, 0, 0, width, height, null);
        graphics2D.dispose();
 
        return bufferedImage;
    }
    
      @Override
    public void actionPerformed(ActionEvent e) {
          for(i=1;i<=m_db;i++)
            if(gombMap.get(i) == e.getSource()){
                System.out.println(kep.get(i) + " - " +
                           cim.get(i) + " - " +
                           szerzo.get(i) + " - " +
                           leiras.get(i));
             //itt kellene meghívni egy olyan függvényt, ami
             //a könyv paramétereit várja és megjeleníti nagyban
            }
                
    }
}
