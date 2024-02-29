package controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import services.IService;
import entities.Livraison1;
import services.ServiceLivraison;
import  javafx.event.ActionEvent;
import java.io.IOException;
import java.sql.SQLException;

public class AjouterLivraisonController {

    @FXML
    private TextField tf_adresse;

    @FXML
    private TextField tf_DateDeLivraison;

    @FXML
    private TextField tf_prix;

    @FXML
    private Button ajouterButton;

    private IService<Livraison1> serviceLivraison;

    public AjouterLivraisonController() {
        serviceLivraison = new ServiceLivraison();
    }

    @FXML
    void afficher(ActionEvent event) {
        try {
            Parent root= FXMLLoader.load(getClass().getResource("/view/AfficherLivraison.fxml"));
            tf_adresse.getScene().setRoot(root);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
    @FXML
    void addp(ActionEvent event) {
        try {
            Parent root= FXMLLoader.load(getClass().getResource("/view/AjouterPointDistribution.fxml"));
            tf_adresse.getScene().setRoot(root);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
    @FXML
    public void handleAjouterButton() {
        int id=2;
        int idc=2;
        String date = tf_DateDeLivraison.getText();
        String adrC = tf_adresse.getText();
        Float prix = Float.parseFloat(tf_prix.getText());
        if (date.isEmpty() ||  adrC.isEmpty()  ||  prix.isNaN() ) {
            showAlert("Erreur", "Champs vides", "Veuillez remplir tous les champs.", Alert.AlertType.ERROR);
            return;
        }

        // Vérifier que le numéro de téléphone est valide
        if (!isFloat(tf_prix.getText())) {
            showAlert("Erreur", "Numéro de téléphone invalide", "Veuillez saisir un numéro de téléphone valide.", Alert.AlertType.ERROR);
            return;
        }

        try {
            serviceLivraison.ajouter(new Livraison1(id,idc,date,adrC, prix));

            Alert alert =new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("succes");
            alert.setContentText("personne ajoute");
            alert.showAndWait(); }
        catch (SQLException e) {
            throw new RuntimeException(e.getMessage());
        }
      /*  if (!validateInput()) {
            String dateLivraison = tf_DateDeLivraison.getText();
            String adresse = tf_adresse.getText();

            float prix = Float.parseFloat(tf_prix.getText());

            Livraison1 livraison =new Livraison1(dateLivraison,adresse,prix);

            try {
                serviceLivraison.ajouter(livraison);
                showAlert(Alert.AlertType.INFORMATION, "Ajout réussi", "La livraison a été ajoutée avec succès.");
            } catch (SQLException e) {
                showAlert(Alert.AlertType.ERROR, "Erreur", "Une erreur s'est produite lors de l'ajout de la livraison : " + e.getMessage());
            }
        }*/
    }

    /*private boolean validateInput() {
        String errorMessage = "";


        if (tf_DateDeLivraison.getText().isEmpty()) {
            errorMessage += "Date de livraison invalide!\n";
        }
        if ( tf_adresse.getText().isEmpty() ) {
            errorMessage += "Adresse invalide!\n";
        }
        if ( tf_prix.getText().isEmpty() || !isFloat(tf_prix.getText())) {
            errorMessage += "Prix invalide!\n";
        }

        if (errorMessage.isEmpty()) {
            return true;
        } else {
            showAlert(Alert.AlertType.ERROR, "Erreur de saisie", errorMessage);
            return false;
        }
    }
*/
    private boolean isFloat(String s) {
        try {
            Float.parseFloat(s);
            return true;
        } catch (NumberFormatException e) {
            return false;
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
