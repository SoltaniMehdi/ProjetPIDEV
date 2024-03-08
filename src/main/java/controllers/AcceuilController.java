package controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

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
    void retourAcceuil(ActionEvent event) {
        try {Parent root;
            Scene scene;
            Stage stage;
            root = (Parent) FXMLLoader.load(this.getClass().getResource("/AcceuilFront.fxml"));
            scene = new Scene(root);
            stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void initialize() {

    }

}