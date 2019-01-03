package com.ocr.nicolas.Mastermind;

import com.ocr.nicolas.Config;
import com.ocr.nicolas.Games;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.*;

public abstract class Mastermind extends Games {

    protected boolean iswin;
    protected List<String> listCombinationRemainingexport;
    protected int goodplaceExport;
    protected int presentExport;
    protected boolean noMoreTry;

    Scanner sc = new Scanner(System.in);

    static final Logger logger = LogManager.getLogger();

    public Mastermind(Config config) {
        super(config);
    }

    /**
     * For create ArrayList with number ok in
     *
     * @return ArrayList (Integer)
     */
    protected List<Integer> createListNbrOk() {

        //creation d'une list avec les chiffres possible
        List<Integer> listNbrOk = new ArrayList<>();

        //je met les chiffres ok a l'interieur
        for (int i = 0; i < (nbrMaxOnDigit + 1); i++) {
            listNbrOk.add(i);
        }
        logger.info("liste chiffres ok = " + listNbrOk);

        return listNbrOk;
    }


    /**
     * For force user input combination ok
     *
     * @return user input combination ok (string)
     */

    protected String inputUserStringMast() {

        boolean responseIsGood;
        int nbrNok;
        int countDigitOK;
        int countDigitNok;
        int countNbrNok = 0;
        String nbrOkStr;
        String inputUser;

        // je lance la methode pour avoir la liste des chiffres utilisable
        List<Integer> listNbrOk = this.createListNbrOk();

        // je compte combien ne sont pas ok si je test chaque digit avec chaque digit de ma listeNbrOk. (en fonction du nbrDigit du jeu)
        for (int k = 0; k < listNbrOk.size(); k++) {
            countNbrNok++;
        }
        nbrNok = (countNbrNok * nbrDigit) - nbrDigit;

        do {
            // variable mis ou remis a zero
            countDigitOK = 0;
            countDigitNok = 0;

            // je fais venir l'entrée utilisateur
            inputUser = sc.next();

            // je compare chaque digit avec le tableau de chiffre ok et j'incremente mes compteurs
            for (int i = 0; i < inputUser.length(); i++) {
                char letter = inputUser.charAt(i);
                String letterStr = String.valueOf(letter);
                // je compare avec la liste des nbrOk
                for (int j = 0; j < listNbrOk.size(); j++) {
                    nbrOkStr = String.valueOf(listNbrOk.get(j));
                    if (letterStr.contains(nbrOkStr)) {
                        countDigitOK++;
                    } else {
                        countDigitNok++;
                    }
                }
            }
            if (countDigitOK == nbrDigit && countDigitNok == nbrNok) {
                responseIsGood = true;
            } else {
                System.out.println("Erreur, choisi une combinaison de " + nbrDigit + " chiffre(s) (chaque chiffre entre 0 et " + nbrMaxOnDigit + ")");
                responseIsGood = false;
                logger.info("Mauvaise entrée utilisateur");
            }
        } while (!responseIsGood);
        logger.info("Entrée utilisateur = " + inputUser);
        return inputUser;
    }

