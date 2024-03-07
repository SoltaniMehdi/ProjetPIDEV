package controller;

import entities.User;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import services.AuthService;

import java.io.IOException;

public class LoginController {
    @FXML
    private AnchorPane ap;

    @FXML
    private Button login;

    @FXML
    private PasswordField pw;

    @FXML
    private TextField un;

    @FXML
    private void initialize() {
        Platform.runLater(() -> ap.requestFocus());
    }


    @FXML
    public void handleLoginButton(ActionEvent event) {
        String username = un.getText();
        String password = pw.getText();

        if (AuthService.authenticate(username, password)) {
            showAlert("Login Successful", Alert.AlertType.INFORMATION);

            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/commande.fxml"));
                Parent root = loader.load();

                Scene scene = new Scene(root);
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.setScene(scene);
                stage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }

        } else {
            showAlert("Login Failed", Alert.AlertType.ERROR);
        }
    }


    public void showAlert(String message, Alert.AlertType alertType) {
        Alert alert = new Alert(alertType);
        alert.setTitle("Alert");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

}


