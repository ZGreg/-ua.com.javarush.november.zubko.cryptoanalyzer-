package service;

import java.io.*;

import static constants.Alphabet.ALPHABET;
import static constants.Alphabet.ALPHABET_SIZE;
import static constants.Messages.FILE_NOT_FOUND;
import static constants.Messages.IO_EXCEPTION;

public class Decoder {
    public void decode() {
        File input = new UserDataInput().getInput();

        try (BufferedReader br = new BufferedReader(new FileReader(input));
             BufferedWriter bw = new BufferedWriter(new FileWriter(new UserDataInput().getOutput()))) {
            int key = new UserDataInput().getKey();

            while (br.ready()) {

                char fileChar = (char) br.read();

                int charIndexFromAlph = ALPHABET.indexOf(Character.toLowerCase(fileChar));
                if (charIndexFromAlph == -1) {
                    bw.write(fileChar);
                } else {
                    int encodedChar = charIndexFromAlph - key;
                    if (encodedChar < 0) {
                        encodedChar = encodedChar + ALPHABET_SIZE;
                    }
                    bw.write(ALPHABET.get(encodedChar));
                }
            }
            System.out.println("The message is decoded successfully.");
            System.out.println("=============================================\n");
        } catch (FileNotFoundException e) {
            System.out.println(FILE_NOT_FOUND);
        } catch (IOException e) {
            System.out.println(IO_EXCEPTION);
            e.getStackTrace();
        }
    }
}