package net.explorator.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import net.explorator.model.PasswordFactory;
import net.explorator.model.FileWorker;
import net.explorator.model.PasswordAttributes;

import java.awt.*;
import java.util.List;


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
        }

        int quantity = 0;
        try {
            quantity = Integer.parseInt(quantityTextField.getText());
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }

        List<String> passwordsArray = PasswordFactory.generateMultiplePasswords(attributes, length, quantity);
        passwords = "";
        for (String password : passwordsArray) {
            passwords += password + "\n";
        }
        passwordsTA.setText(passwords);
    }

    @FXML
    public void saveToFile() {
        FileDialog fileDialog = new FileDialog(new Frame(), "Save file as...", FileDialog.SAVE);
        fileDialog.setVisible(true);
        if (fileDialog.getDirectory() == null || fileDialog.getFile() == null) {
            System.out.println("No File Selected!!!");
        } else {
            String currDocPath = fileDialog.getDirectory() + fileDialog.getFile();
            FileWorker.write(currDocPath + ".txt", passwords);
        }
    }

    @FXML
    public void clearTA() {
        passwordsTA.clear();
    }
}
