package com.isep.reviewcommand.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import com.isep.reviewcommand.model.*;
import com.isep.reviewcommand.services.ProductService;
import com.isep.reviewcommand.services.ReviewService;

import java.util.List;
import java.util.stream.Collectors;

@Tag(name = "Review", description = "Endpoints for managing Review")
@RestController
class ReviewController {

    @Autowired
    private ReviewService rService;

    @Autowired
    private ProductService pService;

    // @Operation(summary = "gets review by user")
    // @GetMapping("/reviews/{userID}")
    // public ResponseEntity<List<ReviewDTO>> findReviewByUser(@PathVariable(value = "userID") final Long userID) {

    //     final var review = rService.findReviewsByUser(userID);

    //     return ResponseEntity.ok().body(review);
    // }

    @Operation(summary = "creates review")
    @PostMapping("/products/{sku}/reviews")
    public ResponseEntity<ReviewDTO> createReview(@PathVariable(value = "sku") final String sku, @RequestBody CreateReviewDTO createReviewDTO) {
        Review review = ReviewMapper.toReview(createReviewDTO);
        Product product = pService.findBySku(sku).orElseThrow(
            () -> new ResourceNotFoundException("Product not found with sku: " + sku)
        );    
        final Review revCreated = rService.create(review, product);
        if(revCreated == null){
            return ResponseEntity.badRequest().build();
        }
        return new ResponseEntity<ReviewDTO>(ReviewMapper.toDto(review), HttpStatus.CREATED);
    }

    @Operation(summary = "deletes review")
    @DeleteMapping("/reviews/{reviewID}")
    public ResponseEntity<Boolean> deleteReview(@PathVariable(value = "reviewID") final Long reviewID) {

        Boolean rev = rService.DeleteReview(reviewID);
        if (rev == false) return ResponseEntity.unprocessableEntity().build();

        return ResponseEntity.ok().body(rev);
    }

    @Operation(summary = "Accept or reject review")
    @PutMapping("/reviews/acceptreject/{reviewID}")
    public ResponseEntity<ReviewDTO> putAcceptRejectReview(@PathVariable(value = "reviewID") final Long reviewID, @RequestBody String approved){

        try {
            Review rev = rService.moderateReview(reviewID, approved);
            return ResponseEntity.ok().body(ReviewMapper.toDto(rev));
        }
        catch( IllegalArgumentException e ) {
            return ResponseEntity.badRequest().build();
        }
        catch( ResourceNotFoundException e ) {
            return ResponseEntity.notFound().build();
        }
    }
}
