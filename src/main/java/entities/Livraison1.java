package entities;

public class Livraison1 {
    private  int id_livraison; // Déclaré comme final car il est défini une fois dans le constructeur
    private  int id_commande;
    private  int id_client;
    private String date_livraison;
    private String adresse;
    private float prix;


    public Livraison1() {
    }

    public Livraison1(int id_livraison, int id_commande, int id_client, String date_livraison, String adresse, float prix) {
        this.id_livraison = id_livraison;
        this.id_commande = id_commande;
        this.id_client = id_client;
        this.date_livraison = date_livraison;
        this.adresse = adresse;
        this.prix = prix;
    }


    public Livraison1(int id_commande, int id_client, String date_livraison, String adresse, float prix) {
        this.id_commande = id_commande;
        this.id_client = id_client;
        this.date_livraison = date_livraison;
        this.adresse = adresse;
        this.prix = prix;
    }

    public int getId_livraison() {
        return id_livraison;
    }

    public void setId_livraison(int id_livraison) {
        this.id_livraison = id_livraison;
    }

    public int getId_commande() {
        return id_commande;
    }

    public void setId_commande(int id_commande) {
        this.id_commande = id_commande;
    }

    public int getId_client() {
        return id_client;
    }

    public void setId_client(int id_client) {
        this.id_client = id_client;
    }

    public String getDate_livraison() {
        return date_livraison;
    }

    public void setDate_livraison(String date_livraison) {
        this.date_livraison = date_livraison;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public float getPrix() {
        return prix;
    }

    public void setPrix(float prix) {
        this.prix = prix;
    }

    @Override
    public String toString() {
        return "Livraison1{" +
                "id_livraison=" + id_livraison +
                ", id_commande=" + id_commande +
                ", id_client=" + id_client +
                ", date_livraison='" + date_livraison + '\'' +
                ", adresse='" + adresse + '\'' +
                ", prix=" + prix +
                '}';
    }
}