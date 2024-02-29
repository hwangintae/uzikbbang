package com.khpl.uzikbbang.api.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.khpl.uzikbbang.api.controller.Page;
import com.khpl.uzikbbang.api.controller.auth.request.SignUp;
import com.khpl.uzikbbang.api.controller.food.FoodCreate;
import com.khpl.uzikbbang.api.service.auth.AuthService;
import com.khpl.uzikbbang.api.service.food.FoodService;
import com.khpl.uzikbbang.api.service.order.OrderService;
import com.khpl.uzikbbang.domain.food.Food;
import com.khpl.uzikbbang.domain.food.FoodRepository;
import com.khpl.uzikbbang.domain.order.OrderProductRepository;
import com.khpl.uzikbbang.domain.order.OrderRepository;
import com.khpl.uzikbbang.domain.order.UzikOrderProduct;
import com.khpl.uzikbbang.domain.user.UserRepository;
import com.khpl.uzikbbang.domain.user.UzikUser;

@SpringBootTest
public class OrderServiceTest {

    @Autowired
    private AuthService authService;

    @Autowired
    private FoodService foodService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private FoodRepository foodRepository;

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private OrderProductRepository orderProductRepository;

    @Autowired
    private OrderService orderService;

    @BeforeEach
    void clean() {
        userRepository.deleteAll();
        foodRepository.deleteAll();
        orderRepository.deleteAll();
        orderProductRepository.deleteAll();
    }

    @Test
    void testOrderByCartItem() {

    }

    @Test
    @DisplayName("상품 바로 주문 테스트")
    void testOrderByProduct() {
        SignUp signUp = SignUp.builder()
            .name("황인태")
            .email("hwang@hwang.com")
            .password("1234")
        .build();

        UzikUser user = authService.signUp(signUp);

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

        Food food = foodService.add(foodCreate);

        Page page = Page.builder().page(1).size(10).build();

        List<UzikOrderProduct> orderProducts = orderService.getOrderProducts(page, user.getId());
        
        assertEquals(1, orderProducts.size());
        assertEquals(100, orderProducts.get(0).getCnt());
    }
}
