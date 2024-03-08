package controllers;

import entities.Utilisateur;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import services.CRUDUtilisateur;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import static entities.Auth.checkEmail;

public class Inscriptioncontroller {
CRUDUtilisateur crudUtilisateur= new CRUDUtilisateur();

    @FXML
    private TextField ins_mail;

    @FXML
    private TextField ins_nom;

    @FXML
    private TextField ins_numero;

    @FXML
    private PasswordField ins_password;

    @FXML
    private TextField ins_prenom;

    @FXML
    void inscrire(ActionEvent event) throws Exception {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);

    // Vérifier si tous les champs sont remplis
        if (ins_mail.getText().isEmpty() || ins_password.getText().isEmpty() || ins_nom.getText().isEmpty() || ins_prenom.getText().isEmpty() || ins_numero.getText().isEmpty() ) {
            alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur de saisie");
            alert.setHeaderText("Veuillez remplir tous les champs");
            alert.showAndWait();
            return;
        }

// Vérifier si le format de l'email est valide
        else if (!ins_mail.getText().matches("[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}")) {
            alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur de saisie");
            alert.setHeaderText("L'adresse e-mail est invalide.");
            alert.showAndWait();
            return;
        }

// Vérifier si le format du numéro de téléphone est valide
       else if (!ins_numero.getText().matches("[0-9]{8}")) {
            // afficher le message d'erreur dans une alert box
            alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur de saisie");
            alert.setHeaderText("Le numéro de téléphone est invalide.");
            alert.showAndWait();
            return;
        }
        else if (!ins_prenom.getText().matches("[a-zA-Z]+")) {
            alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur de saisie");
            alert.setHeaderText("Le prenom est invalide.");
            alert.showAndWait();
            return;
        }
        else if (!ins_nom.getText().matches("[a-zA-Z]+")) {
            alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur de saisie");
            alert.setHeaderText("Le nom est invalide.");
            alert.showAndWait();
            return;
        }
        crudUtilisateur.ajouterUtilisateur(new Utilisateur(ins_nom.getText(), ins_prenom.getText(),  ins_numero.getText(), ins_password.getText(), ins_mail.getText()));
        alert.setTitle("success");
        alert.setContentText("Client ajouté avec succée");
        alert.showAndWait();
        //taadi l page login
        Parent root = FXMLLoader.load(getClass().getResource("/login.fxml"));
        ins_nom.getScene().setRoot(root);


/*
        if (checkEmail(mail)) {
            // afficher un message d'erreur dans une alert box
            alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("E-mail existant");
            alert.setHeaderText("Un compte avec cet e-mail existe déjà.");
            alert.showAndWait();
            return;
        }
/*
        // Vérifier si les deux mots de passe sont identiques
        if (!password.equals(passwordConfirm)) {
            // afficher le message d'erreur dans une alert box
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Erreur de saisie");
            alert.setHeaderText("Les deux mots de passe ne sont pas identiques");
            alert.showAndWait();
            return;
        }

 */


    }


    @FXML
    void dejainscrit(ActionEvent event) throws IOException {
        try {Parent root;
            Scene scene;
            Stage stage;
            root = (Parent) FXMLLoader.load(this.getClass().getResource("/login.fxml"));
            scene = new Scene(root);
            stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
