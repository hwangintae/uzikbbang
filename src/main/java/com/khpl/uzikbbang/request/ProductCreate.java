package com.khpl.uzikbbang.request;

import java.util.List;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ProductCreate {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String kind;
    private String addr;
    private int exprirationDate;
    private int cost;
    private int price;
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

    @Builder
    public ProductCreate(String name, String kind, String addr, int exprirationDate, int cost, int price, Double wight, Double calories, Double sodium, Double totalCarbo, Double sugars, Double totalFat, Double transFat, Double saturatedFat, Double cholesterol, Double protein, List<String> ingredients, List<String> allergies) {
        this.name = name;
        this.kind = kind;
        this.addr = addr;
        this.exprirationDate = exprirationDate;
        this.cost = cost;
        this.price = price;
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
        this.ingredients = ingredients;
        this.allergies = allergies;
    }
}
