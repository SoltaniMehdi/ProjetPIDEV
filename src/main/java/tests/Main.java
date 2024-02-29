package tests;
import entities.Livraison1;
import services.ServiceLivraison;
import entities.PointDistribution;
import java.sql.SQLException;
import services.ServicePointDistribution;
public class Main {

    public static void main(String[] args) {

        ServiceLivraison serviceLivraison = new ServiceLivraison();

        try {
            // Création d'une nouvelle livraison pour l'ajout
            // Livraison1 nouvelleLivraison = new Livraison1( 18, 20, "admis", "bravoooo", 2.0f);
             //Ajouter la nouvelle livraison
            //serviceLivraison.ajouter(nouvelleLivraison);
           // System.out.println("Nouvelle livraison ajoutée avec succès");

            // Modification de la livraison avec l'identifiant
            //Livraison1 livraisonAModifier = new Livraison1(7, 12, 99, "23/02", "boumhel", 8f);
            //  serviceLivraison.modifierLivraison(livraisonAModifier);
            //  System.out.println("Livraison modifiée avec succès");

            // Supprimer la livraison avec l'identifiant
            //  serviceLivraison.supprimerLivraison(8);
            //  System.out.println("Livraison supprimée avec succès");

            // Création d'un nouveau point de distribution pour l'ajout
            ServicePointDistribution servicePointDistribution = new ServicePointDistribution();
            PointDistribution nouveauPointDistribution = new PointDistribution( 1, "Point 1", "Adresse 1", 7);

            // Ajouter le nouveau point de distribution
             servicePointDistribution.ajouter(nouveauPointDistribution);
             System.out.println("Nouveau point de distribution ajouté avec succès");

            PointDistribution pointDistributionAModifier = new PointDistribution(1, "adam", "esprit", 10);
            servicePointDistribution.modifier(pointDistributionAModifier);
            System.out.println("Point de distribution modifié avec succès");

            // Supprimer un point de distribution
             // servicePointDistribution.supprimer(6);
          //   System.out.println("Point de distribution supprimé avec succès");


        } catch (SQLException e) {
            System.out.println("Erreur : " + e.getMessage());
        }
    }
}

