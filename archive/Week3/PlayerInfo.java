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

    public PlayerInfo(String nameIn)
    {
        name = nameIn;
        firstTry = new boolean[64];
        levelScores = new int[]{0, 0, 0};
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
        }
        catch(IOException e)
        {
        }
    }

    public void writeSave()
    {
        PrintWriter pw = null;
        File outFile = new File(name + ".txt");
        try
        {
            pw = new PrintWriter(new FileWriter(outFile, false));
            pw.println(levelScores);
            pw.println(firstTry);
            pw.close();
        }
        catch(IOException e)
        {

        }
    }
}
