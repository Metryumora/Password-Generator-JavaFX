package net.metryumora.passgen.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import net.metryumora.passgen.model.FileWorker;
import net.metryumora.passgen.model.PasswordAttributes;
import net.metryumora.passgen.model.PasswordFactory;

import java.awt.*;
import java.util.List;

public class Controller {

    @FXML Button generateButton;
    @FXML Button saveButton;
    @FXML CheckBox smallCheckBox;
    @FXML CheckBox capitalCheckBox;
    @FXML CheckBox numbersCheckBox;
    @FXML CheckBox symbolsCheckBox;
    @FXML TextField lengthTextField;
    @FXML TextField quantityTextField;
    @FXML TextArea passwordsTA;

    private int passwordLength = 0;
    private int passwordsQuantity = 0;

    @FXML
    private void initialize() {
        smallCheckBox.setFocusTraversable(false);
        capitalCheckBox.setFocusTraversable(false);
        numbersCheckBox.setFocusTraversable(false);
        symbolsCheckBox.setFocusTraversable(false);
        lengthTextField.setFocusTraversable(false);
        quantityTextField.setFocusTraversable(false);

        passwordsTA.setFocusTraversable(false);

        generateButton.requestFocus();
    }

    @FXML
    void handleKeyPressed(KeyEvent event) {
        switch (event.getCode()) {
            case S:
                if (event.isControlDown()) {
                    saveToFile();
                }
            default:
                break;
        }
    }

    @FXML
    private void generatePasswords() {
        PasswordAttributes attributes = new PasswordAttributes(
                capitalCheckBox.isSelected(),
                smallCheckBox.isSelected(),
                numbersCheckBox.isSelected(),
                symbolsCheckBox.isSelected()
        );

        try {
            passwordLength = Integer.parseInt(lengthTextField.getText());
        } catch (NumberFormatException e) {
            passwordsTA.setText("Length should be a number grater than 0 :)");
            lengthTextField.setText("20");
        }

        try {
            passwordsQuantity = Integer.parseInt(quantityTextField.getText());
        } catch (NumberFormatException e) {
            passwordsTA.setText("Quantity should be a number grater than 0 :)");
            quantityTextField.setText("1");
        }

        List<String> passwordsList = PasswordFactory.generateMultiplePasswords(attributes, passwordLength, passwordsQuantity);
        StringBuilder passwords = new StringBuilder();
        for (String password : passwordsList) {
            passwords.append(password).append("\n");
        }
        passwordsTA.setText(passwords.toString());
    }

    @FXML
    public void saveToFile() {
        Frame frame = new Frame();
        frame.setLocationRelativeTo(null);

        FileDialog fileDialog = new FileDialog(frame, "Save file as...", FileDialog.SAVE);
        fileDialog.setFile("passwords.txt");
        fileDialog.setVisible(true);

        if (fileDialog.getDirectory() != null && fileDialog.getFile() != null) {
            String currDocPath = fileDialog.getDirectory() + fileDialog.getFile();
            FileWorker.write(currDocPath + ".txt", passwordsTA.getText());
        }
    }

    @FXML
    public void clearTextArea() {
        passwordsTA.clear();
    }
}
