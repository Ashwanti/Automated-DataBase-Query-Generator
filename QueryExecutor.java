import java.io.IOException;
import java.util.List;
import java.util.ArrayList;


public class QueryExecutor {
  public int runQuery(String command) {
   try {

           

            List <String> commands = new ArrayList<String>();
            commands.add("cmd");
            commands.add("/c");
            // commands.add("cd "+System.getProperty("user.dir"));
            commands.add(""+ command);
            commands.add("cls");

            ProcessBuilder processBuilder = new ProcessBuilder(commands);
            Process process = processBuilder.start();

            int exitCode = process.waitFor();
            return exitCode;
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
        return 0;
    }

}
