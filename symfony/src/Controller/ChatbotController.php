<?php

namespace App\Controller;
use App\Entity\Evenement;
use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\HttpFoundation\Request;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\Routing\Annotation\Route;  

class ChatbotController extends AbstractController


{
    #[Route('/chatbot', name: 'chatbot')]
    public function index(Request $request): Response
    {
        $response = '';
        
        // Handle form submission
        if ($request->isMethod('POST')) {
            $userInput = $request->request->get('message');
            $response = $this->generateResponse($userInput);
        }

        return $this->render('chat/chatbot.html.twig', [
            'response' => $response,
        ]);
    }

    private function generateResponse($userInput)
    {
        switch ($userInput) {
            case 'hello':
                return "Hello! What can I help you with?";
            case 'What day is it today?':
                return "Today is " . date("l");
            default:
                return "I'm sorry, I didn't understand that. Can you please rephrase?";
        }
    }
}
