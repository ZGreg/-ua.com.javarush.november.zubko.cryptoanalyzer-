package service;

import errors.InvalidChoice;
import service.analysis.BasedOnLettersAnalysis;
import service.analysis.BasedOnSpaceAnalysis;

import java.util.Scanner;

public class ConsoleMenu {
    private boolean exit;

    private void printGreeting() {
        System.out.println("+---------------------------------------+");
        System.out.println("|      Welcome to cryptoanalyzer!       |");
        System.out.println("|      Produced by Hryhorii Zubko       |");
        System.out.println("|      ******* version 1.1 ******       |");
        System.out.println("+---------------------------------------+\n");
    }

    private void makeChoice() {
        System.out.println("     Chose one of options below :");
        System.out.println("1 <- Decode message using Cesar method.");
        System.out.println("2 <- Encode message using Cesar method.");
        System.out.println("3 <- Find the key by brute force method");
        System.out.println("4 <- Find the key by the method of letter frequency analysis");
        System.out.println("0 <- Exit application.");
    }

    private int getOption() {
        Scanner keyboard = new Scanner(System.in);
        int option = -1;

        while (option < 0 || option > 4) {
            try {
                System.out.println("\nSelect number :");
                option = Integer.parseInt(keyboard.nextLine());
                if (option < 0 || option > 4) {
                    throw new InvalidChoice();
                }
            } catch (NumberFormatException var4) {
                System.out.println("Invalid input. Try again");
            } catch (InvalidChoice var5) {
                System.out.println("You've chosen option -> " + option + ", which is not in the list!");
            }
        }

        return option;
    }

    private void activateMenu(int option) {
        switch (option) {
            case 0:
                this.exit = true;
                System.out.println("See you!");
                break;
            case 1:
                new Decoder().decode();
                break;
            case 2:
                new Encoder().encode();
                break;
            case 3:
                new SimpleBruteForce().findKey();
                break;
            case 4:
                new BasedOnLettersAnalysis().runLetterAnalysis();
                break;
            default:
                System.out.println("Unknown command. Please try again");
        }

    }

    public void runApp() {
        this.printGreeting();

        while (!this.exit) {
            this.makeChoice();
            int option = this.getOption();
            this.activateMenu(option);
        }

    }
}

