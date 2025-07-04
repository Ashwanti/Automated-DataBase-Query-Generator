import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;


public class CommandGen {

    String[] getCommands(File commandFile)
    {
        String[] commands = null;
        if(commandFile.exists())
        {
            int n_characters = (int) commandFile.length();
            char[] text = new char[n_characters];

            FileReader fr;
            try
            { 
                int i = 1,idx = 0;
                fr = new FileReader(commandFile);
                while ((i = fr.read()) != - 1) {
                    text[idx] = (char)i;
                    idx++;
                }
            }
            catch (IOException e) 
            {
                System.err.println("Please ensure file is not being used by another process.");
            }
            commands = new String(text).split("\n");
       }
        return commands;
    }
    public static void main(String[] args) {
        CommandGen ob = new CommandGen();
        QueryGenerator qg = new QueryGenerator();
        QueryExecutor exec = new QueryExecutor();
        Scanner sc = new Scanner(System.in);

        File commandFile = new File("cmd.txt");

        if(commandFile.exists()) // executes commands from file
        {
            String[] textCommands = ob.getCommands(commandFile);
            for (String textCommand:textCommands) {
                String command = qg.getQuery(textCommand);
                // System.err.println(textCommand);
                int exitCode = exec.runQuery(command);
                if(exitCode == 0)
                    System.out.println("\nExecuted "+textCommand+" successfully!");
                else
                {
                    System.err.println(command+" executed with status code "+exitCode);
                    break;
                }
            }
        }
        else
        {
        while(true)
        {
            System.out.println("Enter command description or enter q to quit");
            String commandText = sc.nextLine().trim();
            
                if(commandText.equals("q"))
                    break;
            String command = qg.getQuery(commandText);

            int exitCode = exec.runQuery(command);
            if(exitCode != 0)
            {
                System.out.println("Error occured  with error code "+exitCode);
            }
            };
        }
        
        sc.close();
    }
}
