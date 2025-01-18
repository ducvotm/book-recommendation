package vn.duke.book;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@Service
public class BookRecommendationService {
    private static final String GOOGLE_BOOKS_API = "https://www.googleapis.com/books/v1/volumes?q=";

    public Map<String, Object> getRecommendationsByMood(String mood) {
        // Use intitle property to narrow down results based on the title
        String query = mapMoodToQuery(mood);

        // Google Books API search with intitle and a limit of 5 results
        String apiUrl = GOOGLE_BOOKS_API + "intitle:" + query + "&maxResults=5";

        RestTemplate restTemplate = new RestTemplate();
        Map<String, Object> response = restTemplate.getForObject(apiUrl, Map.class);

        // Parse and prepare the response
        Map<String, Object> result = new HashMap<>();
        result.put("mood", mood);
        result.put("books", response != null ? response.get("items") : null);

        return result;
    }

    private String mapMoodToQuery(String mood) {
        switch (mood.toLowerCase()) {
            case "happy": return "happiness";
            case "sad": return "grief";
            case "adventurous": return "adventure";
            case "romantic": return "romance";
            case "curious": return "mystery";
            default: return "fiction"; // Default to generic fiction
        }
    }
}