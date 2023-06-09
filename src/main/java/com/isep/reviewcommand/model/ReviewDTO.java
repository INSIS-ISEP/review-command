package com.isep.reviewcommand.model;



import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ReviewDTO {

    private Long idReview;
    private String reviewText;
    private LocalDate publishingDate;
    private String approvalStatus;
    private String funFact;
    private Double rate;




}
