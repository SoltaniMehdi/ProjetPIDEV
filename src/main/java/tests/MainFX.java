package tests;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class MainFX extends Application {

    public static void main(String[] args) {
        launch(args);
    }

   @Override
    public void start(Stage primaryStage) {
       FXMLLoader fxmlLoader= new FXMLLoader(getClass().getResource("/AjouterRepas.fxml"));
        try {
           Parent root= fxmlLoader.load();
           Scene scene= new Scene(root);
            primaryStage.setScene(scene);
           primaryStage.setTitle("Gestion Repas");
            primaryStage.show();

       } catch (IOException e) {
           System.out.println(e.getMessage());
       }


   }



   // @Override
   // public void start(Stage primaryStage) {
      //  FXMLLoader fxmlLoader= new FXMLLoader(getClass().getResource("/AjouterCategorie.fxml"));
      //  try {
         //   Parent root= fxmlLoader.load();
         //   Scene scene= new Scene(root);
            //primaryStage.setScene(scene);
           // primaryStage.show();

       // } catch (IOException e) {
       //     System.out.println(e.getMessage());
      //  }


    //}
}
