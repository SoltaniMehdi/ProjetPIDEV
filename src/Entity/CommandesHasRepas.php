<?php
namespace App\Entity;

use Doctrine\ORM\Mapping as ORM;

/**
 * @ORM\Entity
 * @ORM\Table(name="commandes_repas")
 */
class CommandesHasRepas
{
   /**
     * @var int
     *
     * @ORM\Column(name="idR", type="integer", nullable=false)
     * @ORM\Id
     */
    private $idR;

/**
     * @var int
     *
     * @ORM\Column(name="id_commande", type="integer", nullable=false)
     * @ORM\Id
     */
    private $id_commande;

    public function getIdR(): ?int
    {
        return $this->idR;
    }
    
    public function setIdR(int $idR): self
    {
        $this->idR = $idR;
        return $this;
    }
    
    public function getIdCommande(): ?int
    {
        return $this->id_commande;
    }
    
    public function setIdCommande(int $idCommande): self
    {
        $this->id_commande = $idCommande;
        return $this;
    }
    

   
}
