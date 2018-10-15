/**
 * @author TJ Breitenfeldt
 * course CSCD320 Algorithms
 *
 * midterm project
*/


import java.util.Scanner;
import java.util.LinkedList;
import java.io.IOException;


/**
 * class Tester
 * This class is used to test the SolverTree and dictionary class by taking an user input 
 * and showing the words that can be built with the given string of numbers 
*/
public class Tester {

    public static void main(String[] args) throws IOException {
        SolverTree tree = new SolverTree();
        DictPrefixTree dictionary = new DictPrefixTree();
        LinkedList<String> inputPossibilities = new LinkedList<String>();
        Scanner kin = new Scanner(System.in);
        String input = "";
        String dictionaryFileName = "dictionary.txt";

        try {
            System.out.print("Enter a sequence of numbers between 2 and 9: ");
            input = kin.nextLine();

            if (InputUtils.filterInput(input)) {
                InputUtils.buildDictionary(dictionary, dictionaryFileName);
                tree.buildTree(input);
                inputPossibilities = tree.getPossibilities();
                InputUtils.printWords(dictionary, inputPossibilities);
            } else {
                throw new IllegalArgumentException("invalid input");
            }  //end else
        } catch(Exception e) {
            System.out.println(e);
        }  //end try catch 

}  //end method main(String[])

}  //end class Tester