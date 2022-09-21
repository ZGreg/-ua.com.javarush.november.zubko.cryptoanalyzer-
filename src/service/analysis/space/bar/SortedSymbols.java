package service.analysis.space.bar;

import service.UserDataInput;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static constants.Messages.FILE_NOT_FOUND;
import static constants.Messages.IO_EXCEPTION;

public class SortedSymbols {

      Map<Character, Integer> sortLetterFrequency(){

        Map<Character, Integer> storage = new HashMap<>();

        try (BufferedReader bf = new BufferedReader(new FileReader(new UserDataInput().getInput()))) {

            while (bf.ready()) {

                char letter = (char) bf.read();

                if (!storage.containsKey(letter) && letter != '\r' && letter != '\n') {
                    storage.put(letter, 1);
                } else if(storage.containsKey(letter)){
                    storage.replace(letter, storage.get(letter) + 1);
                }
            }
        }catch (FileNotFoundException e){
            System.out.println(FILE_NOT_FOUND);
        }catch (IOException e ){
            System.out.println(IO_EXCEPTION );
        }
        return storage;
    }
}
