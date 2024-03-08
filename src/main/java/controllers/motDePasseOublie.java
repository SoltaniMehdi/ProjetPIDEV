package controllers;
import java.io.IOException;
import java.net.URL;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.Objects;
import java.util.ResourceBundle;

import entities.Utilisateur;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import services.CRUDUtilisateur;
import utils.Encryptor;

import javax.swing.*;

public class motDePasseOublie {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private AnchorPane codeVerifAnchor;

    @FXML
    private TextField codeVerification;

    @FXML
    private ProgressBar complexiteBar;

    @FXML
    private Label complexiteLabel;

    @FXML
    private PasswordField confirmerNouveauMdp;

    @FXML
    private Label echecLoginLabel;

    @FXML
    private Label echecLoginLabel1;

    @FXML
    private PasswordField nouveauMdp;

    @FXML
    private AnchorPane nouveauMdpAnchor;

    @FXML
    private Button retourButton;

    @FXML
    private Button retourButton1;

    @FXML
    private Button validerCode;

    @FXML
    private Button validerMdp;
    Encryptor encryptor = new Encryptor();
    CRUDUtilisateur crudUtilisateur=new CRUDUtilisateur();
    private static int rand;
    public static void setRand(int r) {
        rand = r;
    }
    private static Utilisateur currentUtilisateur;

    public static void setCurrentUtilisateur(Utilisateur utilisateur) {
        currentUtilisateur = utilisateur;
    }

    // Method to get the current user
    public static Utilisateur getCurrentUtilisateur() {
        return currentUtilisateur;
    }
    // Méthode getter pour obtenir le numéro aléatoire
    public static int getRand() {
        return rand;
    }
    @FXML
    void retourButtonOnClick(ActionEvent event) {try {Parent root;
        Scene scene;
        Stage stage;
        root = (Parent) FXMLLoader.load(this.getClass().getResource("/modifiersupprimerclient.fxml"));
        scene = new Scene(root);
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    } catch (IOException e) {
        throw new RuntimeException(e);
    }

    }

    @FXML
    void validerCodeOnClick(ActionEvent event) {
        if (Integer.parseInt(codeVerification.getText()) == rand){
            codeVerifAnchor.setVisible(false);
            nouveauMdpAnchor.setVisible(true);
        }
        else {
            JOptionPane.showMessageDialog(null,"Code incorrecte! ");
        }

    }
    private boolean getError(){
        String thisUserMdp = currentUtilisateur.getPassword();
        if(nouveauMdp.getText().isBlank()|| complexiteLabel.getText().equals("Faible") || complexiteLabel.getText().equals("Très Faible")){
            echecLoginLabel.setTextFill(Color.RED);
            echecLoginLabel.setText("Le mot de passe est invalide");
            return true;
        }
        if(confirmerNouveauMdp.getText().isBlank()){
            echecLoginLabel.setTextFill(Color.RED);
            echecLoginLabel.setText("La confirmation du mot de passe est invalide");
            return true;
        }
        if(!Objects.equals(confirmerNouveauMdp.getText(), nouveauMdp.getText())){
            echecLoginLabel.setTextFill(Color.RED);
            echecLoginLabel.setText("Le mot de passe doit etre le meme");
            return true;
        }
        return false;
    }

    @FXML
    void validerMdpOnClick(ActionEvent event) {
        if (!getError()){
            //CRUDUtilisateur crudUtilisateur1=new CRUDUtilisateur();
            try {
                crudUtilisateur.modifierMdp(currentUtilisateur, encryptor.encryptString(nouveauMdp.getText()));
                JOptionPane.showMessageDialog(null,"Mot de Passe modifié avec succès !");
                try {Parent root;
                    Scene scene;
                    Stage stage;
                    root = (Parent) FXMLLoader.load(this.getClass().getResource("/login.fxml"));
                    scene = new Scene(root);
                    stage = (Stage)((Node)event.getSource()).getScene().getWindow();
                    stage.setScene(scene);
                    stage.show();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            } catch (NoSuchAlgorithmException e) {
                throw new RuntimeException(e);
            }
        }

    }

}
