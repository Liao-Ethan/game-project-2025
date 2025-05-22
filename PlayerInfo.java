import java.util.Scanner;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.FileWriter;

public class PlayerInfo 
{
    private String name; // Name of the player, name of the text file
    private int[] levelScores; // Scores per level (how many were correct first try)
    private boolean[] firstTry; // List that shows which words were answered correctly

    // Words that are in each level
    private final int LEVEL1_COUNT = 18;
    private final int LEVEL2_COUNT = 21;
    private final int LEVEL3_COUNT = 14;


    public PlayerInfo()
    {
        // Set up PlayerInfo, initializes all field variables
        name = new String("");
        firstTry = new boolean[LEVEL1_COUNT + LEVEL2_COUNT + LEVEL3_COUNT];
        levelScores = new int[]{0, 0, 0};
        reset();
    }


    // Uses the classic try catch to load pre-existing data of the player.
    // If the player doesn't exist then it is disregarded.
    public void checkForFile()
    {
        File file = new File(name + ".txt");
        try
        {
            Scanner input = new Scanner(file);
            for (int i=0; i<levelScores.length; i++)
            {
                levelScores[i] = input.nextInt();
            }
            for (int i=0; i<firstTry.length; i++)
            {
                firstTry[i] = input.nextBoolean();
            }
            input.close();
        }
        catch(IOException e)
        {
            System.out.println("File " + name + ".txt doesn't exist.");
        }
    }

    // Getter and setter for the name of the user
    public String getName()
    {
        return name;
    }

    public void setName(String newName)
    {
        name = newName;
    }

    // Uses PrintWriter and try catch to write the player's save data.
    public void writeSave()
    {
        PrintWriter pw = null;
        File outFile = new File(name + ".txt");
        try
        {
            pw = new PrintWriter(new FileWriter(outFile, false));
            
            for (int i=0; i<levelScores.length; i++)
            {
                pw.print(levelScores[i] + " ");
            }
            pw.println("");
            
            for (int i=0; i<firstTry.length; i++)
            {
                pw.print(firstTry[i] + " ");
            }
            pw.println("");
            
            pw.close();
        }
        catch(IOException e)
        {
			System.err.println("Unable to write to " + name + ".txt");
			System.exit(3);
        }
    }

    // Reset all variables in the case of a new player in the same session.
    public void reset()
    {
        name = new String("");
        
        for (int i=0; i<firstTry.length; i++)
        {
            firstTry[i]=false;
        }
        for (int i=0; i<levelScores.length; i++)
        {
            levelScores[i] = 0;
        }
    }

    // Sets the score of the player in a certain level
    public void setScore()
    {
        int newScore = 0;
        for (int i=0; i<LEVEL1_COUNT; i++)
        {
            if (firstTry[i] == true)
            {
                newScore++;
            }
        }
        levelScores[0] = newScore;
        newScore = 0;

        for (int i=LEVEL1_COUNT; i<LEVEL1_COUNT + LEVEL2_COUNT; i++)
        {
            if (firstTry[i] == true)
            {
                newScore++;
            }
        }
        levelScores[1] = newScore;
        newScore = 0;

        for (int i=LEVEL1_COUNT+LEVEL2_COUNT; i<LEVEL1_COUNT + LEVEL2_COUNT + LEVEL3_COUNT; i++)
        {
            if (firstTry[i] == true)
            {
                newScore++;
            }
        }
        levelScores[2] = newScore;
    }

    // Check ifthe player can proceed to the next level.
    public boolean isComplete(int levelIn)
    {
        System.out.println("LevelScores at " + levelIn + " = " + levelScores[levelIn]);
        if (levelScores[levelIn] >= 10)
        {
            return true;
        }
        else
        {
            return false;
        }
    }

    // Switches the word to be correct if the player answered correctly.
    public void switchCorrect(int levelIn, String question)
    {
        int idxAdder = 0;
        if (levelIn == 2)
        {
            idxAdder = LEVEL1_COUNT;
        }
        else if (levelIn == 3)
        {
            idxAdder = LEVEL1_COUNT + LEVEL2_COUNT;
        }
        firstTry[idxAdder +( Integer.parseInt(question.substring(0, question.indexOf(" "))) - 1)] = true; 
    }

    // Getter for getting if the word was answered correctly.
    public boolean getCorrect(int idx)
    {
        return firstTry[idx];
    }
}
