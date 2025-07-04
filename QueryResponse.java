
import com.google.gson.annotations.SerializedName;
import java.util.List;

public class QueryResponse{

    @SerializedName("candidates")
    List<Candidate> candidates;

    @SerializedName("usageMetadata")
    UsageMetadata usageMetadata;

}


class Candidate {
    @SerializedName("content")
    Content content;

    @SerializedName("finishReason")
    String finishReason;

    @SerializedName("index")
    int index;

    @SerializedName("safetyRatings")
    List<SafetyRating> safetyRatings;
}

class Content {
    @SerializedName("parts")
    List<Part> parts;

    @SerializedName("role")
    String role;
}

class Part {
    @SerializedName("text")
    String text;
}

class SafetyRating {
    @SerializedName("category")
    String category;

    @SerializedName("probability")
    String probability;
}

class UsageMetadata {
    @SerializedName("promptTokenCount")
    int promptTokenCount;

    @SerializedName("candidatesTokenCount")
    int candidatesTokenCount;

    @SerializedName("totalTokenCount")
    int totalTokenCount;
}
