package services;

import entities.Livraison1;
import entities.PointDistribution;

import java.sql.SQLException;
import java.util.List;

public interface IService<L> {

    void ajouter(L l) throws SQLException;


    void supprimer(int id) throws SQLException;

    void modifier(L l) throws SQLException;

    public List<L> afficher() throws SQLException;

    List<L> liste();
}
