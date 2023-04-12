package com.isep.reviewcommand.model;

import javax.persistence.*;

import lombok.Getter;
import lombok.Setter;


@Entity
@Getter
@Setter
public class Product {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long productID;

    @Column(nullable = false, unique = true)
    public String sku;

    @Column(nullable = false)
    private String designation;

    @Column(nullable = false)
    private String description;
    /*
    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
    private List<Review> review = new ArrayList<Review>(); */

}
