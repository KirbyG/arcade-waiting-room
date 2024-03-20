import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Arrays;
/**
 * Contains and looks up all valid Scrabble words.
 * @author Kirby Gordon
 */
class Dictionary
{
    private String[] words;
    /**
     * Constructs a new dictionary by loading words from a text file.
     */
    public Dictionary()
    {
        try
        {
            ArrayList<String> temp = new ArrayList<String>();
            //File file = new File("src/ospd.txt");
            URL url = this.getClass().getResource("ospd.txt");
            //File file = new File(url.getFile());
            Scanner scanner = new Scanner(url.openStream());
            //System.out.println(file.getName());
            while (scanner.hasNextLine())
            {
                temp.add(scanner.nextLine());
            }
            words = temp.toArray(new String[temp.size()]);
        }
        catch (IOException e) {System.out.println(e.getMessage());}
    }
    /**
     * Looks up the given word in the dictionary.
     * @param word the String to be searched
     * @return true if found, false if not
     */
    public boolean lookUp(String word)
    {
        if (Arrays.binarySearch(words, word) < 0)
        {
            return false;
        }
        return true;
    }
}