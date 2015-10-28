/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package markovimagegenerator;

import java.io.IOException;
import java.util.Calendar;

/**
 *
 * @author Nyefan
 */
public class MarkovImageGenerator {

    static MarkovDictionary mDict;
    static RawImageData oldData;
    static RawImageData newData;
    static MarkovTransformer mTrans;
    static ImageDisplay oldImage;
    static ImageDisplay newImage;
    
    static String imageFilePath = "";
    static int dictionaryOrder = 2;
    
    public static void main(String[] args) {
        
        // Import image and store it in a rawImage object
        oldData = new RawImageData(imageFilePath);
        // Build the x->y dictionary
        mDict = new MarkovDictionary(oldData, dictionaryOrder);
        // Generate the new image, based on the original one
        mTrans = new MarkovTransformer();
        mTrans.generate(mDict, newData);
        // Display both images, either side by side or in separate windows
        oldImage = new ImageDisplay(oldData);
        newImage = new ImageDisplay(newData);
        oldImage.display();
        newImage.display();
        // Save the newly generated 
        try {
            newData.save(generateSaveFilePath());
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }
    
    private static String generateSaveFilePath() {
        
        String timeString = Calendar.getInstance().getTime().toString().replace(' ', '_');
        return imageFilePath + "_" + dictionaryOrder + timeString;
    }
    
}
