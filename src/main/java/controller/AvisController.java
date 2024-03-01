package controller;

import entities.Avis;
import entities.Repas;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.*;
import services.ServiceAvis;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class AvisController {
    @FXML
    private ListView<Repas> LV_panier;
    @FXML
    private Button modifieravis;

    @FXML
    private Button supprimeravis;
    @FXML
    private Button afficheravis;
    @FXML
    private Button pageprec;

    @FXML
    private ListView<Avis> LV_avis;
    @FXML
    private TextField updatedcom;

    @FXML
    private TextField updatednote;
    @FXML
    private TextField updatedID;


    @FXML
    void backafficher(ActionEvent event) {
        try {
            Parent root= FXMLLoader.load(getClass().getResource("/view/AfficherCommande.fxml"));
            pageprec.getScene().setRoot(root);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }


    @FXML
    void initializeAvis () {
        try {
            ServiceAvis service = new ServiceAvis();
            List<Avis> avisList = service.afficheravis();
            ObservableList<Avis> avis = FXCollections.observableArrayList(avisList);
            ListView<Avis> listView = new ListView<Avis>(avis);
            listView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
            LV_avis.setItems(avis);
            LV_avis.setCellFactory(param -> new ListCell<Avis>() {
                @Override
                protected void updateItem(Avis item, boolean empty) {
                    super.updateItem(item, empty);
                    if (empty || item == null) {
                        setText(null);
                    } else {
                        setText("Commentaire: " + item.getCommentaire() + ", Note: " + item.getNote());
                    }
                }
            });

        } catch (SQLException e) {
            System.out.println("error"+e.getMessage());
        }
    }


    @FXML
    void DeleteA (ActionEvent event) {
        try {
            int id = Integer.parseInt(updatedID.getText());
            ServiceAvis service = new ServiceAvis();
            service.supprimer(id);
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Success");
            alert.setContentText("Avis supprimée");
            alert.showAndWait();
        } catch (NumberFormatException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setContentText("Veuillez entrer un ID valide");
            alert.showAndWait();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    @FXML
    void updateavis(ActionEvent event) {
        try {
            int id = Integer.parseInt(updatedID.getText());
            String com = updatedcom.getText();
            int nnote = Integer.parseInt(updatednote.getText());
            ServiceAvis service = new ServiceAvis();
            Avis avis = new Avis();
            avis.setId_avis(id);
            avis.setCommentaire(com);
            avis.setNote(nnote);
            service.modifier(avis);
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Success");
            alert.setContentText("Avis modifiée avec succès");
            alert.showAndWait();
        } catch (NumberFormatException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setContentText("essayez plus tard");
            alert.showAndWait();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }



}
