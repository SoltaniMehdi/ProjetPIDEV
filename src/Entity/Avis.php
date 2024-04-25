<?php

namespace App\Entity;

use Doctrine\ORM\Mapping as ORM;

namespace App\Entity;

use Doctrine\ORM\Mapping as ORM;

/**
 * Avis
 *
 * @ORM\Table(name="avis")
 * @ORM\Entity
 */
class Avis
{
    /**
     * @var int
     *
     * @ORM\Column(name="id_avis", type="integer", nullable=false)
     * @ORM\Id
     * @ORM\GeneratedValue(strategy="IDENTITY")
     */
    private $idAvis;

    /**
     * @var int
     *
     * @ORM\Column(name="idR", type="integer", nullable=false)
     */
    private $idR;

    /**
     * @var string
     *
     * @ORM\Column(name="commentaire", type="string", length=100, nullable=false)
     */
    private $commentaire;

    /**
     * @var float
     *
     * @ORM\Column(name="note", type="float", precision=10, scale=0, nullable=false)
     */
    private $note;

    public function getIdAvis(): ?int
    {
        return $this->idAvis;
    }

    public function getIdR(): ?int
    {
        return $this->idR;
    }

    public function setIdR(int $idR): self
    {
        $this->idR = $idR;

        return $this;
    }

    public function getCommentaire(): ?string
    {
        return $this->commentaire;
    }

    public function setCommentaire(string $commentaire): self
    {
        $this->commentaire = $commentaire;

        return $this;
    }

    public function getNote(): ?float
    {
        return $this->note;
    }

    public function setNote(float $note): self
    {
        $this->note = $note;

        return $this;
    }


}




