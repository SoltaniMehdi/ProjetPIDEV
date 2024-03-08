package controllers;

import entities.Repas;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import services.ServiceRepas;

import java.io.IOException;
import java.sql.SQLException;

public class EditRepasController {

    @FXML
    private  TextField ajouter_prix;
    @FXML
    private  TextField ajouter_nom;
    @FXML
    private  TextField ajouter_desc;
    @FXML
    private  TextField ajouter_cat;

    private Repas selectedCategorie;

    private ServiceRepas serviceRepas = new ServiceRepas();

    public void initData(Repas selectedCategorie) {
        this.selectedCategorie = selectedCategorie;
        ajouter_nom.setText(selectedCategorie.getNom());
        ajouter_desc.setText(selectedCategorie.getDescription());
        ajouter_cat.setText(selectedCategorie.getCategorie()+"");
        ajouter_desc.setText(selectedCategorie.getDescription());
        ajouter_prix.setText(selectedCategorie.getPrix()+"");
    }

    @FXML
    private void ajouter() {
        if (ajouter_prix.getText().isEmpty() || ajouter_nom.getText().isEmpty() || ajouter_desc.getText().isEmpty() || ajouter_cat.getText().isEmpty()) {
            showAlert("Erreur", "Champs vides", "Veuillez remplir tous les champs.", Alert.AlertType.ERROR);
            return;
        }
        try {
            serviceRepas.modifier(new Repas(this.selectedCategorie.getIdR(),Float.parseFloat(ajouter_prix.getText()),ajouter_nom.getText(),ajouter_desc.getText(),Integer.parseInt(ajouter_cat.getText())));
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Succès");
            alert.setContentText("Repas Modifer avec succès");
            alert.showAndWait();
        } catch (NumberFormatException e) {
            showAlert("Erreur", "Format de prix ou de catégorie invalide", "Veuillez saisir un prix et une catégorie valides.", Alert.AlertType.ERROR);
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @FXML
    private void afficher() {
        try {
            Parent root= FXMLLoader.load(getClass().getResource("/AfficherRepas.fxml"));
            ajouter_prix.getScene().setRoot(root);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
    private void showAlert(String title, String headerText, String contentText, Alert.AlertType alertType) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(headerText);
        alert.setContentText(contentText);
        alert.showAndWait();
    }

}
