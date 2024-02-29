package com.khpl.uzikbbang.api.controller.whisky;

import java.util.List;

import com.khpl.uzikbbang.api.service.whisky.request.WhiskyServiceEdit;
import com.khpl.uzikbbang.domain.Level;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class WhiskyEdit {
    private String title;
    private String content;
    private int price;
    private List<String> options;
    private String country;
    private String region;
    private String distillery;
    private int age;
    private String style;
    private Level level;

    

    @Builder
    public WhiskyEdit(String title, String content, int price, List<String> options, String country, String region, String distillery, int age, String style, Level level) {
        this.title = title;
        this.content = content;
        this.price = price;
        this.options = options;
        this.country = country;
        this.region = region;
        this.distillery = distillery;
        this.age = age;
        this.style = style;
        this.level = level;
    }

    public WhiskyServiceEdit toServiceEdit(Long menuId) {
        return WhiskyServiceEdit.builder()
                .menuId(menuId)
                .country(this.country)
                .region(this.region)
                .distillery(this.distillery)
                .age(this.age)
                .style(this.style)
                .level(this.level)
                .build();
    }
}
