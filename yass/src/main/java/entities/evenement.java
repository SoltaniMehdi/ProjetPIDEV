package entities;


import services.servicecategorie;

import java.sql.Date;
import java.sql.SQLException;
import java.text.DateFormat;
import java.time.LocalDate;

public class evenement {
    private int id ;
    private String nom ,lieu,description  ;
    private Date dateD;

    private int id_categorie;

    servicecategorie s = new servicecategorie();

    public evenement() {
    }

    public evenement(int id, String nom, String lieu, String description, Date dateD, int id_categorie) {
        this.id = id;
        this.nom = nom;
        this.lieu = lieu;
        this.description = description;
        this.dateD = dateD;
        this.id_categorie = id_categorie;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getLieu() {
        return lieu;
    }

    public void setLieu(String lieu) {
        this.lieu = lieu;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getDateD() {
        return dateD;
    }

    public void setDateD(Date dateD) {
        this.dateD = dateD;
    }

    public int getId_categorie() {
        return id_categorie;
    }

    public void setId_categorie(int id_categorie) {
        this.id_categorie = id_categorie;
    }

    @Override
    public String toString() {
        try {
            return "evenement{" +
                   // "id=" + id +
                    ", nom='" + nom + '\'' +
                    ", description='" + description + '\'' +
                    ", lieu='" + lieu + '\'' +
                    ", dateD=" + dateD +
                    ", categorie=" + s.findCategById(id_categorie).getCategevent() +
                    '}';
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
