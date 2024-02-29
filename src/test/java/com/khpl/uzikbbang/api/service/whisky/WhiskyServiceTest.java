package com.khpl.uzikbbang.api.service.whisky;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.khpl.uzikbbang.domain.Level;
import com.khpl.uzikbbang.domain.whisky.Whisky;
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
    @DisplayName("위스키를 추가한다.")
    void addWhisky() {
        //given
        Whisky whisky = Whisky.builder()
                .country("인태나라")
                .region("인태시")
                .distillery("인태증류소")
                .age(30)
                .style("개쩜")
                .level(Level.JUINOR)
                .build();

        //when
        Whisky result = whiskyRepository.save(whisky);

        //then
        Assertions.assertThat(result)
                .extracting("country", "region", "distillery", "age", "style", "level")
                .contains("인태나라", "인태시", "인태증류소", 30, "개쩜", Level.JUINOR);
    }
}
