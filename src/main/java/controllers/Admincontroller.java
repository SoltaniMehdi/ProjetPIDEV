package controllers;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import entities.Utilisateur;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import services.CRUDUtilisateur;

public class Admincontroller {
    CRUDUtilisateur crudUtilisateur=new CRUDUtilisateur();

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;
    @FXML
    private TableColumn<Utilisateur,String> role_utilisateur;

    @FXML
    private TableColumn<Utilisateur, String> id_utilisateur;

    @FXML
    private TableColumn<Utilisateur, String> mail_utilisateur;

    @FXML
    private TableColumn<Utilisateur, String> nom_utilisateur;

    @FXML
    private TableColumn<Utilisateur, String> numero_utilisateur;

    @FXML
    private TableColumn<Utilisateur, String> password_utilisateur;

    @FXML
    private TableColumn<Utilisateur, String> prenom_utilisateur;

    @FXML
    private TableView<Utilisateur> tv_admin;
    private Utilisateur selectedUser;
    @FXML
    void initialize() {
        ObservableList<Utilisateur> utilisateurs= FXCollections.observableList(crudUtilisateur.afficherUtilisateur());
        tv_admin.setItems(utilisateurs);
        id_utilisateur.setCellValueFactory(new PropertyValueFactory<>("ID_user"));
        nom_utilisateur.setCellValueFactory(new PropertyValueFactory<>("nom"));
        prenom_utilisateur.setCellValueFactory(new PropertyValueFactory<>("prenom"));
        numero_utilisateur.setCellValueFactory(new PropertyValueFactory<>("numero"));
        mail_utilisateur.setCellValueFactory(new PropertyValueFactory<>("mail"));
        password_utilisateur.setCellValueFactory(new PropertyValueFactory<>("password"));
        role_utilisateur.setCellValueFactory(new PropertyValueFactory<>("role"));
        tv_admin.setOnMouseClicked(event -> {
            if (event.getClickCount()== 1) { // Check for single click
                selectedUser = tv_admin.getSelectionModel().getSelectedItem();
            }
        });
    }

    @FXML
    void modifiersupprimerUtilisateur(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/modifiersupprimeradmin.fxml"));
            Parent root = (Parent) loader.load();
            modifiersupprimeradminController controller = loader.getController();
            controller.setSelectedUser(selectedUser); // Pass the selected user to the controller
            Scene scene = new Scene(root);
            Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } catch (IOException var9) {
            System.err.println(var9.getMessage());
        }
    }
    @FXML
    void AdminSupprimer(ActionEvent event) {
        CRUDUtilisateur crudUtilisateur= new CRUDUtilisateur();
        // Appeler la méthode pour supprimer l'utilisateur
        crudUtilisateur.supprimerUtilisateur(selectedUser.getID_user());
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("success");
        alert.setContentText("Utilisateur supprimé avec succée");
        alert.showAndWait();
        //tarjaa lel admin.fxml
        try {
            Parent root;
            Scene scene;
            Stage stage;
            root = (Parent) FXMLLoader.load(this.getClass().getResource("/Admin.fxml"));
            scene = new Scene(root);
            stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } catch (IOException var9) {
            System.err.println(var9.getMessage());
        }

    }
    @FXML
    void redcommandeadmin(ActionEvent event) {
        try {
            Parent root;
            Scene scene;
            Stage stage;
            root = (Parent) FXMLLoader.load(this.getClass().getResource("/view/commande.fxml"));
            scene = new Scene(root);
            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    @FXML
    void redeventadmin(ActionEvent event) {
        try {
            Parent root;
            Scene scene;
            Stage stage;
            root = (Parent) FXMLLoader.load(this.getClass().getResource("/AfficherEvents.fxml"));
            scene = new Scene(root);
            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }


}
