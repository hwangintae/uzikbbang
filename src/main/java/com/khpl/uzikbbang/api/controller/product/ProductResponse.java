package com.khpl.uzikbbang.api.controller.product;

import com.khpl.uzikbbang.domain.product.Product;

import lombok.Getter;

@Getter
public class ProductResponse {
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

    public ProductResponse(Product product) {
        this.name = product.getName();
        this.kind = product.getKind();
        this.addr = product.getAddr();
        this.exprirationDate = product.getExprirationDate();
        this.cost = product.getCost();
        this.price = product.getPrice();
        this.profit = product.getProfit();
        this.wight = product.getWight();
        this.calories = product.getCalories();
        this.sodium = product.getSodium();
        this.totalCarbo = product.getTotalCarbo();
        this.sugars = product.getSugars();
        this.totalFat = product.getTotalFat();
        this.transFat = product.getTransFat();
        this.saturatedFat = product.getSaturatedFat();
        this.cholesterol = product.getCholesterol();
        this.protein = product.getProtein();
        this.ingredients = product.getIngredients();
        this.allergies = product.getAllergies();
    }
}
