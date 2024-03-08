package controllers;

import entities.Categorie_event;
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
import services.servicecategorie_event;
import services.serviceevenement;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;





public class  affichercategorie implements Initializable {

    public static int selectedId = 0;

    private ObservableList<Categorie_event> Categorie_eventlist = FXCollections.observableArrayList();
    private servicecategorie_event se = new servicecategorie_event();
    @javafx.fxml.FXML
    private ListView<Categorie_event> lv_categ;
    @javafx.fxml.FXML
    private Button b_supprimer;
    @javafx.fxml.FXML
    private Button b_modifier;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        afficherCategorie_events();
        lv_categ.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                selectedId = newValue.getIdcateg();
                System.out.println("Selected ID: " + selectedId);
            }
        });
    }

    public void afficherCategorie_events()
    {
        lv_categ.getItems().clear();
        List<Categorie_event> Categorie_events = null;
        try {
            Categorie_events = se.afficher();
            System.out.println(se.afficher());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        // Add the events to the ObservableList
        Categorie_eventlist.addAll(Categorie_events);

        // Set the ObservableList to the ListView
        lv_categ.setItems(Categorie_eventlist);
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
            afficherCategorie_events();
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
    public void ajouter_Categorie_event(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/view/ajoutercategorie.fxml"));
        b_modifier.getScene().setRoot(root);
    }

}


