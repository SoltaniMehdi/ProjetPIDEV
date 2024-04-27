<?php

namespace App\Form;

use App\Entity\Categorie;
use Symfony\Component\Form\AbstractType;
use Symfony\Component\Form\FormBuilderInterface;
use Symfony\Component\OptionsResolver\OptionsResolver;
use Symfony\Component\Validator\Constraints\Length;
use Symfony\Component\Validator\Constraints\NotBlank;

class Categorie1Type extends AbstractType
{
    public function buildForm(FormBuilderInterface $builder, array $options): void
    {
        $builder
            ->add('categevent', null, [
                'constraints' => [
                    new NotBlank([
                        'message' => 'The categevent field is required.', // Custom message for the NotBlank constraint
                    ]),                    new Length([
                        'min' => 4,
                        'max' => 10,
                        'minMessage' => 'The categevent must be at least {{ limit }} characters long',
                        'maxMessage' => 'The categevent cannot be longer than {{ limit }} characters',
                    ]),
                ],
            ])
        ;
    }

    public function configureOptions(OptionsResolver $resolver): void
    {
        $resolver->setDefaults([
            'data_class' => Categorie::class,
        ]);
    }
}
