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
import static constants.FrequencyOfRussianLetters.LETTERS_FREQUENCY;

/**
 * Based on a frequency of letters in text.
 * <p>
 * Method  - getLettersRate - returns a map with Character key(with represents a symbol) and Integer value (represents amount
 * of each key ( symbol).
 * <p>
 * Method - getArrayOfPotentialKey - returns int array of possible keys
 * <p>
 * Method - getKey - finds the most common int(key) in array, which should be the key for decoding message.
 * <p>
 * Works good with big size texts. Not accurate with short messages.
 */
public class OldVersionLettersAnalysis {

    public void runLetterAnalysis() {
        getKey(getArrayOfPotentialKey(getLettersRate()));
    }

    private void getKey(int[] keys) {
        Map<Integer, Integer> myMap = new HashMap<>();
        for (int i = 0; i < keys.length; i++) {
            if (myMap.containsKey(keys[i])) {
                myMap.put(keys[i], myMap.get(keys[i]) + 1);
            } else {
                myMap.put(keys[i], 1);
            }
        }
        int maxCount = 0, result = Integer.MIN_VALUE;
        for (Map.Entry<Integer, Integer> val : myMap.entrySet()) {
            if (maxCount < val.getValue()) {
                result = val.getKey();
                maxCount = val.getValue();
            }
        }
        System.out.println("The probable key is " + result + ".");
    }

    private int[] getArrayOfPotentialKey(Map<Character, Integer> letters) {
        char[] sortedChars = new char[11];     // element 0 - is a space bar

        Collection<Integer> valuesFromMapLetters = letters.values();                                //get sorted values
        List<Integer> sortedIntegers = Arrays.asList(valuesFromMapLetters.toArray(new Integer[0])); //from map
        sortedIntegers.sort(Collections.reverseOrder());                                            // 1 elem is the biggest

        for (Map.Entry<Character, Integer> entry : letters.entrySet()) {    // compare values
            for (int i = 0; i < 11; i++) {                                  // dont account space bar, begin from 1 element
                if (entry.getValue() == sortedIntegers.get(i)) {            // if values equal
                    char temp = entry.getKey();                             //
                    sortedChars[i] = temp;                                  // save char, closer to left char is the most common
                }
            }
        }
        System.out.println(Arrays.toString(sortedChars));
        int[] keys = new int[10];
        for (int i = 1, count = 0; i < sortedChars.length; i++, count++) {
            int possibleKey = ALPHABET.indexOf(sortedChars[i]) - ALPHABET.indexOf(LETTERS_FREQUENCY.get(count)); //find the key
            if (possibleKey < 0) {                                                                               //
                possibleKey = possibleKey + ALPHABET_SIZE;                                                       //
            }
            keys[count] = possibleKey;      //add key for first char to array
        }
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
            System.out.println();
        } catch (FileNotFoundException e) {
            System.out.println(FILE_NOT_FOUND);
        } catch (IOException e) {
            System.out.println(IO_EXCEPTION);
        }
        return storage;
    }
}

