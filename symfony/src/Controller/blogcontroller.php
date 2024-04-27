<?php

namespace App\Controller;

use App\Entity\Blog;
use App\Form\BlogType;
use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\HttpFoundation\Request;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\Routing\Annotation\Route;

class blogcontroller extends AbstractController
{



 /**
     * @Route("/back", name="display_back")
     */
    public function indexback(): Response
    {

        return $this->render('evenement/index.html.twig'
        );
    }

    


}