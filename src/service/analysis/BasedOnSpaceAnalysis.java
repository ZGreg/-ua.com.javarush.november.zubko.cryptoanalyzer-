package service;


import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

import static constants.Messages.FILE_NOT_FOUND;
import static constants.Messages.IO_EXCEPTION;
import static constants.Alphabet.ALPHABET;
import static constants.Alphabet.ALPHABET_SIZE;
/**
 * Basing on what I have noticed, that symbol "space" is the most frequent symbol in big texts.
 * In my plans add "search a key" based on the most frequent letters in russian language, because message
 * can be encoded without "space" symbol.
 * */
public class AnalysisTheMessage {    // change name of method

    private  Map<Character, Integer> letters = getLettersRate();

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

    private Map<Character, Integer> getLettersRate(){
        Map<Character, Integer> storage = new HashMap<>();
        try (BufferedReader bf = new BufferedReader(new FileReader(new UserDataInput().getInput()))) {
        
            long amountOfLetters = 0L;
            
            while (bf.ready()) {

                char letter = (char) bf.read();
                //add char as a key, integer represents amount of key char
                //don't count digit and space symbols
                if (!storage.containsKey(letter) && letter != '\r' && letter != '\n') {
                    storage.put(letter, 1);
                } else if(storage.containsKey(letter)){
                    storage.replace(letter, storage.get(letter) + 1);
                }
                if (Character.isAlphabetic(letter)) {
                    amountOfLetters++;
                }
            }
            System.out.println(amountOfLetters + "<----");
            System.out.println(storage);
        }catch (FileNotFoundException e){
            System.out.println(FILE_NOT_FOUND);
        }catch (IOException e ){
            System.out.println(IO_EXCEPTION );
        }
        return storage;
    }
}
