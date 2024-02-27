package services;

import entities.Categorie;
import utils.MyDataBase;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ServiceCategorie implements IService<Categorie> {
    static Connection connection;

    public ServiceCategorie() {
        connection = MyDataBase.getInstance().getConnection();
    }

    @Override
    public void ajouter(Categorie categorie) throws SQLException {
        String req = "INSERT INTO `categorie`(`nom`, `description`) VALUES ('"+categorie.getNom()+"', '"+categorie.getDescription()+"')";
        Statement st = connection.createStatement();
        st.executeUpdate(req);
        System.out.println("Catégorie ajoutée");
    }

    @Override
    public void modifier(Categorie categorie) throws SQLException {
        String req = "UPDATE categorie SET nom = '" + categorie.getNom() + "', description = '" + categorie.getDescription() + "' WHERE id = " + categorie.getId();
        Statement st = connection.createStatement();
        st.executeUpdate(req);
        System.out.println("Catégorie modifiée");
    }

    @Override
    public void supprimer(int id) throws SQLException {
        String req = "DELETE FROM Categorie WHERE id = " + id;
        Statement st = connection.createStatement();
        st.executeUpdate(req);
        System.out.println("Catégorie supprimée");
    }

    @Override
    public List<Categorie> afficher() throws SQLException {
        List<Categorie> categories = new ArrayList<>();
        String req = "SELECT * FROM Categorie";
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
}
