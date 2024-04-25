<?php

namespace App\Controller;

use App\Entity\Blog;
use App\Form\BlogType;
use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\HttpFoundation\Request;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\Routing\Annotation\Route;

class BlogController extends AbstractController
{




    /**
     * @Route("/front", name="display_front")
     */
    
    public function indexfront(): Response
    {

        return $this->render('Front/index.html.twig'
        );
    }

    

      /**
     * @Route("/repas", name="app_repas")
     */
    
     public function repas(): Response
     {
 
         return $this->render('Front/index.html.twig'
         );
     }
      /**
     * @Route("/back", name="display_back")
     */
    
    public function indexback(): Response
    {

        return $this->render('Back/index.html.twig'
        );
    }

    


}