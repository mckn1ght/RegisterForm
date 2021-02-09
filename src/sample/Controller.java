package sample;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class Controller {
    @FXML
    TextField usernameField;

    @FXML
    PasswordField passwordField;

    @FXML
    PasswordField confirmPasswordField;

    @FXML
    TextField firstNameField;

    @FXML
    TextField lastNameField;

    @FXML
    TextField emailField;

    @FXML
    TextField phoneNumberField;

    @FXML
    Label error;

    @FXML
    Button okButton;

    @FXML
    Button quitButton;

    boolean username, firstname, lastname, email, phonenumber, password, confirmpassword;

    public void checkUsernameField(){

        Pattern specialCharacters = Pattern.compile("[^a-z0-9 ]", Pattern.CASE_INSENSITIVE);
        Matcher matcherUsernameField = specialCharacters.matcher(usernameField.getText());
        username = true;
        if (matcherUsernameField.find()) {
            error.setVisible(true);
            error.setText("Username must contain only letters or numbers");
            usernameField.setStyle("-fx-background-color: red");
            username = false;
        } else {
            resetTextField(usernameField);
        }
    }

    public void checkFirstNameField(){
        firstname = true;
        Pattern onlyLetters = Pattern.compile("[^a-z]", Pattern.CASE_INSENSITIVE);
        Matcher matcherFirstnameField= onlyLetters.matcher(firstNameField.getText());
        if(matcherFirstnameField.find()){
            error.setVisible(true);
            error.setText("First name must contain only letters");
            firstNameField.setStyle("-fx-background-color: red");
            firstname = false;
        }
        else {
            resetTextField(firstNameField);
        }
    }

    public void checkLastNameField(){
        lastname = true;
        Pattern onlyLetters = Pattern.compile("[^a-z]", Pattern.CASE_INSENSITIVE);
        Matcher matcherLastnameField = onlyLetters.matcher(lastNameField.getText());
        if(matcherLastnameField.find()) {
            error.setVisible(true);
            error.setText("Last name must contain only letters");
            lastNameField.setStyle("-fx-background-color: red");
            lastname = false;
        }
        else {
            resetTextField(lastNameField);
        }
    }

    public void checkEmailAdressField(){
        email = true;
        Pattern specialCharacters = Pattern.compile("(?:[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*|\"(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21\\x23-\\x5b\\x5d-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])*\")@(?:(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?|\\[(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?|[a-z0-9-]*[a-z0-9]:(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21-\\x5a\\x53-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])+)\\])", Pattern.CASE_INSENSITIVE);
        Matcher matcherEmailField = specialCharacters.matcher(emailField.getText());
        if(!emailField.getText().equals("")) {
            if (!matcherEmailField.find()) {
                error.setVisible(true);
                error.setText("That is not a valid email adress");
                emailField.setStyle("-fx-background-color: red");
                email = false;
            } else {
                resetTextField(emailField);
            }
        }else {
            resetTextField(emailField);
        }
    }

    public void checkPhoneNumberField(){
        phonenumber = true;
        Pattern onlyNumbers = Pattern.compile("[^0-9]");
        Matcher matcherPhoneNumberField = onlyNumbers.matcher(phoneNumberField.getText());
        if((phoneNumberField.getText().length() < 5 && phoneNumberField.getText().length() > 0) ||  phoneNumberField.getText().length() > 15 || matcherPhoneNumberField.find()) {
            error.setVisible(true);
            error.setText("That is not a valid phone number");
            phoneNumberField.setStyle("-fx-background-color: red");
            phonenumber = false;
        }
        else{
            resetTextField(phoneNumberField);
        }
    }

    public boolean checkPasswordField() {
        password = true;
        String specialChars = "~`!@#$%^&*()-_=+\\|[{]};:'\",<.>/?";
        char currentCharacter;
        boolean numberPresent = false;
        boolean upperCasePresent = false;
        boolean specialCharacterPresent = false;
        if(!passwordField.getText().equals("")) {
            for (int i = 0; i < passwordField.getText().length(); i++) {
                currentCharacter = passwordField.getText().charAt(i);
                if (Character.isDigit(currentCharacter)) {
                    numberPresent = true;
                } else if (Character.isUpperCase(currentCharacter)) {
                    upperCasePresent = true;
                } else if (specialChars.contains(String.valueOf(currentCharacter))) {
                    specialCharacterPresent = true;
                }
            }

            if ((passwordField.getText().length() > 0 && passwordField.getText().length() < 8) || passwordField.getText().length() > 20) {
                error.setVisible(true);
                error.setText("Password must be between 8 and 20 characters long");
                passwordField.setStyle("-fx-background-color: red");
                password = false;
                return false;
            }
            if (!numberPresent) {
                error.setVisible(true);
                error.setText("Password must contain at least 1 number");
                passwordField.setStyle("-fx-background-color: red");
                password = false;
                return false;
            }
            if (!upperCasePresent) {
                error.setVisible(true);
                error.setText("Password must contain at least 1 Upper case");
                passwordField.setStyle("-fx-background-color: red");
                password = false;
                return false;
            }
            if (!specialCharacterPresent) {
                error.setVisible(true);
                error.setText("Password must contain at least 1 special character");
                passwordField.setStyle("-fx-background-color: red");
                password = false;
                return false;
            }
            if (!confirmPasswordField.getText().equals(passwordField.getText())) {
                error.setVisible(true);
                error.setText("Passwords do not match");
                confirmPasswordField.setStyle("-fx-background-color: red");
                password = false;
                return false;
            } else {
                resetPasswordField(passwordField, confirmPasswordField);
            }
            if (password) {
                resetPasswordField(passwordField);
                return true;
            }
        }else{
            resetPasswordField(passwordField);
            return false;
        }
        return true;
    }


    public void checkConfirmPasswordField(){
        confirmpassword = true;
        if(!confirmPasswordField.getText().equals("")) {
            if (!confirmPasswordField.getText().equals(passwordField.getText())) {
                confirmpassword = false;
                error.setVisible(true);
                error.setText("Passwords do not match");
                confirmPasswordField.setStyle("-fx-background-color: red");
            } else if (password && checkPasswordField()) {
                resetPasswordField(confirmPasswordField, passwordField);
            }else checkPasswordField();
        }else resetPasswordField(confirmPasswordField);
    }

    public void register() {
        if (usernameField.getText().isEmpty() || firstNameField.getText().isEmpty() || lastNameField.getText().isEmpty() || emailField.getText().isEmpty() || phoneNumberField.getText().isEmpty() ||
                passwordField.getText().isEmpty() || confirmPasswordField.getText().isEmpty()){
            error.setVisible(true);
            error.setText("You must complete all the fields first");
        }else {
            if (username && firstname && lastname && email && phonenumber && password && confirmpassword) {
                //First name and Last name formatter
                //firstname
                String capitalLetter = firstNameField.getText();
                StringBuilder sb = new StringBuilder(capitalLetter);
                sb.setCharAt(0, Character.toUpperCase(sb.charAt(0)));
                capitalLetter = sb.toString();
                firstNameField.setText(capitalLetter);

                //lastname
                capitalLetter = lastNameField.getText();
                sb = new StringBuilder(capitalLetter);
                sb.setCharAt(0, Character.toUpperCase(sb.charAt(0)));
                capitalLetter = sb.toString();
                lastNameField.setText(capitalLetter);
                //

                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Succes!");
                alert.setHeaderText(null);
                alert.setContentText("Account created, you can now log in.");
                alert.showAndWait();
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("ERROR!");
                alert.setHeaderText(null);
                alert.setContentText("Please correct all the issues first");
                alert.showAndWait();
            }
        }
    }

    public void resetTextField(TextField tf){
        if(username || firstname || lastname || email || phonenumber || password || confirmpassword) {
            error.setVisible(false);
            tf.setStyle(null);
        }

    }

    public void resetPasswordField(PasswordField pw , PasswordField confirmpw){
        if(username || firstname || lastname || email || phonenumber || password || confirmpassword) {
            error.setVisible(false);
            pw.setStyle(null);
            confirmpw.setStyle(null);
        }
    }

    public void resetPasswordField(PasswordField pw){
        if(username || firstname || lastname || email || phonenumber || password || confirmpassword) {
            error.setVisible(false);
            pw.setStyle(null);
        }
    }

    public void quit(){
        Platform.exit();
    }
}
