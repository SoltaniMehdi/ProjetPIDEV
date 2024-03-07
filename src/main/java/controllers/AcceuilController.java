package controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.TextField;

public class AcceuilController {
    @FXML
    private TextField date;
    @FXML
    private TextField dateT;

    @FXML
    void handleAjouterButton(ActionEvent event) {
        try {
            Parent root= FXMLLoader.load(getClass().getResource("/AjouterLivraison.fxml"));
            date .getScene().setRoot(root);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    @FXML
    void addP(ActionEvent event) {
        try {
            Parent root= FXMLLoader.load(getClass().getResource("/view/AjouterPointDistribution.fxml"));
            dateT .getScene().setRoot(root);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    @FXML
    void initialize() {

    }

}