package com.isep.reviewcommand.model;

import javax.persistence.*;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Getter
@Setter
public class Review {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long idReview;

    @Version
    private long version;

    @Column(nullable = false)
    private String approvalStatus = "pending";

    @Column(nullable = false)
    private String reviewText;

    @Column(nullable = true)
    private String report;

    @Column(nullable = false)
    private LocalDate publishingDate = LocalDate.now();

    @Column(nullable = false)
    private String funFact;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;

    @Column(nullable = false)
    private String userID;

    @Column(nullable = false)
    private Double rate;

}
