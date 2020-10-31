import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Logger;

public class Game {

    private static Logger log = Logger.getLogger(Game.class.getName());

    private List<String> words = new ArrayList<>();
    private String secretWord = "";
    private String pathToDictionary;

    public static void main(String[] args){
        Game bullsAndCows = new Game("dictionary.txt");
        try {
            bullsAndCows.loadDictionary();
        } catch (IOException error){
            // logger here
        }
    }
    public Game(String pathToDictionary){

        this.pathToDictionary = pathToDictionary;
    }
    private void startGame(){

        System.out.println("Welcome to Bulls and Cows game!");
    }

    protected void loadDictionary() throws IOException {
        FileReader fileReader = new FileReader(pathToDictionary);
        Scanner scan = new Scanner(fileReader);
        while (scan.hasNextLine()){
            words.add(scan.nextLine());
        }
        fileReader.close();
    }
    protected int countBulls(String userString){

        return 0;
    }
    protected int countCows(String userString){

        return 0;
    }
}
