package controllers;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import entities.Categorie;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.image.ImageView;
import services.ServiceCategorie;


public class AfficherCategorieController {

    private final ServiceCategorie serviceCategorie = new ServiceCategorie();

    @FXML
    private ListView<Categorie> afficher_cat;

    @FXML
    private ImageView img;

    @FXML
    void initialize() {
        try {
            ObservableList<Categorie> categoriesList = FXCollections.observableArrayList(serviceCategorie.afficher());
            ObservableList<String> categorieInfo = FXCollections.observableArrayList();
            for (Categorie categorie : categoriesList) {
                categorieInfo.add("Nom: " + categorie.getNom() + ", Description: " + categorie.getDescription());

            }
            afficher_cat.setItems(categoriesList);
        } catch (SQLException e) {
            System.out.println("Erreur lors de l'affichage des repas : " + e.getMessage());
        }
    }

}