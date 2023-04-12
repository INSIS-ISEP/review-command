package com.isep.reviewcommand.services;

import java.util.List;
import com.isep.reviewcommand.model.*;

public interface ReviewService {

    Iterable<Review> getAll();

    Review create(Review review, Product product);

    boolean DeleteReview(Long reviewId);

    List<Review> findPendingReview();

    Review moderateReview(Long reviewID, String approved);

    // List<ReviewDTO> findReviewsByUser(Long userID);
}
