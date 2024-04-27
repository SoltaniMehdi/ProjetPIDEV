package controller;
import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;


public class chatContoller implements Initializable{
    @FXML
    private TextArea textchat;
    @FXML
    private TextField ASK;

    private Map<String, String> responses;
    @FXML
    private Button Home;

    private int alertCount = 0;
    private static final int MAX_ALERTS = 3;
    private static final int BAN_DURATION_MINS = 5;
    private static final String[] FORBIDDEN_WORDS = {"fuck", "shutup", "damn"};


    /**
     * Initializes the controller class.
     */
    public void initialize(URL url, ResourceBundle rb) {
        responses = new HashMap<>();
        responses.put("bonjour","comment je peux vous aidez");
        responses.put("quelle est l'interet des evenements", " la sensibilisation c notre interet  ");
        responses.put("Quels sont les prochains événements prévus", "on vas assister à beaucoup des events ");
        responses.put("Comment puis-je m'inscrire à un événement?", "Vous pouvez vous inscrire à un événement en utilisant la fonction S'inscrire dans la description de l'événement Assurez-vous de remplir tous les détails requis");
        responses.put("nom du responsable events", "Yasmine Bouteraa");
        responses.put("salut", " salut comment va_tu");
        responses.put("who are you?", "je suis un bot créer par yasmine bouteraa");
        responses.put("vitaplat?", "c'est une platforme de healthy food ");
        responses.put("quels sont les plats que vous serviez", " vitaplat propose des nombreux plats sainset délicieux");


    }

    @FXML
    private void UserA(ActionEvent actionEvent) throws Exception {
        String input = ASK.getText();
        String response = responses.getOrDefault(input, "désolé j'ai pas la réponse ");
        for (String forbiddenWord : FORBIDDEN_WORDS) {
            if (input.contains(forbiddenWord)) {
                alertCount++;
                if (alertCount == MAX_ALERTS) {
                    // Ban the user for 5 minutes
                    ASK.setDisable(true);
                    Alert alert = new Alert(AlertType.ERROR, "Vous avez été banni ");
                    alert.showAndWait();
                   //Mail.sendMail("bouteraa.yassmine@esprit.tn");
                    Platform.exit();
                } else {
                    Alert alert = new Alert(AlertType.WARNING, "Le message contient un gros mot. Attention!");
                    alert.showAndWait();            }
                ASK.clear();
                return;
            }
        }
        textchat.appendText("User: " + input + "\n");
        textchat.appendText("Chatbot: " + response + "\n\n");
        ASK.clear();}

    @FXML
    private void onhomeclicked(ActionEvent actionEvent) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/affichercategorie.fxml"));
            Parent root = loader.load();
            Home.getScene().setRoot(root);
        } catch (IOException ex) {
            Logger.getLogger(ajouterevent.class.getName()).log(Level.SEVERE, null, ex);
        }
    }



}
