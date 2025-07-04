
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpRequest.BodyPublishers;
import java.net.http.HttpResponse.BodyHandlers;

import com.google.gson.Gson;


public class QueryGenerator{

   private  HttpRequest.Builder queryBuilder;

    public QueryGenerator()
    {
        URI chatGPTUri = null;
        try {
            chatGPTUri = new URI("https://generativelanguage.googleapis.com/v1beta/models/gemini-1.5-flash-latest:generateContent?key="+Config.API_KEY);
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }

        queryBuilder = HttpRequest.newBuilder()
            .uri(chatGPTUri)
            .header("Content-Type","application/json");


    }

    private String parseJsonString(Gson gson,String responseJsonString)
    {
        QueryResponse jsonResponse = gson.fromJson(responseJsonString, QueryResponse.class);
        String commands = jsonResponse.candidates.get(0).content.parts.get(0).text;
        return commands;
    }
    
    public String getQuery(String textPrompt)
    {
        Prompt prompt = new Prompt();
        prompt.setTextPrompt(textPrompt);

        Gson gsonObject = new Gson();
        String jsonString = gsonObject.toJson(prompt.data); // not a good practice

        HttpRequest queryGenerateRequest = queryBuilder.POST(BodyPublishers.ofString(jsonString)).build();

        HttpClient httpClient = HttpClient.newHttpClient();
        HttpResponse<String> response = null;
        try {
             response = httpClient.send(queryGenerateRequest, BodyHandlers.ofString());
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }

        return parseJsonString(gsonObject, response.body());

    }
}