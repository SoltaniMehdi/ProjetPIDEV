<?php

namespace App\Entity;

use Doctrine\ORM\Mapping as ORM;
use Doctrine\Common\Collections\ArrayCollection;

/**
 * Repas
 *
 * @ORM\Table(name="repas")
 * @ORM\Entity
 */
class Repas
{
    /**
     * @var int
     *
     * @ORM\Column(name="idR", type="integer", nullable=false)
     * @ORM\Id
     * @ORM\GeneratedValue(strategy="IDENTITY")
     */
    private $idR;

    /**
     * @var int
     *
     * @ORM\Column(name="prix", type="integer", nullable=false)
     */
    private $prix;

    /**
     * @var string
     *
     * @ORM\Column(name="nom", type="string", length=255, nullable=false)
     */
    private $nom;

    /**
     * @var string
     *
     * @ORM\Column(name="description", type="string", length=255, nullable=false)
     */
    private $description;

    public function getIdR(): ?int
    {
        return $this->idR;
    }

    public function getPrix(): ?int
    {
        return $this->prix;
    }

    public function setPrix(int $prix): self
    {
        $this->prix = $prix;

        return $this;
    }

    public function getNom(): ?string
    {
        return $this->nom;
    }

    public function setNom(string $nom): self
    {
        $this->nom = $nom;

        return $this;
    }

    public function getDescription(): ?string
    {
        return $this->description;
    }

    public function setDescription(string $description): self
    {
        $this->description = $description;

        return $this;
    }
/***********************new code**************************/
 /**
     * @var ArrayCollection
     * @ORM\ManyToMany(targetEntity="Commandes", mappedBy="repas")
     */
    private $commandes;

           /**
     * Constructor
     */
    public function __construct()
    {
        $this->commandes = new ArrayCollection();
    }

    /**
     * Add commandes
     *
     * @param Commandes $commandes
     *
     * @return commandes
     */
    public function addCommandes(Commandes $commandes)
    {
        $this->commandes[] = $commandes;

        return $this->commandes;
    }

    /**
     * Remove commandes
     *
     * @param Commandes $commandes
     */
    public function removeCommandes(Commandes $commandes)
    {
        $this->commandes->removeElement($commandes);
    }

    /**
     * Get commandes
     *
     * @return \Doctrine\Common\Collections\Collection
     */
    public function getCommandes()
    {
        return $this->commandes;
     }




}
