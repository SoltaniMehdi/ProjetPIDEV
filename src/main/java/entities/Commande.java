package entities;

import java.time.LocalDate;
import java.util.Date;

public class Commande {
    private int id_commande ;
    private String statut;
    private Date datec;
    private float totalprix;

    public Commande() {
    }

    public Commande(int id_commande, Date datec, String statut, float totalprix) {
        this.id_commande = id_commande;
        this.datec = datec;
        this.statut = statut;
        this.totalprix = totalprix;
    }
    public Commande(Date datec, String statut, float totalprix) {
        this.datec = datec;
        this.statut = statut;
        this.totalprix = totalprix;
    }


    public int getId_commande() {
        return id_commande;
    }

    public void setId_commande(int id_commande) {
        this.id_commande = id_commande;
    }


    public String getStatut() {
        return statut;
    }

    public void setStatut(String statut) {
        this.statut = statut;
    }

    public Date getDatec() {
        return datec;
    }

    public void setDatec(Date datec) {
        this.datec = datec;
    }

    public float getTotalprix() {
        return totalprix;
    }

    public void setTotalprix(float totalprix) {
        this.totalprix = totalprix;
    }

    @Override
    public String toString() {
        return "commandes{" +
                "id_commande=" + id_commande +
                ", statut='" + statut + '\'' +
                ", datec=" + datec +
                ", totalprix=" + totalprix +
                '}';
    }


}