    /**
     * For compare two mastermind combination
     *
     * @param pFirst  first combination
     * @param pSecond Second combination
     * @return code value string ( unit = present; decade = goodplace; ex: 11 = 1 good placed et 1 present)
     */
    protected String compareTwoStringMast(String pFirst, String pSecond) {

        //Je crée deux ArrayList avec des booleans
        List<Boolean> listpFirstBoolean = new ArrayList<>();
        List<Boolean> listpSecondBoolean = new ArrayList<>();

        // je crée deux compteur
        int goodplace = 0; // les bien placés
        int present = 0; // les présents

        //je compare pour trouvé les bien placés
        for (int i = 0; i < pFirst.length(); i++) {
            if (pFirst.charAt(i) != pSecond.charAt(i)) {
                listpFirstBoolean.add(false);
                listpSecondBoolean.add(false);
            }
            if (pFirst.charAt(i) == pSecond.charAt(i)) {
                goodplace++;
                listpFirstBoolean.add(true);
                listpSecondBoolean.add(true);
            }
        }
        if (logger.isDebugEnabled()) {
            logger.debug("******************************************************");
            logger.debug("list pFirst boolean bien placé = " + listpFirstBoolean);
            logger.debug("list pSecond boolean bien placé = " + listpSecondBoolean);
        }

        // je compare une seconde fois en prenant en compte les bien placés
        for (int i = 0; i < pFirst.length(); i++) {
            for (int j = 0; j < pSecond.length(); j++) {
                if (!listpFirstBoolean.get(i) && !listpSecondBoolean.get(j) && pFirst.charAt(i) == pSecond.charAt(j)) {
                    present++;
                    // je change la valeur boolean si il trouve une comparaison ok
                    listpFirstBoolean.set(i, true);
                    listpSecondBoolean.set(j, true);
                }
            }
        }
        if (logger.isDebugEnabled()) {
            logger.debug("list pFirst boolean present = " + listpFirstBoolean);
            logger.debug("list pSecond boolean present = " + listpSecondBoolean);
            logger.debug("******************************************************");
        }

        String codeValueString = "";

        // pour une reponse de comparaison (0 goodplace et 0 present)
        if (goodplace == 0 && present == 0) {
            codeValueString = "0";
        }

        // pour une reponse de comparaison uniquement avec des presents
        if (goodplace == 0 && present != 0) {
            codeValueString = String.valueOf(present);
        }

        // pour une reponse de comparaison avec des bien placé(s) et pas de present
        if (goodplace != 0 && present == 0) {
            codeValueString = String.valueOf(goodplace) + "0";
        }

        // pour une reponse de comparaison avec des bien placé(s) et des present(s)
        if (goodplace != 0 && present != 0) {
            codeValueString = String.valueOf(goodplace) + String.valueOf(present);
        }
        goodplaceExport = goodplace;
        presentExport = present;

        //logger.info("valeur code = " + codeValueString);

        return codeValueString;
    }

    /**
     * For interact with User
     *
     * @param pUser User combination
     * @param pComp Comp combination
     */
    protected void interactWithUserMastermind(String pUser, String pComp) {

        //je récupére les bien placé(s) ( en fesant attention a la triche) et je regarde si ordinateur gagne.
        System.out.println("Peux-tu donner le nombre de chiffre(s) bien placé(s):");
        this.goodPlacedNoCheat(pUser, pComp);

        //je récupére les presents (attention a la triche)
        System.out.println("Peux-tu donner le nombre de chiffre(s) présent(s):");
        this.presentNoCheat(pUser, pComp);
    }

    /**
     * For see if user is a winner
     *
     * @param pGoodPlaced Integer with goodPlaces in.
     */
    protected void seeUserWinner(int pGoodPlaced, int pPresent) {

        // j'affiche le resultat selon les précédentes valeurs
        if (pGoodPlaced == nbrDigit) {
            System.out.println("Félicitation tu as trouvé la combinaison !");
            System.out.println("");
            iswin = true;
        }
        if (pGoodPlaced == 0 && pPresent == 0) {
            System.out.println("Aucun bien placé ou present.");
        } else {
            if (!iswin) {
                System.out.println(pGoodPlaced + " bien placé(s), " + pPresent + " present(s) ");
            }
        }
    }

    /**
     * For have a goodPlaced input with no cheat
     *
     * @param pFirst  string for mastermind compare
     * @param pSecond String for mastermind compare
     * @return user input (good placed) whith no cheat
     */
    protected int goodPlacedNoCheat(String pFirst, String pSecond) {

        // variables
        boolean responseIsGood;
        int userInput = 0;
        String userInputStr = "";
        String goodplaceExportStr = "";


        // je check la bonne reponse
        logger.info("verification bien placé(s)");
        this.compareTwoStringMast(pFirst, pSecond);
        goodplaceExportStr = String.valueOf(goodplaceExport);

        do {
            userInputStr = sc.next();
            if (userInputStr.equals(goodplaceExportStr)) {
                responseIsGood = true;
                userInput = Integer.valueOf(userInputStr);
            } else {
                System.out.println("Chiffre bien placé non valable, erreur humaine ou tentative de tricherie? ;-) --> " + goodplaceExport);
                responseIsGood = false;
            }
        } while (!responseIsGood);
        return userInput;
    }

    /**
     * For have a present input with no cheat
     *
     * @param pFirst  string for mastermind compare
     * @param pSecond string for mastermind compare
     * @return user (present) input whith no cheat
     */
    protected int presentNoCheat(String pFirst, String pSecond) {

        //variables
        boolean responseIsGood;
        int userInput = 0;
        String userInputStr = "";
        String presentExportStr = "";

        // je check la bonne réponse
        logger.info("verification présent(s)");
        this.compareTwoStringMast(pFirst, pSecond);
        presentExportStr = String.valueOf(presentExport);

        do {
            userInputStr = sc.next();
            if (userInputStr.equals(presentExportStr)) {
                responseIsGood = true;
                userInput = Integer.valueOf(userInputStr);
            } else {
                System.out.println("Chiffre présent non valable, erreur humaine ou tentative de tricherie? ;-) --> " + presentExport);
                responseIsGood = false;
            }
        } while (!responseIsGood);
        return userInput;
    }

