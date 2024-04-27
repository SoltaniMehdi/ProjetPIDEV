<?php
namespace App\Controller;

use App\Entity\Evenement;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\Routing\Annotation\Route;
use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Doctrine\ORM\EntityManagerInterface;
class StatsController extends AbstractController
{
    /**
     * @Route("/stats", name="app_chart")
     */
    public function chart(EntityManagerInterface $entityManager): Response
    {
        // Retrieve all events from the database
        $events = $entityManager->getRepository(Evenement::class)->findAll();

        // Initialize counters for the Grand Tunis and other states
        $grandTunisCount = 0;
        $otherStatesCount = 0;

        // Loop through each event to count the occurrences of Grand Tunis and other states
        foreach ($events as $event) {
            $lieu = $event->getLieu();
            // Check if the lieu is in the Grand Tunis
            if (in_array($lieu, ['Tunis', 'Ben Arous'])) {
                $grandTunisCount++;
            } else {
                $otherStatesCount++;
            }
        }

        // Create an array containing the counts for the chart
        $chartData = [
            'Grand Tunis' => $grandTunisCount,
            'Other States' => $otherStatesCount
        ];

        // Render the chart view and pass the chart data
        return $this->render('Chart/stat.html.twig', [
            'chartData' => $chartData
        ]);
    }
}
