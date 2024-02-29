package com.khpl.uzikbbang.api.controller.menu;

import java.util.List;

import com.khpl.uzikbbang.api.service.menu.MenuServiceEdit;
import com.khpl.uzikbbang.domain.menu.MenuUseAt;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class MenuEdit {
    private String title;
    private String content;
    private int price;
    private List<String> options;

    @Builder
    public MenuEdit(String title, String content, int price, List<String> options) {
        this.title = title;
        this.content = content;
        this.price = price;
        this.options = options;
    }

    public MenuServiceEdit toServiceEdit() {
        return MenuServiceEdit.builder()
                .title(title)
                .content(content)
                .price(price)
                .menuUseAt(MenuUseAt.Y)
                .options(options)
                .build();
    }
}
