package controllers;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import entities.Categorie;
import entities.Repas;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import services.ServiceCategorie;


public class AjouterCategorieController {

ServiceCategorie serviceCategorie = new ServiceCategorie();
    @FXML
    private Button afficher;

    @FXML
    private Button ajout;

    @FXML
    private TextField ajouter_nomcat;

    @FXML
    private TextField ajouter_descat;
    @FXML
    private ImageView img;

    private void showAlert(String title, String headerText, String contentText, Alert.AlertType alertType) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(headerText);
        alert.setContentText(contentText);
        alert.showAndWait();
    }


    @FXML
    void afficher(ActionEvent event) {
        try {
            Parent root= FXMLLoader.load(getClass().getResource("/AfficherCategorie.fxml"));
            ajouter_nomcat.getScene().setRoot(root);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    @FXML
    void ajouter(ActionEvent event) {


        if (ajouter_nomcat.getText().isEmpty() || ajouter_descat.getText().isEmpty()) {
            showAlert("Erreur", "Champs vides", "Veuillez remplir tous les champs.", Alert.AlertType.ERROR);
            return;
        }
        try {
            serviceCategorie.ajouter(new Categorie(ajouter_nomcat.getText(),ajouter_descat.getText()));

            // Afficher une alerte de succès
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Succès");
            alert.setContentText("Catégories ajoutée avec succès");
            alert.showAndWait();
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

}
