<?php

namespace App\Controller;

use App\Entity\Commandes;
use App\Entity\Repas;
use App\Entity\CommandesHasRepas;
use Doctrine\ORM\EntityManagerInterface;
use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\HttpFoundation\JsonResponse;
use Symfony\Component\HttpFoundation\Request;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\Routing\Annotation\Route;
use Doctrine\Common\Collections\ArrayCollection;
use Psr\Log\LoggerInterface;

#[Route('/repas')]
class RepasController extends AbstractController
{


    public function __construct(
        private LoggerInterface $logger,
    ) {
        
    }



    /**
     * @Route("/api/addMeal", name="add_meal", methods={"POST","GET"})
     */
    public function addMeal(EntityManagerInterface $entityManager, Request $request): Response
{
    // Extract meal data from request
    //$requestData = $request->request->all();
    $nom = $_GET['nom'] ?? null;
    $prix = $_GET['prix'] ?? null;
    $description = $_GET['description'] ?? null;

    // Validate meal data
    if (!$nom || !$prix || !$description) {
        echo "<script>alert(\"Invalid meal data\")</script>";
    }

    // Create new Repas entity and set meal data
    $repas = new Repas();
    $repas->setNom($nom);
    $repas->setPrix($prix);
    $repas->setDescription($description);

    $entityManager->getRepository(Repas::class);
    // Save Repas entity to database
    $entityManager->persist($repas);
    $entityManager->flush();
    echo "<script>alert(\"Meal added successfully\")</script>";

    // Use self to access the static variable
    session_start();
    array_push($_SESSION['repas'],$repas);
    return new Response(Response::HTTP_OK);
}


        /**
     * @Route("/api/addMealToCommandeRepas", name="add-meal-to-commande-repas", methods={"POST","GET"})
     */
    public function addMealToCommandeRepas(EntityManagerInterface $entityManager): Response
    {
        $commandes = new Commandes();
        $commandes -> setDatec(new \DateTime());
        $commandes -> setStatut("traité");
        $commandes -> setTotalprix(280);
        $entityManager->persist($commandes);
        $entityManager->flush();
        
    
        // Create a new CommandeRepas entity and associate it with the Repas and Commande
        $this->logger->info('*******************************');
        foreach ($_SESSION['repas'] as $repas) {
            $commandeRepas = new CommandesHasRepas();
            $commandeRepas->setIdCommande($commandes->getIdCommande());
            $commandeRepas->setIdR($repas->getIdR());
            // Persist the CommandeRepas entity to the database
            $entityManager->persist($commandeRepas);
        }
       
    
        $entityManager->flush();
    
        // You may return a success response or redirect the user to another page
        return new Response(Response::HTTP_OK);
    }


/**
     * @Route("/api/init", name="init", methods={"POST","GET"})
     */
    public function init(): Response
    {
        session_start(); 
        $_SESSION['repas'] = array();
        $this->logger->info('***************init*****************');

        return new Response(Response::HTTP_OK);
    }





}
    



