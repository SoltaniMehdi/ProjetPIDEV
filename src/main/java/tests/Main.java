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
        ServiceRepas serviceRepas= new ServiceRepas();
        Repas r1= new Repas(4.2f,"hdhjhhhhhhsgf","repasj",3);



        try {
           //serviceRepas.afficher();
          // System.out.println(serviceRepas.afficher());
         // serviceRepas.ajouter(r1);
          serviceRepas.modifier(r1);
          // serviceCategorie.ajouter(c1);



        }
        catch (SQLException e){
            System.out.println(e.getMessage());
        }

    }
}
