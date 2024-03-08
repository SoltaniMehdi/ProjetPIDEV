package controllers;

import entities.Categorie;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import services.ServiceCategorie;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Collections;

public class AfficherCategorieController {

    private final ServiceCategorie serviceCategorie = new ServiceCategorie();
    public TextField rechercheFIELD;

    @FXML
    private ListView<Categorie> afficher_cat;

    @FXML
    private ImageView img;

    @FXML
    void initialize() {
        try {
            ObservableList<Categorie> categoriesList = FXCollections.observableArrayList(serviceCategorie.afficher());
            ObservableList<String> categorieInfo = FXCollections.observableArrayList();
            for (Categorie categorie : categoriesList) {
                categorieInfo.add("Nom: " + categorie.getNom() + ", Description: " + categorie.getDescription());

            }
            afficher_cat.setItems(categoriesList);
            afficher_cat.setOnMouseClicked(this::handleItemClick);

            rechercheFIELD.textProperty().addListener(new ChangeListener<String>() {
                @Override
                public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                    if (newValue == null || newValue.isEmpty()) {
                        afficher_cat.setItems(categoriesList);
                    } else {
                        ObservableList<Categorie> filteredList = FXCollections.observableArrayList();
                        for (Categorie categorie : categoriesList) {
                            if (categorie.getNom().toLowerCase().contains(newValue.toLowerCase())) {
                                filteredList.add(categorie);
                            }
                        }
                        afficher_cat.setItems(filteredList);
                    }
                }
            });
        } catch (SQLException e) {
            System.out.println("Erreur lors de l'affichage des repas : " + e.getMessage());
        }
    }

    private void handleItemClick(MouseEvent event) {
        if (event.getClickCount() == 2) { // Check for double click
            // Get the selected item
            Categorie selectedCategorie = afficher_cat.getSelectionModel().getSelectedItem();

            // Load the edit category view
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/EditCategorie.fxml"));
                Parent root = loader.load();

                // Pass the selected category to the controller of the edit category view
                EditCategoryController editCategoryController = loader.getController();
                editCategoryController.initData(selectedCategorie);

                // Show the edit category view
                Stage stage = new Stage();
                stage.setScene(new Scene(root));
                stage.show();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @FXML
    public void OnSort(ActionEvent actionEvent) {
        try {
            ObservableList<Categorie> categoriesList = FXCollections.observableArrayList(serviceCategorie.afficher());
            Collections.sort(categoriesList, (cat1, cat2) -> cat1.getNom().compareToIgnoreCase(cat2.getNom()));
            afficher_cat.getItems().clear();
            afficher_cat.setItems(categoriesList);
        } catch (SQLException e) {
            System.out.println("Erreur lors de l'affichage des repas : " + e.getMessage());
        }
    }

    public void OnStats(ActionEvent actionEvent) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/StatsCategorie.fxml"));
            Parent root = loader.load();
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void OnExport(ActionEvent actionEvent) {
        try {
            ObservableList<Categorie> categoriesList = FXCollections.observableArrayList(serviceCategorie.afficher());

            // Create a new PDF document
            PDDocument document = new PDDocument();
            PDPage page = new PDPage();
            document.addPage(page);

            // Create a new content stream for writing to the PDF
            PDPageContentStream contentStream = new PDPageContentStream(document, page);

            // Set font and font size
            contentStream.setFont(PDType1Font.HELVETICA, 12);

            // Write the data to the PDF
            int yPosition = 700; // Starting y position for writing
            for (Categorie categorie : categoriesList) {
                contentStream.beginText();
                contentStream.newLineAtOffset(50, yPosition);
                contentStream.showText("Nom: " + categorie.getNom() + ", Description: " + categorie.getDescription());
                contentStream.endText();
                yPosition -= 20; // Adjust y position for next line
            }

            // Close the content stream
            contentStream.close();

            // Save the PDF to a file
            File file = new File("categories.pdf");
            document.save(file);
            document.close();
            System.out.println("PDF exported successfully.");

        } catch (IOException | SQLException e) {
            e.printStackTrace();
        }
    }
}