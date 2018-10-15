/**
 * @author TJ Breitenfeldt
 * course: CSCD320 Algorithms
 *
 * midterm project
*/


import java.util.TreeMap;


/**
 * class DictPrefixTree
 * This class is used to store the contents of dictionary.txt in a prefix tree
 * this implementation uses Nodes that are linked together to using the java.util.TreeMap class
*/
public class DictPrefixTree {


    /**
     * inner class Node
     * This class stores the data for the current element, as well as links for the children of the element using the TreeMap data structure.
    */
    public class Node {

    private TreeMap<Character, Node> children;
    private boolean isAWord;

        //constructor
        public Node() {
            this.children = new TreeMap<Character, Node>();
            this.isAWord = false;
        }  //end constructor Node

    }  //end class Node


    private Node root;

    //constructor
    public DictPrefixTree() {
        this.root = new Node();
    }  //end constructor DictPrefixTree

    /**
     * method insert
     * insert a string into this prefixTree
     * @param String - the string to insert
    */
    public void insert(final String text) {
        Node currentNode = this.root;
        char[] textArray = text.toCharArray();

        for (char c : textArray) {
            Node next = currentNode.children.get(c);

            if (next == null) {
                next = new Node();
                currentNode.children.put(c, next);
            }  //end if 

            currentNode = next;
        }  //end for each loop 

        currentNode.isAWord = true;
    }  //end method insert(String)

    /**
     * method isWord
     * check if the given string is a word by grabbing each letter in the string and tracing through the tree 
     * @param String - the string to test if it is a word or not
     * @return boolean - returns true if the given string is a word and false if not
    */
    public boolean isWord(final String text) {
        Node currentNode = this.root;
        char[] textArray = text.toCharArray();

        for (char c : textArray) {
            Node next = currentNode.children.get(c);

            if (next == null) {
                return false;
            }  //end if 

        currentNode = next;
        }  //end for each loop 

        if (currentNode.isAWord) {
            return true;
        }  //end if

        return false;
    }  //end method isWord(String)
     

}  //end class DictPrefixTree