    /**
     * for evaluate code on String of 2 digit ( highter is close of solution )
     * only one Digit (unit) = number of present; decade = number of goodplaced,
     * (ex: for 4 digit and 4 figure possible -> 0,1,2,3,4,10,11,12,13,20,21,22,30,40
     */
    protected String codeValue(String pString) {

        String codeValueString = pString;

        // pour une reponse de comparaison (0 goodplace et 0 present)
        if (goodplaceExport == 0 && presentExport == 0) {
            codeValueString = "0";
        }

        // pour une reponse de comparaison uniquement avec des presents
        if (goodplaceExport == 0 && presentExport != 0) {
            codeValueString = String.valueOf(presentExport);
        }

        // pour une reponse de comparaison avec des bien placé(s) et pas de present
        if (goodplaceExport != 0 && presentExport == 0) {
            codeValueString = String.valueOf(goodplaceExport) + "0";
        }

        // pour une reponse de comparaison avec des bien placé(s) et des present(s)
        if (goodplaceExport != 0 && presentExport != 0) {
            codeValueString = String.valueOf(goodplaceExport) + String.valueOf(presentExport);
        }

        //logger.info("valeur code = " + codeValueString);

        return codeValueString;

    }

    /**
     * for make all combination possible with codeValueParameter
     */
    protected List<String> makeAllCombinationMastermind() {

        String combination = "";

        // je fabrique une array list avec toutes les combinaisons
        List<String> listCombinationTotal = new ArrayList<>();

        // nombre de conbinaison totale
        int combTotal = (int) Math.pow((nbrMaxOnDigit + 1), nbrDigit);
        logger.info("nombre de combinaison totale = " + combTotal);
        // j'enleve 1 pour evité un nombre de digit au dessus de la normale
        combTotal -= 1;

        //je converti chaque nombre de combinaison totale en base (4 si nbrmax on digit = 4)
        do {
            combination = intToBase(combTotal, (nbrMaxOnDigit + 1));
            listCombinationTotal.add(combination);
            combTotal--;

        } while (combTotal > 0);

        // je rajoute le dernier manuellement (le zero)
        listCombinationTotal.add("0");

        // je rajoute les zeros a celle qui n'ont pas assez de digit
        String nbrDigitNok;
        String nbrWithGoodNbrOfDigit;
        for (int i = 0; i < listCombinationTotal.size(); i++) {
            nbrDigitNok = listCombinationTotal.get(i);
            nbrWithGoodNbrOfDigit = completeStringFollowingNbrDigit(nbrDigitNok);
            listCombinationTotal.set(i, nbrWithGoodNbrOfDigit);
        }

        logger.info("tableau des combinaisons = " + listCombinationTotal);

        return listCombinationTotal;
    }

    /**
     * For convert integer on any base
     *
     * @param pEntier Integer
     * @param pBase   integer for convert base
     * @return integer on base "number"
     */
    protected String intToBase(int pEntier, int pBase) {

        int nbr = pEntier;
        int rest;
        String resultTemp = "";
        String resultFinal = "";

        do {
            rest = nbr % pBase;
            resultTemp = resultTemp + rest;
            nbr = nbr / pBase;
        } while (nbr > 0);

        //j'inverse le string resultFinal
        int decompte = resultTemp.length() - 1;
        for (int i = decompte; i >= 0; i--) {
            char digit = resultTemp.charAt(i);
            resultFinal = resultFinal + digit;
        }

        return resultFinal;
    }

