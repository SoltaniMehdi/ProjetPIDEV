package entities;

import java.sql.Date;

public class Categorie {




    private int idcateg  ;
    private String categevent  ;



    public Categorie() {
    }

    public Categorie(int idcateg, String categevent) {
        this.idcateg = idcateg;
        this.categevent = categevent ;


    }

    public Categorie(String categevent) {
        this.categevent= categevent;

    }




    public int getIdcateg() {
        return idcateg;
    }
    public void setIdcateg(int idcateg) {
        this.idcateg = idcateg;
    }
    public String getCategevent() {
        return categevent ;
    }
    public void setCategevent(String categevent ) {
        this.categevent= categevent ;
    }







    @Override
    public String toString() {
        return "Categprie{" +
                "idcateg=" + idcateg+
                ", categevent=" + categevent + '\'' +
                '}';
    }
}
