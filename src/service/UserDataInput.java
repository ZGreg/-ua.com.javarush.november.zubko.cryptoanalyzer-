package service;

import java.util.Scanner;

import static constants.Alphabet.ALPHABET_SIZE;
import static constants.Messages.NUMBER_FORMAT_EX;

public class UserDataInput {
    private final Scanner KEYBOARD = new Scanner(System.in);

    public String getInput() {
        System.out.println("\u2193 Type the path to source file below \u2193");
        return KEYBOARD.nextLine();
    }


    public String getOutput() {
        System.out.println("\u2193 Type the path to a file for saving the result below \u2193");
        return KEYBOARD.nextLine();
    }


    public int getKey() {
        System.out.println("\u2193 Type a key for encoding/decoding below \u2193 ");
        System.out.println("      Chose number between 1 and " + (ALPHABET_SIZE - 1));
        int key;
        while (true) {
            try {
                key = Integer.parseInt(KEYBOARD.nextLine());
                while (key >= ALPHABET_SIZE || key < 0) {
                    System.out.println("Not appropriate value of the key. Try again!");
                    key = Integer.parseInt(KEYBOARD.nextLine());
                }
            } catch (NumberFormatException e) {
                System.out.println(NUMBER_FORMAT_EX);
                continue;
            }
            return key;
        }
    }
}
