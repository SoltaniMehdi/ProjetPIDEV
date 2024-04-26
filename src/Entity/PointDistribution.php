<?php

namespace App\Entity;

use Doctrine\ORM\Mapping as ORM;

#[ORM\Table(name: "point_distribution")]
#[ORM\Entity(repositoryClass: "App\Repository\PointDistributionRepository")]
class PointDistribution
{
    #[ORM\Id]
    #[ORM\GeneratedValue(strategy: "IDENTITY")]
    #[ORM\Column(name: "id_point_distribution", type: "integer", nullable: false)]
    private int $idPointDistribution;

    #[ORM\Column(name: "nom", type: "string", length: 255, nullable: false)]
    private string $nom;

    #[ORM\Column(name: "adresse", type: "string", length: 255, nullable: false)]
    private string $adresse;

    #[ORM\Column(name: "id_livraison", type: "integer", nullable: false)]
    private int $idLivraison;

    public function getIdPointDistribution(): ?int
    {
        return $this->idPointDistribution;
    }

    public function getNom(): ?string
    {
        return $this->nom;
    }

    public function setNom(string $nom): static
    {
        $this->nom = $nom;

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

    public function getIdLivraison(): ?int
    {
        return $this->idLivraison;
    }

    public function setIdLivraison(int $idLivraison): static
    {
        $this->idLivraison = $idLivraison;

        return $this;
    }
}
