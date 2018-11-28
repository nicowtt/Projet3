package com.ocr.nicolas;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.*;

public class SearchNumber extends Common {

    Scanner sc = new Scanner(System.in);

    private int countWin;

    public SearchNumber(int nbrDigit, int nbrOfTry, String developerMode, int countWin) {
        super(nbrDigit, nbrOfTry, developerMode);
        this.countWin = countWin;
    }

    public int getCountWin() {return countWin;}

    static final Logger logger = LogManager.getLogger();


    /**
     * For playing SearchNumber game
     *
     * @return playing
     */
    public void playSearchNumber() {

        // Affichage du menu du type de jeux.
        MenuDisplay display = new MenuDisplay();
        display.displayAskTypeOfGame();

        // Recuperation variable du type de jeux
        int gameTypeChoice = display.displayGameTypeChoice();

        // objet searchNumber Challenger Mode
        SearchNumberChallenger searchNumberChallenger = new SearchNumberChallenger(getNbrDigit(), getNbrOfTry(), getDeveloperMode(), getCountWin());
        SearchNumberDefender searchNumberDefender = new SearchNumberDefender(getNbrDigit(),getNbrOfTry(),getDeveloperMode(), getCountWin());


        do
            switch (gameTypeChoice) {
            case 1:
                while (gameTypeChoice == 1) {
                    searchNumberChallenger.playChallengerMode();
                    display.displayAskTypeOfGame();
                    gameTypeChoice = display.displayGameTypeChoice();
                    break;
                }
            case 2:
                while (gameTypeChoice == 2) {
                    searchNumberDefender.playDefenderMode();
                    display.displayAskTypeOfGame();
                    gameTypeChoice = display.displayGameTypeChoice();
                    break;
                }
            case 3:
                while (gameTypeChoice == 3) {
                    //searchNumberChallenger.playDuelMode();
                    break;
                }
            default:
                break;
        }while (gameTypeChoice == 1 || gameTypeChoice == 2 || gameTypeChoice == 3);
    }


    /**
     * for make random computer number(s) combination
     *
     * @param nbrDigit digit number(take on config.properties)
     * @return random digit number (type of character string)
     */
    public String computerNbrCombination(int nbrDigit, int min, int max) {

        String finalRandomDigitNumberString = "";
        int counterNbrDigit = nbrDigit;

        while (counterNbrDigit >= 1) {

            //int de 0 a 9 (base 10) avec min et max
            int base10RandomDigitNumber = min + (int) (Math.random() * ((max - min) + 1));
            //logger.info("base 10 random =" + base10RandomDigitNumber);

            // je converti le chiffre en string
            String base10RandomDigitNumberString = String.valueOf(base10RandomDigitNumber);

            // Je l'ajoute au string computer final
            finalRandomDigitNumberString = finalRandomDigitNumberString + base10RandomDigitNumberString;

            // j'incremente le compteur
            counterNbrDigit--;
        }
        return finalRandomDigitNumberString;
    }


    /**
     * For Compare 2 String (with number(s) inside) result -> (+--=) for exemple
     *
     * @param computer Computeur string (with number(s) inside)
     * @param user     User string (with number(s) inside)
     */
    public String CompareTwoString(String computer, String user) {


        // Je converti la chaine de caractere computeur en arraylist de integer -> computerArrayListInt
        List<Integer> computerArrayListInt = stringToArrayList(computer);

        // Je converti la chaine de caractere utilisateur en arraylist de integer -> userArrayListInt
        List<Integer> userArrayListInt = stringToArrayList(user);

        // Je compare les deux arrayLists
        String compareListString = compareTwoArrayList(userArrayListInt, computerArrayListInt); //-> compareListString

        //logger.info("resultat de la comparaison = " + compareListString);

        return compareListString;
    }


