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
import javafx.stage.Stage;
import services.CRUDUtilisateur;
import utils.SendSMS;
import utils.SendMail;

import javax.swing.*;

import static entities.Auth.getCurrentUtilisateur;

public class modifiersupprimerclientController {
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField modsupp_mail;

    @FXML
    private TextField modsupp_nom;

    @FXML
    private TextField modsupp_numero;

    @FXML
    private TextField modsupp_password;

    @FXML
    private TextField modsupp_prenom;
    private static Utilisateur currentUtilisateur;
    public void setCurrentUser(Utilisateur user) {
        currentUtilisateur = user;
        // Now you can use 'selectedUser' in your controller
        // For example, you can populate the fields in the UI with user information
        if (currentUtilisateur != null) {
            modsupp_nom.setText(currentUtilisateur.getNom());
            modsupp_prenom.setText(currentUtilisateur.getPrenom());
            modsupp_numero.setText(currentUtilisateur.getNumero());
            modsupp_mail.setText(currentUtilisateur.getMail());
            modsupp_password.setText(currentUtilisateur.getPassword());
        }
    }
    @FXML
    void modsupp_modifier(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        // Vérifier si tous les champs sont remplis
        if (modsupp_mail.getText().isEmpty() || modsupp_password.getText().isEmpty() || modsupp_nom.getText().isEmpty() || modsupp_prenom.getText().isEmpty() || modsupp_numero.getText().isEmpty() ) {
            alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur de saisie");
            alert.setHeaderText("Veuillez remplir tous les champs");
            alert.showAndWait();
            return;
        }

// Vérifier si le format de l'email est valide
        else if (!modsupp_mail.getText().matches("[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}")) {
            alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur de saisie");
            alert.setHeaderText("L'adresse e-mail est invalide.");
            alert.showAndWait();
            return;
        }

// Vérifier si le format du numéro de téléphone est valide
        else if (!modsupp_numero.getText().matches("[0-9]{8}")) {
            // afficher le message d'erreur dans une alert box
            alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur de saisie");
            alert.setHeaderText("Le numéro de téléphone est invalide.");
            alert.showAndWait();
            return;
        }
        else if (!modsupp_prenom.getText().matches("[a-zA-Z]+")) {
            alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur de saisie");
            alert.setHeaderText("Le prenom est invalide.");
            alert.showAndWait();
            return;
        }
        else if (!modsupp_nom.getText().matches("[a-zA-Z]+")) {
            alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur de saisie");
            alert.setHeaderText("Le nom est invalide.");
            alert.showAndWait();
            return;
        }
        Utilisateur utilisateurActuel = currentUtilisateur;
        if (utilisateurActuel != null) {
            // Assignez l'utilisateur actuel à currentUtilisateur
            currentUtilisateur = utilisateurActuel;

            CRUDUtilisateur crudUtilisateur = new CRUDUtilisateur();
            Utilisateur user = new Utilisateur(currentUtilisateur.getID_user(), modsupp_nom.getText(),
                    modsupp_prenom.getText(), modsupp_numero.getText(),
                    modsupp_password.getText(),modsupp_mail.getText(), currentUtilisateur.getRole());
            crudUtilisateur.modifier(user);
            JOptionPane.showMessageDialog(null, "Modification effectuée! ");
            System.out.println("Utilisateur modifié avec succès !");}
        else{
            System.out.println("L'utilisateur actuel est null. Impossible de procéder.");
        }

    }
/*
    @FXML
    void mtpOublie(ActionEvent event) {
        try {
            Parent root;
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
    @FXML
    private void verifierEmailOnClick(ActionEvent event) throws SQLException {
        Random rd = new Random();
        int Rand = rd.nextInt(1000000 + 1);
        motDePasseOublie.setCurrentUtilisateur(currentUtilisateur);
        motDePasseOublie.setRand(Rand);
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
        SendMail.SendMail(event, Rand, currentUtilisateur);
    }

    @FXML
    private void verifierNumTelOnClick(ActionEvent event){
        Random rd = new Random();
        int Ra = rd.nextInt(1000000+1);
        motDePasseOublie.setRand(Ra);
        String message = "Bonjour " + currentUtilisateur.getNom() + " Voici votre code de Confirmation de le mot de passe : " + String.valueOf(Ra);
        System.out.println(message);
        String num = "+216"+String.valueOf(currentUtilisateur.getNumero());
        System.out.println(num);
        SendSMS.SendSMS(message, num);
        motDePasseOublie.setCurrentUtilisateur(currentUtilisateur);
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
    @FXML
    void retourAcceuil(ActionEvent event) {
        try {Parent root;
            Scene scene;
            Stage stage;
            root = (Parent) FXMLLoader.load(this.getClass().getResource("/AcceuilFront.fxml"));
            scene = new Scene(root);
            stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
