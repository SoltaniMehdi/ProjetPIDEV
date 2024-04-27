<?php

namespace App\Entity;

use Doctrine\ORM\Mapping as ORM;

/**
 * Categorie
 *
 * @ORM\Table(name="categorie")
 * @ORM\Entity
 */
class Categorie
{
    /**
     * @var int
     *
     * @ORM\Column(name="idcateg", type="integer", nullable=false)
     * @ORM\Id
     * @ORM\GeneratedValue(strategy="IDENTITY")
     */
    private $idcateg;

    /**
     * @var string
     *
     * @ORM\Column(name="categevent", type="string", length=50, nullable=false)
     */
    private $categevent;

    public function getIdcateg(): ?int
    {
        return $this->idcateg;
    }

    public function getCategevent(): ?string
    {
        return $this->categevent;
    }

    public function setCategevent(string $categevent): static
    {
        $this->categevent = $categevent;

        return $this;
    }


}
