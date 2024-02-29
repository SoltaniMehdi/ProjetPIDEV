package controllers;

import entities.Livraison1;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import services.IService;
import services.ServiceLivraison;

import java.io.IOException;
import java.sql.SQLException;

public class ModifierLivraisonController {

    @FXML
    private TextField idLivraisonTextField;

    @FXML
    private TextField adresseTextField;

    @FXML
    private TextField dateLivraisonTextField;

    @FXML
    private TextField prixTextField;
    @FXML
    private Button back;
   // private IService<Livraison1> serviceLivraison;

    public void setAfficherLivraisonController(AfficherLivraisonController afficherLivraisonController) {
        this.afficherLivraisonController = afficherLivraisonController;
    }

    public void setServiceLivraison(ServiceLivraison serviceLivraison) {
        this.serviceLivraison = serviceLivraison;
    }

    private ServiceLivraison serviceLivraison;
private AfficherLivraisonController afficherLivraisonController;
    private  Livraison1 livraison1;
    @FXML
    public void handleModifierButton() {
        if (serviceLivraison == null) {
            showAlert("Erreur", "Serviceterrain non initialisé", "Le serviceTerrain n'est pas initialisé.", Alert.AlertType.ERROR);
            return;
        }

        // Mettre à jour les données du pack avec les nouvelles valeurs des champs

        livraison1.setId_livraison(Integer.parseInt(idLivraisonTextField.getText()));
        livraison1.setDate_livraison(dateLivraisonTextField.getText());
        livraison1.setAdresse(adresseTextField.getText());
        livraison1.setPrix(Float.parseFloat(prixTextField.getText()));

        // Enregistrer les modifications dans la base de données ou dans votre service
        try {
            serviceLivraison.modifier(livraison1);
            // Appel de la méthode pour modifier le pack dans la base de données
            if (afficherLivraisonController == null) {
                System.out.println("*");
            } else {
                afficherLivraisonController.refreshTable();
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
            Parent root = FXMLLoader.load(getClass().getResource("/view/AfficherLivraison.fxml"));
            Scene scene = back.getScene();
            scene.setRoot(root);
        } catch (IOException e) {
            e.printStackTrace();
            showAlert("Erreur", "Erreur lors du retour à l'interface précédente", e.getMessage(), Alert.AlertType.ERROR);
        }
    }
    public void initData(Livraison1 livraison1) {
        this.livraison1 = livraison1;
        idLivraisonTextField.setText(String.valueOf(livraison1.getId_livraison()));
        adresseTextField.setText(livraison1.getAdresse());
        dateLivraisonTextField.setText(livraison1.getDate_livraison());
        prixTextField.setText(String.valueOf(livraison1.getPrix()));


    }
    private void showAlert(String title, String headerText, String contentText, Alert.AlertType alertType) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(headerText);
        alert.setContentText(contentText);
        alert.showAndWait();
    }
}
