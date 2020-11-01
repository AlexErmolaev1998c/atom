import org.junit.Test;

import static junit.framework.TestCase.assertTrue;
import static junit.framework.TestCase.assertNotNull;
import static junit.framework.TestCase.assertEquals;


public class GameTest {

    private Game game = new Game("dictionary.txt");

    @Test
    public void bullsAndCowsNumCheck1(){
        game.secretWord = "witch";
        int[] currentResult = game.countBullsAndCows("hctiw");
        int[] expectedResult = {1,4};
        int matches = 0;
        for (int i = 0; i < 2; i++){
            if (expectedResult[i] == currentResult[i]){
                matches++;
            }
        }
        assertTrue(matches == 2);
    }
    @Test
    public void bullsAndCowsNumCheck2(){
        game.secretWord = "cinema";
        int[] currentResult = game.countBullsAndCows("amenic");
        int[] expectedResult = {0,6};
        int matches = 0;
        for (int i = 0; i < 2; i++){
            if (expectedResult[i] == currentResult[i]){
                matches++;
            }
        }
        assertTrue(matches == 2);
    }
    @Test
    public void bullsAndCowsNumCheck3(){
        game.secretWord = "polearm";
        int[] currentResult = game.countBullsAndCows("poelram");
        int[] expectedResult = {3, 4};
        int matches = 0;
        for (int i = 0; i < 2; i++){
            if (expectedResult[i] == currentResult[i]){
                matches++;
            }
        }
        assertTrue(matches == 2);
    }
    @Test
    public void bullsAndCowsNumCheck4(){
        game.secretWord = "vinegar";
        int[] currentResult = game.countBullsAndCows("vinegar");
        int[] expectedResult = {7, 0};
        int matches = 0;
        for (int i = 0; i < 2; i++){
            if (expectedResult[i] == currentResult[i]){
                matches++;
            }
        }
        assertTrue(matches == 2);
    }
    @Test
    public void loadDictionaryNullCheck(){
        assertNotNull(game.words);
    }
    @Test
    public void makeSecretWordCheck(){
        assertEquals(game.makeSecretWord(1), "aahing");
    }
    @Test
    public void makeSecretWordCheck2(){
        assertEquals(game.makeSecretWord(35153), "picker");
    }
}
