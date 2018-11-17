package net.metryumora.passgen.model;

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

    boolean hasCapitalLetters() {
        return capitalLetters;
    }

    boolean hasSmallLetters() {
        return smallLetters;
    }

    boolean hasNumbers() {
        return numbers;
    }

    boolean hasSymbols() {
        return symbols;
    }

    boolean areNotEmpty() {
        return capitalLetters || smallLetters || numbers || symbols;
    }
}
