import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import terning.Dice;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static org.junit.jupiter.api.Assertions.*;

class DiceTest {

    @BeforeEach
    void setUp() {

    }

    @AfterEach
    void tearDown() {
    }

    @Test
    public void given60000Rolls_returnAbout10000TimesPerSide(){
        Dice dice = new Dice();
        String rolls = "";
        int baseExpectedOccurrances = 10_000;
        int deviation = 400;
        int n = 60000;
        for(int i = 0; i < n; i++){
            rolls += dice.roll();
        }

        Pattern pattern = Pattern.compile("1");
        Matcher matcherOne = pattern.matcher(rolls);
        int countOneces = 0;
        while (matcherOne.find())
            countOneces++;

        System.out.println(rolls);
        System.out.println(countOneces);

        assertTrue(countOneces >= (baseExpectedOccurrances - deviation)
                         && countOneces <= (baseExpectedOccurrances + deviation)
                  );
    }

}