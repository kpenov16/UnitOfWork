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

    private static final int NUMBER_OF_ROLLS = 60_000;
    private static final double FRAC_DIVIATION = 0.2;
    private static int NUMBER_DICE_SIDES = 6;
    private static String[] DICE_SIDES = new String[]{"1","2","3","4","5","6"};
    public static final int BASE_EXPECTED_OCCURRANCES = NUMBER_OF_ROLLS / NUMBER_DICE_SIDES;
    public static final int DEVIATION = (int)(BASE_EXPECTED_OCCURRANCES * FRAC_DIVIATION);
    private static final String STR_TWO = "2";
    public static final String STR_ONE = "1";
    //private static String DICE_SIDES = "1,2,3,4,5,6";

    private Dice dice;

    @BeforeEach
    void setUp() {
        dice = new Dice();

    }

    @AfterEach
    void tearDown() {
    }

    @Test
    public void givenCertainNumberOfRolls_returnOnlyValidNumbersOccurrence(){
        assertTrue( eachRollIsValidNumber(NUMBER_OF_ROLLS) );
    }

    private boolean eachRollIsValidNumber(int numberOfRolls) {
        //List<String> validNumbers = new ArrayList<>( Arrays.asList(strValidNumbers.split("\\s*,\\s*")) );
        List<String> validNumbers = new ArrayList<>( Arrays.asList(DICE_SIDES) );
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
    public void givenBaseLineAndDeviation_returnEachOccurrenceWithinLimits() {
        String allRolls = "";
        for (int i = 0; i < NUMBER_OF_ROLLS; i++)
            allRolls += dice.roll();

        for(String diceSide : DICE_SIDES)
            assertOccurrenceMatchExpectedRange(diceSide, allRolls);

        //region Playing with general solution using standard deviation
        int occurrenceOf1 = getOccurrence("1", allRolls);
        int occurrenceOf2 = getOccurrence("2", allRolls);
        int occurrenceOf3 = getOccurrence("3", allRolls);
        int occurrenceOf4 = getOccurrence("4", allRolls);
        int occurrenceOf5 = getOccurrence("5", allRolls);
        int occurrenceOf6 = getOccurrence("6", allRolls);

        double mean = (occurrenceOf1 + occurrenceOf2 + occurrenceOf3 + occurrenceOf4 + occurrenceOf5 + occurrenceOf6) / 6;
        int meanAsInt = (int) mean;
        //System.out.println("mean : " + mean);
        double averageMeanOf1 = Math.pow((occurrenceOf1 - mean), 2);
        double averageMeanOf2 = Math.pow((occurrenceOf2 - mean), 2);
        //System.out.println("averageMeanOf2 : " + averageMeanOf2 + "pow of: " + (occurrenceOf2 - mean));
        double averageMeanOf3 = Math.pow((occurrenceOf3 - mean), 2);
        double averageMeanOf4 = Math.pow((occurrenceOf4 - mean), 2);
        double averageMeanOf5 = Math.pow((occurrenceOf5 - mean), 2);
        double averageMeanOf6 = Math.pow((occurrenceOf6 - mean), 2);
        double sqredAvrMean = (averageMeanOf1 + averageMeanOf2 + averageMeanOf3 + averageMeanOf4 + averageMeanOf5 + averageMeanOf6) / 6;
        //System.out.println("sqredAvrMean : " + sqredAvrMean);
        double stdDev = Math.sqrt(sqredAvrMean);
        System.out.println("standard diviation : " + stdDev);
        System.out.println("1 : " + occurrenceOf1 + " \n" +
                "2 : " + occurrenceOf2 + " \n" +
                "3 : " + occurrenceOf3 + " \n" +
                "4 : " + occurrenceOf4 + " \n" +
                "5 : " + occurrenceOf5 + " \n" +
                "6 : " + occurrenceOf6 + " \n");

        assertOccurrenceMatchExpectedRange("6", allRolls);
        assertOccurrenceMatchExpectedRange("5", allRolls);
        assertOccurrenceMatchExpectedRange("4", allRolls);
        assertOccurrenceMatchExpectedRange("3", allRolls);
        assertOccurrenceMatchExpectedRange("2", allRolls);
        assertOccurrenceMatchExpectedRange("1", allRolls);
        //endregion
    }

    private int getOccurrence(String occurrenceOf, String rolls){
        Pattern pattern = Pattern.compile(occurrenceOf);
        Matcher matcherOne = pattern.matcher(rolls);
        int countOccurrences = 0;
        while (matcherOne.find())
            countOccurrences++;
        return countOccurrences;
    }

    private void assertOccurrenceMatchExpectedRange(String occurrenceOf, String rolls) {
        Pattern pattern = Pattern.compile(occurrenceOf);
        Matcher matcherOne = pattern.matcher(rolls);
        int countOccurrences = 0;
        while (matcherOne.find())
            countOccurrences++;

        //System.out.println(rolls);
        //System.out.println(countOccurrences);

        assertTrue(countOccurrences >= (BASE_EXPECTED_OCCURRANCES - DEVIATION)
                         && countOccurrences <= (BASE_EXPECTED_OCCURRANCES + DEVIATION)
                  );
    }

}