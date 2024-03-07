package controller;
import org.controlsfx.control.Notifications;

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
import services.ServiceNotif;

import static services.SoundPlayer.playNotificationSound;

public class commandecontroller {
    ServiceCommandes serviceCommandes=new ServiceCommandes();

    @FXML
    private Button affichercommande;
    @FXML
    private Button mailing;

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

    public commandecontroller() throws SQLException {
    }


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
    void mail(ActionEvent event) {
        try {
            Parent root= FXMLLoader.load(getClass().getResource("/view/Mailing.fxml"));
            prix.getScene().setRoot(root);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    @FXML
    void validerAndNotify(ActionEvent event) {
        try {
            if (isInputValid()) {
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                String dates = calendrier.getValue().format(formatter);
                Date datecmd = Date.valueOf(dates);
                serviceCommandes.ajouter(new Commande(datecmd, statut.getSelectionModel().getSelectedItem().toString(),
                        Integer.parseInt(prix.getText())));

                ServiceNotif.showNotification("Success", "Commande ajoutée");
                ServiceNotif.playNotificationSound(true);
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
