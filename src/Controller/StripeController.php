<?php

namespace App\Controller;

use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\Routing\Annotation\Route;
use Symfony\Component\HttpFoundation\Request;
use Stripe;
use Twilio\Rest\Client; 


class StripeController extends AbstractController
{
    #[Route('/stripe', name: 'app_stripe')]
    public function index(): Response
    {
        return $this->render('stripe/index.html.twig', [
            'stripe_key' => $_ENV["STRIPE_KEY"],
        ]);
    }



#[Route('/stripe/create-charge', name: 'app_stripe_charge', methods: ['POST'])]
public function createCharge(Request $request): Response
{
    Stripe\Stripe::setApiKey($_ENV["STRIPE_SECRET"]);
    Stripe\Charge::create([
        "amount" => 5 * 100, // Adjust the amount as needed
        "currency" => "usd", // Adjust the currency as needed
        "source" => $request->request->get('stripeToken'),
        "description" => "Binaryboxtuts Payment Test"
    ]);

    // Send confirmation SMS
    $sid = $_ENV["TWILIO_SID"];
    $token = $_ENV["TWILIO_AUTH_TOKEN"];
    $twilio = new Client($sid, $token);
    $twilio->messages
           ->create(
               '+21625175698', // Adjust recipient phone number
               [
                   'from' => '+17577932884',
                   'body' => 'Bravo ! Votre achat est confirmé. Votre paiement a été accepté avec succès. Bienvenue sur VitaPlat, votre destination privilégiée pour des plats sains de qualité. Explorez nos offres exclusives dès maintenant ! Merci pour votre confiance.'
               ]
           );

    $this->addFlash(
        'success',
        'Payment Successful!'
    );
    return $this->redirectToRoute('app_stripe', [], Response::HTTP_SEE_OTHER);
}

}
