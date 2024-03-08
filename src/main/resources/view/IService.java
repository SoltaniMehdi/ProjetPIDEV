package services;

import entities.Categorie;
import entities.evenement;

import java.sql.SQLException;
import java.util.List;

public interface IService<T> {
    public  boolean ajouter(T t) throws SQLException;
    public  void modifier(T t) throws SQLException;
    public  void supprimer(int id) throws SQLException;

    public List<T> afficher() throws SQLException;


    public Categorie_event findCategById(int id) throws SQLException;


}
