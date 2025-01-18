package vn.duke.book;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@CrossOrigin(origins = {"http://localhost:63342", "http://localhost:8080"})
public class BookRecommendationController {

    @Autowired
    private BookRecommendationService bookRecommendationService;

    @GetMapping("/recommendations")
    public Map<String, Object> getBooksByMood(@RequestParam String mood) {
        return bookRecommendationService.getRecommendationsByMood(mood);
    }
}
