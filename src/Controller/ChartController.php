<?php

namespace App\Controller;

use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\Routing\Annotation\Route;
use App\Repository\EventRepository;

#[Route('/Chart')]
class ChartController extends AbstractController
{
    #[Route('/stats', name: 'app_stats')]
    public function stat(EventRepository $eventRepository): Response
    {
        // Retrieve the avis entities
        $avis = $eventRepository->findAll();

        // Initialize arrays to store counts for each note type
        $avisCount = [
            'Good' => 10,
            'Average' => 5,
            'Bad' => 3
        ];

        // Count the number of avis for each note type
        foreach ($avis as $avi) {
            $note = $avi->getNote(); // Assuming there's a method to retrieve the note from the Avis entity
            switch ($note) {
                case 'good':
                    $avisCount['good']++;
                    break;
                case 'average':
                    $avisCount['average']++;
                    break;
                case 'bad':
                    $avisCount['bad']++;
                    break;
                default:
                    // Handle unexpected note types if needed
                    break;
            }
        }
        
        return $this->render('ChartAvis/index.html.twig', [
            'avisCount' => $avisCount,
        ]);
    }
}
