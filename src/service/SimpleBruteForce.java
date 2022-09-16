package service;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import static constants.Alphabet.ALPHABET;
import static constants.Alphabet.ALPHABET_SIZE;
import static constants.Messages.FILE_NOT_FOUND;
import static constants.Messages.IO_EXCEPTION;
/**
 * Simple search all possibles keys. User must look through all variants himself.
 * Perfectly works with any message size.
 * */
public class SimpleBruteForce {
    public void findKey() {

        try (BufferedReader bf = new BufferedReader(new BufferedReader(new FileReader(new UserDataInput().getInput())))) {
            StringBuilder sb = new StringBuilder("");
            String line = bf.readLine();                            // take first line for encoding
            for (int key = 1; key < ALPHABET_SIZE; key++) {
                for (int i = 0; i < line.length(); i++) {
                    if (Character.isUpperCase(line.charAt(i))) {
                        int valueInAlph = ALPHABET.indexOf(Character.toLowerCase(line.charAt(i)));
                        int encodedChar = valueInAlph - key;
                        if (encodedChar < 0) {
                            encodedChar = encodedChar + ALPHABET_SIZE;
                        }
                        sb.append(Character.toUpperCase(ALPHABET.get(encodedChar)));
                    } else {
                        int valueInAlph = ALPHABET.indexOf(line.charAt(i));
                        int encodedChar = valueInAlph - key;
                        if (encodedChar < 0) {
                            encodedChar = encodedChar + ALPHABET_SIZE;
                        }
                        sb.append(ALPHABET.get(encodedChar));
                    }
                }
                System.out.printf("Key #%d : %s \n", key, sb);
                sb.delete(0, line.length());
            }
        } catch (FileNotFoundException e) {
            System.out.println(FILE_NOT_FOUND);
        } catch (IOException e) {
            System.out.println(IO_EXCEPTION);
            e.getStackTrace();
        }
    }
}