    /**
     * For compare Two Array List (with number(s) inside)
     *
     * @param userArrayListInt     User Array List (number inside )
     * @param computerArrayListInt Computer Array List (number inside)
     * @return String with + , - or =
     */
    public String compareTwoArrayList(List<Integer> userArrayListInt, List<Integer> computerArrayListInt) {

        // mise en place d'un compteur de String "=" (indication si gagnant) et d'une variable (0 = perdant, 1= gagnant)
        int counterForSeeEgal = 0;
        int counterForWin = 0;

        // creation d'une nouvelle list pour les resultat (+--+)
        List<String> resultWithIndicationList = new ArrayList<>();

        for (int i = 0; i < computerArrayListInt.size(); i++) {
            int nbrComputerForCompare = computerArrayListInt.get(i);
            int nbrUserForCompare = userArrayListInt.get(i);

            if (nbrComputerForCompare > nbrUserForCompare) {
                resultWithIndicationList.add("+");
            } else if (nbrComputerForCompare < nbrUserForCompare) {
                resultWithIndicationList.add("-");
            } else {
                resultWithIndicationList.add("=");
                counterForSeeEgal += 1; // pour savoir combien il y a d'egal lorsque la methode est lancé.
            }
        }

        // Si il a autant d'egal que de digit je signale qu'on a un gagnant
        if (counterForSeeEgal == computerArrayListInt.size()) {
            counterForWin = 1;
        }

        //j'exporte l'information pour savoir si il y a gagnant
        countWin = counterForWin;

        // je converti cette liste en string
        String compareListString = "";

        for (int i = 0; i < computerArrayListInt.size(); i++) {
            String oneString = resultWithIndicationList.get(i);
            compareListString = compareListString + oneString;
        }
        return compareListString;
    }


    /**
     * For convert String to Array List (integer inside)
     *
     * @param inputString String for convert
     * @return List of integer
     */
    public List<Integer> stringToArrayList(String inputString) {

        List<Integer> result = new ArrayList<>();
        for (int i = 0; i < inputString.length(); i++) {
            char letter = inputString.charAt(i);
            String letterString = String.valueOf(letter);
            int letterInt = Integer.parseInt(letterString);
            result.add(letterInt);
        }
        return result;

    }

    /**
     * For convert String to Array List (String inside)
     *
     * @param inputStr String for convert
     * @return List of integer
     */
    public List<String> stringToArrayListString(String inputStr) {

        //creation nouvelle ArrayList
        List<String> result = new ArrayList<>();

        for (int i = 0; i < inputStr.length(); i++) {
            char letter = inputStr.charAt(i);
            String letterString = String.valueOf(letter);
            result.add(letterString);
        }
        return result;

    }

    /**
     * For create a hashMap with increment key (nbrdigit)
     *
     * @param pNbrDigit
     * @return hashMap with base infomation for each digit
     */
    public Map<String, Integer> createHashMapBase(int pNbrDigit) {

        int countDigit = 0;

        int valueMin = 0;
        int valueMax = 9;

        // creation d'une arrayList avec le nombre de digit en String
        List<String> nbrDigitList = new ArrayList<>();


        // creation d'un hashMap
        Map<String, Integer> completeHashMapBase = new HashMap<String, Integer>();

        //j'incremente le String refinedMin, max et digit ok (grâce à l'ArrayList nbr DigitList) et je le rajoute a la hashMap
        for (int j = 0; j < pNbrDigit; j++) {
            nbrDigitList.add(String.valueOf(countDigit));

            String refinedMin = "refinedMin";
            refinedMin = refinedMin + nbrDigitList.get(j);
            completeHashMapBase.put(refinedMin, valueMin);

            String refinedMax = "refinedMax";
            refinedMax = refinedMax + nbrDigitList.get(j);
            completeHashMapBase.put(refinedMax, valueMax);

            String digitOk = "digitOk";
            digitOk = digitOk + nbrDigitList.get(j);
            completeHashMapBase.put(digitOk, 10);


            countDigit++;
        }
        return completeHashMapBase;

    }

