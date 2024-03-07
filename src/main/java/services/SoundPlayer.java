package services;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import java.io.File;

public class SoundPlayer {

    public static void playNotificationSound() {
        // Define the sound file path
        String soundFilePath = "C:/Users/33629/Downloads/appstore.mp3";

        // Create a Media object with the sound file
        Media sound = new Media(new File(soundFilePath).toURI().toString());

        // Create a MediaPlayer with the Media object
        MediaPlayer mediaPlayer = new MediaPlayer(sound);

        // Play the sound
        mediaPlayer.play();
    }
}
