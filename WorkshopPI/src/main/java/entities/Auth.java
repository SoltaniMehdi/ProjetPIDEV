package entities;
import entities.Utilisateur;
import services.CRUDUtilisateur;

import java.sql.*;

public class Auth {
    private static Utilisateur currentUtilisateur;

    public static Utilisateur getCurrentUtilisateur() {
        return currentUtilisateur;
    }

    public static Utilisateur signIn(String mail, String password) {
        Utilisateur user = Utilisateur.getUserByEmail(mail);
        currentUtilisateur = user;
        if (user != null && user.getPassword().equals(password)) {
            System.out.println(currentUtilisateur);
            System.out.println("Utilisateur Connecté");
            return currentUtilisateur;
        } else {
            System.out.println("Authentification échouée");
            return null; // Retourne null si l'authentification échoue
        }
    }


/*
    public static void logout() {
        currentUtilisateur = null;
    }


    public static boolean changePassword(String mail, String newPassword) {
        try {

            Class.forName("com.mysql.jdbc.Driver");
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/vitaplat3a20", "root", "");
            String query = "UPDATE utilisateurs SET password=? WHERE mail=?";
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setString(1, newPassword);
            pstmt.setString(2, mail);

            // Exécuter la requête
            pstmt.executeUpdate();

            // Fermer la connexion
            conn.close();

            System.out.println("Mot de passe modifié avec succès.");
            return true;
        } catch (Exception e) {
            System.err.println("Erreur : " + e.getMessage());
            return false;
        }
    }
*/

    public static boolean checkEmail(String mail) {
        Utilisateur utilisateur = Utilisateur.getUserByEmail(mail);
        boolean exists = false;
        try {
            // Vérifier si l'email existe déjà dans la base de données
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/vitaplat3a20", "root", "");
            String query = "SELECT COUNT(*) FROM utilisateurs WHERE mail = ?";
            PreparedStatement st = conn.prepareStatement(query);
            st.setString(1, mail);
            ResultSet rs = st.executeQuery();
            rs.next();
            int count = rs.getInt(1);
            if (count > 0) {
                exists = true;
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        return exists;
    }

}