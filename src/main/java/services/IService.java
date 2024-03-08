package services;

import entities.Avis;
import entities.Categorie_event;
import entities.Repas;
import entities.evenement;

import java.sql.SQLException;
import java.util.List;

public interface IService<T> {
    public  void ajouter(T t) throws SQLException;
    public  void modifier(T t) throws SQLException;
    public  void supprimer(int id) throws SQLException;

    public List<T> afficher() throws SQLException;
    List<Avis> afficheravis() throws SQLException;

    List<Repas> afficherdetails(int id_commande) throws SQLException;

    List<Repas> afficherrepas() throws SQLException;

    public Categorie_event findCategById(int id) throws SQLException;

    evenement findEventById(int id) throws SQLException;
}
