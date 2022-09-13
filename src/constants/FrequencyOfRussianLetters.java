package constants;

import java.util.HashMap;
import java.util.Map;

//double shows % of all letters in text

public class FrequencyOfRussianLetters {
    public static final Map<Character,Double> LETTERS_FREQUENCY = new HashMap<>(){{
        put('о', 10.98);
        put('е', 8.5);
        put('а', 8.0);
        put('и', 7.4);
        put('н', 6.7);
        put('т', 6.3);
        put('с', 5.4);
        put('р',4.7);
        put('в', 4.5);
        put('л', 4.3);
    }};
}
