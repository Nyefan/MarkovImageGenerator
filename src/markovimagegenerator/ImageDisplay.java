/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package markovimagegenerator;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

/**
 *
 * @author Nyefan
 */
public class ImageDisplay {
    
    private RawImageData toDisplay;
    
    public ImageDisplay(RawImageData toDisplay) {
        this.toDisplay = toDisplay;
    }
    
    public void display() {
        JFrame f = new JFrame("CurrentBufferedImage");
        f.addWindowListener(
            new WindowAdapter() {
                @Override
                public void windowClosing(WindowEvent e) {
                    System.exit(0);
                }
            }
        );
        f.add(new JLabel(new ImageIcon(toDisplay.getBufferedImage())));
        f.pack();
        f.setVisible(true);
    }
    
    public void display(RawImageData toDisplay) {
        this.toDisplay = toDisplay;
        display();
    }
    
}
