<?php

namespace App\Controller;

use App\Entity\PointDistribution;
use App\Form\PointDistributionType;
use App\Repository\PointDistributionRepository;
use Doctrine\ORM\EntityManagerInterface;
use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\HttpFoundation\Request;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\Routing\Annotation\Route;

#[Route('/pointdistribution')]
class PointDistributionController extends AbstractController
{
    #[Route('/', name: 'app_point_distribution_index', methods: ['GET'])]
    public function index(PointDistributionRepository $pointDistributionRepository): Response
    {
        return $this->render('point_distribution/index.html.twig', [
            'point_distributions' => $pointDistributionRepository->findAll(),
        ]);
    }

    #[Route('/new', name: 'app_point_distribution_new', methods: ['GET', 'POST'])]
    public function new(Request $request, EntityManagerInterface $entityManager): Response
    {
        $pointDistribution = new PointDistribution();
        $form = $this->createForm(PointDistributionType::class, $pointDistribution);
        $form->handleRequest($request);

        if ($form->isSubmitted() && $form->isValid()) {
            $entityManager->persist($pointDistribution);
            $entityManager->flush();

            return $this->redirectToRoute('app_point_distribution_index', [], Response::HTTP_SEE_OTHER);
        }

        return $this->renderForm('point_distribution/new.html.twig', [
            'point_distribution' => $pointDistribution,
            'form' => $form,
        ]);
    }

    #[Route('/{idPointDistribution}', name: 'app_point_distribution_show', methods: ['GET'])]
    public function show(PointDistribution $pointDistribution): Response
    {
        return $this->render('point_distribution/show.html.twig', [
            'point_distribution' => $pointDistribution,
        ]);
    }

    #[Route('/{idPointDistribution}/edit', name: 'app_point_distribution_edit', methods: ['GET', 'POST'])]
    public function edit(Request $request, PointDistribution $pointDistribution, EntityManagerInterface $entityManager): Response
    {
        $form = $this->createForm(PointDistributionType::class, $pointDistribution);
        $form->handleRequest($request);

        if ($form->isSubmitted() && $form->isValid()) {
            $entityManager->flush();

            return $this->redirectToRoute('app_point_distribution_index', [], Response::HTTP_SEE_OTHER);
        }

        return $this->renderForm('point_distribution/edit.html.twig', [
            'point_distribution' => $pointDistribution,
            'form' => $form,
        ]);
    }

    #[Route('/{idPointDistribution}', name: 'app_point_distribution_delete', methods: ['POST'])]
    public function delete(Request $request, PointDistribution $pointDistribution, EntityManagerInterface $entityManager): Response
    {
        if ($this->isCsrfTokenValid('delete'.$pointDistribution->getIdPointDistribution(), $request->request->get('_token'))) {
            $entityManager->remove($pointDistribution);
            $entityManager->flush();
        }

        return $this->redirectToRoute('app_point_distribution_index', [], Response::HTTP_SEE_OTHER);
    }
}