    /**
     * for complete combination for have good nbr Of Digit
     *
     * @param pIn integer in
     * @return String with good nbr of digit
     */
    protected String completeStringFollowingNbrDigit(String pIn) {

        //variables
        String nbrStr = pIn;
        String nbrMaxStr = "1";
        String nbrFinalStr = "";

        // je trouve le chiffre minimum avec le nombre de digit
        for (int i = 0; i < (nbrDigit - 1); i++) {
            nbrMaxStr = nbrMaxStr + "0";
        }
        Integer nbrMax = Integer.parseInt(nbrMaxStr);

        // je complete les String avec pas assez de digit

        // je compte le nombre de digit
        int countNbrDigit = 0;

        for (int i = 0; i < nbrStr.length(); i++) {
            countNbrDigit++;
        }
        // il faut don rajouter combien de zero
        int nbrzero = nbrDigit - countNbrDigit;

        // je crée les zero manquant
        for (int i = 0; i < nbrzero; i++) {
            nbrFinalStr = nbrFinalStr + "0";
        }

        // je rajoute mon pIn
        nbrFinalStr = nbrFinalStr + nbrStr;

        return nbrFinalStr;
    }

    /**
     * For create a list with all possible value
     * exemple -> for nbrMaxOnDigit = 4 -> 0,1,2,3,4,10,11,12,13,20,21,22,30,40
     */
    protected List<String> createListOfValue() {

        int nbr = 0;
        int nbrDecade = nbrMaxOnDigit;
        int maxDecade = 0;
        String digit = "";
        int countDecade = 1;


        //creation d'une arrayList avec toutes les valeurs possible
        List<String> listPossibleValues = new ArrayList<>();

        for (int i = 0; i < (nbrMaxOnDigit + 2); i++) { // pour le nombre max sur un digit
            listPossibleValues.add(String.valueOf(nbr));
            nbr++;
        }

        maxDecade = (listPossibleValues.size() - 1);
        int count1 = 0;
        int count2 = 0;

        do {
            for (int i = maxDecade; i > 0; i--) { // pour les dizaines
                digit = String.valueOf(countDecade);
                digit = digit + listPossibleValues.get(count1);
                listPossibleValues.add(digit);
                count1++;
                count2++;
            }
            countDecade++;
            maxDecade--;
            digit = "";
            count1 = 0;

        } while (maxDecade != 0);

        //je dois enlever l'avant dernier (si 3 bien placé, le dernier est forcement non present !!)
        int total = listPossibleValues.size();
        listPossibleValues.remove((total - 2));

        logger.info("liste des valeurs possible dans cette partie = " + listPossibleValues);
        //logger.info("Compteur de valeur possible = " + count2);

        return listPossibleValues;
    }

    /**
     * For create a list of candidates
     *
     * @param pListeCombinaisonTotale combination total
     * @return clone list
     */
    protected List<String> duplicateCombinationForCandidates(List<String> pListeCombinaisonTotale) {
        // je crée une nouvelle liste candidates
        List<String> listCandidates = new ArrayList<>();

        //variables
        String combination = "";

        // je la duplique sur la liste de toute les combinaison
        for (int i = 0; i < pListeCombinaisonTotale.size(); i++) {
            combination = pListeCombinaisonTotale.get(i);
            listCandidates.add(combination);
        }
        return listCandidates;
    }


    /**
     * For make a list with only combination possible after user response
     *
     * @param pListCandidates toutes les combinaison restante
     * @param pComp           computer combination
     * @param pValue          value of comparaison
     */
    protected List<String> sortCombinationPossible(List<String> pListCandidates, String pComp, String pValue) {

        // je crée une list avec les combinaison possible
        List<String> listCombOk = new ArrayList<>();

        // je garde uniquement les combinaisons qui sont de même valeur
        String compare = "";
        int count = 0;

        for (int i = 0; i < pListCandidates.size(); i++) {
            compare = this.compareTwoStringMast(pComp, pListCandidates.get(i)); // je compare les candidate avec la combinaison utilisateur

            if (compare.equals(pValue)) {
                listCombOk.add(pListCandidates.get(i));
                count++;
            }
        }
        logger.info("combinaison(s) restante possible = " + count + "( qui ont la meme valeur de comparaison)");
        //logger.info("Liste avec combinaison restante possible (methode sortCombinationPossible)= " + listCombOk);

        return listCombOk;
    }

