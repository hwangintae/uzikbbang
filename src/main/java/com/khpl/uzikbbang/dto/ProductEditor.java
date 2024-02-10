package com.khpl.uzikbbang.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
public class ProductEditor {
    private String name;
    private String kind;
    private String addr;
    private int exprirationDate;
    private int cost;
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
    public ProductEditor(String name, String kind, String addr, int exprirationDate, int cost, Double wight, Double calories, Double sodium, Double totalCarbo, Double sugars, Double totalFat, Double transFat, Double saturatedFat, Double cholesterol, Double protein, String ingredients, String allergies, Boolean useAt) {
        this.name = name;
        this.kind = kind;
        this.addr = addr;
        this.exprirationDate = exprirationDate;
        this.cost = cost;
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
        this.ingredients = allergies;
        this.allergies = ingredients;
        this.useAt = useAt;
    }
}
