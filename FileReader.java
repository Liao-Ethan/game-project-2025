/* Ethan Liao
 * FileReader.java
 */

import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

class FileReader 
{
    private final int LEVEL1_COUNT = 18;
    private final int LEVEL2_COUNT = 22;
    private final int LEVEL3_COUNT = 14;

    private String[] wordsList;
    private File file;
    private String name;
    private Scanner reader;

    public FileReader(String nameIn)
    {
        file = null;
        name = nameIn;
        reader = null;
        wordsList = new String[LEVEL1_COUNT + LEVEL2_COUNT + LEVEL3_COUNT];
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
        reader.nextLine();
        int counter = 0;
        for (int i=0; i<=LEVEL1_COUNT + LEVEL2_COUNT + LEVEL3_COUNT; i++)
        {
            String line = reader.nextLine();
            if (line.indexOf("Level") == -1)
            {
                words[counter] = line;
                counter++;
            }
        }
        return words;
    }

    public void setUpWords()
    {
        loadFile();
        wordsList = getList();
        reader.close();
    }

    public String[] shuffle(int level)
    {
        String[] newList;
        if (level == 1)
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

        for (int i=0; i<newList.length; i++) {
            int randIdx = (int)(Math.random() * newList.length);
            String temp =  new String("");
            temp = newList[i];
            newList[i] = newList[randIdx];
            newList[randIdx] = temp;
        }

        return newList;
    }

    public int getLevelLengths(int level)
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
