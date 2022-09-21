package service.bruteforce;

import service.UserDataInput;

import java.io.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;



import static constants.Messages.FILE_NOT_FOUND;
import static constants.Messages.IO_EXCEPTION;

public class FileBruteForce {
    KeyFinder keyFinder = new KeyFinder();

    public void findKeyFileSource() {

            File input = new UserDataInput().getInput();

        try (BufferedReader bf = new BufferedReader(new FileReader(input))) {

            String line = bf.readLine();

            while (true) {
                if(findAppropriateLine(line)){
                    break;
                }
                line = bf.readLine();
            }

            keyFinder.findKey(line);

            System.out.println("Use option 2 in menu with received key to decode and save message in file.");

        } catch (FileNotFoundException e) {
            System.out.println(FILE_NOT_FOUND);
        } catch (IOException e) {
            System.out.println(IO_EXCEPTION);
            e.getStackTrace();
        }
    }

    private boolean findAppropriateLine(String line){
        Pattern pattern = Pattern.compile("[а-яА-ЯёЁ]");
        Matcher matcher = pattern.matcher(line);
        return matcher.find();
    }
}

