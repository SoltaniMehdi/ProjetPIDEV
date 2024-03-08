package controllers;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.event.ActionEvent;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;

import javafx.scene.Scene;
import javafx.scene.control.*;
import entities.evenement;
import javafx.stage.Stage;
import services.serviceevenement;

import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.Comparator;

import java.util.logging.Level;
import java.util.logging.Logger;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;



import org.controlsfx.control.Notifications;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;







public class AfficherEventsController implements Initializable {

    public static int selectedId = 0;
    @javafx.fxml.FXML
    private Button supp;
    @javafx.fxml.FXML
    private ListView<evenement> lv_event;
    @javafx.fxml.FXML
    private Button b_modifier;
    @javafx.fxml.FXML
    private ComboBox<String> triComboBox;


    private ObservableList<evenement> eventList = FXCollections.observableArrayList();
    private serviceevenement se = new serviceevenement();
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        afficherevents();
        triComboBox.getItems().addAll("Nom", "Date");

        // Gérer le changement de sélection dans la ComboBox
        triComboBox.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                // Appeler la méthode de tri appropriée en fonction de la sélection
                if (newValue.equals("Nom")) {
                    trierParNom();
                } else if (newValue.equals("Date")) {
                    trierParDate();
                }
            }
        });

        checkEventNotifications();




        lv_event.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                selectedId = newValue.getId();
                System.out.println("Selected ID: " + selectedId);



                filteredEventList = new FilteredList<>(eventList, p -> true);

                // Liaison de la liste filtrée à la ListView
                lv_event.setItems(filteredEventList);


            }
        });
    }



    private void trierParNom() {
        eventList.sort(Comparator.comparing(evenement::getNom));
        lv_event.setItems(eventList);
    }

    private void trierParDate() {
        eventList.sort(Comparator.comparing(evenement::getDateD));
        lv_event.setItems(eventList);
    }


    public void afficherevents()
    {
       lv_event.getItems().clear();
        List<evenement> events = null;
        try {
            events = se.afficher();
            System.out.println(se.afficher());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        // Add the events to the ObservableList
        eventList.addAll(events);

        // Set the ObservableList to the ListView
        lv_event.setItems(eventList);
    }


    @javafx.fxml.FXML
    public void OnDeleteClicked(ActionEvent actionEvent) throws SQLException {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Delete Confirmation");
        alert.setHeaderText(null);
        alert.setContentText("Are you sure you want to delete the selected item?");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.OK) {
            se.supprimer(selectedId);
            afficherevents();
        }
    }

    @javafx.fxml.FXML
    public void GoUpdate(ActionEvent actionEvent) {
        if (selectedId !=0) {
            try {
                Parent root = FXMLLoader.load(getClass().getResource("/UpdateEvent.fxml"));
                b_modifier.getScene().setRoot(root);
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        }
        else
        {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("No event selected !");
            alert.showAndWait();
        }
    }

    @javafx.fxml.FXML
    public void afficher_categorie(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/affichercategorie.fxml"));
        b_modifier.getScene().setRoot(root);
    }


    @javafx.fxml.FXML
    private Button b_rech;
    @javafx.fxml.FXML
    private TextField tf_rech;



    private FilteredList<evenement> filteredEventList;

    @javafx.fxml.FXML
    void OnrechercherCliked(ActionEvent event) {

        String searchTerm = tf_rech.getText().toLowerCase();

        // Appliquer le filtre de recherche
        filteredEventList.setPredicate(evenement -> {
            if (searchTerm.isEmpty()) {
                return true; // Afficher tous les événements si la recherche est vide
            }

            // Vérifier si le terme de recherche est présent dans le nom ou la description
            return evenement.getNom().toLowerCase().contains(searchTerm)
                    || evenement.getDescription().toLowerCase().contains(searchTerm);
        });


    }
    @javafx.fxml.FXML
    private Button b_ex;
    @javafx.fxml.FXML
    private void ExportExce(ActionEvent event) {
        try {
            Workbook workbook = new XSSFWorkbook();
            Sheet sheet = workbook.createSheet("Liste des Evenements");

            // En-tête
            Row headerRow = sheet.createRow(0);
            headerRow.createCell(0).setCellValue("Nom");
            headerRow.createCell(1).setCellValue("Description");
            headerRow.createCell(2).setCellValue("Date Début");
            headerRow.createCell(4).setCellValue("Lieu");
            headerRow.createCell(5).setCellValue("Categorie");

            // Données
            ObservableList<evenement> eventList = FXCollections.observableList(se.afficher());
            for (int i = 0; i < eventList.size(); i++) {
                Row row = sheet.createRow(i + 1);
                evenement evenement = eventList.get(i);
                row.createCell(0).setCellValue( evenement.getNom());
                row.createCell(1).setCellValue( evenement.getDescription());
                row.createCell(2).setCellValue( evenement.getDateD().toString());
                row.createCell(4).setCellValue( evenement.getLieu());
                row.createCell(5).setCellValue( evenement.getId_categorie());
            }

            // Sauvegarde du fichier
            String fileName = "liste_evenements.xlsx";
            try (FileOutputStream fileOut = new FileOutputStream(fileName)) {
                workbook.write(fileOut);
                fileOut.flush();
            }

            System.out.println("Export Excel réussi.");
        } catch (IOException | SQLException e) {
            e.printStackTrace();
        }
    }









    @javafx.fxml.FXML
    private Button b_chat;

    @javafx.fxml.FXML
    public void onchatcliked(ActionEvent event) throws IOException  {

      FXMLLoader loader = new FXMLLoader(getClass().getResource("/chat.fxml"));
        Parent root = loader.load();
        b_chat.getScene().setRoot(root);

    }


    @javafx.fxml.FXML
    private Button  Home;

    @javafx.fxml.FXML
    public void onhomeclicked(ActionEvent event) { try {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/affichercategorie.fxml"));
        Parent root = loader.load();
        Home.getScene().setRoot(root);
    } catch (IOException ex) {
        Logger.getLogger(ajouterevent.class.getName()).log(Level.SEVERE, null, ex);
    }
    }


    private void checkEventNotifications() {
        LocalDate today = LocalDate.now();
        LocalDate threeDaysLater = today.plus(3, ChronoUnit.DAYS);

        for (evenement evenement : eventList) {
            LocalDate eventDate = evenement.getDateD().toLocalDate();
            if (eventDate.isAfter(today) && eventDate.isBefore(threeDaysLater)) {
                // Événement dans les 3 prochains jours, afficher la notification
                showNotification("Événement à venir", "L'événement \"" + evenement.getNom() + "\" dans les 3 prochains jours.");
            }
        }
    }

    private void showNotification(String title, String text) {
        Notifications.create()
                .title(title)
                .text(text)
                .showInformation();
    }
    @FXML
    void retourAcceuil(ActionEvent event) {
        try {Parent root;
            Scene scene;
            Stage stage;
            root = (Parent) FXMLLoader.load(this.getClass().getResource("/Admin.fxml"));
            scene = new Scene(root);
            stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }






}










