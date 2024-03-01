package controller;

import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import entities.Commande;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import services.ServiceCommandes;

public class commandecontroller {
    ServiceCommandes serviceCommandes=new ServiceCommandes();

    @FXML
    private Button affichercommande;

    @FXML
    private DatePicker calendrier;

    @FXML
    private VBox chosenFruitCard;

    @FXML
    private Label fruitNameLable;

    @FXML
    private TextField prix;

    @FXML
    private ComboBox<String> statut;

    @FXML
    private Button validercommande;


    @FXML
    void afficher(ActionEvent event) {
        try {
            Parent root= FXMLLoader.load(getClass().getResource("/view/AfficherCommande.fxml"));
            prix.getScene().setRoot(root);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    @FXML
    void valider(ActionEvent event) throws RuntimeException {
        try {
            if (isInputValid()) {
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                String dates = calendrier.getValue().format(formatter);
                Date datecmd = Date.valueOf(dates);
                serviceCommandes.ajouter(new Commande(datecmd, statut.getSelectionModel().getSelectedItem().toString(),
                        Integer.parseInt(prix.getText())));

                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Success");
                alert.setContentText("Commande ajoutée");
                alert.showAndWait();
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setContentText("Veuillez remplir tous les champs obligatoires.");
                alert.showAndWait();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private boolean isInputValid() {
        return !prix.getText().isEmpty() && statut.getValue() != null && calendrier.getValue() != null;
    }
}
