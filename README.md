# Jeux de logique

Voici deux jeux de logique:
- Search Number
- Mastermind

## Description

**Le but ?**
 
Search number :

Découvrir la combinaison à x chiffres de l'adversaire (le défenseur). Pour ce faire, l'attaquant fait une proposition. Le défenseur indique pour chaque chiffre de la combinaison proposée si le chiffre de sa combinaison est plus grand (+), plus petit (-) ou si c'est le bon chiffre (=).

    Choisi une combinaison de 2 chiffre(s):
    74
    +=
    -> ce n'est pas la bonne combinaison !
  
Mastermind:

Découvrir la combinaison à x chiffres(couleurs) de l'adversaire (le défenseur). Pour ce faire, l'attaquant fait une proposition. Le défenseur indique pour chaque proposition le nombre de chiffre(couleur) de la proposition qui apparaissent à la bonne place et à la mauvaise place dans la combinaison secrète.

    0444
    2 bien placé(s), 1 present(s) 
    1 essai(s) restant.

Pour chaque jeux, l'attaquant doit deviner la combinaison secrète en un nombre limité d'essais.

## Mode de jeux

Chaque jeux pourra être joués selon 3 modes :

- Mode challenger où vous devez trouver la combinaison secrète de l'ordinateur

- Mode défenseur où c'est à l'ordinateur de trouver votre combinaison secrète

- Mode duel où l'ordinateur et vous jouez tour à tour, le premier à trouver la combinaison secrète de l'autre a gagné

-------- a faire clone et ouvre dans intellij

## Réglages

Un fichier de configuration (config.properties) permet de paramétrer l'application, notamment :

Pour chaque jeu :

- le nombre de cases de la combinaison secrète
- le nombre d'essais possibles
 
Pour le Mastermind :

- le nombre couleur(chiffre) utilisables (de 4 à 10)

## Contribution

1: clone repositories

2: Créer une nouvelle branche

2: Faite vos évolutions / changements (git checkout -b my-new-feature)

3: "Commit" les évolutions / changements (git commit -am "add some feature")

4: "Push" la nouvelle branche (git push origin my-new-feature)

5: Créer une nouvelle "pull request"

## Auteur
Nicow

Voir mes autres projets :
[ICI](https://github.com/nicowtt?tab=repositories)

*twitter: nicow@nicowtt*


