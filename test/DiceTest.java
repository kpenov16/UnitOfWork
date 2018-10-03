import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import terning.Dice;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static org.junit.jupiter.api.Assertions.*;

class DiceTest {
/*
* Lav en JUnit test i IntelliJ der tester terningen.
* Den skal teste at terningen ikke kan give andre værdier end 1-6,
* samt at alle seks værdier forekommer lige hyppigt indenfor en passende fejlmargin.
* For eksempel:
* - hvis I kaster terningen 60000 gange skal hver værdi forekomme ca. 10000 gange ± 400.
* Tilføj JUnit test filerne til jeres repository på github.
* Lav en Branch med navnet BugFixes i jeres repository.
* Ret de fejl I har fundet i terning-programmet og læg dem i BugFixes vha. Commit.
* Lav en Pull Request der anmoder om at indsætte rettelserne i jeres master branch.
* */
    public static final String STR_ONE = "1";
    public static final int DEVIATION = 400;
    public static final int BASE_EXPECTED_OCCURRANCES = 12_000;
    private static final String STR_TWO = "2";
    private Dice dice;
    private int numberOfRolls;

    @BeforeEach
    void setUp() {
        dice = new Dice();
        numberOfRolls = 60_000;
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    public void givenCertainNumberOfRolls_returnOnlyValidNumbersOccurre(){
        assertTrue( eachRollIsValidNumber( numberOfRolls, "1,2,3,4,5,6" ) );
    }

    private boolean eachRollIsValidNumber(int numberOfRolls, String strValidNumbers) {
        List<String> validNumbers = new ArrayList<>( Arrays.asList(strValidNumbers.split("\\s*,\\s*")) );
        boolean allRollsValid = true;
        for(int i = 0; i < numberOfRolls; i++){
            String currentRoll = String.valueOf( dice.roll() );
            if(!isCurrentRollFound(validNumbers, currentRoll)){
                allRollsValid = false;
                break;
            }
        }
        return allRollsValid;
    }

    private boolean isCurrentRollFound(List<String> validNumbers, String currentRoll) {
        boolean currentRollFound = false;
        for(String num : validNumbers){
            if(num.equals(currentRoll)){
                currentRollFound = true;
                break;
            }
        }
        return currentRollFound;
    }

    @Test
    public void given60000Rolls_returnAbout10000TimesPerSide(){

        String allRolls = "";
        for(int i = 0; i < numberOfRolls; i++){
            allRolls += dice.roll();
        }

        int occurrenceOf1 = getOccurrence("1", allRolls);
        int occurrenceOf2 = getOccurrence("2", allRolls);
        int occurrenceOf3 = getOccurrence("3", allRolls);
        int occurrenceOf4 = getOccurrence("4", allRolls);
        int occurrenceOf5 = getOccurrence("5", allRolls);
        int occurrenceOf6 = getOccurrence("6", allRolls);

        System.out.println("1 : " + occurrenceOf1 + " \n" +
                           "2 : " + occurrenceOf2 + " \n" +
                           "3 : " + occurrenceOf3 + " \n" +
                           "4 : " + occurrenceOf4 + " \n" +
                           "5 : " + occurrenceOf5 + " \n" +
                           "6 : " + occurrenceOf6 + " \n" );



        assertOccurrenceMatchExpectedRange("6", allRolls,
                  BASE_EXPECTED_OCCURRANCES, DEVIATION);

        assertOccurrenceMatchExpectedRange("5", allRolls,
                BASE_EXPECTED_OCCURRANCES, DEVIATION);

        assertOccurrenceMatchExpectedRange("4", allRolls,
                BASE_EXPECTED_OCCURRANCES, DEVIATION);

       assertOccurrenceMatchExpectedRange("3", allRolls,
               BASE_EXPECTED_OCCURRANCES, DEVIATION);

        assertOccurrenceMatchExpectedRange("2", allRolls,
                BASE_EXPECTED_OCCURRANCES, DEVIATION);

        assertOccurrenceMatchExpectedRange("1", allRolls,
                                           BASE_EXPECTED_OCCURRANCES, DEVIATION);

    }

    private int getOccurrence(String occurrenceOf, String rolls){
        Pattern pattern = Pattern.compile(occurrenceOf);
        Matcher matcherOne = pattern.matcher(rolls);
        int countOccurrences = 0;
        while (matcherOne.find())
            countOccurrences++;
        return countOccurrences;
    }

    private void assertOccurrenceMatchExpectedRange(String occurrenceOf, String rolls, int baseExpectedOccurrances, int deviation) {
        Pattern pattern = Pattern.compile(occurrenceOf);
        Matcher matcherOne = pattern.matcher(rolls);
        int countOccurrences = 0;
        while (matcherOne.find())
            countOccurrences++;

        System.out.println(rolls);
        System.out.println(countOccurrences);

        assertTrue(countOccurrences >= (baseExpectedOccurrances - deviation)
                         && countOccurrences <= (baseExpectedOccurrances + deviation)
                  );
    }

}