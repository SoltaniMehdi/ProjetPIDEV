package tests;

import entities.Commande;
import javafx.application.Application;
import javafx.stage.Stage;
import services.ServiceCommandes;

import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class Main {
    public static void main(String[] args) throws ParseException {
        ServiceCommandes serviceCommandes= new ServiceCommandes();
        DateFormat formatter = new SimpleDateFormat("yyyy/MM/dd");
        java.util.Date datecmd1 = formatter.parse("2014/04/13");
        java.util.Date datecmd2 = formatter.parse("2014/04/14");
        java.util.Date datecmd3 = formatter.parse("2014/04/15");
        Commande c1= new Commande(new java.sql.Date(datecmd1.getTime()),"en traitement",90.5f);
        //Commande c2= new Commande(new java.sql.Date(datecmd2.getTime()),"en traitement",85.5f);
        //Commande c3= new Commande(new java.sql.Date(datecmd3.getTime()),"en traitement",100.5f);


        try {
            serviceCommandes.ajouter(c1);
            //serviceCommandes.ajouter(c2);
            //serviceCommandes.ajouter(c3);
            serviceCommandes.modifier(new Commande(1,new java.sql.Date(datecmd1.getTime()),"en reception",800f));
            serviceCommandes.supprimer(3);
            System.out.println(serviceCommandes.afficher());

        }
        catch (SQLException e){
            System.out.println(e.getMessage());
        }

    }


    public void start(Stage stage) throws Exception {

    }
}
