package controllers;
import  java.sql.Connection;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import java.io.IOException;
import javafx.scene.control.TextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import entities.Livraison1;
import javafx.scene.control.cell.PropertyValueFactory;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JasperViewer;
import services.ServiceLivraison;
import utils.MyDataBase;
public class AfficherLivraisonController {
    private MyDataBase myDatabase = MyDataBase.getInstance();

    ServiceLivraison serviceLivraison = new ServiceLivraison();
    @FXML
    private TableView<Livraison1> livraisonTable;

    @FXML
    private TableColumn<Livraison1, Number> idLivraisonColumn;

    @FXML
    private TableColumn<Livraison1, Number> idCommandeColumn;

    @FXML
    private TableColumn<Livraison1, Number> idClientColumn;

    @FXML
    private TableColumn<Livraison1, String> dateLivraisonColumn;

    @FXML
    private TableColumn<Livraison1, String> adresseColumn;

    @FXML
    private TableColumn<Livraison1, Number> prixColumn;
    @FXML
    private Button back;
    @FXML
    private Button supprimerButton;
    @FXML
    private TextField searchField;
    //private IService<Livraison1> serviceLivraison;

    @FXML
    void afficherT(ActionEvent event) {
        try {
            Parent root= FXMLLoader.load(getClass().getResource("/Stat.fxml"));
            back.getScene().setRoot(root);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
    @FXML
    void supprimer(ActionEvent event) {
        Livraison1 livraison1 = livraisonTable.getSelectionModel().getSelectedItem();
        if (livraison1 != null) {
            try {
                serviceLivraison.supprimer(livraison1.getId_livraison());
                livraisonTable.getItems().remove(livraison1);
                showAlert("Succès", "livraison supprimé avec succès.", "La livraison a été supprimé avec succès.", Alert.AlertType.INFORMATION);
            } catch (SQLException e) {
                e.printStackTrace();
                showAlert("Erreur", "Erreur lors de la suppression du pack.", e.getMessage(), Alert.AlertType.ERROR);
            }
        } else {
            showAlert("Aucune sélection", "Aucun complexe sélectionné", "Veuillez sélectionner une complexe à supprimer.", Alert.AlertType.WARNING);
        }
    }
    @FXML
    public void handleAnnulerButton() {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/AjouterLivraison.fxml"));
            Scene scene = back.getScene();
            scene.setRoot(root);
        } catch (IOException e) {
            e.printStackTrace();
            showAlert("Erreur", "Erreur lors du retour à l'interface précédente", e.getMessage(), Alert.AlertType.ERROR);
        }
    }
    @FXML
    public void initialize() {
        try {


        ObservableList<Livraison1> Livraisons= FXCollections.observableList(serviceLivraison.afficher());
        livraisonTable.setItems(Livraisons);
        idLivraisonColumn.setCellValueFactory(new PropertyValueFactory<>("id_livraison"));
        idCommandeColumn.setCellValueFactory(new PropertyValueFactory<>("id_commande"));
        idClientColumn.setCellValueFactory(new PropertyValueFactory<>("id_client"));
        dateLivraisonColumn.setCellValueFactory(new PropertyValueFactory<>("date_livraison"));
        adresseColumn.setCellValueFactory(new PropertyValueFactory<>("adresse"));
        prixColumn.setCellValueFactory(new PropertyValueFactory<>("prix"));
    }catch (SQLException e) {
            System.out.println(e.getMessage());
        }

    }
    @FXML

    void modifier(ActionEvent event) {
        Livraison1 livraison1 = livraisonTable.getSelectionModel().getSelectedItem();
        if (livraison1 != null) {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/ModifierLivraison.fxml"));
                Parent root = loader.load();
                ModifierLivraisonController controller = loader.getController();

                // Passer le pack sélectionné au contrôleur de modification
                controller.initData(livraison1);

                // Passer l'instance de ServicePack au contrôleur de modification
                controller.setServiceLivraison(serviceLivraison);

                // Remplacer le contenu de la fenêtre actuelle avec celui de l'interface de modification
                livraisonTable.getScene().setRoot(root);
            } catch (IOException e) {
                e.printStackTrace();
                showAlert("Erreur", "Erreur lors de l'ouverture de l'interface de modification", e.getMessage(), Alert.AlertType.ERROR);
            }
        } else {
            showAlert("Aucune sélection", "Aucun pack sélectionné", "Veuillez sélectionner un pack à modifier.", Alert.AlertType.WARNING);
        }
    }
    public void refreshTable() {
        try {
            ObservableList<Livraison1> livraison1s = FXCollections.observableList(serviceLivraison.afficher());
            livraisonTable.setItems(livraison1s);
        } catch (Exception e) {
            System.out.println("An error occurred while refreshing table: " + e.getMessage());
        }
    }

    private void showAlert(String title, String headerText, String contentText, Alert.AlertType alertType) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(headerText);
        alert.setContentText(contentText);
        alert.showAndWait();
    }

    @FXML
    void search()  {
        String keyword = searchField.getText().toLowerCase();
ObservableList<Livraison1> filteredList = FXCollections.observableArrayList();
        // Si le champ de recherche est vide, rétablissez la liste complète
        if (keyword.isEmpty()) {
            try {
                livraisonTable.setItems(FXCollections.observableList(serviceLivraison.afficher()));
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            return;
        }

        //Filtrer la liste selon le mot-clé de recherche
        for (Livraison1 livraison1 : livraisonTable.getItems()) {
            String prixStr = String.valueOf(livraison1.getPrix());
            if (livraison1.getDate_livraison().toLowerCase().contains(keyword) ||
                    prixStr.toLowerCase().contains(keyword) || livraison1.getAdresse().toLowerCase().contains(keyword)) {
                filteredList.add(livraison1);
            }
        }
        livraisonTable.setItems(filteredList);
    }

    @FXML
    private void pdf_user(ActionEvent event) {
        System.out.println("hello");
        try{

            JasperDesign jDesign = JRXmlLoader.load("C:\\VitaPlat\\src\\main\\resources\\report.jrxml");

            JasperReport jReport = JasperCompileManager.compileReport(jDesign);
            Connection connection = myDatabase.getConnection();
            JasperPrint jPrint = JasperFillManager.fillReport(jReport, null, connection);

            JasperViewer viewer = new JasperViewer(jPrint, false);

            viewer.setTitle("Liste des Utilistaeurs");
            viewer.show();
            System.out.println("hello");


        }catch(Exception e){
            System.out.println(e.getMessage());
        }
    }
    



}
