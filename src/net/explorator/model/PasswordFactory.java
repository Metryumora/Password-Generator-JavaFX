package net.explorator.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by Supreme on 03.06.2016.
 */
public class PasswordFactory {

    public static String generatePassword(PasswordAttributes attributes, int length) {
        String pass = "";
        Random random = new Random();
        if (attributes.attributesAreNotEmpty())
            while (pass.length() < length) {
                switch (random.nextInt(4)) {
                    case 0: {
                        if (attributes.hasCapitalLetters())
                            pass += (char) (65 + random.nextInt(26));
                        break;
                    }
                    case 1: {
                        if (attributes.hasSmallLetters())
                            pass += (char) (97 + random.nextInt(26));
                        break;
                    }
                    case 2: {
                        if (attributes.hasNumbers())
                            pass += (random.nextInt(10));
                        break;
                    }
                    case 3: {
                        if (attributes.hasSymbols())
                            pass += (char) (33 + random.nextInt(15));
                        break;
                    }
                }
            }
        return pass;
    }

    public static List<String> generateMultiplePasswords(PasswordAttributes attributes, int length, int quantity) {
        List<String> result = new ArrayList<>(quantity);
        for (int i = 0; i < quantity; i++) {
            result.add(generatePassword(attributes, length));
        }
        return result;
    }
}
