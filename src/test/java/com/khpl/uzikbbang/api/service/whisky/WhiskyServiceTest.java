package com.khpl.uzikbbang.api.service.whisky;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.khpl.uzikbbang.domain.whisky.WhiskyRepository;

@SpringBootTest
public class WhiskyServiceTest {

    @Autowired
    private WhiskyRepository whiskyRepository;

    @BeforeEach
    void clean() {
        whiskyRepository.deleteAllInBatch();
    }
    
    @Test
    @DisplayName("비기너 위스키를 10잔 주문했을 경우 유저의 레벨이 주니어 레벨로 변경된다.")
    void isNextLevel() {
        
    }
}
