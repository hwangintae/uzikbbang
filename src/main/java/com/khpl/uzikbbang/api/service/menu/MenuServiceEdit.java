package com.khpl.uzikbbang.api.service.menu;

import java.util.List;

import com.khpl.uzikbbang.domain.menu.Menu;
import com.khpl.uzikbbang.domain.menu.MenuUseAt;

import lombok.Builder;
import lombok.Getter;

@Getter
public class MenuServiceEdit {

    private String title;
    private String content;
    private int price;
    private MenuUseAt menuUseAt;
    private List<String> options;

    @Builder
    public MenuServiceEdit(String title, String content, int price, MenuUseAt menuUseAt, List<String> options) {
        this.title = title;
        this.content = content;
        this.price = price;
        this.menuUseAt = menuUseAt;
        this.options = options;
    }

    public Menu toEntity() {
        return Menu.builder()
                .title(title)
                .content(content)
                .price(price)
                .menuUseAt(menuUseAt)
                .option(String.join(",", options))
                .build();
    }

}
