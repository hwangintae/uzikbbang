package com.khpl.uzikbbang.api.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.khpl.uzikbbang.api.controller.food.FoodCreate;
import com.khpl.uzikbbang.api.controller.food.FoodEdit;
import com.khpl.uzikbbang.api.service.food.FoodService;
import com.khpl.uzikbbang.domain.food.Food;
import com.khpl.uzikbbang.domain.food.FoodRepository;

@SpringBootTest
public class FoodServiceTest {

    @Autowired
    private FoodService foodService;

    @Autowired
    private FoodRepository foodRepository;

    @BeforeEach
    void clean() {
        foodRepository.deleteAll();
    }

    @Test
    @DisplayName("product 등록")
    void testAdd() {
        List<String> ingredients = new ArrayList<>();
        List<String> allergies = new ArrayList<>();

        ingredients.add("우유");
        allergies.add("우유");

        FoodCreate foodCreate = FoodCreate.builder()
                    .name("우직한빵")
                    .kind("마들렌")
                    .addr("대흥동")
                    .exprirationDate(5)
                    .cost(5000)
                    .wight(125D)
                    .calories(1000D)
                    .sodium(1000D)
                    .totalCarbo(1000D)
                    .sugars(1000D)
                    .totalFat(1000D)
                    .transFat(1000D)
                    .saturatedFat(1000D)
                    .cholesterol(1000D)
                    .protein(1000D)
                    .ingredients(ingredients)
                    .allergies(allergies)
                .build();

        Food product = foodService.add(foodCreate);
        
        Food result = foodRepository.findById(product.getId()).get();

        assertEquals("우직한빵", result.getName());
        assertEquals(5000, result.getCost());
        assertEquals(15_000, result.getPrice());
        assertEquals(10_000, result.getProfit());
    }

    @Test
    @DisplayName("product 수정")
    void testEdit() {
        List<String> ingredients = new ArrayList<>();
        List<String> allergies = new ArrayList<>();

        ingredients.add("우유");
        allergies.add("우유");

        Food product = Food.builder()
                    .name("우직한빵")
                    .kind("마들렌")
                    .addr("대흥동")
                    .exprirationDate(5)
                    .cost(5000)
                    .wight(125D)
                    .calories(1000D)
                    .sodium(1000D)
                    .totalCarbo(1000D)
                    .sugars(1000D)
                    .totalFat(1000D)
                    .transFat(1000D)
                    .saturatedFat(1000D)
                    .cholesterol(1000D)
                    .protein(1000D)
                    .ingredients(ingredients)
                    .allergies(allergies)
                .build();

        foodRepository.save(product);

        List<String> newIngredients = new ArrayList<>();
        List<String> newAllergies = new ArrayList<>();

        newIngredients.add("땅콩");
        newAllergies.add("땅콩");

        FoodEdit edit = FoodEdit.builder()
            .name("땅콩크림빵")
            .kind("빵")
            .addr("관악구")
            .exprirationDate(5)
            .cost(5_000_000)
            .wight(12_500D)
            .calories(2_000D)
            .sodium(2_000D)
            .totalCarbo(2_000D)
            .sugars(4_000D)
            .totalFat(5_000D)
            .transFat(6_000D)
            .saturatedFat(1_000D)
            .cholesterol(22_000D)
            .protein(6_000D)
            .ingredients(newIngredients)
            .allergies(newAllergies)
            .useAt(false)
        .build();
        
        foodService.edit(product.getId(), edit);
        
        Food result = foodRepository.findById(product.getId()).get();

        assertEquals("땅콩크림빵", result.getName());
        assertEquals(5_000_000 * 3, result.getPrice());
        assertEquals("땅콩", result.getAllergies());
        assertEquals(false, result.isUseAt());
    }
}
