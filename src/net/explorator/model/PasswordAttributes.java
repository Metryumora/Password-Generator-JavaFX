package net.explorator.model;

/**
 * Created by Supreme on 03.06.2016.
 */
public class PasswordAttributes {

    private boolean capitalLetters;

    private boolean smallLetters;

    private boolean numbers;

    private boolean symbols;

    public PasswordAttributes(boolean capitalLetters, boolean smallLetters, boolean numbers, boolean symbols) {
        this.capitalLetters = capitalLetters;
        this.smallLetters = smallLetters;
        this.numbers = numbers;
        this.symbols = symbols;
    }

    public boolean hasCapitalLetters() {
        return capitalLetters;
    }

    public boolean hasSmallLetters() {
        return smallLetters;
    }

    public boolean hasNumbers() {
        return numbers;
    }

    public boolean hasSymbols() {
        return symbols;
    }

    public boolean attributesAreNotEmpty() {
        if (capitalLetters
                || smallLetters
                || numbers
                || symbols
                )
            return true;
        else return false;
    }
}
