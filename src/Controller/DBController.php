<?php

namespace App\Controller;

use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\Routing\Annotation\Route;
use App\Service\UserDatabaseService;
use Doctrine\DBAL\DriverManager;

class DBController extends AbstractController
{
    private $userDatabaseService;

    public function __construct(UserDatabaseService $userDatabaseService)
    {
        $this->userDatabaseService = $userDatabaseService;
    }

    /**
     * @Route("/some-action", name="some_action")
     */
    public function someAction()
    {
        
        $defaultParams = [
            'driver' => 'pdo_mysql',
            'host' => 'localhost', // Assuming your database is hosted locally
            'port' => '3306', // Assuming default MySQL port
            'charset' => 'utf8mb4',
            'user' => $_ENV['DATABASE_USER'], // Assuming you have set this in your .env file
            'password' => null, // No password required
            'dbname' => 'gestions_commande', // Your database name
        ];
        $dynamicConnection = new DynamicConnection($defaultParams);

        // Get the dynamic database connection
        $connection = $dynamicConnection->getConnection();

        // Use the connection to execute queries
        // $connection->executeQuery(...);

        return $this->render('basefront.html.twig', [
            // Pass any necessary data to the template
        ]);
    }

    /**
     * @Route("/test-db-connection", name="test_db_connection")
     */
    public function testDbConnection(): Response
    {
        // Create a connection configuration array
        $connectionParams = [
            'dbname' => 'gestions_commande',
            'user' => $_ENV['DATABASE_USER'],
            'password' => $_ENV['DATABASE_PASSWORD'],
            'host' => 'localhost',
            'driver' => 'pdo_mysql',
        ];

        // Create a new connection
        $conn = DriverManager::getConnection($connectionParams);

        // Return a response indicating the successful connection
        return new Response('<h1>Test Database Connection</h1><p style="color: green;">Database connection successful!</p>');
    }
}
