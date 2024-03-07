package controller;


import controller.MailingController;
import entities.Commande;
import javafx.event.ActionEvent;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.awt.*;
import java.io.IOException;
import java.util.Properties;
import entities.Commande;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class MailingController {
    @FXML
    private TextField adr;

    @FXML
    private TextArea contenuemail;

    @FXML
    private Button envoyeemail;

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
    public void initialize() {
    }


    @FXML
    public void sendCustomizedEmail(ActionEvent event) {
        String userEmail = "ela.marzouki@esprit.tn";
        Commande commande = new Commande();
        String customizedMessage = contenuemail.getText();
        sendCustomizedEmail(event, customizedMessage, userEmail, commande);
    }

    @FXML
    public void sendCustomizedEmail(ActionEvent event, String customizedMessage, String userEmail, Commande commande) {
                Properties props = new Properties();
                props.put("mail.smtp.host", "smtp.gmail.com");
                props.put("mail.smtp.port", 465);
                props.put("mail.smtp.user", "ela.marzouki@esprit.tn");
                props.put("mail.smtp.auth", true);
                props.put("mail.smtp.starttls.enable", true);
                props.put("mail.smtp.debug", true);
                props.put("mail.smtp.socketFactory.port", 465);
                props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
                props.put("mail.smtp.socketFactory.fallback", false);

                try {
                    Session session = Session.getDefaultInstance(props, null);
                    session.setDebug(true);
                    MimeMessage message = new MimeMessage(session);
                    message.setSubject("Customized Email Subject");
                    message.setFrom(new InternetAddress("ela.marzouki@esprit.tn"));

                    // Set the customized message
                    message.setText(customizedMessage);

                    message.addRecipient(Message.RecipientType.TO, new InternetAddress(userEmail));

                    try {
                        Transport transport = session.getTransport("smtp");
                        transport.connect("smtp.gmail.com", "ela.marzouki@esprit.tn", "azre qofw sipr eyae");
                        transport.sendMessage(message, message.getAllRecipients());
                        transport.close();
                        System.out.println("Email sent successfully!");
                    } catch (Exception e) {
                        // Failure message
                        System.out.println("Failed to send email. Error: " + e.getMessage());
                    }

                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
            }
}
