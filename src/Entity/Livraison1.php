<?php

namespace App\Entity;

use Doctrine\ORM\Mapping as ORM;

#[ORM\Table(name: "livraison1")]
#[ORM\Entity(repositoryClass: "App\Repository\livraison1Repository")]
class Livraison1
{
    #[ORM\Id]
    #[ORM\GeneratedValue(strategy: "IDENTITY")]
    #[ORM\Column(name: "id_livraison", type: "integer", nullable: false)]
    private int $idLivraison;

    #[ORM\Column(name: "id_commande", type: "integer", nullable: false)]
    private int $idCommande;

    #[ORM\Column(name: "id_client", type: "integer", nullable: false)]
    private int $idClient;

    #[ORM\Column(name: "date_livraison", type: "string", length: 20, nullable: false)]
    private string $dateLivraison;

    #[ORM\Column(name: "adresse", type: "string", length: 20, nullable: false)]
    private string $adresse;

    #[ORM\Column(name: "prix", type: "float", precision: 10, scale: 0, nullable: false)]
    private float $prix;

    public function getIdLivraison(): ?int
    {
        return $this->idLivraison;
    }

    public function getIdCommande(): ?int
    {
        return $this->idCommande;
    }

    public function setIdCommande(int $idCommande): static
    {
        $this->idCommande = $idCommande;

        return $this;
    }

    public function getIdClient(): ?int
    {
        return $this->idClient;
    }

    public function setIdClient(int $idClient): static
    {
        $this->idClient = $idClient;

        return $this;
    }

    public function getDateLivraison(): ?string
    {
        return $this->dateLivraison;
    }

    public function setDateLivraison(string $dateLivraison): static
    {
        $this->dateLivraison = $dateLivraison;

        return $this;
    }

    public function getAdresse(): ?string
    {
        return $this->adresse;
    }

    public function setAdresse(string $adresse): static
    {
        $this->adresse = $adresse;

        return $this;
    }

    public function getPrix(): ?float
    {
        return $this->prix;
    }

    public function setPrix(float $prix): static
    {
        $this->prix = $prix;

        return $this;
    }
}
