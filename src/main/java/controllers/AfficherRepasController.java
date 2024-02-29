package controllers;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import entities.Repas;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.image.ImageView;
import services.ServiceRepas;

public class AfficherRepasController {

    private final ServiceRepas serviceRepas = new ServiceRepas();

    @FXML
    private ListView<Repas> afficher_rep;
    @FXML
    private ImageView img;

    @FXML
    void initialize() {
        try {
            ObservableList<Repas> repasList = FXCollections.observableArrayList(serviceRepas.afficher());
            ObservableList<String> repasInfo = FXCollections.observableArrayList();
            for (Repas repas : repasList) {
                repasInfo.add("Nom: " + repas.getNom() + ", Prix: " + repas.getPrix() + ", Description: " + repas.getDescription() + ", Catégorie: " + repas.getCategorie()); // Ajouter les informations du repas à la liste
            }
            afficher_rep.setItems(repasList);
        } catch (SQLException e) {
            System.out.println("Erreur lors de l'affichage des repas : " + e.getMessage());
        }
    }
}

