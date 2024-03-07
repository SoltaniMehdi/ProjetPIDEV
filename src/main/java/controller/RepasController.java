package controller;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;

import entities.Commande;
import entities.Repas;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import services.ServiceCommandes;


public class RepasController {
    @FXML
    private ListView<Repas> LV_details;
    static int idC;
    @FXML
    private Button pageprrec;

    @FXML
    void backafficher(ActionEvent event) {
        try {
            Parent root= FXMLLoader.load(getClass().getResource("/view/AfficherCommande.fxml"));
            pageprrec.getScene().setRoot(root);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    @FXML
    void initialize() {
        try {
            ServiceCommandes service = new ServiceCommandes();
            List<Repas> repasList = service.afficherdetails(idC);
            ObservableList<Repas> repas = FXCollections.observableArrayList(repasList);
            ListView<Repas> listView = new ListView<Repas>(repas);
            listView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
            LV_details.setItems(repas);
            LV_details.setCellFactory(param -> new ListCell<Repas>() {
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
