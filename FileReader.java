/* FileReader.java
 * Done by Ethan Liao
 */

import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

class FileReader 
{
    private final int LEVEL1_COUNT = 18; // words that are in each level
    private final int LEVEL2_COUNT = 22;
    private final int LEVEL3_COUNT = 14;

    private String[] wordsList; // list of words from the file "words.txt"
    private File file; // words.txt but saved as file
    private String name; // name of file we are reading from
    private Scanner reader; // scanner to read the .txt file

    public FileReader(String nameIn) // take in the name when called
    {
        file = null;
        name = nameIn;
        reader = null;
        wordsList = new String[LEVEL1_COUNT + LEVEL2_COUNT + LEVEL3_COUNT]; // set to lines in word.txt
        // wordsList = getList();
        setUpWords();
        
    }

    public void loadFile()
    {
        file = new File(name + ".txt");
        try
        {
            reader = new Scanner(file);
        }
        catch (FileNotFoundException e)
        {
            System.out.println("\n\nFile " + name + ".txt does not exist.");
            System.exit(1);
        }
    }

    public String[] getList()
    {
        String[] words = new String[LEVEL1_COUNT + LEVEL2_COUNT + LEVEL3_COUNT];
        reader.nextLine(); // skip first line of words.txt
        int counter = 0;
        for (int i=0; i<=LEVEL1_COUNT + LEVEL2_COUNT + LEVEL3_COUNT; i++) // loop until words.txt is over
        {
            String line = reader.nextLine();
            if (line.indexOf("Level") == -1) // check if "level" is read(we skip that part)
            {
                words[counter] = line;
                counter++;
            }
        }
        System.out.println("counter = " + counter); // for debugging
        return words;
    }

    public void setUpWords() // call the methods to get words from file
    {
        loadFile();
        wordsList = getList();
        reader.close();
    }

    public String[] shuffle(int level) // take in which level it is
    {
        String[] newList;
        if (level == 1) // store only the words from the level selected to newList[]
        {
            newList = new String[LEVEL1_COUNT];
            for (int i=0; i<LEVEL1_COUNT; i++) {
                newList[i] = wordsList[i];
            }
        }
        else if (level == 2)
        {
            newList = new String[LEVEL2_COUNT];
            for (int i=0; i<LEVEL2_COUNT; i++) {
                newList[i] = wordsList[i + LEVEL1_COUNT];
            }
        }
        else if (level == 3)
        {
            newList = new String[LEVEL3_COUNT];
            for (int i=0; i<LEVEL3_COUNT; i++) {
                newList[i] = wordsList[i + LEVEL3_COUNT];
            }
        }
        else
        {
            newList = new String[1];
        }

        for (int i=0; i<newList.length; i++)
        {
            int randIdx = (int)(Math.random() * newList.length); // randomize the words in the array
            String temp =  new String("");
            temp = newList[i];
            newList[i] = newList[randIdx];
            newList[randIdx] = temp;
        }

        return newList;
    }

    public int getLevelLengths(int level) // returns the level chosen
    {
        if (level == 1)
        {
            return LEVEL1_COUNT;
        }
        else if (level == 2)
        {
            return LEVEL2_COUNT;
        }
        else
        {
            return LEVEL3_COUNT;
        }
    }
}
