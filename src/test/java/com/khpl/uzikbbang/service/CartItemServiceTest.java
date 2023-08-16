package com.khpl.uzikbbang.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.khpl.uzikbbang.domain.CartItem;
import com.khpl.uzikbbang.domain.Product;
import com.khpl.uzikbbang.domain.UzikUser;
import com.khpl.uzikbbang.repository.ProductRepository;
import com.khpl.uzikbbang.repository.UserRepository;
import com.khpl.uzikbbang.request.SignUp;

@SpringBootTest
public class CartItemServiceTest {
    @Autowired
    private SignUpService signUpService;
    
    @Autowired
    private UserRepository userRepository;
    

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CartItemService cartItemService;

    @BeforeEach
    void clean() {
        userRepository.deleteAll();
    }

    @Test
    void testAddItem() {
        SignUp signUp = SignUp.builder()
            .name("황인태")
            .email("hwang@hwang.com")
            .passWord("1234")
        .build();

        UzikUser user = signUpService.save(signUp);

        List<String> ingredients = new ArrayList<>();
        List<String> allergies = new ArrayList<>();

        ingredients.add("우유");
        allergies.add("우유");

        Product product = Product.builder()
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

        productRepository.save(product);

        CartItem cartItem = cartItemService.addItem(user, product);

        assertEquals(user.getId(), cartItem.getUser().getId());
        assertEquals(product.getId(), cartItem.getProduct().getId());
        assertEquals("우직한빵", cartItem.getProduct().getName());
    }
}