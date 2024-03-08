package services;
import entities.Utilisateur;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javafx.event.ActionEvent;
import utils.MyDatabase;

public class CRUDUtilisateur {
     Connection cnx;

    public CRUDUtilisateur() {
        cnx = MyDatabase.getInstance().getConnection();
        System.out.println(cnx);
    }


    public void ajouterUtilisateur(Utilisateur u) {
        try {
            PreparedStatement statement = cnx.prepareStatement("INSERT INTO utilisateurs(nom,prenom,numero,password,mail,role) VALUES (?,?,?,?,?,'client')");
            statement.setString(1, u.getNom());
            statement.setString(2, u.getPrenom());
            statement.setString(3, u.getNumero());
            statement.setString(4, u.getPassword());
            statement.setString(5, u.getMail());

            statement.executeUpdate();
            System.out.println("Utilisateur ajoute");
        } catch (SQLException var1) {
            System.err.println(var1.getMessage());
        }

    }
    public void supprimerUtilisateur(int id) {
        try {

            String requete2 = "DELETE FROM utilisateurs where ID_user= ?";
            PreparedStatement statement = cnx.prepareStatement(requete2);
            // Remplacement du paramètre par la valeur réelle
            statement.setInt(1, id);

            // Exécution de la requête
            int rowsAffected = statement.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Utilisateur supprimé");
            } else {
                System.out.println("Aucun utilisateur trouvé avec cet ID.");
            }
        } catch (SQLException var2) {
            System.out.println(var2.getMessage());
        }

    }
/*
    public void modifierUtilisateur(int id, String nom, String prenom, String numero, String password, String mail, String role) {
        try {
            String Requete3 = "UPDATE utilisateurs SET nom='" + nom + "',prenom='" + prenom + "',numero='" + numero + "',password='" + password + "', mail='" + mail + "',role='" + role + "' where id_user=" + id;
            PreparedStatement st3 = cnx.prepareStatement(Requete3);
            st3.executeUpdate();
            System.out.println("Utilisateur modifié");
        } catch (SQLException var3) {
            System.out.println(var3.getMessage());
        }

    }
    */

    public void modifier(Utilisateur utilisateur){
        String req="UPDATE `utilisateurs` SET `nom`=?,`prenom`=?,`numero`=?,`password`=?,`mail`=?, `role`=?  WHERE id_user=?";
        try {
            PreparedStatement ps = cnx.prepareStatement(req);
            ps.setString(1, utilisateur.getNom());
            ps.setString(2, utilisateur.getPrenom());
            ps.setString(3, utilisateur.getNumero());
            ps.setString(4, utilisateur.getPassword());
            ps.setString(5, utilisateur.getMail());
            ps.setString(6, utilisateur.getRole());
            ps.setInt(7, utilisateur.getID_user());
            ps.executeUpdate();
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
        System.out.println("utilisateur modifie");

    }
    public  List<Utilisateur> afficherUtilisateur() {
        List<Utilisateur> users = new ArrayList();

        try {
            String requete4 = "SELECT * FROM utilisateurs";
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(requete4);

            while(rs.next()) {
                Utilisateur u = new Utilisateur();
                u.setID_user(rs.getInt(1));
                u.setNom(rs.getString(2));
                u.setPrenom(rs.getString(3));
                u.setNumero(rs.getString(4));
                u.setPassword(rs.getString(5));
                u.setMail(rs.getString(6));
                u.setRole(rs.getString(7));
                users.add(u);
            }
        } catch (SQLException var4) {
            System.out.println(var4.getMessage());
        }

        return users;
    }

    /*
    public List<Utilisateur> afficherClient() {
        List<Utilisateur> users = new ArrayList();

        try {
            String requete4 = "SELECT * FROM utilisateurs WHERE ID_user= ?";
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(requete4);

            while(rs.next()) {
                Utilisateur u = new Utilisateur();
                u.setID_user(rs.getInt(1));
                u.setNom(rs.getString(2));
                u.setPrenom(rs.getString(3));
                u.setNumero(rs.getString(4));
                u.setPassword(rs.getString(5));
                u.setMail(rs.getString(6));
                u.setRole(rs.getString(7));
                users.add(u);
            }
        } catch (SQLException var4) {
            System.out.println(var4.getMessage());
        }

        return users;
    }


    public Utilisateur afficherParID(String id) throws SQLException {
        Utilisateur u = new Utilisateur();
        String req = "SELECT nom, prenom, numero, mail, password , role FROM utilisateurs WHERE id_user = ?";
        PreparedStatement ps= cnx.prepareStatement(req);
        ps.setString(1, id);
        ResultSet rs = ps.executeQuery(); // Remove req from executeQuery
        while (rs.next()){
            u = new Utilisateur(rs.getInt("id_user"), rs.getString("nom"), rs.getString("prenom"), rs.getString("numero"),  rs.getString("mail"), rs.getString("password"), rs.getString("role"));
        }
        return u;
    }

     */
    public void modifierMdp(Utilisateur utilisateur, String mdp) throws SQLException {
        String req="UPDATE `utilisateurs` SET `password`=? WHERE ID_user=?";
        PreparedStatement ps= cnx.prepareStatement(req);
        ps.setString(1, mdp);
        ps.setInt(2,utilisateur.getID_user());
        ps.executeUpdate();
        System.out.println("Personne modifie");
    }
}

