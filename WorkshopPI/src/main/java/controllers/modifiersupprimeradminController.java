package controllers;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.Random;
import java.util.ResourceBundle;

import entities.Utilisateur;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import services.CRUDUtilisateur;

import javax.swing.*;

import static entities.Auth.getCurrentUtilisateur;


public class modifiersupprimeradminController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;
    @FXML
    private TextField modsuppadmin_role;


    @FXML
    private TextField modsuppadmin_mail;

    @FXML
    private TextField modsuppadmin_nom;

    @FXML
    private TextField modsuppadmin_numero;

    @FXML
    private TextField modsuppadmin_password;

    @FXML
    private TextField modsuppadmin_prenom;

    private static Utilisateur currentUtilisateur;

    private Utilisateur selectedUser;
    public void setSelectedUser(Utilisateur user) {
        selectedUser = user;
        // Now you can use 'selectedUser' in your controller
        // For example, you can populate the fields in the UI with user information
        if (selectedUser != null) {
            modsuppadmin_nom.setText(selectedUser.getNom());
            modsuppadmin_prenom.setText(selectedUser.getPrenom());
            modsuppadmin_numero.setText(selectedUser.getNumero());
            modsuppadmin_mail.setText(selectedUser.getMail());
            modsuppadmin_password.setText(selectedUser.getPassword());
            modsuppadmin_role.setText(selectedUser.getRole());
        }
    }

    @FXML
    void modsuppadmin_modifier(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        // Vérifier si tous les champs sont remplis
        if (modsuppadmin_mail.getText().isEmpty() || modsuppadmin_password.getText().isEmpty() || modsuppadmin_nom.getText().isEmpty() || modsuppadmin_prenom.getText().isEmpty() || modsuppadmin_numero.getText().isEmpty() ) {
            alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur de saisie");
            alert.setHeaderText("Veuillez remplir tous les champs");
            alert.showAndWait();
            return;
        }

// Vérifier si le format de l'email est valide
        else if (!modsuppadmin_mail.getText().matches("[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}")) {
            alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur de saisie");
            alert.setHeaderText("L'adresse e-mail est invalide.");
            alert.showAndWait();
            return;
        }

// Vérifier si le format du numéro de téléphone est valide
        else if (!modsuppadmin_numero.getText().matches("[0-9]{8}")) {
            // afficher le message d'erreur dans une alert box
            alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur de saisie");
            alert.setHeaderText("Le numéro de téléphone est invalide.");
            alert.showAndWait();
            return;
        }
        else if (!modsuppadmin_prenom.getText().matches("[a-zA-Z]+")) {
            alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur de saisie");
            alert.setHeaderText("Le prenom est invalide.");
            alert.showAndWait();
            return;
        }
        else if (!modsuppadmin_nom.getText().matches("[a-zA-Z]+")) {
            alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur de saisie");
            alert.setHeaderText("Le nom est invalide.");
            alert.showAndWait();
            return;
        }
        Utilisateur utilisateurActuel = selectedUser;
        if (utilisateurActuel != null) {
            // Assignez l'utilisateur actuel à currentUtilisateur
            currentUtilisateur = utilisateurActuel;

            CRUDUtilisateur crudUtilisateur = new CRUDUtilisateur();
            Utilisateur newUser = new Utilisateur(currentUtilisateur.getID_user(), modsuppadmin_nom.getText(),
                    modsuppadmin_prenom.getText(), modsuppadmin_numero.getText(),
                    modsuppadmin_password.getText(),modsuppadmin_mail.getText(), modsuppadmin_role.getText());
            crudUtilisateur.modifier(newUser);
            JOptionPane.showMessageDialog(null, "Modification effectuée! ");
            System.out.println("Utilisateur modifié avec succès !");}
                else{
                System.out.println("L'utilisateur actuel est null. Impossible de procéder.");
            }


    }
}

