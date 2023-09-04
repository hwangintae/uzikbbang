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
@NoArgsConstructor(access = AccessLevel.PROTECTED)
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

    public ProductEditor.ProductEditorBuilder toEdit() {
        return ProductEditor.builder()
            .name(this.name)
            .kind(this.kind)
            .addr(this.addr)
            .exprirationDate(this.exprirationDate)
            .cost(this.cost)
            .wight(this.wight)
            .calories(this.calories)
            .sodium(this.sodium)
            .totalCarbo(this.totalCarbo)
            .sugars(this.sugars)
            .totalFat(this.totalFat)
            .transFat(this.transFat)
            .saturatedFat(this.saturatedFat)
            .cholesterol(this.cholesterol)
            .protein(this.protein)
            .ingredients(this.ingredients)
            .allergies(this.allergies)
            .useAt(this.useAt);
    }

    public void edit(ProductEditor editor) {
        this.name = editor.getName();
        this.kind = editor.getKind();
        this.addr = editor.getAddr();
        this.exprirationDate = editor.getExprirationDate();
        this.cost = editor.getCost();
        this.price = this.cost * 3;
        this.profit = this.price - this.cost;
        this.wight = editor.getWight();
        this.calories = editor.getCalories();
        this.sodium = editor.getSodium();
        this.totalCarbo = editor.getTotalCarbo();
        this.sugars = editor.getSugars();
        this.totalFat = editor.getTotalFat();
        this.transFat = editor.getTransFat();
        this.saturatedFat = editor.getSaturatedFat();
        this.cholesterol = editor.getCholesterol();
        this.protein = editor.getProtein();
        this.ingredients = editor.getIngredients();
        this.allergies = editor.getAllergies();
        this.useAt = editor.isUseAt();
    }
}
