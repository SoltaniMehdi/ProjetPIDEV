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