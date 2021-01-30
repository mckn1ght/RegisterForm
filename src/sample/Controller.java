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

    public void checkEntry() {
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
        }
        else {
            error.setVisible(false);
            usernameField.setStyle(null);
        }
//first name check
        if(matcherFirstnameField.find()){
            error.setVisible(true);
            error.setText("First name must contain only letters");
            firstNameField.setStyle("-fx-background-color: red");
        }
        else {
            error.setVisible(false);
            firstNameField.setStyle(null);
        }
//last name check
        if(matcherLastnameField.find()) {
            error.setVisible(true);
            error.setText("Last name must contain only letters");
            lastNameField.setStyle("-fx-background-color: red");
        }
        else {
            error.setVisible(false);
            lastNameField.setStyle(null);
        }
//email adress check
        if(matcherEmailField.find()){
            error.setVisible(true);
            error.setText("That is not a valid email adress");
           emailField.setStyle("-fx-background-color: red");
        }
        else {
            error.setVisible(false);
            emailField.setStyle(null);
        }
//phone number check
        if(matcherPhoneNumberField.find() && (phoneNumberField.getText().length() < 5 || phoneNumberField.getText().length() > 15)){
            error.setVisible(true);
            error.setText("That is not a valid phone number");
            phoneNumberField.setStyle("-fx-background-color: red");
        }
        else {
            error.setVisible(false);
            phoneNumberField.setStyle(null);
        }
    }

    public void checkPass() {
        Tooltip tooltip = new Tooltip();
        tooltip.setText("PASSWORDS DO NOT MATCH!");
        tooltip.activatedProperty();

        if (!passwordField.getText().equals(confirmPasswordField.getText()) || !(confirmPasswordField.getText().equals(passwordField.getText()))) {
            passwordField.setTooltip(tooltip);
            confirmPasswordField.setTooltip(tooltip);
            error.setVisible(true);
            error.setText("Passwords do not match");
            confirmPasswordField.setStyle("-fx-background-color: red");
            passwordField.setStyle("-fx-background-color: red");
        }else{
            error.setVisible(false);
            confirmPasswordField.setStyle(null);
            passwordField.setStyle(null);
        }
    }
    public void register(){
        if(error.isVisible() == false) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Succes!");
            alert.setHeaderText(null);
            alert.setContentText("Account created, you can now log in.");
            alert.showAndWait();
        }else{

        }
    }
}