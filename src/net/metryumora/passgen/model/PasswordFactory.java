package net.metryumora.passgen.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class PasswordFactory {

    public static String generatePassword(PasswordAttributes attributes, int length) {
        StringBuilder pass = new StringBuilder();
        Random random = new Random();
        if (attributes.areNotEmpty())
            while (pass.length() < length) {
                switch (random.nextInt(4)) {
                    case 0: {
                        if (attributes.hasCapitalLetters())
                            pass.append((char) (65 + random.nextInt(26)));
                        break;
                    }
                    case 1: {
                        if (attributes.hasSmallLetters())
                            pass.append((char) (97 + random.nextInt(26)));
                        break;
                    }
                    case 2: {
                        if (attributes.hasNumbers())
                            pass.append((random.nextInt(10)));
                        break;
                    }
                    case 3: {
                        if (attributes.hasSymbols())
                            pass.append((char) (33 + random.nextInt(15)));
                        break;
                    }
                }
            }
        return pass.toString();
    }

    public static List<String> generateMultiplePasswords(PasswordAttributes attributes, int length, int quantity) {
        List<String> result = new ArrayList<>(quantity);
        for (int i = 0; i < quantity; i++) {
            result.add(generatePassword(attributes, length));
        }
        return result;
    }
}
