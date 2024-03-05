package controllers;


import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import entities.Auth;
import entities.Utilisateur;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class Logincontroller {

        @FXML
        private ResourceBundle resources;

        @FXML
        private URL location;

        @FXML
        private TextField log_mail;

        @FXML
        private TextField log_password;

        @FXML
        void login(ActionEvent event) {
            String mail = this.log_mail.getText();
            String password = this.log_password.getText();
            Utilisateur currentUtilisateur = Auth.signIn(mail, password);
            if (currentUtilisateur != null) {
                // Authentification réussie, vérifie le rôle de l'utilisateur
                String role = currentUtilisateur.getRole();
                if (role != null && role.equals("admin")) {
                    try {
                        Parent root;
                        Scene scene;
                        Stage stage;
                        root = FXMLLoader.load(this.getClass().getResource("/Admin.fxml"));
                        scene = new Scene(root);
                        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                        stage.setScene(scene);
                        stage.show();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                } else if (role != null && role.equals("client")) {
                    try {
                        FXMLLoader loader = new FXMLLoader(getClass().getResource("/modifiersupprimerclient.fxml"));
                        Parent root = loader.load();
                        modifiersupprimerclientController ClientController = loader.getController();
                        ClientController.setCurrentUser(currentUtilisateur); // Pass the selected user to the controller
                        System.out.println(currentUtilisateur);
                        Scene scene = new Scene(root);
                        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                        stage.setScene(scene);
                        stage.show();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            } else {
                // Afficher un message d'erreur si l'authentification échoue
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Erreur d'authentification");
                alert.setHeaderText(null);
                alert.setContentText("Adresse e-mail ou mot de passe incorrect.");
                alert.showAndWait();
            }
        }
        /*
    @FXML
    void mtpOublie(ActionEvent event) {
        try {Parent root;
            Scene scene;
            Stage stage;
            root = (Parent) FXMLLoader.load(this.getClass().getResource("/motDePasseOublie.fxml"));
            scene = new Scene(root);
            stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
*/
}
