package services;

import entities.*;
import utils.MyDatabase;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ServiceCommandes implements  IService<Commande>{
    static Connection connection;
    public ServiceCommandes() throws SQLException {
        connection= MyDatabase.getInstance().getConnection();

    }


    @Override
     public void ajouter(Commande commandes) throws SQLException {
        String req ="insert into commandes (datec,statut,totalprix)"+
                "values('"+commandes.getDatec()+"','"+commandes.getStatut()+"',"+commandes.getTotalprix()+")";
        Statement st = connection.createStatement();
        st.executeUpdate(req);
        System.out.println("commande ajouté");
    }

    @Override
        public void modifier(Commande f) throws SQLException {
        String req="update commandes set statut=? ,totalprix=? where id_commande=?";
        PreparedStatement ps= connection.prepareStatement(req);
        ps.setString(1, f.getStatut());
        ps.setFloat(2, f.getTotalprix());
        ps.setInt(3, f.getId_commande());
        ps.executeUpdate();
        System.out.println("commande modifie");

    }

    @Override
    public void supprimer(int id) throws SQLException {
        System.out.println("test1");
        String req = "DELETE FROM commandes WHERE id_commande=?";
        System.out.println("test2");
        PreparedStatement ps= connection.prepareStatement(req);
        ps.setInt(1,id);
        ps.executeUpdate();
        System.out.println("commande Supprimée");
    }

    @Override
    public List<Commande> afficher() throws SQLException {

        List<Commande> commandes= new ArrayList<>();
        String req="select * from commandes";
        Statement st  = connection.createStatement();
       ResultSet rs = st.executeQuery(req);
       while (rs.next()){
           Commande c = new Commande();
           c.setId_commande(rs.getInt(1));
           c.setDatec(rs.getDate(2));
           c.setStatut(rs.getString(3));
           c.setTotalprix(rs.getInt(4));
           commandes.add(c);
       }
        return commandes;
    }

    @Override
    public List<Avis> afficheravis() throws SQLException {
        return null;
    }

    @Override
    public List<Repas> afficherdetails(int id_commande) throws SQLException {
        System.out.println(id_commande);
        List<Repas> repas= new ArrayList<>();
        String req="select * from repas";
        Statement st  = connection.createStatement();
        ResultSet rs = st.executeQuery(req);
        while (rs.next()){
            if (rs.getInt(5)==id_commande) {
                Repas r = new Repas();
                r.setNom(rs.getString(3));
                r.setDescription(rs.getString(4));
                r.setPrix(rs.getInt(2));
                repas.add(r);
            }
        }
        return repas;
    }

    @Override
    public List<Repas> afficherrepas() throws SQLException {
        List<Repas> repas= new ArrayList<>();
        String req="select * from repas";
        Statement st  = connection.createStatement();
        ResultSet rs = st.executeQuery(req);
        while (rs.next()){
                Repas r = new Repas();
                r.setNom(rs.getString(3));
                r.setDescription(rs.getString(4));
                r.setPrix(rs.getInt(2));
                repas.add(r);
            }
        return repas;
    }

    @Override
    public Categorie_event findCategById(int id) throws SQLException {
        return null;
    }

    @Override
    public evenement findEventById(int id) throws SQLException {
        return null;
    }

    public Commande getById(int id) throws SQLException {
        String req = "SELECT * FROM commandes WHERE id_commande=?";
        try (PreparedStatement ps = connection.prepareStatement(req)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    Commande commande = new Commande();
                    commande.setId_commande(rs.getInt(1));
                    commande.setDatec(rs.getDate(2));
                    commande.setStatut(rs.getString(3));
                    commande.setTotalprix(rs.getInt(4));
                    return commande;
                }
            }
        }
        return null; // If no Commande with the given ID is found
    }

    public List<Commande> getByStatut(String statut) throws SQLException {
        List<Commande> commandes = new ArrayList<>();
        String req = "SELECT * FROM commandes WHERE statut=?";
        try (PreparedStatement ps = connection.prepareStatement(req)) {
            ps.setString(1, statut);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Commande commande = new Commande();
                    commande.setId_commande(rs.getInt(1));
                    commande.setDatec(rs.getDate(2));
                    commande.setStatut(rs.getString(3));
                    commande.setTotalprix(rs.getInt(4));
                    commandes.add(commande);
                }
            }
        }
        return commandes;
    }









}
