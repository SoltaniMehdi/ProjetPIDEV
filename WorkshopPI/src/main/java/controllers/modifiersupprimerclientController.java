package controllers;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import entities.Utilisateur;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import services.CRUDUtilisateur;

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
        Utilisateur utilisateurActuel = getCurrentUtilisateur();
        if (utilisateurActuel != null) {
            // Assignez l'utilisateur actuel à currentUtilisateur
            currentUtilisateur = utilisateurActuel;

            CRUDUtilisateur crudUtilisateur = new CRUDUtilisateur();
            Utilisateur newUser = new Utilisateur(currentUtilisateur.getID_user(), modsupp_nom.getText(),
                    modsupp_prenom.getText(), modsupp_numero.getText(),
                    currentUtilisateur.getPassword(),modsupp_mail.getText(), currentUtilisateur.getRole());
            crudUtilisateur.modifier(newUser);
            JOptionPane.showMessageDialog(null, "Modification effectuée! ");
            System.out.println("Utilisateur modifié avec succès !");}
        else{
            System.out.println("L'utilisateur actuel est null. Impossible de procéder.");
        }


    }

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


}
