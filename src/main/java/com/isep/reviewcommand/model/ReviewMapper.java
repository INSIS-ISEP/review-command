package com.isep.reviewcommand.model;

public class ReviewMapper {

    public static Review toReview(CreateReviewDTO review){
        Review rev = new Review();
        rev.setReviewText(review.getReviewText());
        rev.setRate(review.getRating());
        rev.setUserID(review.getUserID());
        return rev;
        
    }
    public static ReviewDTO toDto(Review review) {
        return new ReviewDTO(
                review.getIdReview(),
                review.getReviewText(),
                review.getPublishingDate(),
                review.getApprovalStatus(),
                review.getFunFact(),
                review.getRate()
        );
    }
    // public static List<ReviewDTO> toDtoList(List<Review> review) {
    //     List<ReviewDTO> dtoList = new ArrayList<>();

    //     for (Review rev: review) {
    //         dtoList.add(toDto(rev));
    //     }
    //     return dtoList;
    // }
}
