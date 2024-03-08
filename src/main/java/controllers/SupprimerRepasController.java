package controllers;

import entities.Repas;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.image.ImageView;
import services.ServiceRepas;

import java.sql.SQLException;

public class SupprimerRepasController {
    ServiceRepas serviceRepas = new ServiceRepas();

    @FXML
    private Button supp;

    @FXML
    private ListView<Repas> supp_rep;
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
        ObservableList<Repas> repas = FXCollections.observableList(serviceRepas.afficher());
        supp_rep.setItems(repas);
    }

    @FXML
    void supprimer(ActionEvent event) {


        Repas selectedRepas = supp_rep.getSelectionModel().getSelectedItem();
        if (selectedRepas != null) {
            try {
                serviceRepas.supprimer(selectedRepas.getIdR());
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




