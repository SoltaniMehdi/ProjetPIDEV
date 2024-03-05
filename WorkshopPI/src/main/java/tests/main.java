package tests;
import entities.Utilisateur;
import utils.MyDatabase;
import services.CRUDUtilisateur;

import java.util.List;

public class main {
    public static void main (String[] args){
        MyDatabase.getInstance().getConnection();
        CRUDUtilisateur crudutilisateur = new CRUDUtilisateur();
        //ajout//
        //crudutilisateur.ajouterUtilisateur(crudutilisateur);
        //supp//
        //crudutilisateur.supprimerUtilisateur(4);
        //modif//
        //crudutilisateur.modifierUtilisateur(3,"BelhajMabrouk","adem","6969","0101","adem.belhajmabrouk@esprit.tn","livreur");
        //affichage//
        /*
        List<Utilisateur> utilisateurs = CRUDUtilisateur.afficherUtilisateur();
        // Affichage des utilisateurs récupérés
        for (Utilisateur utilisateur : utilisateurs) {
            System.out.println(utilisateur);
        }
        */


    }
}
