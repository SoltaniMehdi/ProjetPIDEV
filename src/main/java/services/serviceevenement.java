package services;

import entities.Avis;
import entities.Categorie_event;
import entities.Repas;
import entities.evenement;
import utils.MyDatabase;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class serviceevenement implements  IService<evenement>{
    static Connection connection;
    public serviceevenement(){
        connection= MyDatabase.getInstance().getConnection();

    }
    @Override
    public void ajouter(evenement evenement) {
        try {
            String req = "INSERT INTO `evenement`(`nom`, `description`, `lieu`, `dateD`,`id_categ`) VALUES (?,?,?,?,?)";
            PreparedStatement ps = connection.prepareStatement(req);
            ps.setString(1, evenement.getNom());
            ps.setString(2, evenement.getDescription());
            ps.setString(3, evenement.getLieu());
            ps.setDate(4, evenement.getDateD());
            ps.setInt(5, evenement.getId_categorie());

            System.out.println("Prepared SQL Query: " + ps.toString());
            int rowsAffected = ps.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("Evenement ajouté");

            } else {
                System.out.println("Échec de l'ajout de l'événement");

            }
        } catch (SQLException e) {
            System.out.println("Erreur lors de l'ajout de l'événement : " + e.getMessage());

        }
    }



    @Override
    public void modifier(evenement evenement) throws SQLException {
        String req="update evenement set nom=? , lieu=? , description=? , dateD=?  where id=?";
        PreparedStatement ps=connection.prepareStatement(req);
        ps.setString(1, evenement.getNom());
        ps.setString(3, evenement.getDescription());
        ps.setString(2, evenement.getLieu());
        ps.setDate(4, evenement.getDateD());
        ps.setInt(5, evenement.getId());
        ps.executeUpdate();
        System.out.println("evenement modifie");

    }




        @Override
        public void supprimer(int id) throws SQLException {
            String requete = "DELETE FROM evenement WHERE id="+id;
            try {
                Statement st = connection.createStatement();
                st.executeUpdate(requete);
                System.out.println("delete success");
            }
            catch (SQLException e){
                throw new RuntimeException(e);
            }
        }



    @Override
    public List<evenement> afficher() throws SQLException {

        List<evenement> evenement = new ArrayList<>();
        String req="select * from evenement";
        Statement st  = connection.createStatement();
       ResultSet rs = st.executeQuery(req);
       while (rs.next()){
           evenement e = new evenement();
           e.setId(rs.getInt(1));
           e.setNom(rs.getString(2));
           e.setDescription(rs.getString(3));
           e.setLieu(rs.getString(4));
           e.setDateD((rs.getDate("dateD")));
           e.setId_categorie(rs.getInt(6));

           evenement.add(e);
       }
        return evenement;
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

        evenement e= new evenement();
        String req="select * from evenement where id ="+id;
        Statement st  = connection.createStatement();
        ResultSet rs = st.executeQuery(req);
        while (rs.next()){

            e.setId(rs.getInt(1));
            e.setNom(rs.getString(2));
            e.setDescription(rs.getString(3));
            e.setLieu(rs.getString(4));
            e.setDateD((rs.getDate("dateD")));
            e.setId_categorie(rs.getInt(6));

        }
        return e;
    }


}
