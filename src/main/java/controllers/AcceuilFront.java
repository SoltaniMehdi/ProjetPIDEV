package controllers;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;

import entities.Repas;
import services.ServiceRepas;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;


public class AcceuilFront {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ListView<Repas> afficher_rep;

    @FXML
    private ImageView img;

    @FXML
    private TextField rechercheField;
    private final ServiceRepas serviceRepas = new ServiceRepas();
    @FXML
    void initialize() {
        try {
            List<Repas> repasList = serviceRepas.afficher();
            ObservableList<Repas> observableRepasList = FXCollections.observableArrayList(repasList);
            afficher_rep.setItems(observableRepasList);
            rechercheField.textProperty().addListener((observable, oldValue, newValue) -> {
                if (newValue == null || newValue.isEmpty()) {
                    afficher_rep.setItems(observableRepasList);
                } else {
                    ObservableList<Repas> filteredList = FXCollections.observableArrayList();
                    for (Repas repas : repasList) {
                        if (repas.getNom().toLowerCase().contains(newValue.toLowerCase())) {
                            filteredList.add(repas);
                        }
                    }
                    afficher_rep.setItems(filteredList);
                }
            });
            afficher_rep.setCellFactory(param -> new AfficherRepasController.RepasListCell());
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Erreur lors de l'affichage des repas : " + e.getMessage());
        }
    }

    @FXML
    void OnSort(ActionEvent event) {

    }

    @FXML
    void redavis(ActionEvent event) {

    }


    @FXML
    void redcatr(ActionEvent event) {
        try {
            Parent root;
            Scene scene;
            Stage stage;
            root = (Parent) FXMLLoader.load(this.getClass().getResource("/AfficherCategorieRepas.fxml"));
            scene = new Scene(root);
            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void redevent(ActionEvent event) {
        try {
            Parent root;
            Scene scene;
            Stage stage;
            root = (Parent) FXMLLoader.load(this.getClass().getResource("/view/ajouterevent.fxml"));
            scene = new Scene(root);
            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void redprofil(ActionEvent event) {
        try {
            Parent root;
            Scene scene;
            Stage stage;
            root = (Parent) FXMLLoader.load(this.getClass().getResource("/modifiersupprimerclient.fxml"));
            modifiersupprimerclientController cs = new modifiersupprimerclientController();
            scene = new Scene(root);
            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();


        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void redlivr(ActionEvent event) {
        try {
            Parent root;
            Scene scene;
            Stage stage;
            root = (Parent) FXMLLoader.load(this.getClass().getResource("/Acceuil.fxml"));
            scene = new Scene(root);
            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

}
