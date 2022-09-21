package service.bruteforce;


import java.util.Scanner;

class ConsoleBruteForce {

    KeyFinder keyFinder = new KeyFinder();
    private Scanner keyboard = new Scanner(System.in);
    public void displayOnScreen() {
        System.out.println("\u2193Type a message below\u2193");
            String message = keyboard.nextLine();
            keyFinder.findKey(message);
    }

}
