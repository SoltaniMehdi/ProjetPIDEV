package controllers;

import entities.Categorie;
import javafx.fxml.FXML;
import javafx.scene.chart.PieChart;
import services.ServiceCategorie;

import java.sql.SQLException;
import java.util.List;

public class StatsCategorieController {

    @FXML
    private PieChart pieChart;

    private final ServiceCategorie serviceCategorie = new ServiceCategorie();

    @FXML
    void initialize() {
        // Initialize the controller
        try {
            // Load data for the initial pie chart
            loadPieChartData();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void loadPieChartData() throws SQLException {
        // Clear any existing data from the pie chart
        pieChart.getData().clear();

        // Get category data from the service
        List<Categorie> categories = serviceCategorie.afficher();

        // Add data to the pie chart
        for (Categorie categorie : categories) {
            PieChart.Data data = new PieChart.Data(categorie.getNom(), categorie.getId());
            pieChart.getData().add(data);
        }
    }
}
