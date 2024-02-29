package controllers;

import entities.Livraison1;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import entities.PointDistribution;
import services.IService;
import services.ServiceLivraison;
import services.ServicePointDistribution;

import java.io.IOException;
import java.sql.SQLException;

public class ModifierPointDistributionController {

    @FXML
    private TextField adresseTextField;

    @FXML
    private Button back;

    @FXML
    private TextField dateLivraisonTextField;

    @FXML
    private TextField idLivraisonTextField;



//private PointDistribution pointDistribution;
  //  private IService<PointDistribution> servicePointDistribution;

    public void setAfficherPointDistributionController(AfficherPointDistributionController afficherPointDistributionController) {
        this.afficherPointDistributionController = afficherPointDistributionController;
    }

    public void setServicePointDistribution(ServicePointDistribution servicePointDistribution) {
        this.servicePointDistribution = servicePointDistribution;
    }
private  ServicePointDistribution servicePointDistribution;
    private AfficherPointDistributionController afficherPointDistributionController;
    private PointDistribution pointDistribution;


    @FXML
    public void handleModifierButton() {
        if (servicePointDistribution == null) {
            showAlert("Erreur", "Serviceterrain non initialisé", "Le serviceTerrain n'est pas initialisé.", Alert.AlertType.ERROR);
            return;
        }

        // Mettre à jour les données du pack avec les nouvelles valeurs des champs

        pointDistribution.setIdLivraison(Integer.parseInt(idLivraisonTextField.getText()));
        pointDistribution.setNom(dateLivraisonTextField.getText());
        pointDistribution.setAdresse(adresseTextField.getText());

        // Enregistrer les modifications dans la base de données ou dans votre service
        try {
            servicePointDistribution.modifier(pointDistribution);
            // Appel de la méthode pour modifier le pack dans la base de données
            if (afficherPointDistributionController == null) {
                System.out.println("*");
            } else {
                afficherPointDistributionController.refreshTable();
            }
// Rafraîchir la TableView dans le contrôleur AfficherPackController
        } catch (SQLException e) {
            e.printStackTrace();
            showAlert("Erreur", "Erreur lors de la sauvegarde des modifications.", e.getMessage(), Alert.AlertType.ERROR);
            // Quitte la méthode si une erreur se produit lors de la mise à jour de la base de données
        }

        // Fermer la fenêtre de modification
    }


    @FXML
    public void handleAnnulerButton() {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/view/AfficherPointDistibution.fxml"));
            Scene scene = back.getScene();
            scene.setRoot(root);
        } catch (IOException e) {
            e.printStackTrace();
            showAlert("Erreur", "Erreur lors du retour à l'interface précédente", e.getMessage(), Alert.AlertType.ERROR);
        }
    }
    public void initData(PointDistribution pointDistribution) {
        this.pointDistribution = pointDistribution;
        idLivraisonTextField.setText(String.valueOf(pointDistribution.getIdLivraison()));
        adresseTextField.setText(pointDistribution.getAdresse());
        dateLivraisonTextField.setText(pointDistribution.getNom());


    }
    private void showAlert(String title, String headerText, String contentText, Alert.AlertType alertType) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(headerText);
        alert.setContentText(contentText);
        alert.showAndWait();
    }
}
