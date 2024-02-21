package com.khpl.uzikbbang.api.service;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.khpl.uzikbbang.api.service.whisky.WhiskyService;
import com.khpl.uzikbbang.domain.menu.MenuRepository;
import com.khpl.uzikbbang.dto.MenuEdit;
import com.khpl.uzikbbang.request.WhiskyEdit;
import com.khpl.uzikbbang.service.MenuService;

@SpringBootTest
public class MenuServiceTest {

    @Autowired
    MenuRepository menuRepository;

    @Autowired MenuService menuService;
    @Autowired WhiskyService whiskyService;

    @BeforeEach
    void clean() {
        menuRepository.deleteAll();
    }

    @Test
    @DisplayName("위스키를 메뉴에 추가한다.")
    void save() {

        List<String> options = new ArrayList<>();
        
        options.add("glass");
        options.add("bottle");

        WhiskyEdit whiskyEdit = WhiskyEdit.builder()
                .title("위스키1")
                .content("비싼 위스키")
                .price(100_000)
                .options(options)
                .country("인태나라")
                .region("인태시")
                .distillery("인태증류소")
                .age(30)
                .style("개쩜")
                .build();

        MenuEdit menuEdit = MenuEdit.builder()
                .title(whiskyEdit.getTitle())
                .content(whiskyEdit.getContent())
                .price(whiskyEdit.getPrice())
                .options(whiskyEdit.getOptions())
                .build();

        Long menuId = menuService.save(menuEdit.toServiceEdit());

        // whiskyService.save(menuId, whiskyEdit);

        // List<Whisky> whiskies = whiskyService.findByMenuId(menuId);
        

        // assertEquals(1, menuId);
        // assertEquals(1, whiskies.size());
        // assertEquals(30, whiskies.get(0).getAge());

    }
}
