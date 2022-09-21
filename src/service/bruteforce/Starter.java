package service.bruteforce;

import java.util.Scanner;

import static constants.Messages.NUMBER_FORMAT_EXCEPTION;
import static constants.Messages.WRONG_CHOICE;

public class Starter {
    private Scanner keyboard = new Scanner(System.in);
    public void getUserChoice() {

        while (true) {
            try {
                System.out.println("If you want to type a message in console, press <1>, or take as a message a line from file, type <2>.");
                int choice = Integer.parseInt(keyboard.nextLine());

                if (choice == 1) {
                    new ConsoleBruteForce().displayOnScreen();
                    break;
                }
                if (choice == 2) {
                    new FileBruteForce().findKeyFileSource();
                    break;
                }
                System.out.println(WRONG_CHOICE);
                choice = Integer.parseInt(keyboard.nextLine());
            } catch (NumberFormatException e) {
                System.out.println(NUMBER_FORMAT_EXCEPTION);
                continue;
            }
        }
    }
}
