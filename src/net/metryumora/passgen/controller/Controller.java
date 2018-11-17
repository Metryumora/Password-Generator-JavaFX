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

    private static final String DEFAULT_FILE_NAME = "passwords.txt";

    @FXML Button generateButton;
    @FXML Button saveButton;
    @FXML CheckBox smallCheckBox;
    @FXML CheckBox capitalCheckBox;
    @FXML CheckBox numbersCheckBox;
    @FXML CheckBox symbolsCheckBox;
    @FXML TextField passwordLengthTF;
    @FXML TextField passwordsNumberTF;
    @FXML TextArea passwordsTA;

    private int passwordLength = 0;
    private int passwordsNumber = 0;

    @FXML
    private void initialize() {
        smallCheckBox.setFocusTraversable(false);
        capitalCheckBox.setFocusTraversable(false);
        numbersCheckBox.setFocusTraversable(false);
        symbolsCheckBox.setFocusTraversable(false);
        passwordLengthTF.setFocusTraversable(false);
        passwordsNumberTF.setFocusTraversable(false);

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
        PasswordAttributes attributes = getPasswordAttributes();
        setPasswordLength();
        setPasswordsNumber();
        generateAndShowPasswords(attributes);
    }

    private void generateAndShowPasswords(PasswordAttributes attributes) {
        List<String> passwordsList = PasswordFactory.generateMultiplePasswords(attributes, passwordLength, passwordsNumber);
        StringBuilder passwords = new StringBuilder();
        for (String password : passwordsList) {
            passwords.append(password).append("\n");
        }
        passwordsTA.setText(passwords.toString());
    }

    private void setPasswordsNumber() {
        try {
            passwordsNumber = Integer.parseInt(passwordsNumberTF.getText());
        } catch (NumberFormatException e) {
            passwordsTA.setText("Quantity should be a number grater than 0 :)");
            passwordsNumberTF.setText("1");
        }
    }

    private void setPasswordLength() {
        try {
            passwordLength = Integer.parseInt(passwordLengthTF.getText());
        } catch (NumberFormatException e) {
            passwordsTA.setText("Length should be a number grater than 0 :)");
            passwordLengthTF.setText("20");
        }
    }

    private PasswordAttributes getPasswordAttributes() {
        return new PasswordAttributes(
                    capitalCheckBox.isSelected(),
                    smallCheckBox.isSelected(),
                    numbersCheckBox.isSelected(),
                    symbolsCheckBox.isSelected()
            );
    }

    @FXML
    public void saveToFile() {
        FileDialog fileDialog = createAndShowFileDialog();

        if (hasProperFileChosen(fileDialog)) {
            String currDocPath = fileDialog.getDirectory() + fileDialog.getFile();
            FileWorker.write(currDocPath, passwordsTA.getText());
        }
    }

    private boolean hasProperFileChosen(FileDialog fileDialog) {
        return fileDialog.getDirectory() != null && fileDialog.getFile() != null;
    }

    private FileDialog createAndShowFileDialog() {
        Frame frame = createCenteredFrame();

        FileDialog fileDialog = new FileDialog(frame, "Save file as...", FileDialog.SAVE);
        fileDialog.setFile(DEFAULT_FILE_NAME);
        fileDialog.setVisible(true);
        return fileDialog;
    }

    private Frame createCenteredFrame() {
        Frame frame = new Frame();
        frame.setLocationRelativeTo(null);
        return frame;
    }

    @FXML
    public void clearTextArea() {
        passwordsTA.clear();
    }
}
