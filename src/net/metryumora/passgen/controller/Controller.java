package net.metryumora.passgen.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import net.metryumora.passgen.model.PasswordFactory;
import net.metryumora.passgen.model.FileWorker;
import net.metryumora.passgen.model.PasswordAttributes;

import java.awt.*;
import java.util.List;


public class Controller {

    @FXML
    CheckBox capitalCheckBox;
    @FXML CheckBox smallCheckBox;
    @FXML CheckBox numbersCheckBox;
    @FXML CheckBox symbolsCheckBox;
    @FXML TextField lengthTextField;
    @FXML TextField quantityTextField;
    @FXML TextArea passwordsTA;
    @FXML Button saveButton;
    @FXML Button generateButton;

    private int passwordLength = 0;
    private int passwordsQuantity = 0;

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
        FileDialog fileDialog = new FileDialog(new Frame(), "Save file as...", FileDialog.SAVE);
        fileDialog.setVisible(true);
        if (fileDialog.getDirectory() == null || fileDialog.getFile() == null) {
            System.out.println("No File Selected!!!");
        } else {
            String currDocPath = fileDialog.getDirectory() + fileDialog.getFile();
            FileWorker.write(currDocPath + ".txt", passwordsTA.getText());
        }
    }

    @FXML
    public void clearTextArea() {
        passwordsTA.clear();
    }
}
