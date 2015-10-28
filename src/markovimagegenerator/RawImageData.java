/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package markovimagegenerator;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

/**
 *
 * @author Nyefan
 */

public class RawImageData {
    
    private String imageFilePath;
    private String imageName; // The image file path minus the extension
    private String imageType; // The image file extension only
    private BufferedImage bi;
    
    public RawImageData(String imageFilePath) {
        this.imageFilePath = imageFilePath;
        splitImageFilePath();
        //TODO: Import the image, convert it into raw data
    }
    
    public RawImageData(BufferedImage bi) {
        this.bi = bi;
    }
    
    public RawImageData(ByteArrayOutputStream baos) {
        try {
            bi = ImageIO.read(new ByteArrayInputStream(baos.toByteArray()));
        } catch (IOException ex) {
            System.err.println(ex.getMessage());
        }
    }
    
    public void save() throws IOException {
        save(imageFilePath);
    }
    
    public void save(String toSaveFilePath) throws IOException {
        
    }
    
    public void load() throws IOException {
        bi = ImageIO.read(new File(imageFilePath));
    }
    
    public void load(String toLoadFilePath) throws IOException {
        this.imageFilePath = toLoadFilePath;
        load();
    }

    public BufferedImage getBufferedImage() {
        return bi;
    }
    
    public int getSize() {
        throw new UnsupportedOperationException("This feature has not been implemented.");
    }
    
    public Byte[] getKey(int loc, int order) {
        throw new UnsupportedOperationException("This feature has not been implemented.");
    }

    private void splitImageFilePath() { //Takes the full imageFilePath and aplits it into a name and an extension;
        String[] pathAndType = imageFilePath.split(".");
        imageType = pathAndType[pathAndType.length];
        // This should be slightly faster than concating all of the Strings in pathAndType save the last one;
        // Also covers the case where there is a '.' in the filepath at a place other than immediately before the extension;
        imageName = imageFilePath.substring(0, imageFilePath.length() - imageType.length() - 1);
    }
    
    
    //TODO: Look at this, it's not done/right yet
    private void getSequencedPixelData() { 
        int[] pixelArray = null;
        int[] trueArray = bi.getRaster().getPixels(0, 0, bi.getWidth(), bi.getHeight(), pixelArray);
        
    }
    
}
