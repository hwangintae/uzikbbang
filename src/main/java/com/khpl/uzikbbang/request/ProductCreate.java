package com.khpl.uzikbbang.request;

import java.util.List;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

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

    private int ExprirationDate;

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
}
