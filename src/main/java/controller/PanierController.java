package controller;

import entities.Repas;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import services.ServiceCommandes;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class PanierController {

    @FXML
    private ListView<Repas> LV_panier;

    @FXML
    private Button afficherpanier;
    @FXML
    private Button pageprec;
    @FXML
    private Button affichertout;



    @FXML
    void backafficher(ActionEvent event) {
        try {
            Parent root= FXMLLoader.load(getClass().getResource("/view/AfficherCommande.fxml"));
            pageprec.getScene().setRoot(root);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    private void loadrepas () {
        Stage primaryStage = (Stage) LV_panier.getScene().getWindow();
        FXMLLoader fxmlLoader= new FXMLLoader(getClass().getResource("/view/repas.fxml"));
        try {
            Parent root=fxmlLoader.load();
            Scene scene= new Scene(root);
            primaryStage.setScene(scene);
            primaryStage.setTitle("Details Commande");
            primaryStage.show();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

    }




    @FXML
    void initialize(ActionEvent event) {
        try {
            ServiceCommandes service = new ServiceCommandes();
            List<Repas> commandesList = service.afficherrepas();
            ObservableList<Repas> repas = FXCollections.observableArrayList(commandesList);
            ListView<Repas> listView = new ListView<Repas>(repas);
            listView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
            LV_panier.setItems(repas);
            LV_panier.setCellFactory(param -> new ListCell<Repas>() {
                @Override
                protected void updateItem(Repas item, boolean empty) {
                    super.updateItem(item, empty);
                    if (empty || item == null) {
                        setText(null);
                    } else {
                        setText("Nom: " + item.getNom() + ", Description: " + item.getDescription() + ", Prix: " + item.getPrix());
                    }
                }
            });

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

}
