package entities;

public class Repas {
    private int idR;
    private float prix;
    private String nom;
    private String description;
    private int idC;

    private Categorie categorie; // Ajoutez cet attribut

    public Repas() {
    }

    public Repas(int idR, float prix, String nom, String description, int idC) {
        this.idR = idR;
        this.prix = prix;
        this.nom = nom;
        this.description = description;
        this.idC = idC;
    }

    public Repas(float prix, String nom, String description, int idC) {
        this.prix = prix;
        this.nom = nom;
        this.description = description;
        this.idC = idC;
    }

    public int getIdR() {
        return idR;
    }

    public void setIdR(int idR) {
        this.idR = idR;
    }

    public float getPrix() {
        return prix;
    }

    public void setPrix(float prix) {
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

    public int getIdC() {
        return idC;
    }

    public void setIdC(int idC) {
        this.idC = idC;
    }

    public Categorie getCategorie() {
        return categorie;
    }

    public void setCategorie(Categorie categorie) {
        this.categorie = categorie;
    }

    @Override
    public String toString() {
        return "Repas{" +
                "idR=" + idR +
                ", prix=" + prix +
                ", nom='" + nom + '\'' +
                ", description='" + description + '\'' +
                ", idC=" + idC +
                '}';
    }
}