    /**
     * For choose an optimal combination
     *
     * @param pListCombination total combination
     */
    protected String optimalCombination(List<String> pListCombination) {
        // pour chaque combinaison de la liste  je compare avec la combinaison de la liste dupliqué et je recupere le poid, je prend la combinaison avec le poid le plus legé (knuth)
        // je crée une liste de valeur
        List<String> listvalue = this.createListOfValue();

        // je crée une list dupliqué
        List<String> listDuplicate = this.duplicateCombinationForCandidates(pListCombination);

        // variable
        Integer count = 0; //poid max
        int nbrTemp = 0;
        int nbrToKeep = 0;
        int valueMinInList = 10000;
        int temp;
        int temp2;

        String combinationFinale = "";

        // variable pour decompte
        int decreasecount = (pListCombination.size() - 1);
        int forvaluelist = 0;

        // combinaison a tester
        int countForComb = 1;

        // list pour stocker les compteurs
        List<Integer> listCount = new ArrayList<>();

        // je crée une hashmap pour stocker la combinaison et ça valeur
        Map<String, Integer> listCombWhithweight = new HashMap<>();

        // je compare chaque combinaison avec chaque combinaison de la même liste dupliqué (en comparant, je compte le nombre de fois qu'une valeur identique sort)
        for (int k = 0; k < pListCombination.size(); k++) {
            String comb1 = pListCombination.get(pListCombination.size() - countForComb);
            for (int j = 0; j < listvalue.size(); j++) { // pour le nombre de valeur
                for (int i = 0; i < pListCombination.size(); i++) { // pour toutes les combinaisons
                    int compareInt = Integer.parseInt(this.compareTwoStringMast(comb1, listDuplicate.get(i))); // je compare avec la liste dupliqué
                    int valueInt = Integer.valueOf(listvalue.get(forvaluelist));
                    comb1 = pListCombination.get(pListCombination.size() - countForComb);
                    String comb2 = listDuplicate.get(i);
                    decreasecount++;
                    if (valueInt == compareInt) {
                        count++;
                    }
                    decreasecount--;
                }
                listCount.add(count);
                count = 0;
                forvaluelist++;
            }
            //logger.info("liste des compteurs = " + listCount);

            // pour chaque combinaison je garde le compteur le plus haut (c'est le "poids")
            for (int i = 0; i < listCount.size(); i++) {
                nbrTemp = listCount.get(i);
                if (nbrTemp >= nbrToKeep) {
                    nbrToKeep = nbrTemp;
                }
            }
            //logger.info(" je garde le nombre = " + nbrToKeep);

            // je met la combinaison et le poids dans la hashmap
            listCombWhithweight.put(comb1, nbrToKeep);
            //logger.info("combinaison et poids = " + listCombWhithweight);

            // j'incremente
            countForComb++;
            forvaluelist = 0;
            listCount.clear();
        }

        // il faut prendre la premiere combinaison avec le plus faible poids (knuth)
        // je crée une liste avec les Values de la hash map
        List<Integer> listValueInHashMap = new ArrayList<>();
        // je crée une liste avec les key de la hashMap
        List<String> listCombinationRemaining = new ArrayList<>();
        // iterator pour lire au fur et a mesure la hash map vers les nouvelles listes crée.
        Iterator iterator = listCombWhithweight.entrySet().iterator();

        while (iterator.hasNext()) {
            Map.Entry entry = (Map.Entry) iterator.next();
            String keyHashMap = (String) entry.getKey();
            Integer valueHashMap = (Integer) entry.getValue();
            listValueInHashMap.add(valueHashMap);
            listCombinationRemaining.add(keyHashMap);
        }
        logger.info(" liste avec les valeurs des combinaisons restantes = " + listCombinationRemaining);
        logger.info(" liste avec le poids de chaque combinaison = " + listValueInHashMap);

        // j'exporte la liste avec les combinaison restante
        listCombinationRemainingexport = listCombinationRemaining;

        // je regarde la valeur mini dans cette liste
        for (int i = 0; i < listValueInHashMap.size(); i++) {
            temp = listValueInHashMap.get(i);
            if (temp < valueMinInList) {
                valueMinInList = temp;
            }
        }
        // je capte la premiere combinaison avec le poids mini dans la hashMap.
        for (int i = 0; i < listValueInHashMap.size(); i++) {
            temp2 = listValueInHashMap.get(i);
            if (temp2 == valueMinInList) {
                combinationFinale = listCombinationRemaining.get(i);
                break;
            }
        }
        logger.info("Proposition grâce a l'algo de knuth (premiere combinaison avec le poids mini) = " + combinationFinale);
        return combinationFinale;
    }

