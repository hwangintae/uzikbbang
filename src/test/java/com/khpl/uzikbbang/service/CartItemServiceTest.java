package com.khpl.uzikbbang.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.khpl.uzikbbang.domain.CartItem;
import com.khpl.uzikbbang.domain.Product;
import com.khpl.uzikbbang.domain.UzikUser;
import com.khpl.uzikbbang.repository.ProductRepository;
import com.khpl.uzikbbang.repository.UserRepository;
import com.khpl.uzikbbang.request.ProductCreate;
import com.khpl.uzikbbang.request.SignUp;

@SpringBootTest
public class CartItemServiceTest {
    
    @Autowired
    private UserRepository userRepository;
    
    @Autowired
    private ProductService productService;

    @Autowired
    private CartItemService cartItemService;

    @Autowired
    private AuthService authService;

    @BeforeEach
    void clean() {
        userRepository.deleteAll();
    }

    @Test
    @DisplayName("장바구니에 물건 추가 중복 된 상품이 추가될 경우에는 cnt가 증가하여야 한다.")
    void testAddItem() {
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

        ProductCreate productCreate1 = ProductCreate.builder()
                    .name("우직한빵1")
                    .kind("마들렌1")
                    .addr("대흥동1")
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

        ProductCreate productCreate2 = ProductCreate.builder()
                    .name("우직한빵2")
                    .kind("마들렌2")
                    .addr("대흥동2")
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

        Product product1 = productService.add(productCreate1);
        Product product2 = productService.add(productCreate2);

        CartItem cartItem1 = cartItemService.addItems(user, product1);
        CartItem cartItem2 = cartItemService.addItems(user, product1);
        CartItem cartItem3 = cartItemService.addItems(user, product2);

        assertEquals(user.getId(), cartItem1.getUser().getId());
        assertEquals(product1.getId(), cartItem1.getProduct().getId());
        assertEquals("우직한빵1", cartItem1.getProduct().getName());

        assertEquals(2, cartItem2.getCnt());
        assertEquals(1, cartItem3.getCnt());
    }

    @Test
    void testTest() {

    }
}