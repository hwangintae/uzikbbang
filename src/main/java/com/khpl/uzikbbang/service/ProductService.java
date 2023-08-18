package com.khpl.uzikbbang.service;

import static java.util.stream.Collectors.toList;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.khpl.uzikbbang.domain.Product;
import com.khpl.uzikbbang.domain.ProductEditor;
import com.khpl.uzikbbang.domain.ProductEditor.ProductEditorBuilder;
import com.khpl.uzikbbang.repository.ProductRepository;
import com.khpl.uzikbbang.request.Page;
import com.khpl.uzikbbang.request.ProductCreate;
import com.khpl.uzikbbang.request.ProductEdit;
import com.khpl.uzikbbang.response.ProductResponse;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;

    public Product add(ProductCreate request) {
        Product product = Product.builder()
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
        productRepository.save(product);

        return product;
    }

    public List<ProductResponse> getList(Page page) {
       return productRepository.getList(page).stream()
                .map(ProductResponse::new)
                .collect(toList());
    }

    @Transactional
    public void edit(Long id, ProductEdit edit) {
        Product product = productRepository.findById(id).get();

        ProductEditorBuilder builder = product.toEdit();

        ProductEditor editor = builder
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

        product.edit(editor);
    }
}
