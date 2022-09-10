import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Solver {
    public static void main(String[] args) throws IOException {
        //creates an ArrayList and a Scanner for user input
        ArrayList wordList = new ArrayList();
        Scanner input = new Scanner(System.in);

        //populates the ArrayList
        populateList(wordList);


        try {
            System.out.print("Enter your first guess: ");
        }catch (Exception exception){
            System.out.println();
        }

        for (int i=0 ; i<5 ; i++){
            String test = wordList.get(0).toString();
            System.out.println(test.charAt(i));
        }

    }

    public void wordRemover(){

    }

    //populates an ArrayList of all the possible answers
    public static ArrayList populateList(ArrayList wordList) throws IOException{
        //opens the file and declares a scanner to iterate through the file
        File file = new File("wordle-nyt-answers-alphabetical.txt");
        Scanner inputFile = new Scanner(file);

        //read lines from the file until there aren't more
        while (inputFile.hasNext()) {
            wordList.add(inputFile.nextLine());
        }

        //closes the file and returns the ArrayList
        inputFile.close();
        return wordList;
    }

}

