package service;

import java.io.*;

import static constants.Alphabet.ALPHABET;
import static constants.Alphabet.ALPHABET_SIZE;
import static constants.Messages.FILE_NOT_FOUND;
import static constants.Messages.IO_EXCEPTION;

public class Encoder {

    public void encode() {
        File input = new UserDataInput().getInput();

        try (BufferedReader bf = new BufferedReader(new FileReader(input));
             BufferedWriter bw = new BufferedWriter(new FileWriter(new UserDataInput().getOutput()))) {
            int key = new UserDataInput().getKey();

            while (bf.ready()) {

                char fileChar = (char) bf.read();

                int charIndexFromAlph = ALPHABET.indexOf(Character.toLowerCase(fileChar));
                if (charIndexFromAlph == -1) {
                    bw.write(fileChar);
                } else {
                    int encodedChar = charIndexFromAlph + key;
                    if (encodedChar >= ALPHABET_SIZE) {
                        encodedChar = encodedChar - ALPHABET_SIZE;
                    }
                    bw.write(ALPHABET.get(encodedChar));
                }
            }
            System.out.println("The message is encoded successfully.");
            System.out.println("=============================================\n");
        } catch (FileNotFoundException e) {
            System.out.println("File is not found");
            System.out.println(FILE_NOT_FOUND);
        } catch (IOException e) {
            System.out.println(IO_EXCEPTION);
            e.getStackTrace();
        }
    }
}

