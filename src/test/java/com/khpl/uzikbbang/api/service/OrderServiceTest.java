package com.khpl.uzikbbang.api.service;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.khpl.uzikbbang.api.controller.auth.request.SignUp;
import com.khpl.uzikbbang.api.controller.food.FoodCreate;
import com.khpl.uzikbbang.api.controller.whisky.WhiskyEdit;
import com.khpl.uzikbbang.api.service.auth.AuthService;
import com.khpl.uzikbbang.api.service.food.FoodService;
import com.khpl.uzikbbang.api.service.order.OrderService;
import com.khpl.uzikbbang.api.service.whisky.WhiskyService;
import com.khpl.uzikbbang.domain.Level;
import com.khpl.uzikbbang.domain.food.Food;
import com.khpl.uzikbbang.domain.food.FoodRepository;
import com.khpl.uzikbbang.domain.order.Orders;
import com.khpl.uzikbbang.domain.order.OrdersRepository;
import com.khpl.uzikbbang.domain.user.User;
import com.khpl.uzikbbang.domain.user.UserRepository;
import com.khpl.uzikbbang.domain.whisky.Whisky;

@SpringBootTest
public class OrderServiceTest {

    @Autowired
    private AuthService authService;

    @Autowired
    private FoodService foodService;

    @Autowired
    private ordersS

    @BeforeEach
    void clean() {
    }

    @Test
    void testOrderByCartItem() {

    }

    @Test
    @DisplayName("음식을 주문하면 사용자 id와 상품 id가 저장된다.")
    void testOrderByProduct() {
        SignUp signUp = SignUp.builder()
                .name("황인태")
                .email("hwang@hwang.com")
                .password("1234")
                .build();

        User user = authService.signUp(signUp);

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

        Orders order = Orders.builder()
                .userId(user.getId())
                .productId(food.getId())
                .build();

        Orders result = orderService.productOrder(order);

        assertThat(result.getUserId()).isEqualTo(user.getId());
        assertThat(result.getProductId()).isEqualTo(food.getId());

    }

    @Test
    @DisplayName("위스키를 10잔 주문한다.")
    void test() {
        // given
        WhiskyEdit whiskyEdit = WhiskyEdit.builder()
                .title("위스키1")
                .content("비싼 위스키")
                .price(100_000)
                .options(List.of("glass", "bottle"))
                .country("인태나라")
                .region("인태시")
                .distillery("인태증류소")
                .age(30)
                .style("개쩜")
                .level(Level.JUINOR)
                .build();

        // when
        WhiskyService
        
        // then

    }

}
