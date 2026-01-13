package edu.icet.controller;

import com.jfoenix.controls.JFXTextField;
import edu.icet.model.signup;
import edu.icet.service.impl.signupServiceImpl;
import edu.icet.service.signupService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.regex.Pattern;

public class signupController {

    signupService signupService = new signupServiceImpl();

    signup signup = new signup();

    @FXML
    private Label lblEmailValidation;

    @FXML
    private Label lblSignValidation;

    @FXML
    private JFXTextField txtConfirmPassword;

    @FXML
    private JFXTextField txtEmailAddress;

    @FXML
    private JFXTextField txtFirstName;

    @FXML
    private JFXTextField txtLastName;

    @FXML
    private JFXTextField txtPassword;

    @FXML
    void btnBackToLogin(ActionEvent event) {





    }

    @FXML
    void btnRegister(ActionEvent event) {
        lblSignValidation.setText("");
        lblEmailValidation.setText("");

        String firstName = txtFirstName.getText();
        String lastName = txtLastName.getText();
        String email = txtEmailAddress.getText();
        String password = txtPassword.getText();
        String confirmPassword = txtConfirmPassword.getText();

        if(firstName.isEmpty() || lastName.isEmpty() || email.isEmpty() || password.isEmpty() || confirmPassword.isEmpty()){
            lblSignValidation.setText("You are not allowed to keep empty form. Fill all information");
            return;
        }

        if(!email.endsWith("@gmail.com")){
            lblEmailValidation.setText("Please enter a valid @gmail.com address");
            return;
        }

        if(!password.equals(confirmPassword)) {
            lblSignValidation.setText("Passwords do not match!");
            return;
        }

        String passwordRegex = "^(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#$%^&*]).{8,}$";
        if (!Pattern.matches(passwordRegex, password)) {
            lblSignValidation.setText("Password too weak! Needs 8+ chars, 1 Uppercase, 1 Lowercase, 1 Symbol.");
            return;
        }
        try {
            String hashedPassword = hashPassword(password);

            signup newUser = new signup();
            newUser.setFirstName(firstName);
            newUser.setLastName(lastName);
            newUser.setEmailAddress(email);
            newUser.setPassword(hashedPassword);

            signupService.register(newUser);

            System.out.println("User Registered successfully with Hash: " + hashedPassword);
            lblSignValidation.setText("Registration Successful!");

            clearForm();

        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            lblSignValidation.setText("Error processing security.");
        } catch (RuntimeException e) {
            // Catches the RuntimeException thrown by the Service if SQL fails
            e.printStackTrace();
            lblSignValidation.setText("Error: Email might already exist.");
        }
    }

    private void clearForm() {
        txtFirstName.setText("");
        txtLastName.setText("");
        txtEmailAddress.setText("");
        txtPassword.setText("");
        txtConfirmPassword.setText("");
    }

    private String hashPassword(String originalPassword) throws NoSuchAlgorithmException {
        MessageDigest digest = MessageDigest.getInstance("SHA-256");
        byte[] encodedhash = digest.digest(originalPassword.getBytes(StandardCharsets.UTF_8));
        return bytesToHex(encodedhash);
    }

    private String bytesToHex(byte[] hash) {
        StringBuilder hexString = new StringBuilder(2 * hash.length);
        for (int i = 0; i < hash.length; i++) {
            String hex = Integer.toHexString(0xff & hash[i]);
            if(hex.length() == 1) {
                hexString.append('0');
            }
            hexString.append(hex);
        }
        return hexString.toString();
    }
}






