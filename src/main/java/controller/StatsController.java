package controller;

import entities.Avis;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import org.apache.commons.math3.stat.descriptive.DescriptiveStatistics;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.chart.PieChart;
import utils.MyDatabase;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class StatsController {

    @FXML
    private PieChart chart;
    private List<Avis> avisList;
    @FXML
    private Button pageprec;

    @FXML
    void backafficher(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/view/Avis.fxml"));
            pageprec.getScene().setRoot(root);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    @FXML
    private void initialize() {
        avisList = fetchAvisData();
        updateChart();
    }

    private void updateChart() {
        int badCount = 0;
        int averageCount = 0;
        int goodCount = 0;

        DescriptiveStatistics stats = new DescriptiveStatistics();

        for (Avis avis : avisList) {
            switch (getNoteCategory(avis.getNote())) {
                case "Bad":
                    badCount++;
                    break;
                case "Average":
                    averageCount++;
                    break;
                case "Good":
                    goodCount++;
                    break;
            }

            stats.addValue(avis.getNote());
        }

        ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList(
                new PieChart.Data("Bad", badCount),
                new PieChart.Data("Average", averageCount),
                new PieChart.Data("Good", goodCount)
        );

        chart.setData(pieChartData);

        double meanNote = stats.getMean();
        System.out.println("Mean Note: " + meanNote);
    }

    private String getNoteCategory(int note) {
        if (note < 4) {
            return "Bad";
        } else if (note >= 4 && note <= 6) {
            return "Average";
        } else {
            return "Good";
        }
    }

    private List<Avis> fetchAvisData() {
        List<Avis> avisList = new ArrayList<>();

        try (Connection connection = MyDatabase.getInstance().getConnection()) {
            String sql = "SELECT * FROM avis";

            try (PreparedStatement statement = connection.prepareStatement(sql);
                 ResultSet resultSet = statement.executeQuery()) {

                while (resultSet.next()) {
                    int id = resultSet.getInt("id_avis");
                    int idR = resultSet.getInt("idR");
                    String commentaire = resultSet.getString("commentaire");
                    float note = resultSet.getFloat("note");

                    Avis avis = new Avis(id, idR, commentaire, note);
                    avisList.add(avis);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return avisList;
    }

    public void handleDynamicUpdate() {
        updateChart();
    }
}
