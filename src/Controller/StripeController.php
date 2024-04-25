<?php

namespace App\Controller;

use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\Routing\Annotation\Route;
use Symfony\Component\HttpFoundation\Request;
use Stripe;
use Symfony\Component\Mailer\MailerInterface;
use Symfony\Component\Mime\Email;

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
    public function createCharge(Request $request, MailerInterface $mailer): Response
    {
        Stripe\Stripe::setApiKey($_ENV["STRIPE_SECRET"]);
        Stripe\Charge::create([
            "amount" => 5 * 100, // Adjust the amount as needed
            "currency" => "usd", // Adjust the currency as needed
            "source" => $request->request->get('stripeToken'),
            "description" => "Binaryboxtuts Payment Test"
        ]);

        // Send confirmation email
        $email = (new Email())
            ->from('elamarzouky00@gmail.com') // Adjust sender email address
            ->to('elamarzouky00@gmail.com') // Adjust recipient email address
            ->subject('Payment Confirmation')
            ->text('Your payment was successful.'); // Email body

        $mailer->send($email);

        $this->addFlash(
            'success',
            'Payment Successful!'
        );
        return $this->redirectToRoute('app_stripe', [], Response::HTTP_SEE_OTHER);
    }
}
