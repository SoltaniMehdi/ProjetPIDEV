package tests;

import entities.Categorie;
import entities.Repas;
import services.ServiceCategorie;
import services.ServiceRepas;

import java.sql.SQLException;

public class Main {
    public static void main(String[] args) {
       ServiceCategorie serviceCategorie= new ServiceCategorie();
        Categorie c1= new Categorie(1,"jfjf","kdfh");
        Categorie c2= new Categorie(2,"salade","salade laitue tomates thon");
        ServiceRepas serviceRepas= new ServiceRepas();
        Repas r1= new Repas(4.2f,"hdhjhhhhhhsgf","repasj",3);

        ServiceRepas serviceRepas1= new ServiceRepas();
        Repas r2= new Repas(45f,"hdhjhhhhhhsgf","repasj",3);


        try {
            //serviceCategorie.ajouter(c2);
            serviceCategorie.afficher();
            System.out.println(serviceCategorie.afficher());
           //serviceRepas.afficher();
          // System.out.println(serviceRepas.afficher());
         // serviceRepas.supprimer(2);
         // serviceRepas.modifier(r2);
          // serviceCategorie.ajouter(c1);



        }
        catch (SQLException e){
            System.out.println(e.getMessage());
        }

    }
}
