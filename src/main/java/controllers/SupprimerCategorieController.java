package controllers;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import entities.Categorie;
import entities.Repas;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.image.ImageView;
import services.ServiceCategorie;

public class SupprimerCategorieController {

    ServiceCategorie serviceCategorie = new ServiceCategorie();

    @FXML
    private Button supp;

    @FXML
    private ListView<Categorie> supp_cat;
    @FXML
    private ImageView img;


    @FXML
    void initialize() {
        try {
            refreshListView();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    private void refreshListView() throws SQLException {
        ObservableList<Categorie> categories = FXCollections.observableArrayList(serviceCategorie.afficher());
        supp_cat.setItems(categories);
    }

    @FXML
    void supprimer(ActionEvent event) {


        Categorie selectedCategorie = supp_cat.getSelectionModel().getSelectedItem();
        if (selectedCategorie!= null) {
            try {
                serviceCategorie.supprimer(selectedCategorie.getId());
                refreshListView();
                showAlert("Succès", "Repas supprimé avec succès.", Alert.AlertType.INFORMATION);
            } catch (SQLException e) {
                showAlert("Erreur", "Erreur lors de la suppression du repas.", Alert.AlertType.ERROR);
                System.out.println(e.getMessage());
            }
        } else {
            showAlert("Aucune sélection", "Veuillez sélectionner un repas à supprimer.", Alert.AlertType.WARNING);
        }
    }

    private void showAlert(String title, String content, Alert.AlertType alertType) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setContentText(content);
        alert.showAndWait();
    }
}