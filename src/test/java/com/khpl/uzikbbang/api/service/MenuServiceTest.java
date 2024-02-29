package com.khpl.uzikbbang.api.service;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.khpl.uzikbbang.api.controller.menu.MenuEdit;
import com.khpl.uzikbbang.api.controller.whisky.WhiskyEdit;
import com.khpl.uzikbbang.api.service.menu.MenuService;
import com.khpl.uzikbbang.api.service.whisky.WhiskyService;
import com.khpl.uzikbbang.domain.Level;
import com.khpl.uzikbbang.domain.menu.Menu;
import com.khpl.uzikbbang.domain.menu.MenuRepository;
import com.khpl.uzikbbang.domain.menu.MenuUseAt;
import com.khpl.uzikbbang.domain.whisky.Whisky;
import com.khpl.uzikbbang.domain.whisky.WhiskyRepository;

@SpringBootTest
public class MenuServiceTest {

    @Autowired
    MenuRepository menuRepository;

    @Autowired
    MenuService menuService;

    @Autowired
    WhiskyService whiskyService;

    @Autowired
    WhiskyRepository whiskyRepository;

    @BeforeEach
    void clean() {
        menuRepository.deleteAll();
        whiskyRepository.deleteAllInBatch();
    }

    @Test
    @DisplayName("위스키를 메뉴에 추가한다.")
    void save() {

        // given
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

        String option = String.join(",", List.of("glass", "bottle"));
        Menu menu = Menu.builder()
                .productId(result.getId())
                .title("위스키1")
                .content("비싼 위스키")
                .price(100_000)
                .option(option)
                .menuUseAt(MenuUseAt.Y)
                .build();

        // then
        Menu save = menuRepository.save(menu);

        assertThat(save)
                .extracting("title", "content", "price", "option", "menuUseAt")
                .contains("위스키1", "비싼 위스키", 100_000, option, MenuUseAt.Y);

    }
}
