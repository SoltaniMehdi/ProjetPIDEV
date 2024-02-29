package services;

import entities.Categorie;
import entities.Repas;
import utils.MyDataBase;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ServiceRepas implements IService<Repas> {
    Connection connection;

    public ServiceRepas() {
        connection = MyDataBase.getInstance().getConnection();
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
}

