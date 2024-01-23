package dev.farhan.movieist.movies;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class ReviewService {
    @Autowired
    private ReviewRepository repository;

    @Autowired
    private MongoTemplate mongoTemplate;

    public Review createReview(String reviewBody, String imdbId) {
        Review review = repository.insert(new Review(reviewBody, LocalDateTime.now(), LocalDateTime.now()));

        mongoTemplate.update(Movie.class)
                .matching(Query.query(Criteria.where("imdbId").is(imdbId)))
                .apply(new Update().addToSet("reviews", review))
                .first();

        return review;
    }

    public void deleteReview(ObjectId id) {
        Optional<Review> optionalReview = repository.findById(id);
        optionalReview.ifPresent(review -> {
            mongoTemplate.updateMulti(Query.query(Criteria.where("reviews._id").is(id)),
                    new Update().pull("reviews", Query.query(Criteria.where("_id").is(id))), Movie.class);
            repository.delete(review);
        });
    }
    public Review updateReview(ObjectId id, String reviewBody) {
        Optional<Review> optionalReview = repository.findById(id);
        if (optionalReview.isPresent()) {
            Review existingReview = optionalReview.get();
            existingReview.setBody(reviewBody);
            existingReview.setUpdated(LocalDateTime.now());
            return repository.save(existingReview);
        }
        return null;
    }
}
