package org.passgen.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import org.passgen.model.FileWorker;
import org.passgen.model.PasswordAttributes;

import java.awt.*;

import static org.passgen.model.PasswordFactory.generateMultiplePasswords;


public class Controller {

    private static String passwords;

    @FXML
    CheckBox capitalCheckBox;
    @FXML
    CheckBox smallCheckBox;
    @FXML
    CheckBox numbersCheckBox;
    @FXML
    CheckBox symbolsCheckBox;
    @FXML
    TextField lengthTextField;
    @FXML
    TextField quantityTextField;
    @FXML
    TextArea passwordsTA;
    @FXML
    Button saveButton;
    @FXML
    Button generateButton;

    @FXML
    public void updateTextArea() {
        
        generatePasswords();
        passwordsTA.setText(passwords);
    }

    private void generatePasswords() {
        PasswordAttributes attributes = new PasswordAttributes(
                capitalCheckBox.isSelected(),
                smallCheckBox.isSelected(),
                numbersCheckBox.isSelected(),
                symbolsCheckBox.isSelected()
        );
        int length = 0;
        try {
            length = Integer.parseInt(lengthTextField.getText());
        } catch (NumberFormatException e) {
            e.printStackTrace();
            System.out.println("Wrong password length typed");
        }
        int quantity = 0;
        try {
            length = Integer.parseInt(quantityTextField.getText());
        } catch (NumberFormatException e) {
            e.printStackTrace();
            System.out.println("Wrong passwords' quantity typed");
        }
        String passwordsArray[] = generateMultiplePasswords(attributes, length, quantity);
        passwords = "";
        for (String password : passwordsArray) {
            passwords += password + "\n";
        }
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
    public void clearTA() {
        passwordsTA.clear();
    }
}
