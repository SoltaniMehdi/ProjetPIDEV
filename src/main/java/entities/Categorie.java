package entities;

import java.util.ArrayList;
import java.util.List;

public class Categorie {
    private int id;
    private String nom;
    private String description;
    private List<Repas> repasList; // Liste des repas associés à cette catégorie

    public Categorie() {
        repasList = new ArrayList<>(); // Initialisation de la liste des repas
    }

    public Categorie(int id, String nom, String description) {
        this.id = id;
        this.nom = nom;
        this.description = description;
        repasList = new ArrayList<>(); // Initialisation de la liste des repas
    }
    public Categorie( String nom, String description) {
        this.nom = nom;
        this.description = description;

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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    // Méthode pour ajouter un repas à la liste des repas de cette catégorie
    public void addRepas(Repas repas) {
        repasList.add(repas);
    }

    // Méthode pour récupérer la liste des repas associés à cette catégorie
    public List<Repas> getRepasList() {
        return repasList;
    }


}