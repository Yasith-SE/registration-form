package edu.icet.controller;

import com.jfoenix.controls.JFXTextField;
import edu.icet.service.UserLoginService;
import edu.icet.service.impl.UserLoginServiceImpl;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class userLoginController {

    UserLoginService loginService = new UserLoginServiceImpl();

    @FXML
    private Label lblEmalValidation;

    @FXML
    private Label lblValidation;

    @FXML
    private JFXTextField txtEmail;

    @FXML
    private JFXTextField txtPassword;

    @FXML
    void btnCreateAccount(ActionEvent event) {

    }

    @FXML
    void btnSignInOnAction(ActionEvent event) {
        lblValidation.setText("");
        lblEmalValidation.setText("");

        String email = txtEmail.getText();
        String password = txtPassword.getText();

        if (email.isEmpty() || password.isEmpty()) {
            lblValidation.setText("Please enter both email and password.");
            return;
        }

        if (!email.endsWith("@gmail.com")) {
            lblEmalValidation.setText("Please use a valid @gmail.com address.");
            return;
        }

        try {
            String hashedPassword = hashPassword(password);

            boolean isAuthenticated = loginService.authenticateUser(email, hashedPassword);

            if (isAuthenticated) {

                System.out.println("Login Successful!");
                navigateTo("/view/dashboard.fxml");
            } else {
                lblValidation.setText("Invalid credentials. If you are new, please Create an Account.");
            }

        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            lblValidation.setText("Security Error occurred.");
        }
    }


    private void navigateTo(String fxmlPath) {
        try {
            Stage stage = (Stage) txtEmail.getScene().getWindow();
            stage.setScene(new Scene(FXMLLoader.load(getClass().getResource(fxmlPath))));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
            lblValidation.setText("Error loading page: " + fxmlPath);
        }
    }


    private String hashPassword(String originalPassword) throws NoSuchAlgorithmException {
        MessageDigest digest = MessageDigest.getInstance("SHA-256");
        byte[] encodedhash = digest.digest(originalPassword.getBytes(StandardCharsets.UTF_8));
        return bytesToHex(encodedhash);
    }

    private String bytesToHex(byte[] hash) {
        StringBuilder hexString = new StringBuilder(2 * hash.length);
        for (byte b : hash) {
            String hex = Integer.toHexString(0xff & b);
            if (hex.length() == 1) {
                hexString.append('0');
            }
            hexString.append(hex);
        }
        return hexString.toString();

    }

}
