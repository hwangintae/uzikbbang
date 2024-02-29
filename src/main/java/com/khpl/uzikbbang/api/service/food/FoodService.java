package com.khpl.uzikbbang.api.service.food;

import static java.util.stream.Collectors.toList;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.khpl.uzikbbang.api.controller.Page;
import com.khpl.uzikbbang.api.controller.food.FoodCreate;
import com.khpl.uzikbbang.api.controller.food.FoodEdit;
import com.khpl.uzikbbang.api.controller.food.FoodResponse;
import com.khpl.uzikbbang.api.service.food.FoodEditor.FoodEditorBuilder;
import com.khpl.uzikbbang.domain.food.Food;
import com.khpl.uzikbbang.domain.food.FoodRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class FoodService {
    private final FoodRepository foodRepository;

    public Food add(FoodCreate request) {
        Food product = Food.builder()
                .name(request.getName())
                .kind(request.getKind())
                .addr(request.getAddr())
                .exprirationDate(request.getExprirationDate())
                .cost(request.getCost())
                .wight(request.getWight())
                .calories(request.getCalories())
                .sodium(request.getSodium())
                .totalCarbo(request.getTotalCarbo())
                .sugars(request.getSugars())
                .totalFat(request.getTotalFat())
                .transFat(request.getTransFat())
                .saturatedFat(request.getSaturatedFat())
                .cholesterol(request.getCholesterol())
                .protein(request.getProtein())
                .ingredients(request.getIngredients())
                .allergies(request.getAllergies())
                .build();
        foodRepository.save(product);

        return product;
    }

    public List<FoodResponse> getList(Page page) {
        return foodRepository.getList(page).stream()
                .map(FoodResponse::new)
                .collect(toList());
    }

    @Transactional
    public void edit(Long id, FoodEdit edit) {
        Food food = foodRepository.findById(id).get();

        FoodEditorBuilder builder = food.toEdit();

        FoodEditor editor = builder
                .name(edit.getName())
                .kind(edit.getKind())
                .addr(edit.getAddr())
                .exprirationDate(edit.getExprirationDate())
                .cost(edit.getCost())
                .wight(edit.getWight())
                .calories(edit.getCalories())
                .sodium(edit.getSodium())
                .totalCarbo(edit.getTotalCarbo())
                .sugars(edit.getSugars())
                .totalFat(edit.getTotalFat())
                .transFat(edit.getTransFat())
                .saturatedFat(edit.getSaturatedFat())
                .cholesterol(edit.getCholesterol())
                .protein(edit.getProtein())
                .ingredients(String.join(",", edit.getIngredients()))
                .allergies(String.join(",", edit.getAllergies()))
                .useAt(edit.getUseAt())
                .build();

        food.edit(editor);
    }
}
