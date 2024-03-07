package entities;

public class Avis {
    private int id_avis ;
    private int idR;
    private String commentaire;
    private float note;

    public Avis() {
    }

    public Avis(int id_avis, int idR, String commentaire, float note) {
        this.id_avis = id_avis;
        this.idR = idR;
        this.commentaire = commentaire;
        this.note = note;
    }
    public Avis(int idR, String commentaire, float note) {
        this.idR = idR;
        this.commentaire = commentaire;
        this.note = note;
    }

    public int getId_avis() {
        return id_avis;
    }

    public void setId_avis(int id_avis) {
        this.id_avis = id_avis;
    }

    public int getIdR() {
        return idR;
    }

    public void setIdR(int idR) {
        this.idR = idR;
    }

    public String getCommentaire() {
        return commentaire;
    }

    public void setCommentaire(String commentaire) {
        this.commentaire = commentaire;
    }

    public int getNote() {
        return (int) note;
    }

    public void setNote(float note) {
        this.note = note;
    }

    @Override
    public String toString() {
        return "Avis{" +
                "id_avis=" + id_avis +
                ", idR=" + idR +
                ", commentaire='" + commentaire + '\'' +
                ", note=" + note +
                '}';
    }

}


