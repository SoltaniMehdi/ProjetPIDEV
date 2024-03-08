package services;

import entities.*;
import utils.MyDatabase;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ServiceRepas implements IService<Repas> {
    Connection connection;

    public ServiceRepas() {
        connection = MyDatabase.getInstance().getConnection();
    }

    @Override
    public void ajouter(Repas repas) throws SQLException {
        String req = "INSERT INTO repas (prix, nom, description, idC) VALUES (" +
                repas.getPrix() + ", '" + repas.getNom() + "', '" +
                repas.getDescription() + "', " + repas.getIdC() + ")";

        Statement st = connection.createStatement();
        st.executeUpdate(req);
        System.out.println("Repas ajouté avec succès ");
    }

    @Override
    public void supprimer(int idRepas) throws SQLException {
        String req = "DELETE FROM repas WHERE idR = " + idRepas;

        Statement st = connection.createStatement();
        st.executeUpdate(req);
        System.out.println("Repas supprimé avec succès");
    }

    @Override
    public void modifier(Repas repas) throws SQLException {
        String req = "UPDATE repas SET prix = " + repas.getPrix() + ", " +
                "nom = '" + repas.getNom() + "', " +
                "description = '" + repas.getDescription() + "', " +
                "idC = " + repas.getIdC() + " " +
                "WHERE idR = " + repas.getIdR();

        Statement st = connection.createStatement();
        st.executeUpdate(req);
        System.out.println("Repas modifié avec succès");
    }

    @Override
    public List<Repas> afficher() throws SQLException {
        List<Repas> repasList = new ArrayList<>();
        String req = "SELECT r.*, c.nom AS categorie_nom, c.description AS categorie_description " +
                "FROM repas r " +
                "INNER JOIN categorie c ON r.idC = c.id";
        Statement st = connection.createStatement();
        ResultSet rs = st.executeQuery(req);
        while (rs.next()) {
            Repas repas = new Repas();
            repas.setIdR(rs.getInt("idR"));
            repas.setPrix(rs.getFloat("prix"));
            repas.setNom(rs.getString("nom"));
            repas.setDescription(rs.getString("description"));
            repas.setIdC(rs.getInt("idC"));

            // Récupérer les détails de la catégorie associée
            Categorie categorie = new Categorie();
            categorie.setNom(rs.getString("categorie_nom"));
            categorie.setDescription(rs.getString("categorie_description"));

            repas.setCategorie(categorie);

            repasList.add(repas);
        }
        return repasList;
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

    public boolean exists(String nom) throws SQLException {
        String sql = "SELECT COUNT(*) FROM repas WHERE nom = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, nom);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    int count = resultSet.getInt(1);
                    return count > 0;
                }
            }
        }
        return false;
    }
}

