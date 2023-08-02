package com.khpl.uzikbbang.domain;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PUBLIC)
public class Product {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String kind;
    private String addr;
    private int exprirationDate;
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

    private String ingredients;

    private String allergies;

    private boolean useAt;

    @Builder
    public Product(String name, String kind, String addr, int exprirationDate, int cost, Double wight, Double calories, Double sodium, Double totalCarbo, Double sugars, Double totalFat, Double transFat, Double saturatedFat, Double cholesterol, Double protein, List<String> ingredients, List<String> allergies) {
        this.name = name;
        this.kind = kind;
        this.addr = addr;
        this.exprirationDate = exprirationDate;
        this.cost = cost;
        this.price = cost * 3;
        this.profit = this.price - cost;
        this.wight = wight;
        this.calories = calories;
        this.sodium = sodium;
        this.totalCarbo = totalCarbo;
        this.sugars = sugars;
        this.totalFat = totalFat;
        this.transFat = transFat;
        this.saturatedFat = saturatedFat;
        this.cholesterol = cholesterol;
        this.protein = protein;
        this.ingredients = String.join(",", ingredients) ;
        this.allergies = String.join(",", allergies);
        this.useAt = true;
    }
}
