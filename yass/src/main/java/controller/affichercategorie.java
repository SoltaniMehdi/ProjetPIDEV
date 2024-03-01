package controller;

import entities.Categorie;
import entities.evenement;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ListView;
import services.servicecategorie;
import services.serviceevenement;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;





    public class  affichercategorie implements Initializable {

        public static int selectedId = 0;

        private ObservableList<Categorie> categorielist = FXCollections.observableArrayList();
        private servicecategorie se = new servicecategorie();
        @javafx.fxml.FXML
        private ListView<Categorie> lv_categ;
        @javafx.fxml.FXML
        private Button b_supprimer;
        @javafx.fxml.FXML
        private Button b_modifier;

        @Override
        public void initialize(URL url, ResourceBundle resourceBundle) {

            affichercategories();
            lv_categ.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
                if (newValue != null) {
                    selectedId = newValue.getIdcateg();
                    System.out.println("Selected ID: " + selectedId);
                }
            });
        }

        public void affichercategories()
        {
            lv_categ.getItems().clear();
            List<Categorie> categories = null;
            try {
                categories = se.afficher();
                System.out.println(se.afficher());
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }

            // Add the events to the ObservableList
            categorielist.addAll(categories);

            // Set the ObservableList to the ListView
            lv_categ.setItems(categorielist);
        }



        @javafx.fxml.FXML
        public void OnDeleteClicked(ActionEvent actionEvent) throws SQLException {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Delete Confirmation");
            alert.setHeaderText(null);
            alert.setContentText("Are you sure you want to delete the selected item?");

            Optional<ButtonType> result = alert.showAndWait();
            if (result.isPresent() && result.get() == ButtonType.OK) {
                se.supprimer(selectedId);
                affichercategories();
            }
        }

        @javafx.fxml.FXML
        public void GoUpdate(ActionEvent actionEvent) {
            if (selectedId !=0) {
                try {
                    Parent root = FXMLLoader.load(getClass().getResource("/view/modifiercategorie.fxml"));
                    b_modifier.getScene().setRoot(root);
                } catch (IOException e) {
                    System.out.println(e.getMessage());
                }
            }
            else
            {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("No event selected !");
                alert.showAndWait();
            }
        }


        @javafx.fxml.FXML
        public void ajouter_event(ActionEvent actionEvent) {
            if (selectedId !=0) {
                try {
                    Parent root = FXMLLoader.load(getClass().getResource("/view/ajouterevent.fxml"));
                    b_modifier.getScene().setRoot(root);
                } catch (IOException e) {
                    System.out.println(e.getMessage());
                }
            }
            else
            {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("No event selected !");
                alert.showAndWait();
            }
        }

        @javafx.fxml.FXML
        public void ajouter_categorie(ActionEvent actionEvent) throws IOException {
            Parent root = FXMLLoader.load(getClass().getResource("/view/ajoutercategorie.fxml"));
            b_modifier.getScene().setRoot(root);
        }

    }


