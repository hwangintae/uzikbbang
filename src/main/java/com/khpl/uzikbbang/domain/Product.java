package com.khpl.uzikbbang.domain;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;

import lombok.Getter;

@Getter
@Entity
public class Product {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String kind;

    private String addr;

    private int ExprirationDate;

    private int cost;

    private int price;
    
    private int profit;

    private Double wight;
    private Double calories;
    private Double sodium;
    private Double totalCarbo;
    private Double sugars;
    private Double totalFat;
    private Double transFat;
    private Double saturatedFat;
    private Double cholesterol;
    private Double protein;

    private List<String> ingredients;

    private List<String> allergies;

    private boolean useAt;
}
