package controllers;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import javafx.event.ActionEvent;

import entities.Commande;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import services.ServiceCommandes;
import services.ServiceNotif;

public class AfficherCommande {

    Commande selecteditem=null;
    @FXML
    private ListView<Commande> LVafficher;

    @FXML
    private AnchorPane afficher;

    @FXML
    private Button delete;

    @FXML
    private Button consulterpanier;

    @FXML
    private Button consulteravis;

    @FXML
    private TextField delete_bar;
    @FXML
    private TextField upatedprix;

    @FXML
    private Button update;

    @FXML
    private TextField updatedID;

    @FXML
    private ComboBox<String> updatedstatut;
    @FXML
    private Button rechercher;

    @FXML
    private TextField search_bar;

    @FXML
    private ComboBox<String> trier;
    @FXML
    private Button sort;
    @FXML
    private Button pageprec;


    @FXML
    void backafficher(ActionEvent event) {
        try {
            Parent root= FXMLLoader.load(getClass().getResource("/view/commande.fxml"));
            pageprec.getScene().setRoot(root);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }


    @FXML
    void consulteravis (ActionEvent event) {
        try {
            Parent root= FXMLLoader.load(getClass().getResource("/view/Avis.fxml"));
            consulterpanier.getScene().setRoot(root);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }


    @FXML
    void consulterpanier (ActionEvent event) {
        try {
            Parent root= FXMLLoader.load(getClass().getResource("/view/Panier.fxml"));
            consulterpanier.getScene().setRoot(root);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    private void loadrepas () {
        Stage primaryStage = (Stage) LVafficher.getScene().getWindow();
        FXMLLoader fxmlLoader= new FXMLLoader(getClass().getResource("/view/repas.fxml"));
        try {
            Parent root=fxmlLoader.load();
            Scene scene= new Scene(root);
            primaryStage.setScene(scene);
            primaryStage.setTitle("Details Commande");
            primaryStage.show();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

    }


    @FXML
    void initialize() {
        try {
            ServiceCommandes service = new ServiceCommandes();
            List<Commande> commandesList = service.afficher();
            ObservableList<Commande> commandes = FXCollections.observableArrayList(commandesList);
            ListView<Commande> listView = new ListView<Commande>(commandes);
            listView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
            LVafficher.setItems(commandes);
            LVafficher.setCellFactory(param -> new ListCell<Commande>() {
                @Override
                protected void updateItem(Commande item, boolean empty) {
                    super.updateItem(item, empty);
                    if (empty || item == null) {
                        setText(null);
                    } else {
                        setText("Date: " + item.getDatec() + ", Statut: " + item.getStatut() + ", Prix: " + item.getTotalprix());
                    }
                }
            });
            LVafficher.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Commande>() {

                @Override
                public void changed(ObservableValue<? extends Commande> observable, Commande oldValue, Commande newValue) {
                    selecteditem=newValue;
                }
            });
            LVafficher.setOnMouseClicked(new EventHandler<MouseEvent>() {

                @Override
                public void handle(MouseEvent event) {
                    RepasController.idC=selecteditem.getId_commande();
                    loadrepas();
                }
            });
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    @FXML
    void deleteAndNotify(ActionEvent event) {
        try {
            int id = Integer.parseInt(delete_bar.getText());
            ServiceCommandes service = new ServiceCommandes();
            service.supprimer(id);


            ServiceNotif.showNotification("Success", "Commande supprimée avec succès");
            ServiceNotif.playNotificationSound(true);


            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Success");
            alert.setContentText("Commande supprimée");
            alert.showAndWait();

        } catch (NumberFormatException e) {

            showAlert("Veuillez entrer un ID valide");

        } catch (SQLException e) {

            throw new RuntimeException(e);
        }
    }

    @FXML
    void updateAndNotify(ActionEvent event) {
        try {
            int id = Integer.parseInt(updatedID.getText());
            String statutCmd = updatedstatut.getSelectionModel().getSelectedItem().toString();
            int nouveauPrix = Integer.parseInt(upatedprix.getText());

            ServiceCommandes service = new ServiceCommandes();
            Commande commandes = new Commande();
            commandes.setId_commande(id);
            commandes.setStatut(statutCmd);
            commandes.setTotalprix(nouveauPrix);

            service.modifier(commandes);


            ServiceNotif.showNotification("Success", "Commande modifiée avec succès");
            ServiceNotif.playNotificationSound(true);

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
    void sort(ActionEvent event) {
        String selectedTrier = trier.getSelectionModel().getSelectedItem();
        if (selectedTrier != null) {
            switch (selectedTrier) {
                case "ID":
                    trierParID();
                    break;
                case "Prix":
                    trierParPrix();
                    break;
            }
        }
    }

    private void trierParID() {
        ObservableList<Commande> commandes = LVafficher.getItems();


        commandes.sort(Comparator.comparingInt(Commande::getId_commande));


        LVafficher.setItems(commandes);
    }

    private void trierParPrix() {
        ObservableList<Commande> commandes = LVafficher.getItems();


        commandes.sort(Comparator.comparingDouble(Commande::getTotalprix));


        LVafficher.setItems(commandes);
    }



    @FXML
    private void search(ActionEvent event) {
        try {
            String searchText = search_bar.getText().trim();
            ServiceCommandes service = new ServiceCommandes();

            if (searchText.isEmpty()) {
                List<Commande> commandesList = service.afficher();
                updateListView(commandesList);
            } else {
                try {
                    int id = Integer.parseInt(searchText);
                    Commande commandeById = service.getById(id);
                    if (commandeById != null) {
                        updateListView(List.of(commandeById));
                    } else {
                        showAlert("No commandes found with ID: " + id);
                    }
                } catch (NumberFormatException e) {
                    List<Commande> commandesByStatut = service.getByStatut(searchText);
                    if (!commandesByStatut.isEmpty()) {

                        updateListView(commandesByStatut);
                    } else {
                        showAlert("No commandes found with statut: " + searchText);
                    }
                }
            }
        } catch (SQLException e) {
            showAlert("Error occurred while searching for commandes.");
            e.printStackTrace();
        }


    }
    private void updateListView(List<Commande> commandesList) {
        ObservableList<Commande> commandes = FXCollections.observableArrayList(commandesList);
        LVafficher.setItems(commandes);
    }

    private void showAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information");
        alert.setContentText(message);
        alert.showAndWait();
    }

    @FXML
    void searchById(ActionEvent event) {
        try {
            int id = Integer.parseInt(search_bar.getText());
            ServiceCommandes service = new ServiceCommandes();
            Commande commande = service.getById(id);
            updateListView(Collections.singletonList(commande));
        } catch (NumberFormatException e) {
            showAlert("Veuillez entrer un ID valide");
        } catch (SQLException e) {
            showAlert("Error retrieving commande by ID.");
            e.printStackTrace();
        }
    }

    @FXML
    void searchByStatut(ActionEvent event) {
        try {
            String statut = search_bar.getText();
            ServiceCommandes service = new ServiceCommandes();
            List<Commande> commandesList = service.getByStatut(statut);
            updateListView(commandesList);
        } catch (SQLException e) {
            showAlert("Error retrieving commandes by statut.");
            e.printStackTrace();
        }
    }



}













