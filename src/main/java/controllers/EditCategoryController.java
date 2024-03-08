package controllers;

import entities.Categorie;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import services.ServiceCategorie;

import java.sql.SQLException;

public class EditCategoryController {

    @FXML
    private TextField ajouter_nomcat;

    @FXML
    private TextField ajouter_descat;

    @FXML
    private Button ajouter;

    @FXML
    private Button afficher;
    private ServiceCategorie service = new ServiceCategorie();

    private Categorie selectedCategorie;

    public void initData(Categorie selectedCategorie) {
        this.selectedCategorie = selectedCategorie;
        ajouter_nomcat.setText(selectedCategorie.getNom());
        ajouter_descat.setText(selectedCategorie.getDescription());
    }

    @FXML
    private void ajouter() {
        if (ajouter_nomcat.getText().isEmpty() || ajouter_descat.getText().isEmpty()) {
            showAlert("Erreur", "Champs vides", "Veuillez remplir tous les champs.", Alert.AlertType.ERROR);
            return;
        }
        try {
            service.modifier(new Categorie(this.selectedCategorie.getId(),ajouter_nomcat.getText(),ajouter_descat.getText()));
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Succès");
            alert.setContentText("Catégories ajoutée avec succès");
            alert.showAndWait();
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @FXML
    private void afficher() {
        // Action for the "Afficher" button
    }
    private void showAlert(String title, String headerText, String contentText, Alert.AlertType alertType) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(headerText);
        alert.setContentText(contentText);
        alert.showAndWait();
    }
}
