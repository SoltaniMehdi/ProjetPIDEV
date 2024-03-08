package services;

import entities.*;
import utils.MyDatabase;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;





    public class servicecategorie_event implements  IService<Categorie_event>{
        static Connection connection;
        public servicecategorie_event(){
            connection= MyDatabase.getInstance().getConnection();

        }
        @Override
        public  void ajouter(Categorie_event categorie) {
            try {
                String req = "INSERT INTO `categorie_event`(`categevent` ) VALUES (?)";
                PreparedStatement ps = connection.prepareStatement(req);
                ps.setString(1, categorie.getCategevent());

                System.out.println("Prepared SQL Query: " + ps.toString());
                int rowsAffected = ps.executeUpdate();

                if (rowsAffected > 0) {
                    System.out.println("categorieevent ajoutée");

                } else {
                    System.out.println("Échec de l'ajout ");

                }
            } catch (SQLException e) {
                System.out.println("Erreur lors de l'ajout : " + e.getMessage());

            }
        }



        @Override
        public void modifier(Categorie_event categorie_event) throws SQLException {
            String req="update categorie_event set categevent=?  where idcateg=?";
            PreparedStatement ps=connection.prepareStatement(req);
            ps.setString(1, categorie_event.getCategevent());;
            ps.setInt(2, categorie_event.getIdcateg());
            ps.executeUpdate();
            System.out.println("categorie_event modifie");

        }




        @Override
        public void supprimer(int idcateg) throws SQLException {
            String requete = "DELETE FROM categorie_event WHERE idcateg="+idcateg;
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
        public List<Categorie_event> afficher() throws SQLException {

            List< Categorie_event> categorie_event = new ArrayList<>();
            String req="select * from categorie_event";
            Statement st  = connection.createStatement();
            ResultSet rs = st.executeQuery(req);
            while (rs.next()){
                Categorie_event c  = new Categorie_event();
                c.setIdcateg(rs.getInt(1));
                c .setCategevent(rs.getString(2));

                categorie_event.add(c);
            }
            return categorie_event;
        }

        @Override
        public List<Avis> afficheravis() throws SQLException {
            return null;
        }

        @Override
        public List<Repas> afficherdetails(int id_commande) throws SQLException {
            return null;
        }

        @Override
        public List<Repas> afficherrepas() throws SQLException {
            return null;
        }


        @Override
        public Categorie_event findCategById(int id) throws SQLException {
            Categorie_event c = new Categorie_event();
            String req="select * from categorie_event where idcateg ="+id;
            Statement st  = connection.createStatement();
            ResultSet rs = st.executeQuery(req);
            while (rs.next()){

                c.setIdcateg(rs.getInt(1));
                c.setCategevent(rs.getString(2));


            }
            return c;
        }

        @Override
        public evenement findEventById(int id) throws SQLException {
            return null;
        }
    }



