package controllers;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import entities.Repas;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import services.ServiceRepas;

import java.io.*;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AfficherRepasController {

    private final ServiceRepas serviceRepas = new ServiceRepas();

    @FXML
    private ListView<Repas> afficher_rep;

    @FXML
    private TextField rechercheField;

    @FXML
    private ImageView img;

    @FXML
    void initialize() {
        try {
            List<Repas> repasList = serviceRepas.afficher();
            ObservableList<Repas> observableRepasList = FXCollections.observableArrayList(repasList);
            afficher_rep.setItems(observableRepasList);
            rechercheField.textProperty().addListener((observable, oldValue, newValue) -> {
                if (newValue == null || newValue.isEmpty()) {
                    afficher_rep.setItems(observableRepasList);
                } else {
                    ObservableList<Repas> filteredList = FXCollections.observableArrayList();
                    for (Repas repas : repasList) {
                        if (repas.getNom().toLowerCase().contains(newValue.toLowerCase())) {
                            filteredList.add(repas);
                        }
                    }
                    afficher_rep.setItems(filteredList);
                }
            });
            afficher_rep.setCellFactory(param -> new RepasListCell());
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Erreur lors de l'affichage des repas : " + e.getMessage());
        }
    }

    public void OnExport(ActionEvent actionEvent) {
        try {
            List<Repas> repasList = serviceRepas.afficher();

            Workbook workbook = new XSSFWorkbook();
            Sheet sheet = workbook.createSheet("Repas Data");

            Row headerRow = sheet.createRow(0);
            headerRow.createCell(0).setCellValue("Nom");
            headerRow.createCell(1).setCellValue("Prix");
            headerRow.createCell(2).setCellValue("Description");
            headerRow.createCell(3).setCellValue("Catégorie");

            int rowNum = 1;
            for (Repas repas : repasList) {
                Row row = sheet.createRow(rowNum++);
                row.createCell(0).setCellValue(repas.getNom());
                row.createCell(1).setCellValue(repas.getPrix());
                row.createCell(2).setCellValue(repas.getDescription());
                row.createCell(3).setCellValue(repas.getCategorie()+"");
            }

            FileChooser fileChooser = new FileChooser();
            fileChooser.setInitialFileName("repas_data.xlsx");
            File file = fileChooser.showSaveDialog(null);
            if (file != null) {
                try (FileOutputStream outputStream = new FileOutputStream(file)) {
                    workbook.write(outputStream);
                    Alert alert = new Alert(Alert.AlertType.INFORMATION, "Data exported successfully!", ButtonType.OK);
                    alert.showAndWait();
                }
            }
            workbook.close();
        } catch (IOException | SQLException e) {
            e.printStackTrace();
            Alert alert = new Alert(Alert.AlertType.ERROR, "Error exporting data: " + e.getMessage(), ButtonType.OK);
            alert.showAndWait();
        }
    }

    public void OnStats(ActionEvent actionEvent) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/StatsRepas.fxml"));
            Parent root = loader.load();
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void OnSort(ActionEvent actionEvent) {
        try {
            ObservableList<Repas> repasList = FXCollections.observableArrayList(serviceRepas.afficher());
            SortedList<Repas> sortedList = new SortedList<>(repasList, (repas1, repas2) -> repas1.getNom().compareToIgnoreCase(repas2.getNom()));
            afficher_rep.setItems(sortedList);
        } catch (SQLException e) {
            System.out.println("Erreur lors de l'affichage des repas : " + e.getMessage());
        }
    }

    public static class RepasListCell extends ListCell<Repas> {
        @Override
        protected void updateItem(Repas item, boolean empty) {
            super.updateItem(item, empty);

            if (empty || item == null) {
                setText(null);
                setGraphic(null);
            } else {
                setText("Nom: " + item.getNom() + ", Prix: " + item.getPrix() + ", Description: " + item.getDescription() + ", Catégorie: " + item.getCategorie());
                try {
                    byte[] qrImageBytes = generateQRCode(getRepasInfo(item), 100, 100);
                    Image fxImage = new Image(new ByteArrayInputStream(qrImageBytes));
                    ImageView qrImageView = new ImageView(fxImage);
                    qrImageView.setOnMouseClicked(event -> {
                        if (event.getClickCount() == 2) {
                            // Load the EditRepas.fxml file
                            FXMLLoader loader = new FXMLLoader(getClass().getResource("/EditRepas.fxml"));
                            Parent root;
                            try {
                                root = loader.load();
                                // Get the controller of the EditRepas.fxml file
                                EditRepasController editRepasController = loader.getController();
                                // Pass the selected repas data to the EditRepasController
                                editRepasController.initData(item);
                                // Show the EditRepas.fxml file in a new stage
                                Stage stage = new Stage();
                                stage.setScene(new Scene(root));
                                stage.show();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                    });
                    setGraphic(qrImageView);
                } catch (IOException | WriterException e) {
                    e.printStackTrace();
                }
            }
        }

        private String getRepasInfo(Repas repas) {
            // Return the information you want to encode in the QR code
            return repas.getNom() + "\n" + repas.getPrix() + "\n" + repas.getDescription() + "\n" + repas.getCategorie();
        }

        private byte[] generateQRCode(String text, int width, int height) throws WriterException, IOException {
            Map<EncodeHintType, Object> hintMap = new HashMap<>();
            hintMap.put(EncodeHintType.CHARACTER_SET, "UTF-8");
            BitMatrix bitMatrix = new MultiFormatWriter().encode(text, BarcodeFormat.QR_CODE, width, height, hintMap);
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            MatrixToImageWriter.writeToStream(bitMatrix, "PNG", outputStream);
            return outputStream.toByteArray();
        }
    }
    }