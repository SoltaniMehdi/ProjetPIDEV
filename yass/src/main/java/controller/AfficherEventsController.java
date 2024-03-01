package controller;

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
import entities.evenement;
import services.serviceevenement;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

public class AfficherEventsController implements Initializable {

    public static int selectedId = 0;
    @javafx.fxml.FXML
    private Button supp;
    @javafx.fxml.FXML
    private ListView<evenement> lv_event;
    @javafx.fxml.FXML
    private Button b_modifier;

    private ObservableList<evenement> eventList = FXCollections.observableArrayList();
    private serviceevenement se = new serviceevenement();
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        afficherevents();
        lv_event.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                selectedId = newValue.getId();
                System.out.println("Selected ID: " + selectedId);
            }
        });
    }

    public void afficherevents()
    {
       lv_event.getItems().clear();
        List<evenement> events = null;
        try {
            events = se.afficher();
            System.out.println(se.afficher());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        // Add the events to the ObservableList
        eventList.addAll(events);

        // Set the ObservableList to the ListView
        lv_event.setItems(eventList);
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
            afficherevents();
        }
    }

    @javafx.fxml.FXML
    public void GoUpdate(ActionEvent actionEvent) {
        if (selectedId !=0) {
            try {
                Parent root = FXMLLoader.load(getClass().getResource("/UpdateEvent.fxml"));
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
    public void afficher_categorie(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/affichercategorie.fxml"));
        b_modifier.getScene().setRoot(root);
    }
}
