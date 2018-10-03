import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import terning.Dice;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static org.junit.jupiter.api.Assertions.*;

class DiceTest {

    public static final String STR_ONE = "1";
    public static final int DEVIATION = 400;
    public static final int BASE_EXPECTED_OCCURRANCES = 12_000;
    private static final String STR_TWO = "2";

    @BeforeEach
    void setUp() {

    }

    @AfterEach
    void tearDown() {
    }

    @Test
    public void given60000Rolls_returnAbout10000TimesPerSide(){
        Dice dice = new Dice();
        String allRolls = "";
        int n = 60000;
        for(int i = 0; i < n; i++){
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