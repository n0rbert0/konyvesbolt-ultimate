/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ultimate.gui;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

/**
 *
 * @author lorda
 */
public class TopList extends JPanel {
    
    public TopList() {
        JPanel label = new JPanel(new BorderLayout());
        label.setBorder(LineBorder.createBlackLineBorder());
        label.setPreferredSize(new Dimension(300,100));
        label.add(LoadImage("101751F.gif",65,100), BorderLayout.WEST);
        
        add(label);
    }
    
    private JLabel LoadImage(String name, int width, int height){
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
    
    private static BufferedImage resizeImage(final Image image, int width, int height) {
        final BufferedImage bufferedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        final Graphics2D graphics2D = bufferedImage.createGraphics();
        graphics2D.setComposite(AlphaComposite.Src);
        graphics2D.drawImage(image, 0, 0, width, height, null);
        graphics2D.dispose();
 
        return bufferedImage;
    }
}
