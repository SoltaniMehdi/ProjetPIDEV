package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ResourceBundle;
import entities.evenement;
import services.serviceevenement;

public class UpdateEventController implements Initializable {

    @javafx.fxml.FXML
    private DatePicker dt_dd;
    @javafx.fxml.FXML
    private TextField tf_des;
    @javafx.fxml.FXML
    private TextField tx_nom;
    @javafx.fxml.FXML
    private ComboBox cb_lieu;
    @javafx.fxml.FXML
    private VBox chosenFruitCard;

    private int idmodif;
    private serviceevenement se = new serviceevenement();
    @javafx.fxml.FXML
    public void OnEditClicked(ActionEvent actionEvent) throws SQLException {
            evenement e = new evenement();
            e.setId(idmodif);
            e.setDescription(tf_des.getText());
            e.setLieu(cb_lieu.getValue().toString());
            e.setNom(tx_nom.getText());
            e.setDateD(Date.valueOf(dt_dd.getValue()));

            se.modifier(e);
        try {
            Parent root= FXMLLoader.load(getClass().getResource("/AfficherEvents.fxml"));
            tx_nom.getScene().setRoot(root);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        idmodif = AfficherEventsController.selectedId;
        evenement e = new evenement();
        try {
             e = se.findEventById(idmodif);
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
        dt_dd.setValue(e.getDateD().toLocalDate());
        tf_des.setText(e.getDescription());
        tx_nom.setText(e.getNom());
        cb_lieu.setValue(e.getLieu());

    }
}
