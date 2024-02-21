package com.khpl.uzikbbang.api.service.whisky.request;

import com.khpl.uzikbbang.domain.whisky.Whisky;

import lombok.Builder;
import lombok.Getter;

@Getter
public class WhiskyServiceEdit {

    private Long menuId;
    private String country;
    private String region;
    private String distillery;
    private int age;
    private String style;

    @Builder
    public WhiskyServiceEdit(Long menuId, String country, String region, String distillery, int age, String style) {
        this.menuId = menuId;
        this.country = country;
        this.region = region;
        this.distillery = distillery;
        this.age = age;
        this.style = style;
    }

    public Whisky toEntity() {
        return Whisky.builder()
                .menuId(this.menuId)
                .country(this.country)
                .region(this.region)
                .distillery(this.distillery)
                .age(this.age)
                .style(this.style)
                .build();
    }

}