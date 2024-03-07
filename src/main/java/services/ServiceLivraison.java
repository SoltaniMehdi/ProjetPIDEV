package services;

import entities.Livraison1;
import utils.MyDataBase;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ServiceLivraison implements IService<Livraison1> {
    Connection connection;

    public ServiceLivraison() {
        connection = MyDataBase.getInstance().getConnection();
    }

    @Override
    public void ajouter(Livraison1 livraison1) throws SQLException {
        String req = "INSERT INTO livraison1 (id_commande, id_client, date_livraison, adresse, prix) " +
                "VALUES ('" + livraison1.getId_commande() + "', '" +
                livraison1.getId_client() + "', '" + livraison1.getDate_livraison() + "', '" +
                livraison1.getAdresse() + "', " + livraison1.getPrix() + ")";

        try (Statement st = connection.createStatement()) {
            st.executeUpdate(req);
        }
        System.out.println("Livraison ajoutée");
    }

    @Override
    public void supprimer(int idLivraison) throws SQLException {
        String req = "DELETE FROM livraison1 WHERE id_livraison = " + idLivraison;

        try (Statement st = connection.createStatement()) {
            st.executeUpdate(req);
        }
        System.out.println("Livraison supprimée avec succès");
    }

    @Override
    public void modifier(Livraison1 livraison1) throws SQLException {
        String req = "UPDATE livraison1 SET id_commande = '" + livraison1.getId_commande() + "', " +
                "id_client = '" + livraison1.getId_client() + "', " +
                "date_livraison = '" + livraison1.getDate_livraison() + "', " +
                "adresse = '" + livraison1.getAdresse() + "', " +
                "prix = " + livraison1.getPrix() + " " +
                "WHERE id_livraison = " + livraison1.getId_livraison();

        try (Statement st = connection.createStatement()) {
            st.executeUpdate(req);
        }
        System.out.println("Livraison modifiée avec succès");
    }

    @Override
    public List<Livraison1> afficher() throws SQLException {
        List<Livraison1> livraisons = new ArrayList<>();
        String req = "SELECT * FROM livraison1";

        try (Statement st = connection.createStatement();
             ResultSet rs = st.executeQuery(req)) {

            while (rs.next()) {
                Livraison1 livraison = new Livraison1();
                livraison.setId_livraison(rs.getInt("id_livraison"));
                livraison.setId_commande(rs.getInt("id_commande"));
                livraison.setId_client(rs.getInt("id_client"));
                livraison.setDate_livraison(rs.getString("date_livraison"));
                livraison.setAdresse(rs.getString("adresse"));
                livraison.setPrix(rs.getFloat("prix"));
                livraisons.add(livraison);
            }
        }

        return livraisons;
    }

    @Override
    public List<Livraison1> liste() {
        return null;
    }


}
