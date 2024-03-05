package services;

import entities.Categorie;
import entities.Repas;
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
        String req = "SELECT c.*, r.idR AS repas_id, r.prix AS repas_prix, r.nom AS repas_nom, r.description AS repas_description " +
                "FROM categorie c " +
                "INNER JOIN repas r ON c.id = r.idC";
        Statement st = connection.createStatement();
        ResultSet rs = st.executeQuery(req);
        while (rs.next()) {
            Categorie categorie = new Categorie();
            categorie.setId(rs.getInt("id"));
            categorie.setNom(rs.getString("nom"));
            categorie.setDescription(rs.getString("description"));

            // Récupérer les détails des repas associés à cette catégorie
            Repas repas = new Repas();
            repas.setIdR(rs.getInt("repas_id"));
            repas.setPrix(rs.getFloat("repas_prix"));
            repas.setNom(rs.getString("repas_nom"));
            repas.setDescription(rs.getString("repas_description"));

            categorie.addRepas(repas);

            categories.add(categorie);
        }
        return categories;
    }
}
