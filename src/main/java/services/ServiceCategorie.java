package services;

import entities.*;
import utils.MyDatabase;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ServiceCategorie implements IService<Categorie> {
    static Connection connection;

    public ServiceCategorie() {
        connection = MyDatabase.getInstance().getConnection();
    }

    @Override
    public void ajouter(Categorie categorie) throws SQLException {
        String req = "INSERT INTO `categorie`(`nom`, `description`) VALUES ('"+categorie.getNom()+"', '"+categorie.getDescription()+"')";
        Statement st = connection.createStatement();
        st.executeUpdate(req);
        System.out.println("Catégorie ajoutée avec succès");
    }

    @Override
    public void modifier(Categorie categorie) throws SQLException {
        String req = "UPDATE categorie SET nom = '" + categorie.getNom() + "', description = '" + categorie.getDescription() + "' WHERE id = " + categorie.getId();
        Statement st = connection.createStatement();
        st.executeUpdate(req);
        System.out.println("Catégorie modifiée avec succès");
    }

    @Override
    public void supprimer(int id) throws SQLException {
        String req = "DELETE FROM Categorie WHERE id = " + id;
        Statement st = connection.createStatement();
        st.executeUpdate(req);
        System.out.println("Catégorie supprimée avec succès");
    }

    @Override
    public List<Categorie> afficher() throws SQLException {
        List<Categorie> categories = new ArrayList<>();
        String req = "SELECT * FROM `categorie`";
        Statement st = connection.createStatement();
        ResultSet rs = st.executeQuery(req);
        while (rs.next()) {
            Categorie categorie = new Categorie();
            categorie.setId(rs.getInt("id"));
            categorie.setNom(rs.getString("nom"));
            categorie.setDescription(rs.getString("description"));
            categories.add(categorie);
        }
        return categories;
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