    /**
     * For check is computer win and stop if computer doesn't have enough try.
     *
     * @param pUser user combination
     * @param pComp computeur combination
     * @param pLoop
     * @return
     */
    protected int checkIfComputerWin(String pUser, String pComp, int pLoop) {

        int nbrLoopMastDefend = pLoop;
        noMoreTry = false;

        if (pUser.equals(pComp)) {
            System.out.println("");
            System.out.println("l'ordinateur a gagné en " + pLoop + " coup(s) !!");
            logger.info("l'ordinateur a gagné en " + pLoop + " coup(s)");
            nbrLoopMastDefend = nbrOfTry;
            iswin = true;
        }

        if (!iswin) {
            if (nbrLoopMastDefend >= nbrOfTry) {
                nbrLoopMastDefend = nbrOfTry;
                iswin = true;
                noMoreTry = true;
            }
        }

        return nbrLoopMastDefend;
    }

    /**
     * for create first combination with Knuth algo on defender mode.
     *
     * @param pUser user combination
     * @param pComp comp random combination
     * @return optimal combination (knuth algo)
     */
    protected String firstMethodCreateKnuthCombination(String pUser, String pComp) {

        //variables
        String firstCompareValue = "";
        List<String> afterFirstUserResponse;
        String compDefendMastKnuthStr = "";

        // je compare les deux combinaisons (user avec ordinateur) pour obtenir la valeur ( unité = present, dizaine = bien placé)
        firstCompareValue = this.compareTwoStringMast(pUser, pComp);
        logger.info("valeur comparaison (dizaine = nbr de bien placé, unité = nbr de present) = " + firstCompareValue);

        // interaction avec l'utilisateur
        interactWithUserMastermind(pUser, pComp);

        //je crée toutes les combinaisons possible
        List<String> listCombinationTotale = this.makeAllCombinationMastermind();

        //je fais une liste affiné avec la nouvelle liste des combinaisons + value
        afterFirstUserResponse = this.sortCombinationPossible(listCombinationTotale, pComp, firstCompareValue);

        // je crée la combinaison optimale (knuth)
        compDefendMastKnuthStr = this.optimalCombination(afterFirstUserResponse);

        return compDefendMastKnuthStr;
    }

    /**
     * for create second and next combination with Knuth algo.
     *
     * @param pUser User combination
     * @param pComp Computeur combination
     * @return optimal combination with Knuth algo.
     */
    protected String secondAndNextMethodCreateKnuthCombination(String pUser, String pComp) {

        //Variables
        String valueInLoop = "";
        List<String> combinationRemaining;
        List<String> afterUserResponse;
        String compDefendMastKnuthStr = "";

        // interaction avec l'utilisateur
        interactWithUserMastermind(pUser, pComp);

        //je recupere la valeur et je m'en sert pour faire une nouvelle liste de combinaison
        valueInLoop = this.codeValue(pComp);
        logger.info("valeur de comparaison dans la boucle = " + valueInLoop);

        // je recupere la liste des combinaison possible
        combinationRemaining = listCombinationRemainingexport;

        //je refais une liste avec la nouvelle liste des combinaisons + value
        afterUserResponse = this.sortCombinationPossible(combinationRemaining, pComp, valueInLoop);
        logger.info("liste combinaison restante dans cette boucle = " + afterUserResponse);

        // je crée la combinaison optimale (knuth)
        compDefendMastKnuthStr = this.optimalCombination(afterUserResponse);

        return compDefendMastKnuthStr;
    }

    /**
     * for create first combination with Knuth algo on duel mode.
     *
     * @param pUser user combination.
     * @param pComp Computeur combination
     * @return optimal combination (knuth algo)
     */
    protected String firstMethodCreateKnuthCombinationDuelMode(String pUser, String pComp) {

        //Variables
        String compDefendMastKnuthStr = "";
        String firstCompareValue = "";
        List<String> afterFirstUserResponse;


        // je compare les deux combinaisons (user avec ordinateur) pour obtenir la valeur ( unité = present, dizaine = bien placé)
        firstCompareValue = this.compareTwoStringMast(pUser, pComp);
        logger.info("valeur comparaison (dizaine = nbr de bien placé, unité = nbr de present) = " + firstCompareValue);

        //je crée toutes les combinaisons possible
        List<String> listCombinationTotale = this.makeAllCombinationMastermind();

        //je fais une liste affiné avec la nouvelle liste des combinaisons + value
        afterFirstUserResponse = this.sortCombinationPossible(listCombinationTotale, pComp, firstCompareValue);

        // je crée la combinaison optimale (knuth)
        compDefendMastKnuthStr = this.optimalCombination(afterFirstUserResponse);

        //je l'affiche
        System.out.println(compDefendMastKnuthStr);

        return compDefendMastKnuthStr;
    }

}
















