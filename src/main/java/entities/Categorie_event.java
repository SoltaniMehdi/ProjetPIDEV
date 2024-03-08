package entities;

import java.sql.Date;

public class Categorie_event {




    private int idcateg  ;
    private String categevent  ;



    public Categorie_event() {
    }

    public Categorie_event(int idcateg, String categevent) {
        this.idcateg = idcateg;
        this.categevent = categevent ;


    }

    public Categorie_event(String categevent) {
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
        return "Categorie_event{" +
              //  "idcateg=" + idcateg+
                ", categevent=" + categevent + '\'' +
                '}';
    }
}
