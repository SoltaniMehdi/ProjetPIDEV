//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package entities;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Objects;

public class Utilisateur {
    private int ID_user;
    private String nom;
    private String prenom;
    private String numero;
    private String password;
    private String mail;
    private String role;
    public Utilisateur() {
    }
//contructeur kémel
    public Utilisateur(int ID_user, String nom, String prenom, String numero, String password, String mail, String role) {
        this.ID_user = ID_user;
        this.nom = nom;
        this.prenom = prenom;
        this.numero = numero;
        this.password = password;
        this.mail = mail;
        this.role = role;
    }
//constructeur specific lel inscription
public Utilisateur(String nom, String prenom, String numero, String password, String mail) {
    this.nom = nom;
    this.prenom = prenom;
    this.numero = numero;
    this.password = password;
    this.mail = mail;
    this.role = null;
}

    public Utilisateur(String text, String text1, String text2, String text3, String password, String role) {
    }

    public int getID_user() {
        return ID_user;
    }

    public void setID_user(int ID_user) {
        this.ID_user = ID_user;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }



    public int hashCode() {
        int hash = 5;
        hash = 43 * hash + this.ID_user;
        return hash;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        } else if (obj == null) {
            return false;
        } else if (this.getClass() != obj.getClass()) {
            return false;
        } else {
            Utilisateur other = (Utilisateur)obj;
            if (this.ID_user != other.ID_user) {
                return false;
            } else if (!Objects.equals(this.numero, other.numero)) {
                return false;
            } else if (!Objects.equals(this.password, other.password)) {
                return false;
            } else {
                return Objects.equals(this.mail, other.mail);
            }
        }
    }
    public static Utilisateur getUserById(int id) {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Utilisateur user = new Utilisateur();
        try {
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/vitaplat3a20", "root", "");
            stmt = conn.prepareStatement("SELECT * FROM utilisateurs WHERE id_user = ?");
            stmt.setInt(1, id);
            rs = stmt.executeQuery();
            if (rs.next()) {
                //user = new Utilisateur(rs.getInt(1), rs.getString(2), rs.getString(3));
                user.setID_user(rs.getInt(1));
                System.out.println(rs.getString(1));
                user.setPassword(rs.getString(2));
                user.setMail(rs.getString(3));
                user.setNom(rs.getString(5));
                user.setPrenom(rs.getString(6));
                System.out.println(rs.getString(7));
                user.setNumero(rs.getString(8));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return user;

    }
    public static Utilisateur getUserByEmail(String mail) {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Utilisateur user = null;
        try {
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/vitaplat3a20", "root", "");
            stmt = conn.prepareStatement("SELECT * FROM utilisateurs WHERE mail = ?");
            stmt.setString(1, mail);
            rs = stmt.executeQuery();
            if (rs.next()) {
                user = new Utilisateur(rs.getInt(1), rs.getString(2), rs.getString(3),rs.getString(4), rs.getString(5),rs.getString(6), rs.getString(7));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return user;
    }
    public String toString() {
        return "Utilisateur{ID_user=" + this.ID_user + ", nom=" + this.nom + ", prenom=" + this.prenom +  ", numero=" + ", password=" + this.password + ", mail=" + this.mail +  ", role=" + this.role + '}';
    }
}
