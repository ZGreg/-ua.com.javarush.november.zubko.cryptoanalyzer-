package service.analysis.space.bar;



import java.util.*;

import static constants.Alphabet.ALPHABET;
import static constants.Alphabet.ALPHABET_SIZE;
/**
 * Basing on what I have noticed, that symbol "space" is the most frequent symbol in big texts.
 * */
public class BasedOnSpaceAnalysis {

    private  Map<Character, Integer> letters = new SortedSymbols().sortLetterFrequency();

    public void getKeyBySpace(){

        int maxValue = Collections.max(letters.values());
        char encodedSpace = 0;

        for (Map.Entry<Character,Integer> entry:
                letters.entrySet()) {
            if(entry.getValue() == maxValue){
                encodedSpace = entry.getKey();
            }
        }

        int key = ALPHABET.indexOf(encodedSpace) - ALPHABET.indexOf(' ');

        if(key < 0){
            key = key + ALPHABET_SIZE;
        }

        System.out.println("\nThe key# -> " + key + "\n");
    }
}
