package terning;

import org.junit.experimental.theories.Theories;

import static org.junit.Assert.*;

public class DiceTest {

    @org.junit.Test
    public void roll() {

        Dice dice = new Dice();

        int OneCounter = 0;
        int TwoCounter = 0;
        int ThreeCounter = 0;
        int FourCounter = 0;
        int FiveCounter = 0;
        int SixCounter = 0;

        for(int i=0; i<60000; i++){
            switch (dice.roll()){
                case 1: OneCounter++;
                    break;
                case 2: TwoCounter++;
                    break;
                case 3: ThreeCounter++;
                    break;
                case 4: FourCounter++;
                    break;
                case 5: FiveCounter++;
                    break;
                case 6: SixCounter++;
                    break;
                default:
                    break;

            }
        }

        assertTrue(9600<=TwoCounter && TwoCounter<=10400);
        assertTrue(9600<=ThreeCounter && ThreeCounter<=10400);
        assertTrue(9600<=FourCounter && FourCounter<=10400);
        assertTrue(9600<=FiveCounter && FiveCounter<=10400);
        assertTrue(9600<=SixCounter && SixCounter<=10400);
        assertTrue(9600<=OneCounter && OneCounter<=10400);
        assertEquals(60000,OneCounter+TwoCounter+ ThreeCounter+FourCounter+FiveCounter+SixCounter);

    }



    @org.junit.Test
    public void rollMultiple() {


    }
}