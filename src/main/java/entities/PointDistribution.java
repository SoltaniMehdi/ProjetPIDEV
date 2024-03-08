package entities;

public class PointDistribution {
    private  int idPointDistribution;
    private  String nom;
    private  String adresse;
    private  int idLivraison;

    public PointDistribution() {
        this(0, null, null, 0);
    }

    public PointDistribution(int idPointDistribution, String nom, String adresse, int idLivraison) {
        this.idPointDistribution = idPointDistribution;
        this.nom = nom;
        this.adresse = adresse;
        this.idLivraison =idLivraison;
    }

    public PointDistribution(String nom, String adresse, int idLivraison) {
        this.nom = nom;
        this.adresse = adresse;
        this.idLivraison = idLivraison;
    }

    public int getIdPointDistribution() {
        return idPointDistribution;
    }

    public void setIdPointDistribution(int idPointDistribution) {
        this.idPointDistribution = idPointDistribution;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public int getIdLivraison() {
        return idLivraison;
    }

    public void setIdLivraison(int idLivraison) {
        this.idLivraison = idLivraison;
    }

    @Override
    public String toString() {
        return "PointDistribution{" +
                "idPointDistribution=" + idPointDistribution +
                ", nom='" + nom + '\'' +
                ", adresse='" + adresse + '\'' +
                ", idLivraison=" + idLivraison +
                '}';
    }
}
