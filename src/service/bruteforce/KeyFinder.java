package service.bruteforce;

import java.util.Scanner;

import static constants.Alphabet.ALPHABET;
import static constants.Alphabet.ALPHABET_SIZE;


public class KeyFinder {

    private Scanner keyboard = new Scanner(System.in);
    public void findKey(String line) {

        StringBuilder sb = new StringBuilder("");

        for (int key = 1; key < ALPHABET_SIZE; key++) {
            for (int i = 0; i < line.length(); i++) {

                int valueInAlph = ALPHABET.indexOf(Character.toLowerCase(line.charAt(i)));
                int encodedChar = valueInAlph - key;

                if (encodedChar < 0) {
                    encodedChar = encodedChar + ALPHABET_SIZE;
                }
                sb.append(ALPHABET.get(encodedChar));


            }

            System.out.println("\u2193 Is the line readable? \u2193");
            System.out.println(sb);

            if(pickChoice().equals("1")){
                System.out.println("\nThe key# -> " + key + ".");
                break;
            }

            sb.delete(0, line.length());
        }
    }

    private String pickChoice() {

        System.out.println("If does press <1>, to continue search press any button.");

        return keyboard.nextLine();
    }
}
