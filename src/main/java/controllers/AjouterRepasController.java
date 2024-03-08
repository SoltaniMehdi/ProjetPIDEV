package controllers;

import entities.Repas;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import services.ServiceRepas;

import java.io.IOException;
import java.sql.SQLException;

public class AjouterRepasController {

ServiceRepas serviceRepas = new ServiceRepas();
    @FXML
    private Button afficher;

    @FXML
    private Button ajout;

    @FXML
    private TextField ajouter_desc;

    @FXML
    private TextField ajouter_nom;

    @FXML
    private TextField ajouter_prix;

    @FXML
    private TextField ajouter_cat;
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
            Parent root= FXMLLoader.load(getClass().getResource("/AfficherRepas.fxml"));
            ajouter_prix.getScene().setRoot(root);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    @FXML
    void ajouter(ActionEvent event) {
        // Vérifier que tous les champs sont remplis
        if (ajouter_prix.getText().isEmpty() || ajouter_nom.getText().isEmpty() || ajouter_desc.getText().isEmpty() || ajouter_cat.getText().isEmpty()) {
            showAlert("Erreur", "Champs vides", "Veuillez remplir tous les champs.", Alert.AlertType.ERROR);
            return;
        }
        try {
            if (serviceRepas.exists(ajouter_nom.getText())) {
                showAlert("Erreur", "Repas existant", "Un repas avec le même nom existe déjà.", Alert.AlertType.ERROR);
                return;
            }
            serviceRepas.ajouter(new Repas(Float.parseFloat(ajouter_prix.getText()),ajouter_nom.getText(),ajouter_desc.getText(),Integer.parseInt(ajouter_cat.getText())));
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Succès");
            alert.setContentText("Repas ajouté avec succès");
            alert.showAndWait();
        } catch (NumberFormatException e) {
            showAlert("Erreur", "Format de prix ou de catégorie invalide", "Veuillez saisir un prix et une catégorie valides.", Alert.AlertType.ERROR);
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage());
        }
    }
}