    /**
     * for put information of each digit on Hashmap (!!!! in progress !!!!
     *
     * @param pHashMap old hashMap
     * @param pFirst
     * @param pValue
     * @return hashmap updated with new limit information
     */
    public Map<String, Integer> infoDigitForRefinedToHahMap(Map<String, Integer> pHashMap, List<Integer> pFirst, List<String> pValue) {

        //variable locales
        int digitInt = 0;
        int digitListNewInt = 0;
        String digitString = "";
        String digitStringHashMap = "";
        String valueString = "";

        int digitMin = 0;
        int digitMax = 9;
        int digitMinRefine = 0;
        int digitMaxRefine = 9;
        int digitCompOk = 0;


        //creation d'une nouvelle hasMap refined
        Map<String, Integer> hashMapRefine = new HashMap<>();

        for (int i = 0; i < pFirst.size(); i++) {
            digitListNewInt = pFirst.get(i);
            valueString = pValue.get(i);

            if (valueString.contains("+")) {
                if (pHashMap.containsKey("refinedMin" + i)) {
                    digitInt = pHashMap.get("refinedMin" + i);
                    if (digitInt > digitMin) {
                        digitMinRefine = digitInt;
                        hashMapRefine.put("refinedMin" + i, digitMinRefine);
                    }
                    if (digitListNewInt > digitMinRefine) {
                        hashMapRefine.put("refinedMin" + i, digitListNewInt);
                    }
                    digitInt = 0;
                    digitListNewInt = 0;
                } else {
                    hashMapRefine.put("refinedMin" + i, 0);
                    digitInt = 0;
                }
            }
            if (valueString.contains("-")) {
                if (pHashMap.containsKey("refinedMax" + i)) {
                    digitInt = pHashMap.get("refinedMax" + i);
                    if (digitInt < digitMax) {
                        digitMinRefine = digitInt;
                        hashMapRefine.put("refinedMax" + i, digitMaxRefine);
                    }
                    if (digitListNewInt < digitMaxRefine) {
                        hashMapRefine.put("refinedMax" + i, digitListNewInt);
                    }
                    digitInt = 0;
                    digitListNewInt = 0;
                } else {
                    hashMapRefine.put("refinedMin" + i, 0);
                    digitInt = 0;
                }
            }
            if (valueString.contains("=")) {hashMapRefine.put("digitOk" + i, digitListNewInt);}
        }
        return hashMapRefine;
    }


    /**
     * For create a good string of value ("+-=")
     *
     * @return String "+-="
     */
    public String inputValueUserToString() {

        //creation compteur pour comparer au nombre de digit
        int count = 0;

        boolean responseIsGood;

        String resultString = "";

        do {
            // creation nouvelle ArrayList
            List<String> result = new ArrayList<>();

            //je recupere la valeur user
            String userValueString = sc.next();

            for (int i = 0; i < userValueString.length(); i++) {
                char letter = userValueString.charAt(i);
                String letterString = String.valueOf(letter);
                result.add(letterString);
                count++;
            }

            if (count < getNbrDigit() || count > getNbrDigit()) {
                System.out.println("vous avez rentré trop ou pas assez de valeur, veuillez re-essayer:");
                count = 0;
                responseIsGood = false;
            } else {
                responseIsGood = true;

                // je converti la array list en string
                for (int j = 0; j < userValueString.length(); j++) {
                    resultString = resultString + result.get(j);
                }

            }

        } while (responseIsGood == false);

        return resultString;
    }



    /**
     * for dichotomous Research
     * fonction de recherche dichotomique qui renvoie un indice où se trouve la valeur "val" si elle est dans le tableau "tab[]" et -1 si cette valeur n'y est pas
     *
     * @param pNbrMin min value
     * @param pNbrMax max value
     * @param pNbrOut value out
     * @return
     */
    public void dichotomousResearch(int pNbrMin, int pNbrMax, int pNbrOut) {

        // declaration du tableau
        int[] tab10 = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9};

        // declaration des variables locale à la methode
        boolean find;  //vaut faux tant que la valeur "val" n'aura pas été trouvée
        int id;  //indice de début
        int ifin;  //indice de fin
        int im;  //indice de "milieu"*

        //initialisation de ces variables avant la boucle de recherche
        find = false;  //la valeur n'a pas encore été trouvée
        id = pNbrMin;  //intervalle de recherche compris entre pNbrMin...
        ifin = pNbrMax;  //...et pNbrMax

        // boucle de recherche
        while (!find && ((ifin - id) > 1)) {

            im = (id + ifin) / 2;  //on détermine l'indice de milieu

            find = (tab10[im] == pNbrOut);  //on regarde si la valeur recherchée est à cet indice

            if (tab10[im] > pNbrOut)
                ifin = im;  //si la valeur qui est à la case "im" est supérieure à la valeur recherchée, l'indice de fin "ifin" << devient >> l'indice de milieu, ainsi l'intervalle de recherche est restreint lors du prochain tour de boucle
            else
                id = im;  //sinon l'indice de début << devient >> l'indice de milieu et l'intervalle est de la même façon restreint
        }

        // test conditionnant la valeur que la fonction va renvoyer
        //if (tab10[id] == pNbrOut) return (id);  //si on a trouvé la bonne valeur, on retourne l'indice
        //else return (-1);  //sinon on retourne -1
    }
}






