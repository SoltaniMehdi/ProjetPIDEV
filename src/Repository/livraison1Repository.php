<?php

namespace App\Repository;

use App\Entity\Livraison1;
use Doctrine\Bundle\DoctrineBundle\Repository\ServiceEntityRepository;
use Doctrine\Persistence\ManagerRegistry;

/**
 * @extends ServiceEntityRepository<Livraison1>
 *
 * @method Livraison1|null find($id, $lockMode = null, $lockVersion = null)
 * @method Livraison1|null findOneBy(array $criteria, array $orderBy = null)
 * @method Livraison1[]    findAll()
 * @method Livraison1[]    findBy(array $criteria, array $orderBy = null, $limit = null, $offset = null)
 */
class livraison1Repository extends ServiceEntityRepository
{
    public function __construct(ManagerRegistry $registry)
    {
        parent::__construct($registry, Livraison1::class);
    }

//    /**
//     * @return Livraison1[] Returns an array of Livraison1 objects
//     */
//    public function findByExampleField($value): array
//    {
//        return $this->createQueryBuilder('l')
//            ->andWhere('l.exampleField = :val')
//            ->setParameter('val', $value)
//            ->orderBy('l.id', 'ASC')
//            ->setMaxResults(10)
//            ->getQuery()
//            ->getResult()
//        ;
//    }

//    public function findOneBySomeField($value): ?Livraison1
//    {
//        return $this->createQueryBuilder('l')
//            ->andWhere('l.exampleField = :val')
//            ->setParameter('val', $value)
//            ->getQuery()
//            ->getOneOrNullResult()
//        ;
//    }
}
