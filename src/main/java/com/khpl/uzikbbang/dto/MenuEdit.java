package com.khpl.uzikbbang.dto;

import java.util.List;

import lombok.Builder;
import lombok.Data;

@Data
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
}
