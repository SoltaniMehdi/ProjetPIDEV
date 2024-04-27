package services;

import entities.Categorie;
import entities.evenement;
import utils.MyDatabase;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;





    public class servicecategorie implements  IService<Categorie>{
        static Connection connection;
        public servicecategorie(){
            connection= MyDatabase.getInstance().getConnection();

        }
        @Override
        public boolean ajouter(Categorie categorie) {
            try {
                String req = "INSERT INTO `categorie`(`categevent` ) VALUES (?)";
                PreparedStatement ps = connection.prepareStatement(req);
                ps.setString(1, categorie.getCategevent());

                System.out.println("Prepared SQL Query: " + ps.toString());
                int rowsAffected = ps.executeUpdate();

                if (rowsAffected > 0) {
                    System.out.println("categorie ajoutée");
                    return true; // Event added successfully
                } else {
                    System.out.println("Échec de l'ajout ");
                    return false; // Event not added
                }
            } catch (SQLException e) {
                System.out.println("Erreur lors de l'ajout : " + e.getMessage());
                return false; // Exception occurred, event not added
            }
        }



        @Override
        public void modifier(Categorie categorie) throws SQLException {
            String req="update categorie set categevent=?  where idcateg=?";
            PreparedStatement ps=connection.prepareStatement(req);
            ps.setString(1, categorie.getCategevent());;
            ps.setInt(2, categorie.getIdcateg());
            ps.executeUpdate();
            System.out.println("categorie modifie");

        }




        @Override
        public void supprimer(int idcateg) throws SQLException {
            String requete = "DELETE FROM categorie WHERE idcateg="+idcateg;
            try {
                Statement st = connection.createStatement();
                st.executeUpdate(requete);
                System.out.println("delete success");
            }
            catch (SQLException e){
                throw new RuntimeException(e);
            }
        }



        @Override
        public List<Categorie> afficher() throws SQLException {

            List< Categorie> categorie = new ArrayList<>();
            String req="select * from categorie";
            Statement st  = connection.createStatement();
            ResultSet rs = st.executeQuery(req);
            while (rs.next()){
                Categorie c  = new Categorie();
                c.setIdcateg(rs.getInt(1));
                c .setCategevent(rs.getString(2));

                categorie.add(c);
            }
            return categorie;
        }

        @Override
        public evenement findEventById(int id) throws SQLException {
            return null;
        }


        @Override
        public Categorie findCategById(int id) throws SQLException {
            Categorie c = new Categorie();
            String req="select * from categorie where idcateg ="+id;
            Statement st  = connection.createStatement();
            ResultSet rs = st.executeQuery(req);
            while (rs.next()){

                c.setIdcateg(rs.getInt(1));
                c.setCategevent(rs.getString(2));


            }
            return c;
        }
    }



