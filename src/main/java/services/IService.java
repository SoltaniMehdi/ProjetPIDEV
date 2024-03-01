package services;

import entities.Avis;
import entities.Repas;

import java.sql.SQLException;
import java.util.List;

public interface IService<T> {
    public  void ajouter(T t) throws SQLException;
    public  void modifier(T t) throws SQLException;
    void supprimer( int id) throws SQLException;
    public List<T> afficher() throws SQLException;

    List<Avis> afficheravis() throws SQLException;

    List<Repas> afficherdetails(int id_commande) throws SQLException;

    List<Repas> afficherrepas() throws SQLException;
}
