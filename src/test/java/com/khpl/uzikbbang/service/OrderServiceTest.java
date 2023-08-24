package com.khpl.uzikbbang.service;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.khpl.uzikbbang.domain.Product;
import com.khpl.uzikbbang.domain.UzikUser;
import com.khpl.uzikbbang.repository.OrderProductRepository;
import com.khpl.uzikbbang.repository.OrderRepository;
import com.khpl.uzikbbang.repository.ProductRepository;
import com.khpl.uzikbbang.repository.UserRepository;
import com.khpl.uzikbbang.request.ProductCreate;
import com.khpl.uzikbbang.request.SignUp;

@SpringBootTest
public class OrderServiceTest {

    @Autowired
    private AuthService authService;

    @Autowired
    private ProductService productService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private OrderProductRepository orderProductRepository;

    @BeforeEach
    void clean() {
        userRepository.deleteAll();
        productRepository.deleteAll();
        orderRepository.deleteAll();
        orderProductRepository.deleteAll();
    }


    @Test
    void testOrderByCartItem() {

    }

    @Test
    @Transactional
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

        ProductCreate productCreate = ProductCreate.builder()
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

        Product product = productService.add(productCreate);

        user.addOrder().orderByProduct(product, 100);
        
        Assertions.assertEquals(1, user.getOrders().size());
        Assertions.assertEquals(100, user.getOrders().get(0).getOrderProducts().get(0).getCnt());
    }
}
