package dev.adil.movieist.controller;

import dev.adil.movieist.entity.Review;
import dev.adil.movieist.service.ReviewService;
import dev.adil.movieist.repository.ReviewRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/reviews")
public class ReviewController {

    @Autowired
    private ReviewService service;

    @Autowired
    private ReviewRepository repository;

    @PostMapping()
    public ResponseEntity<Review> createReview(@RequestBody Map<String, String> payload) {
        String userId = payload.get("userId");
        return new ResponseEntity<>(service.createReview(userId, payload.get("reviewBody"), payload.get("imdbId")), HttpStatus.OK);
    }

    @GetMapping()
    public ResponseEntity<List<Review>> getAllReviews() {
        List<Review> reviews = repository.findAll();
        return new ResponseEntity<>(reviews, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteReview(@PathVariable ObjectId id) {
        try {
            service.deleteReview(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @PutMapping("/{id}")
    public ResponseEntity<Review> updateReview(@PathVariable ObjectId id, @RequestBody Map<String, String> payload) {
        String userId = payload.get("userId");
        String reviewBody = payload.get("reviewBody");
        Review updatedReview = service.updateReview(id, userId, reviewBody);
        if (updatedReview != null) {
            return new ResponseEntity<>(updatedReview, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
