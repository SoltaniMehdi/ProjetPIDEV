package services;

import entities.*;
import utils.MyDatabase;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ServicePointDistribution implements IService<PointDistribution> {
    Connection connection;

    public ServicePointDistribution() {
        connection = MyDatabase.getInstance().getConnection();
    }



    @Override
    public void ajouter(PointDistribution pointDistribution) throws SQLException {
        String req = "INSERT INTO point_distribution (nom, adresse, id_livraison) VALUES (?, ?, ?)";

        PreparedStatement preparedStatement = connection.prepareStatement(req);
        preparedStatement.setString(1, pointDistribution.getNom());
        preparedStatement.setString(2, pointDistribution.getAdresse());
        preparedStatement.setInt(3, pointDistribution.getIdLivraison());

        int rowsAffected = preparedStatement.executeUpdate();
        if (rowsAffected > 0) {
            System.out.println("Point de distribution ajouté avec succès");
        } else {
            System.out.println("Erreur lors de l'ajout du point de distribution");
        }
    }

    @Override
    public void supprimer(int idPointDistribution) throws SQLException {
        String req = "DELETE FROM point_distribution WHERE id_point_distribution = ?";

        PreparedStatement preparedStatement = connection.prepareStatement(req);
        preparedStatement.setInt(1, idPointDistribution);

        int rowsAffected = preparedStatement.executeUpdate();
        if (rowsAffected > 0) {
            System.out.println("Point de distribution supprimé avec succès");
        } else {
            System.out.println("Erreur lors de la suppression du point de distribution");
        }
    }

    @Override
    public void modifier(PointDistribution pointDistribution) throws SQLException {
        String req = "UPDATE point_distribution SET nom = ?, adresse = ?, id_livraison = ? WHERE id_point_distribution = ?";

        PreparedStatement preparedStatement = connection.prepareStatement(req);
        preparedStatement.setString(1, pointDistribution.getNom());
        preparedStatement.setString(2, pointDistribution.getAdresse());
        preparedStatement.setInt(3, pointDistribution.getIdLivraison());
        preparedStatement.setInt(4, pointDistribution.getIdPointDistribution());

        int rowsAffected = preparedStatement.executeUpdate();
        if (rowsAffected > 0) {
            System.out.println("Point de distribution modifié avec succès");
        } else {
            System.out.println("Erreur lors de la modification du point de distribution");
        }
    }

    @Override
    public List<PointDistribution> afficher() throws SQLException {
        List<PointDistribution> pointDistributions = new ArrayList<>();
        String req = "SELECT * FROM point_distribution";

        try (Statement st = connection.createStatement();
             ResultSet rs = st.executeQuery(req)) {

            while (rs.next()) {
                PointDistribution pointDistribution = new PointDistribution();
                pointDistribution.setIdPointDistribution(rs.getInt(1));
                pointDistribution.setNom(rs.getString(2));
                pointDistribution.setAdresse(rs.getString(3));
                pointDistribution.setIdLivraison(rs.getInt(4));

                pointDistributions.add(pointDistribution);
            }
        }

        return pointDistributions;
    }

    @Override
    public List<Avis> afficheravis() throws SQLException {
        return null;
    }

    @Override
    public List<Repas> afficherdetails(int id_commande) throws SQLException {
        return null;
    }

    @Override
    public List<Repas> afficherrepas() throws SQLException {
        return null;
    }

    @Override
    public Categorie_event findCategById(int id) throws SQLException {
        return null;
    }

    @Override
    public evenement findEventById(int id) throws SQLException {
        return null;
    }


}
