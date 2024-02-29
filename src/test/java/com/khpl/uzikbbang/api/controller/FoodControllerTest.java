package com.khpl.uzikbbang.api.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import com.khpl.uzikbbang.domain.food.FoodRepository;

@SpringBootTest
@AutoConfigureMockMvc
public class FoodControllerTest {
    
    @Autowired
    private FoodRepository foodRepository;

    @BeforeEach
    void clean() {
        foodRepository.deleteAll();
    }


    @Test
    void testGetNoticeList() {

    }
}
