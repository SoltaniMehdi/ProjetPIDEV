package controller;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import java.util.PropertyPermission;
import java.util.ResourceBundle;

import entities.evenement;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import services.serviceevenement;
import javafx.scene.control.Button;

public class affichereventController implements Initializable {

    @FXML
    private Button b_modifier;

    private ObservableList<evenement> eventList = FXCollections.observableArrayList();
    private serviceevenement se = new serviceevenement();
    @FXML
    private Button supp;
    @FXML
    private ListView<evenement> lv_event;



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        List<evenement> events = null;
        try {
            events = se.afficher();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        // Add the events to the ObservableList
        eventList.addAll(events);

        // Set the ObservableList to the ListView
        lv_event.setItems(eventList);
    }
}
