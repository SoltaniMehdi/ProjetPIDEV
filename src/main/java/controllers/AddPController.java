package controllers;

import entities.PointDistribution;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import services.IService;
import services.ServicePointDistribution;

import java.io.IOException;
import java.sql.SQLException;


public class AddPController {





    @FXML
    private TextField idPointDistributionTextField;

    @FXML
    private TextField nomTextField;

    @FXML
    private TextField adresseTextField;

    @FXML
    private TextField idLivraisonTextField;

    @FXML
    private Button back;

    private IService<PointDistribution> servicePointDistribution;

    public AddPController() {
        servicePointDistribution = new ServicePointDistribution();
    }

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
            Parent root= FXMLLoader.load((getClass().getResource("/view/AfficherPointDistibution.fxml")));
            adresseTextField.getScene().setRoot(root);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    @FXML
    public void handleAnnulerButton() {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/Acceuil.fxml"));
            Scene scene = back.getScene();
            scene.setRoot(root);
        } catch (IOException e) {
            e.printStackTrace();
            showAlert("Erreur", "Erreur lors du retour à l'interface précédente", e.getMessage(), Alert.AlertType.ERROR);
        }
    }
    @FXML
    public void handleAjouterButton() {
        if (validateInput()) {
          //  int idPointDistribution = Integer.parseInt(idPointDistributionTextField.getText());
            String nom = nomTextField.getText();
            String adresse = adresseTextField.getText();
            int idLivraison = 2;

            PointDistribution pointDistribution = new PointDistribution(nom, adresse, idLivraison);

            try {
                servicePointDistribution.ajouter(pointDistribution);
                showAlert(Alert.AlertType.INFORMATION, "Ajout réussi", "Le point de distribution a été ajouté avec succès.");
            } catch (SQLException e) {
                showAlert(Alert.AlertType.ERROR, "Erreur", "Une erreur s'est produite lors de l'ajout du point de distribution : " + e.getMessage());
            }
        }
    }

    private boolean validateInput() {
        String errorMessage = "";

        if (nomTextField.getText() == null || nomTextField.getText().isEmpty() ) {
            errorMessage += "nom du point de distribution invalide!\n";
        }

        if (adresseTextField.getText() == null || adresseTextField.getText().isEmpty() ) {
            errorMessage += "adresse de point invalide!\n";
        }

        if (errorMessage.isEmpty()) {
            return true;
        } else {
            showAlert(Alert.AlertType.ERROR, "Erreur de saisie", errorMessage);
            return false;
        }
    }

    private boolean isInteger(String s) {
        try {
            Integer.parseInt(s);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    private void showAlert(Alert.AlertType type, String title, String content) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }
}
