package tests;

import entities.Commande;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;


import java.io.IOException;

public class MainFX extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        FXMLLoader fxmlLoader= new FXMLLoader(getClass().getResource("/view/commande.fxml"));
        try {
            Parent root=fxmlLoader.load();
            Scene scene= new Scene(root);
            primaryStage.setScene(scene);
            primaryStage.setTitle("Gestion des commandes");
            primaryStage.show();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

    }


}
