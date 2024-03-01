package controller;

import entities.Categorie;
import entities.evenement;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import services.servicecategorie;
import services.serviceevenement;

import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class modifercategorie implements Initializable {
    @javafx.fxml.FXML
    private Label l_categ;
    @javafx.fxml.FXML
    private TextField tx_categ;
    @javafx.fxml.FXML
    private VBox chosenFruitCard;
    private int idmodif;
    private servicecategorie se = new servicecategorie();
    @javafx.fxml.FXML
    public void OnEditClicked(ActionEvent actionEvent) throws SQLException {
        Categorie c = new Categorie();
        c.setIdcateg(idmodif);
        c.setCategevent(tx_categ.getText());


        se.modifier(c);
        try {
            Parent root= FXMLLoader.load(getClass().getResource("/affichercategorie.fxml"));
            tx_categ.getScene().setRoot(root);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        System.out.println(affichercategorie.selectedId);
        idmodif = affichercategorie.selectedId;
        Categorie e = new Categorie();
        try {
            e = se.findCategById(idmodif);
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
        tx_categ.setText(e.getCategevent());


    }
}
