import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Game {

    private static Logger log = Logger.getLogger(Game.class.getName());

    protected List<String> words = new ArrayList<>();
    protected String secretWord = "";
    private String pathToDictionary;
    private int maxShotsNum = 10;
    private int[] bullAndCows;

    public static void main(String[] args) {
        Game bullsAndCows = new Game("dictionary.txt");
        bullsAndCows.startGame();
    }

    public Game(String pathToDictionary) {

        this.pathToDictionary = pathToDictionary;
        try {
            this.loadDictionary();
        } catch (IOException error) {
            log.log(Level.WARNING, error.getMessage());
        }
    }

    private void startGame() {
        log.info("Game was started");
        Scanner scanner = new Scanner(System.in);
        System.out.println("Welcome to Bulls and Cows game!");
        while (true) {
            this.secretWord = makeSecretWord((int) (Math.random() * (this.words.size() - 1)));
            log.info("Secret word was made. It is " + this.secretWord);
            int usedShotsNum = 0;
            while (usedShotsNum < maxShotsNum) {
                System.out.println("Can you guess my secret word? Its length is " + this.secretWord.length() + ". " + "You got " + (maxShotsNum - usedShotsNum) + " shots.");
                String userString = scanner.next();
                if (userString.length() != this.secretWord.length()) {
                    System.out.println("Lengths of your word and mine are different.");
                    continue;
                }
                log.info("User tried to know the secret word. Shots used: " + (usedShotsNum + 1));
                if (userString.equals(secretWord)) {
                    System.out.println("Congratulations! You won!");
                    log.info("User won");
                    break;
                } else {
                    this.bullAndCows = countBullsAndCows(userString);
                    System.out.println("Nice try. You almost guessed my secret word. Bulls: " + this.bullAndCows[0] + ", Cows: " + this.bullAndCows[1] + ".");
                    usedShotsNum++;
                    if (usedShotsNum == maxShotsNum) {
                        System.out.println("You used all available shots and lost the game :c.");
                        log.info("User lost");
                    }
                }
            }
            System.out.println("Do you wanna play again? Y/N");
            if (!scanner.next().equals("Y")) {
                log.info("User stopped playing");
                break;
            }
            log.info("User started new game");
        }
    }

    private void loadDictionary() throws IOException {
        FileReader fileReader = new FileReader(pathToDictionary);
        Scanner scan = new Scanner(fileReader);
        while (scan.hasNextLine()) {
            words.add(scan.nextLine());
        }
        fileReader.close();
        log.info("Dictionary was loaded");
    }

    protected String makeSecretWord(int seed) {
        return this.words.get(seed);
    }

    protected int[] countBullsAndCows(String userString) {
        int[] bullsAndCows = {0, 0};
        for (int i = 0; i < userString.length(); i++) {
            if (userString.charAt(i) == secretWord.charAt(i)) {
                bullsAndCows[0]++;
            } else if (secretWord.indexOf(userString.charAt(i)) != -1) {
                bullsAndCows[1]++;
            }
        }
        return bullsAndCows;
    }
}
