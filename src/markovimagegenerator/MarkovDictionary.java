/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package markovimagegenerator;

import java.util.TreeMap;
import java.util.Iterator;
import java.util.Map.Entry;

/**
 *
 * @author Nyefan
 */
public class MarkovDictionary {
    private TreeMap<Byte[], TreeMap<Byte, Integer>> dict;
    private int order;
    
    public MarkovDictionary(RawImageData inputImage, int order) {
        
        dict = new TreeMap<>();
        this.order = order;
        
        fillDictionary(inputImage);
        procDictionary();
        
    }
    
    private void fillDictionary(RawImageData inputImage) {
        //for each sub-array of length order in the raw image data
        //put the sub-array in a tempKey variable
        //put the next byte in a tempVal variable
        //if dict already contains tempKey
        //incrememt the occurance rate of tempVal
        //else, add tempVal at an occurance rate of 1
        for(int i = 0; i < inputImage.getSize()-(1+order); i++) {
            Byte[] tempKey = inputImage.getKey(i, order);
            Byte tempVal = inputImage.getKey(i+order, 1)[0];
            if (dict.containsKey(tempKey)) {
                incrementRate(tempKey, tempVal);
            } else {
                addInstance(tempKey, tempVal);
            }
        }
    }
    //TODO: Check to see if the Dictionary.get(keytype K) returns by value or by reference
    //NOTE: The current code is safe for both
    private void incrementRate(Byte[] tempKey, Byte tempVal) {
        //Pull out the old occurance rate of tempVal
        //increment it by one
        //Replace the old occurance rate with the new one
        Integer newVal = dict.get(tempKey).get(tempVal);
        ++newVal;
        //The next line can be commented out if Dictionary.get(keytype K) returns the original and not a copy.
        dict.get(tempKey).put(tempVal, newVal);
    }
    
    private void addInstance(Byte[] tempKey, Byte tempVal) {
        Integer tempInt = 1;
        dict.get(tempKey).put(tempVal, tempInt);
    }
    
    private void procDictionary() {
        //throw new UnsupportedOperationException("This feature has not been implemented.");
        
        //for each key in dict
        //add up the number of occurances of each value
        //then divide the number of ocurances for each value by the total number of occurances of the key
        //replace the occurance values for each key with the new value
        Iterator<Entry<Byte[], TreeMap<Byte, Integer>>> outerIterator = dict.entrySet().iterator();
        Entry<Byte[], TreeMap<Byte, Integer>> tempEntry;
        while(outerIterator.hasNext()) {
            tempEntry = outerIterator.next();
            
            TreeMap<Byte, Integer> tempOccuranceMap = tempEntry.getValue();
            Iterator<Entry<Byte, Integer>> innerIterator = tempOccuranceMap.entrySet().iterator();
            
            Integer totalOccurances = 0;
            while(innerIterator.hasNext()) {
               totalOccurances+=innerIterator.next().getValue();
            }
            
            innerIterator = tempOccuranceMap.entrySet().iterator();
            Integer partialOccurances = 0;
            Entry<Byte, Integer> tempInnerEntry;
            
            while(innerIterator.hasNext()) {
                tempInnerEntry = innerIterator.next();
                partialOccurances += tempInnerEntry.getValue();
                tempInnerEntry.setValue(partialOccurances/totalOccurances);
            }
            
            tempEntry.setValue(tempOccuranceMap);
        }
    }
}
