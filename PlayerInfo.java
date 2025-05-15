import java.util.Scanner;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.FileWriter;

public class PlayerInfo 
{
    private String name;
    private int[] levelScores;
    private boolean[] firstTry;

    public PlayerInfo()
    {
        reset();
        checkForFile();
    }


    public void checkForFile()
    {
        File file = new File(name + ".txt");
        try
        {
            Scanner input = new Scanner(file);
            levelScores[0] = input.nextInt();
            firstTry[0] = input.nextBoolean();

            System.out.println(levelScores[0]);
        }
        catch(IOException e)
        {
            System.out.println("File " + name + ".txt doesn't exist.");
        }
    }

    public String getName()
    {
        return name;
    }

    public void setName(String newName)
    {
        name = newName;
    }

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

    public void reset()
    {
        name = new String("");
        firstTry = new boolean[64];
        levelScores = new int[]{0, 0, 0};
    }

    public void setScore(int levelIn, int newScore)
    {
        levelScores[levelIn-1] = Math.max(levelScores[levelIn-1], newScore);
    }
}
