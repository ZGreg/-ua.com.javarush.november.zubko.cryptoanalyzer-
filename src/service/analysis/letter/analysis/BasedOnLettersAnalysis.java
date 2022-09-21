package service.analysis;


import service.UserDataInput;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;


import static constants.Alphabet.ALPHABET_SIZE;
import static constants.Messages.FILE_NOT_FOUND;
import static constants.Messages.IO_EXCEPTION;
import static constants.Alphabet.ALPHABET;
import static constants.Alphabet.ALPHABET_SIZE;
import static constants.FrequencyOfRussianLetters.LETTERS_FREQUENCY;
import static constants.FrequencyOfRussianLetters.LETTERS_FREQUENCY_SIZE;
import static constants.FrequencyOfRussianLetters.LETTERS_FREQUENCY_1;

/**
 * Based on a frequency of letters in text.
 *
 * Method  - getLettersRate - returns a map with Character key(with represents a symbol) and Integer value (represents amount
 * of each key ( symbol).
 *
 * Method - getArrayOfPotentialKey - returns int array of possible keys
 *
 * Method - getKey - finds the most common int(key) in array, which should be the key for decoding message.
 *
 * Works good with big size texts. Not accurate with short messages.
 * */
public class BasedOnLettersAnalysis {


    public void runLetterAnalysis(){
        getKey(getArrayOfPotentialKey(getLettersRate()));
    }

    private void getKey(int[]keys){
        Map<Integer, Integer> myMap = new HashMap<>();

        for (int i = 0; i < keys.length; i++) {
            if (myMap.containsKey(keys[i])) {
                myMap.put(keys[i], myMap.get(keys[i]) + 1);
            } else {
                myMap.put(keys[i], 1);
            }
        }

        int maxCount = 0;
        int result = Integer.MIN_VALUE;

        for (Map.Entry<Integer, Integer> val : myMap.entrySet()) {
            if (maxCount < val.getValue()) {
                result = val.getKey();
                maxCount = val.getValue();
            }
        }

        System.out.println("The probable key is " + result + ".");
    }

    private int [] getArrayOfPotentialKey(Map<Character, Integer> letters) {
        Collection<Integer> valuesFromMapLetters = letters.values();
        List<Integer> sortedIntegers = Arrays.asList(valuesFromMapLetters.toArray(new Integer[0]));
        sortedIntegers.sort(Collections.reverseOrder());
        System.out.println(sortedIntegers);

        List<Character> sortedChars = new ArrayList<>();

        for (Map.Entry<Character, Integer> entry : letters.entrySet()) {
            for (int i = 0; i < sortedIntegers.size(); i++) {
                if (Objects.equals(entry.getValue(), sortedIntegers.get(i)) && !sortedChars.contains(entry.getKey())) {
                    char temp = entry.getKey();
                    sortedChars.add(temp);
                }
            }
        }
        System.out.println(sortedChars);
         int [] keys;

        if(sortedChars.size() < LETTERS_FREQUENCY_SIZE){
            keys = new int[sortedChars.size()];
            for (int i = 0; i < sortedChars.size(); i++) {
                int possibleKey = ALPHABET.indexOf(sortedChars.get(i)) - ALPHABET.indexOf(LETTERS_FREQUENCY.get(i));
                if (possibleKey < 0) {
                    possibleKey = possibleKey + ALPHABET_SIZE;
                }
                    keys[i] = possibleKey;

            }
        }else {
            keys = new int[LETTERS_FREQUENCY_SIZE];
            for (int i = 0; i < LETTERS_FREQUENCY_SIZE; i++) {
                int possibleKey = ALPHABET.indexOf(sortedChars.get(i)) - ALPHABET.indexOf(LETTERS_FREQUENCY.get(i));
                if (possibleKey < 0) {
                    possibleKey = possibleKey + ALPHABET_SIZE;
                }
                    keys[i] = possibleKey;

            }
        }
        System.out.println(Arrays.toString(keys));
        return keys;
    }

    private Map<Character, Integer> getLettersRate() {
        Map<Character, Integer> storage = new HashMap<>();
        try (BufferedReader bf = new BufferedReader(new FileReader(new UserDataInput().getInput()))) {

            while (bf.ready()) {

                char letter = (char) bf.read();
                //add char as a key, integer represents amount of key char
                //don't count digit and space symbols
                if (!storage.containsKey(letter) && !Character.isSpaceChar(letter) && !Character.isDigit(letter)) {
                    if (Character.isUpperCase(letter)) {
                        continue;
                    }
                    storage.put(letter, 1);
                } else if (storage.containsKey(letter)) {
                    storage.replace(letter, storage.get(letter) + 1);
                }
            }
            System.out.println(storage);
        } catch (FileNotFoundException e) {
            System.out.println(FILE_NOT_FOUND);
        } catch (IOException e) {
            System.out.println(IO_EXCEPTION);
        }
        return storage;
    }
}
