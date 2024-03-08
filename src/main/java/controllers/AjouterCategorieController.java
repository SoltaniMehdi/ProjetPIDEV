package controllers;

import entities.Categorie_event;
import entities.evenement;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import org.controlsfx.control.Notifications;
import services.servicecategorie_event;
import services.serviceevenement;

import java.io.IOException;
import java.sql.Date;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class ajouterCategoriecontroller {



        servicecategorie_event serviceCategorie_event = new servicecategorie_event();




            @FXML
            private Button b_ac;

            @FXML
            private Button b_affiher;

            @FXML
            private VBox chosenFruitCard;

            @FXML
            private Label l_categ;

            @FXML
            private TextField tx_categ;

            @FXML
            void affichage(ActionEvent Categorie_event) {
                try {
                    Parent root = FXMLLoader.load(getClass().getResource("/affichercategorie.fxml"));
                    tx_categ.getScene().setRoot(root);
                } catch (IOException e) {
                    System.out.println(e.getMessage());

                }
            }
                @FXML
                void ajouterCategorie_event(ActionEvent Categorie_event ) {

                    try {

                        serviceCategorie_event.ajouter(new Categorie_event(tx_categ.getText()));


                            Alert alert = new Alert(Alert.AlertType.INFORMATION);
                            alert.setTitle("Success");
                            alert.setContentText("Categorie_event ajouté avec succès");
                            alert.showAndWait();

                    } catch (Exception e) {
                        // Handle other exceptions if needed
                        e.printStackTrace();
                    }


                }


    @FXML
    public void ajouter(Event event) {
    }
};



