package com.isep.reviewcommand.services;
import com.isep.reviewcommand.controllers.ResourceNotFoundException;
import java.lang.IllegalArgumentException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.isep.reviewcommand.model.*;

import com.isep.reviewcommand.repositories.ReviewRepository;

import java.util.List;
import java.util.Optional;

@Service
public class ReviewServiceImpl implements ReviewService {

    @Autowired
    ReviewRepository repository;

    @Autowired
    RestService restService;

    @Override
    public Iterable<Review> getAll() {
        return repository.findAll();
    }

    @Override
    public Review create(Review review, Product product) {
        review.setFunFact(restService.getFunFact(review.getPublishingDate()));
        review.setProduct(product);
        return repository.save(review);
    }
    
    @Override
    public boolean DeleteReview(Long reviewId)  {
        Optional<Review> rev =  repository.findById(reviewId);
        if (!rev.isEmpty()){
            Review r = rev.get();
            repository.delete(r);
            return true;
        }else{
            throw new ResourceNotFoundException("Review not found");
        }
    }

    @Override
    public Review moderateReview(Long reviewID, String approved) throws ResourceNotFoundException, IllegalArgumentException {

        Optional<Review> r = repository.findById(reviewID);

        if(r.isEmpty()){
            throw new ResourceNotFoundException("Review not found");
        }
        r.get().setApprovalStatus(approved);
        return repository.save(r.get());
    }


    // @Override
    // public List<ReviewDTO> findReviewsByUser(Long userID) {

    //     final Optional<User> user = uRepository.findById(userID);

    //     if(user.isEmpty()) return null;

    //     Optional<List<Review>> r = repository.findByUserId(user.get());

    //     if (r.isEmpty()) return null;

    //     return ReviewMapper.toDtoList(r.get());
    // }
}