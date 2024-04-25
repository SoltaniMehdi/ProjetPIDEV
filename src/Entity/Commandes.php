<?php

namespace App\Entity;

use Doctrine\ORM\Mapping as ORM;
use Doctrine\Common\Collections\ArrayCollection;

/**
 * Commandes
 *
 * @ORM\Table(name="commandes")
 * @ORM\Entity
 */

class Commandes
{
    /**
     * @var int
     *
     * @ORM\Column(name="id_commande", type="integer", nullable=false)
     * @ORM\Id
     * @ORM\GeneratedValue(strategy="IDENTITY")
     */
    private $idCommande;

    /**
     * @var \DateTime|null
     *
     * @ORM\Column(name="datec", type="date", nullable=true)
     */
    private $datec;

    /**
     * @var string
     *
     * @ORM\Column(name="statut", type="string", length=50, nullable=false)
     */
    private $statut;

    /**
     * @var float
     *
     * @ORM\Column(name="totalprix", type="float", precision=10, scale=0, nullable=false)
     */
    private $totalprix;


    public function getIdCommande(): ?int
    {
        return $this->idCommande;
    }

    public function getDatec(): ?\DateTimeInterface
    {
        return $this->datec;
    }

    public function setDatec(?\DateTimeInterface $datec): self
    {
        $this->datec = $datec;

        return $this;
    }

    public function getStatut(): ?string
    {
        return $this->statut;
    }

    public function setStatut(string $statut): self
    {
        $this->statut = $statut;

        return $this;
    }

    public function getTotalprix(): ?float
    {
        return $this->totalprix;
    }

    public function setTotalprix(float $totalprix): self
    {
        $this->totalprix = $totalprix;

        return $this;
    }
/***********************new code**************************/
    /**
     * @var ArrayCollection
     * @ORM\ManyToMany(targetEntity="Repas", mappedBy="commandes")
     */
    private $repas;

           /**
     * Constructor
     */
    public function __construct()
    {
        $this->repas = new ArrayCollection();
    }

    /**
     * Add repas
     *
     * @param Repas $repas
     *
     * @return repas
     */
    public function addRepas(Repas $repas)
    {
        $this->repas[] = $repas;

        return $this;
    }

    /**
     * Remove repas
     *
     * @param Repas $repas
     */
    public function removeRepas(Repas $repas)
    {
        $this->repas->removeElement($repas);
    }

    /**
     * Get repas
     *
     * @return \Doctrine\Common\Collections\Collection
     */
    public function getRepas()
    {
        return $this->repas;
     }

}