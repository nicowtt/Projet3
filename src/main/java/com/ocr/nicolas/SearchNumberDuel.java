package com.ocr.nicolas;

public class SearchNumberDuel extends SearchNumber{


    public SearchNumberDuel(int nbrDigit, int nbrOfTry, String developerMode) {
        super(nbrDigit, nbrOfTry, developerMode);
    }

    public void playDuelMode() {

        //qui commence? pile ou face
        this.headsOrTails();




        //todo demande qui joue en premier -> ordinateur ou user (question 3 au mentor, j'ai une preference pour commencement par ordi ça simplifi mon code ->)
        //todo methode pile ou face pour savoir qui joue en premier??
        //todo demande une combinaison utilisateur (mode Defender)
        //todo creation d'une combinaison aléatoire ordinateur (mode Challenger)
        //todo annonce le nombre global d'essai possible

        //todo: do

        //todo: -> premier essai
        //todo defender 1: creation d'une hashMap base avec les limite Max, Min et digitok incrementé par digit.
        //todo defender 1: premier jet computeur  uniquement des "5" et affichage sur la console
        //todo challenger 1: Je lance la comparaison et j'affiche le resultat.
        //todo challenger 1: je verifie si le Mode developper a été demandé
        //todo verification si il y a un gagnant du premier coup

        //todo: -> deuxieme essai computeur (et user (si il y a choix du commencement au debut))
        //todo defender 2: lancement des nouveaux DichotomousDigits computeur avec les infos de la hashmapRefined
        //todo defender 2: j'affiche le nouvel essai computeur
        //todo verification si gagnant
        //todo challenger 2 : J'affiche la demande de nombre utilisateur et recupere la valeur (si il y a choix du commencement au debut)
        //todo challenger 2 : Je lance la comparaison et j'affiche le resultat. (si il y a choix du commencement au debut)
        //todo challenger 2 : je verifie si le Mode developper a été demandé (si il y a choix du commencement au debut)
        //todo verification si gagnant

        //todo  while -> essai en boucle a partir d'ici

        //todo challenger 3 et suivant: J'affiche la demande de nombre utilisateur et recupere la valeur
        //todo challenger 3 et suivant: Je lance la comparaison et j'affiche le resultat.
        //todo challenger 3 et suivant: je verifie si le Mode developper a été demandé
        //todo verification si gagnant

        //todo defender 3 et suivant: j'affiche la demande de valeur
        //todo defender 3 et suivant: je check si erreur ou tricherie
        //todo defender 3 et suivant: je fais des nouveaux chiffres computer avec les  nouvelles valeurs
        //todo defender 3 et suivant:  j'affiche le nouvel essai computeur
        //todo verification si gagnant





        //replay
        this.replay();
    }
}
