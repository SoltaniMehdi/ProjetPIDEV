package controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import services.IService;
import entities.PointDistribution;
import services.ServicePointDistribution;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class AfficherPointDistributionController {
ServicePointDistribution servicePointDistribution = new ServicePointDistribution();
    @FXML
    private TableView<PointDistribution> pointDistributionTable;

    @FXML
    private TableColumn<PointDistribution, Integer> idPointDistributionColumn;

    @FXML
    private TableColumn<PointDistribution, String> nomColumn;

    @FXML
    private TableColumn<PointDistribution, String> adresseColumn;

    @FXML
    private TableColumn<PointDistribution, Integer> idLivraisonColumn;
    @FXML
    private Button back;



    @FXML
    void supprimer(ActionEvent event) {
        PointDistribution pointDistribution = pointDistributionTable.getSelectionModel().getSelectedItem();
        if (pointDistribution != null) {
            try {
                servicePointDistribution.supprimer(pointDistribution.getIdPointDistribution());
                pointDistributionTable.getItems().remove(pointDistribution);
                showAlert("Succès", "livraison supprimé avec succès.", "La livraison a été supprimé avec succès.", Alert.AlertType.INFORMATION);
            } catch (SQLException e) {
                e.printStackTrace();
                showAlert("Erreur", "Erreur lors de la suppression du pack.", e.getMessage(), Alert.AlertType.ERROR);
            }
        } else {
            showAlert("Aucune sélection", "Aucun complexe sélectionné", "Veuillez sélectionner une complexe à supprimer.", Alert.AlertType.WARNING);
        }
    }
    @FXML
    public void initialize() {
        try {


            ObservableList<PointDistribution> pointDistributions= FXCollections.observableList(servicePointDistribution.afficher());
            pointDistributionTable.setItems(pointDistributions);
            idPointDistributionColumn.setCellValueFactory(new PropertyValueFactory<>("idPointDistribution"));
            nomColumn.setCellValueFactory(new PropertyValueFactory<>("nom"));
            adresseColumn.setCellValueFactory(new PropertyValueFactory<>("adresse"));
            idLivraisonColumn.setCellValueFactory(new PropertyValueFactory<>("idLivraison"));

        }catch (SQLException e) {
            System.out.println(e.getMessage());
        }

    }
    @FXML
    public void handleAnnulerButton() {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/view/AjouterPointDistribution.fxml"));
            Scene scene = back.getScene();
            scene.setRoot(root);
        } catch (IOException e) {
            e.printStackTrace();
            showAlert("Erreur", "Erreur lors du retour à l'interface précédente", e.getMessage(), Alert.AlertType.ERROR);
        }
    }
    @FXML

    void modifier(ActionEvent event) {
        PointDistribution pointDistribution = pointDistributionTable.getSelectionModel().getSelectedItem();
        if (pointDistribution != null) {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/ModifierPointDistribution.fxml"));
                Parent root = loader.load();
//ModifierPointDistributionController controller = loader.getController();
ModifierPointDistributionController controller1=loader.getController();
                // Passer le pack sélectionné au contrôleur de modification
                controller1.initData(pointDistribution);

                // Passer l'instance de ServicePack au contrôleur de modification
                controller1.setServicePointDistribution(servicePointDistribution);

                // Remplacer le contenu de la fenêtre actuelle avec celui de l'interface de modification
                pointDistributionTable.getScene().setRoot(root);
            } catch (IOException e) {
                e.printStackTrace();
                showAlert("Erreur", "Erreur lors de l'ouverture de l'interface de modification", e.getMessage(), Alert.AlertType.ERROR);
            }
        } else {
            showAlert("Aucune sélection", "Aucun pack sélectionné", "Veuillez sélectionner un pack à modifier.", Alert.AlertType.WARNING);
        }
    }
    public void refreshTable() {
        try {
            ObservableList<PointDistribution> pointDistributions = FXCollections.observableList(servicePointDistribution.afficher());
            pointDistributionTable.setItems(pointDistributions);
        } catch (Exception e) {
            System.out.println("An error occurred while refreshing table: " + e.getMessage());
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
