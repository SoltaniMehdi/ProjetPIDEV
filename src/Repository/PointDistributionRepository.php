<?php

namespace App\Repository;

use App\Entity\PointDistribution;
use Doctrine\Bundle\DoctrineBundle\Repository\ServiceEntityRepository;
use Doctrine\Persistence\ManagerRegistry;

/**
 * @extends ServiceEntityRepository<PointDistribution>
 *
 * @method PointDistribution|null find($id, $lockMode = null, $lockVersion = null)
 * @method PointDistribution|null findOneBy(array $criteria, array $orderBy = null)
 * @method PointDistribution[]    findAll()
 * @method PointDistribution[]    findBy(array $criteria, array $orderBy = null, $limit = null, $offset = null)
 */
class PointDistributionRepository extends ServiceEntityRepository
{
    public function __construct(ManagerRegistry $registry)
    {
        parent::__construct($registry, PointDistribution::class);
    }

//    /**
//     * @return PointDistribution[] Returns an array of PointDistribution objects
//     */
//    public function findByExampleField($value): array
//    {
//        return $this->createQueryBuilder('p')
//            ->andWhere('p.exampleField = :val')
//            ->setParameter('val', $value)
//            ->orderBy('p.id', 'ASC')
//            ->setMaxResults(10)
//            ->getQuery()
//            ->getResult()
//        ;
//    }

//    public function findOneBySomeField($value): ?PointDistribution
//    {
//        return $this->createQueryBuilder('p')
//            ->andWhere('p.exampleField = :val')
//            ->setParameter('val', $value)
//            ->getQuery()
//            ->getOneOrNullResult()
//        ;
//    }
}
