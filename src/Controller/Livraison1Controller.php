<?php

namespace App\Controller;
use Dompdf\Dompdf;
use Dompdf\Options;
use App\Entity\Livraison1;
use App\Form\Livraison1Type;
use App\Repository\livraison1Repository;
use Doctrine\ORM\EntityManagerInterface;
use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\HttpFoundation\Request;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\Routing\Annotation\Route;

#[Route('/livraison1')]
class Livraison1Controller extends AbstractController
{
    #[Route('/', name: 'app_livraison1_index', methods: ['GET'])]
    public function index(livraison1Repository $livraison1Repository): Response
    {
        return $this->render('livraison1/index.html.twig', [
            'livraison1s' => $livraison1Repository->findAll(),
        ]);
    }


    #[Route('/base', name: 'base')]
    public function base(): Response
    {
        return $this->render('base.html.twig');
    }

    #[Route('/basel', name: 'basel')]
    public function basel(): Response
    {
        return $this->render('test.html.twig');
    }






    #[Route('/new', name: 'app_livraison1_new', methods: ['GET', 'POST'])]
    public function new(Request $request, EntityManagerInterface $entityManager): Response
    {
        $livraison1 = new Livraison1();
        $form = $this->createForm(Livraison1Type::class, $livraison1);
        $form->handleRequest($request);

        if ($form->isSubmitted() && $form->isValid()) {
            $entityManager->persist($livraison1);
            $entityManager->flush();

            return $this->redirectToRoute('app_livraison1_index', [], Response::HTTP_SEE_OTHER);
        }

        return $this->renderForm('livraison1/new.html.twig', [
            'livraison1' => $livraison1,
            'form' => $form,
        ]);
    }

    #[Route('/{idLivraison}', name: 'app_livraison1_show', methods: ['GET'])]
    public function show(Livraison1 $livraison1): Response
    {
        return $this->render('livraison1/show.html.twig', [
            'livraison1' => $livraison1,
        ]);
    }

    #[Route('/{idLivraison}/edit', name: 'app_livraison1_edit', methods: ['GET', 'POST'])]
    public function edit(Request $request, Livraison1 $livraison1, EntityManagerInterface $entityManager): Response
    {
        $form = $this->createForm(Livraison1Type::class, $livraison1);
        $form->handleRequest($request);

        if ($form->isSubmitted() && $form->isValid()) {
            $entityManager->flush();

            return $this->redirectToRoute('app_livraison1_index', [], Response::HTTP_SEE_OTHER);
        }

        return $this->renderForm('livraison1/edit.html.twig', [
            'livraison1' => $livraison1,
            'form' => $form,
        ]);
    }

    #[Route('/{idLivraison}', name: 'app_livraison1_delete', methods: ['POST'])]
public function delete(Request $request, Livraison1 $livraison1, EntityManagerInterface $entityManager): Response
{
    if ($this->isCsrfTokenValid('delete'.$livraison1->getIdLivraison(), $request->request->get('_token'))) {
        $entityManager->remove($livraison1);
        $entityManager->flush();
        
        // Ajout d'un message flash
        $this->addFlash('success', 'La livraison a été supprimée avec succès.');
    }

    return $this->redirectToRoute('app_livraison1_index', [], Response::HTTP_SEE_OTHER);
}
    #[Route('/generate-pdf', name: 'generate_pdf', methods: ['GET'])]
    public function generatePdf(livraison1Repository $livraison1Repository): Response
    {
        $livraisons = $livraison1Repository->findAll();

        // Générer le HTML pour le contenu du PDF
        $html = "<h1>Liste des livraisons</h1>";
        $html .= "<table>";
        $html .= "<thead><tr><th>DateLivraison</th><th>Adresse</th><th>Prix</th></tr></thead>";
        $html .= "<tbody>";
        foreach ($livraisons as $Livraison1) {
            $html .= "<tr>";
            $html .= "<td>" . $Livraison1->getDateLivraison() . "</td>";
            $html .= "<td>" . $Livraison1->getAdresse() . "</td>";
            $html .= "<td>" . $Livraison1->getprix() . "</td>";
          
           
            $html .= "</tr>";
        }
        $html .= "</tbody></table>";

        // Générer le PDF
        $dompdf = new Dompdf();
        $options = new Options();
        $options->set('isHtml5ParserEnabled', true);
        $options->set('isRemoteEnabled', true);
        $dompdf->setOptions($options);
        $dompdf->loadHtml($html);
        $dompdf->render();

        // Envoie le PDF en réponse
        $dompdf->stream("liste_livraisons.pdf", [
            "Attachment" => true
        ]);

        return new Response();
    }
    #[Route('/statistics', name: 'livraison1_statistics', methods: ['GET'])]
public function statistics(livraison1Repository $livraison1Repository): Response
{
    // Récupérer les livraisons
    $livraisons = $livraison1Repository->findAll();

    // Initialiser un tableau pour stocker le nombre de livraisons par adresse
    $livraisonsParAdresse = [];

    // Compter le nombre de livraisons pour chaque adresse
    foreach ($livraisons as $livraison) {
        $adresse = $livraison->getAdresse();
        if (!isset($livraisonsParAdresse[$adresse])) {
            $livraisonsParAdresse[$adresse] = 0;
        }
        $livraisonsParAdresse[$adresse]++;
    }

    // Convertir les données en format adapté pour le graphique
    $dataForChart = [
        'labels' => array_keys($livraisonsParAdresse),
        'data' => array_values($livraisonsParAdresse)
    ];

    return $this->render('livraison1/statistics.html.twig', [
        'dataForChart' => $dataForChart,
    ]);
}

}
