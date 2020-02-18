import java.util.ArrayList;
import java.util.*;
import java.io.File;
import java.io.FileNotFoundException;

public class Puzzle {

    String[] target;
    String[] guesses;
    String[] incorrect;
    ArrayList <String> words;

    public Puzzle() {
        words = new ArrayList<String>();

        try {
            File file = new File("words.txt");
            Scanner scanner = new Scanner(file);

            while (scanner.hasNext()) {
                String tempWord = scanner.next().toUpperCase();
                words.add(tempWord);
            }
            scanner.close();

            //ONCE THIS LINE OF CODE IS REACHED, YOUR words ArrayList
            //CONTAINS ALL THE WORDS IN words.txt
        }
        catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        target = makeWord(words.get((int)(Math.random()*words.size())));
        guesses = new String[target.length];
        incorrect = new String[6];
        
        for(int i = 0; i < guesses.length; i++) {
            guesses[i] = "_";
        }
    }
    
    private String[] makeWord(String s){
        String[] out = new String[s.length()];
        for(int i=0; i<s.length(); i++)
            out[i] = s.charAt(i)+"";
        return out;
    }

    public boolean isUnsolved() {

        for (int i = 0; i < guesses.length; i++) {
            if (guesses[i].equals("_")) {
                return true;
            }
        }

        return false;
    }

    public void show() {

        for (int i = 0; i < guesses.length; i++) {
            System.out.print(guesses[i] + " ");   
        }

        System.out.println();
        System.out.println();
        for (int i = 0; i < incorrect.length; i++){

            if(incorrect[i] != null) {
                System.out.print(incorrect[i] + " ");
            } else {
                break;
            }

        }

    }

    public boolean makeGuess(String guess) {

        guess = guess.toUpperCase();
        boolean mode = false;

        for (int i = 0; i < target.length; i++ ) {

            if (target[i].equals(guess)) {

                if(!guesses[i].equals(guess)){

                    guesses[i] = guess;
                    mode = true;

                } else {

                    return true;
                }
            } 
        }

        if(mode) {
            return mode;
        }

        for (int i = 0; i < incorrect.length; i++) {

            if(incorrect[i] == null){
                incorrect[i] = guess;

                return false;
            } else if(incorrect[i].equals(guess)) {
                return true;
            }

        }

        return false;
    }

    public String getWord() {
        String out = "";

        for (int i = 0; i < target.length; i++) {
            out += target[i];
        }

        return out;
    }
}