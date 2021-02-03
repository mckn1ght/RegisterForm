package sample;

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

    boolean clean;

    public void checkEntry() {
        clean = true;
        Pattern specialCharacters = Pattern.compile("[^a-z0-9 ]", Pattern.CASE_INSENSITIVE);
        Pattern onlyLetters = Pattern.compile("[^a-z]", Pattern.CASE_INSENSITIVE);
        Pattern onlyNumbers = Pattern.compile("[^0-9]");

        Matcher matcherUsernameField = specialCharacters.matcher(usernameField.getText());
        Matcher matcherFirstnameField= onlyLetters.matcher(firstNameField.getText());
        Matcher matcherLastnameField = onlyLetters.matcher(lastNameField.getText());
        Matcher matcherEmailField = specialCharacters.matcher(emailField.getText());
        Matcher matcherPhoneNumberField = onlyNumbers.matcher(phoneNumberField.getText());

//username check
        if (matcherUsernameField.find()){
            error.setVisible(true);
            error.setText("Username must contain only letters or numbers");

            usernameField.setStyle("-fx-background-color: red");
            clean = false;
        }
        else {
            resetTextField(usernameField);
        }

//first name check
        if(matcherFirstnameField.find()){
            error.setVisible(true);
            error.setText("First name must contain only letters");
            firstNameField.setStyle("-fx-background-color: red");
            clean = false;
        }
        else {

            resetTextField(firstNameField);
        }

//last name check
        if(matcherLastnameField.find()) {
            error.setVisible(true);
            error.setText("Last name must contain only letters");
            lastNameField.setStyle("-fx-background-color: red");
            clean = false;
        }
        else {

            resetTextField(lastNameField);
        }

//email adress check
        if(matcherEmailField.find()){
            error.setVisible(true);
            error.setText("That is not a valid email adress");
            emailField.setStyle("-fx-background-color: red");
            clean = false;
        }
        else {

            resetTextField(emailField);
        }

//phone number check

        if(phoneNumberField.getText().length() < 5 || phoneNumberField.getText().length() > 15 || matcherPhoneNumberField.find()) {

            error.setVisible(true);
            error.setText("That is not a valid phone number");
            phoneNumberField.setStyle("-fx-background-color: red");
            clean = false;
        }
        else{
            resetTextField(phoneNumberField);
        }




    }

    public void resetTextField(TextField tf){
        if(clean == true)
            error.setVisible(false);
        tf.setStyle(null);

    }

    public void resetPasswordField(PasswordField pw , PasswordField confirmpw){
        if(clean == true) {
            error.setVisible(false);
            pw.setStyle(null);
            confirmpw.setStyle(null);
        }
    }


    public void checkPass() {
        clean = true;
        Tooltip tooltip = new Tooltip();
        tooltip.setText("PASSWORDS DO NOT MATCH!");
        tooltip.activatedProperty();

        if (passwordField.getText().equals(confirmPasswordField.getText()) || (confirmPasswordField.getText().equals(passwordField.getText()))) {
            Pattern onlyNumbers = Pattern.compile("[^0-9]");
            Pattern capital = Pattern.compile("[^A-Z]");
            Pattern special = Pattern.compile("[^a-z0-9 ]",Pattern.CASE_INSENSITIVE);

            Matcher specialPasswordField = special.matcher(passwordField.getText());
            Matcher numbersPasswordField = onlyNumbers.matcher(passwordField.getText());
            Matcher capitalLetterPasswordField = capital.matcher(passwordField.getText());
            if(!numbersPasswordField.find()) {
                passwordField.setTooltip(tooltip);
                confirmPasswordField.setTooltip(tooltip);
                error.setVisible(true);
                error.setText("Passwords must contain at least 1 number");
                confirmPasswordField.setStyle("-fx-background-color: red");
                passwordField.setStyle("-fx-background-color: red");
                clean = false;
            }
            else if(!capitalLetterPasswordField.find()){

                passwordField.setTooltip(tooltip);
                confirmPasswordField.setTooltip(tooltip);
                error.setVisible(true);
                error.setText("Passwords must contain at least 1 capital letter");
                confirmPasswordField.setStyle("-fx-background-color: red");
                passwordField.setStyle("-fx-background-color: red");
                clean = false;
            }

            else if(!capitalLetterPasswordField.find()){

                passwordField.setTooltip(tooltip);
                confirmPasswordField.setTooltip(tooltip);
                error.setVisible(true);
                error.setText("Passwords must contain at least 1 capital letter");
                confirmPasswordField.setStyle("-fx-background-color: red");
                passwordField.setStyle("-fx-background-color: red");
                clean = false;
            }
            else if(!specialPasswordField.find()){
                passwordField.setTooltip(tooltip);
                confirmPasswordField.setTooltip(tooltip);
                error.setVisible(true);
                error.setText("Passwords must contain at least 1 special character");
                confirmPasswordField.setStyle("-fx-background-color: red");
                passwordField.setStyle("-fx-background-color: red");
                clean = false;
            }
        }else{
            resetPasswordField(passwordField, confirmPasswordField);
        }
    }
    public void register(){
        if(clean == true) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Succes!");
            alert.setHeaderText(null);
            alert.setContentText("Account created, you can now log in.");
            alert.showAndWait();
        }else{
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("ERROR!");
            alert.setHeaderText(null);
            alert.setContentText("Please correct all the issues first");
            alert.showAndWait();
        }
    }
}