import java.util.ArrayList;
import java.util.HashMap;

public class Prompt {
    // private final String model ="gpt-3.5-turbo";
    // private ArrayList<HashMap<String,String>> messages = new ArrayList<>();
    // private final float temperature = 0.7f;
    
    public HashMap<String, ArrayList<HashMap<String, ArrayList<HashMap<String, String>>>>> data = new HashMap<>();
  
    public String getTextPrompt() {
        return "test";
    }

    public void setTextPrompt(String textPrompt) {
      
        HashMap<String, String> part = new HashMap<>();
        part.put("text", "Without any formatting just out plain text (strictly) for Generate window commands for " + textPrompt);

        ArrayList<HashMap<String, String>> parts = new ArrayList<>();
        parts.add(part);

        HashMap<String, ArrayList<HashMap<String, String>>> content = new HashMap<>();
        content.put("parts", parts);

        ArrayList<HashMap<String, ArrayList<HashMap<String, String>>>> contents = new ArrayList<>();
        contents.add(content);

        data.put("contents", contents);
    }

    
}
