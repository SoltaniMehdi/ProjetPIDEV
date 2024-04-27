<?php

namespace App\Form;

use App\Entity\Evenement;
use Symfony\Component\Form\AbstractType;
use Symfony\Component\Form\FormBuilderInterface;
use Symfony\Component\OptionsResolver\OptionsResolver;
use Symfony\Component\Validator\Constraints\Length;
use Symfony\Component\Validator\Constraints\NotBlank;


class EvenementType extends AbstractType
{
    public function buildForm(FormBuilderInterface $builder, array $options): void
    {
        $builder
            ->add('nom', null, [
                'constraints' => [
                    new NotBlank([
                        'message' => 'The name field is required.', // Custom message for the NotBlank constraint
                    ]),                    new Length([
                        'min' => 4,
                        'max' => 10,
                        'minMessage' => 'The name must be at least {{ limit }} characters long',
                        'maxMessage' => 'The name cannot be longer than {{ limit }} characters',
                    ]),
                ],
            ])
            ->add('description', null, [
                'constraints' => [
                    new NotBlank([
                        'message' => 'The description field is required.', // Custom message for the NotBlank constraint
                    ]),                    new Length([
                        'min' => 10,
                        'max' => 15,
                        'minMessage' => 'The description must be at least {{ limit }} characters long',
                        'maxMessage' => 'The description cannot be longer than {{ limit }} characters',
                    ]),
                ],
            ])
            ->add('dated', null)
            ->add('lieu', null, [
                'constraints' => [
                    new NotBlank([
                        'message' => 'The lieu field is required.', // Custom message for the NotBlank constraint
                    ]),                    new Length([
                        'min' => 4,
                        'max' => 10,
                        'minMessage' => 'The lieu must be at least {{ limit }} characters long',
                        'maxMessage' => 'The lieu cannot be longer than {{ limit }} characters',
                    ]),
                ],
            ])
        ;
    }

    public function configureOptions(OptionsResolver $resolver): void
    {
        $resolver->setDefaults([
            'data_class' => Evenement::class,
        ]);
    }
}
