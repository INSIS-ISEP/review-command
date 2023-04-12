package com.isep.reviewcommand.model;

import java.time.LocalDate;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateReviewDTO {
    private String reviewText;
    private String userID;
    private Double rating;
}
