package controllers;

import entities.Repas;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.chart.PieChart;
import services.ServiceRepas;

import java.sql.SQLException;
import java.util.List;

public class StatsRepasController {

    @FXML
    private PieChart pieChart;

    private final ServiceRepas serviceRepas = new ServiceRepas();

    @FXML
    public void initialize() {
        try {
            List<Repas> repasList = serviceRepas.afficher();
            ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList();
            for (Repas repas : repasList) {
                pieChartData.add(new PieChart.Data(repas.getNom(), repas.getPrix()));
            }
            pieChart.setData(pieChartData);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
