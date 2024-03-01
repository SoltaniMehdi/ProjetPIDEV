package services;

import entities.Avis;
import entities.Repas;
import utils.MyDatabase;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ServiceAvis implements  IService<Avis>{
    Connection connection;
    public ServiceAvis(){
        connection= MyDatabase.getInstance().getConnection();

    }


    @Override
    public void ajouter(Avis avis) throws SQLException {
        System.out.println("test1");
        String req ="insert into avis (commentaire,note)"+
                "values('"+avis.getCommentaire()+"',"+avis.getNote()+")";
        System.out.println("test2");
        Statement st = connection.createStatement();
        st.executeUpdate(req);
        System.out.println("avis ajouté");
    }

    @Override
    public void modifier(Avis avis) throws SQLException {
        String req="update avis set commentaire=? ,note=? where id_avis=?";
        PreparedStatement ps= connection.prepareStatement(req);
        ps.setString(1, avis.getCommentaire());
        ps.setFloat(2, avis.getNote());
        ps.setInt(3, avis.getId_avis());
        ps.executeUpdate();
        System.out.println("avis modifié");

    }

    @Override
    public void supprimer(int id_avis) throws SQLException {
        String req = "DELETE FROM avis WHERE id_avis=?";
        PreparedStatement ps= connection.prepareStatement(req);
        ps.setInt(1,id_avis);
        ps.executeUpdate();
        System.out.println("avis Supprimée");
    }

    @Override
    public List<Avis> afficher() throws SQLException {
        return null;
    }


    @Override
    public List<Avis> afficheravis() throws SQLException {

        List<Avis> avis= new ArrayList<>();
        String req="select * from avis";
        Statement st  = connection.createStatement();
        ResultSet rs = st.executeQuery(req);
        while (rs.next()){
            Avis a = new Avis();
            a.setId_avis(rs.getInt(1));
            a.setIdR(rs.getInt(2));
            a.setCommentaire(rs.getString(3));
            a.setNote(rs.getFloat(4));
            avis.add(a);
        }
        return avis;
    }

    @Override
    public List<Repas> afficherdetails(int id_commande) throws SQLException {
        return null;
    }

    @Override
    public List<Repas> afficherrepas() throws SQLException {
        return null;
    }
}
