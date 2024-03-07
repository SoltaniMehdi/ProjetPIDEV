package services;

import javafx.scene.media.AudioClip;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import org.controlsfx.control.Notifications;
import java.io.File;

public class ServiceNotif {

    public static void showNotification(String title, String message) {
        Notifications.create()
                .title(title)
                .text(message)
                .showInformation();
    }

    public static void playNotificationSound(boolean isSuccess) {
        try {
            // Define the sound file path
            String soundFilePath = "C:/Users/33629/Downloads/appstore.mp3";

            // Create a Media object
            Media sound = new Media(new File(soundFilePath).toURI().toString());

            // Create a MediaPlayer
            MediaPlayer mediaPlayer = new MediaPlayer(sound);

            // Play the sound
            mediaPlayer.play();
        } catch (Exception e) {
            System.err.println("Error playing notification sound: " + e.getMessage());
        }
    }

}