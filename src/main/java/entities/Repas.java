package entities;

public class Repas {
    public int idR;
    public int prix;
    public String nom;
    public String description;
    public int id_commande;

    public int getIdR() {
        return idR;
    }


    public void setIdR(int idR) {
        this.idR = idR;
    }

    public int getPrix() {
        return prix;
    }

    public void setPrix(int prix) {
        this.prix = prix;
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

    public int getId_commande() {
        return id_commande;
    }

    public void setId_commande(int id_commande) {
        this.id_commande = id_commande;
    }


}
