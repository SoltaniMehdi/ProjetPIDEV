package controller;

import java.io.IOException;
import java.lang.String;
import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

import entities.evenement;
import javafx.fxml.Initializable;
import org.controlsfx.control.Notifications;
import tests.mainFx;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import services.serviceevenement;

public class ajouterevent implements Initializable {
    serviceevenement serviceevenement= new serviceevenement();
    private int idmodif;


    @FXML
    private Button b_aff;

    @FXML
    private Button b_ve;

    @FXML
    private ComboBox<?> cb_lieu;

    @FXML
    private VBox chosenFruitCard;

    @FXML
    private DatePicker dt_dd;

    @FXML
    private TextField tf_des;

    @FXML
    private TextField tx_nom;


    @Deprecated
    void afficher(MouseEvent event) {

    }

    @FXML
    void ajouter(MouseEvent event) {




    }


    @FXML
    void affichage(ActionEvent event) {
        try {
            Parent root= FXMLLoader.load(getClass().getResource("/AfficherEvents.fxml"));
            tx_nom.getScene().setRoot(root);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

    }



    @FXML
    void ajouterEvent(ActionEvent event) {

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String dates;
        LocalDate date = dt_dd.getValue();
        System.out.println(date);
        dates = dt_dd.getValue().format(formatter);

        try {
            Date dateevent = Date.valueOf(date);
            boolean ajoutReussi = serviceevenement.ajouter(new evenement(1, tx_nom.getText(), tf_des.getText(), cb_lieu.getSelectionModel().getSelectedItem().toString(), dateevent,idmodif));

            if (ajoutReussi) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Success");
                alert.setContentText("Événement ajouté avec succès");
                alert.showAndWait();
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Erreur");
                alert.setContentText("Échec de l'ajout de l'événement");
                alert.showAndWait();
            }
        } catch (Exception e) {
            // Handle other exceptions if needed
            e.printStackTrace();


        }


    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        idmodif = affichercategorie.selectedId;
        System.out.println(idmodif);

    }
};
