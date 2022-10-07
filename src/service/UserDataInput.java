package service;



import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Scanner;

import static constants.Alphabet.ALPHABET_SIZE;
import static constants.Messages.NUMBER_FORMAT_EXCEPTION;



public class UserDataInput {

    private Scanner keyboard = new Scanner(System.in);
    public File getInput() {
        System.out.println("\u2193 Type the path to source file below \u2193");

        String path = keyboard.nextLine();
        File file = new File(path);
        while (true) {
            if (!Files.exists(Path.of(path))) {
                System.out.println("File does not exists. Try again.");
                path = keyboard.nextLine();
                continue;
            }
            if (file.length() == 0) {
                System.out.println("The file is empty. Try again.");
                file = new File(keyboard.nextLine());
                continue;
            }
            return file;
        }
    }


    public File getOutput() {
        System.out.println("\u2193 Type the path to a file for saving the result below \u2193");
        File file = new File(keyboard.nextLine());
        while (true) {
            if(!file.exists()){
                return file;
            }
            if (file.length() != 0) {
                System.out.println("The chosen file is not empty.\nPress <1>, if you want overwrite file, <2> for new path.");
                try {
                    int choice = Integer.parseInt(keyboard.nextLine());
                    if (choice == 1) {
                        return file;
                    } else if (choice == 2) {
                        System.out.println("\u2193 Type path for new file below \u2193");
                        file = new File(keyboard.nextLine());
                        return file;
                    }
                    System.out.println("Not appropriate value of the key. Try again!");
                    choice = Integer.parseInt(keyboard.nextLine());
                } catch (NumberFormatException e) {
                    System.out.println(NUMBER_FORMAT_EXCEPTION);
                    continue;
                }
                return file;
            }
        }
    }


    public int getKey() {
        System.out.println("\u2193 Type a key for encoding/decoding below \u2193 ");
        System.out.println("      Chose number between 1 and " + (ALPHABET_SIZE - 1));
        int key;
        while (true) {
            try {
                key = Integer.parseInt(keyboard.nextLine());
                while (key >= ALPHABET_SIZE || key < 0) {
                    System.out.println("Not appropriate value of the key. Try again!");
                    key = Integer.parseInt(keyboard.nextLine());
                }
            } catch (NumberFormatException e) {
                System.out.println(NUMBER_FORMAT_EXCEPTION);
                continue;
            }
            return key;
        }
    }
}
