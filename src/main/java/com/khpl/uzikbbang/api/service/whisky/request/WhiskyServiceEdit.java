package com.khpl.uzikbbang.api.service.whisky.request;

import com.khpl.uzikbbang.domain.Level;
import com.khpl.uzikbbang.domain.whisky.Whisky;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class WhiskyServiceEdit {

    private String country;
    private String region;
    private String distillery;
    private int age;
    private String style;
    private Level level;

    @Builder
    public WhiskyServiceEdit(String country, String region, String distillery, int age, String style, Level level) {
        this.country = country;
        this.region = region;
        this.distillery = distillery;
        this.age = age;
        this.style = style;
        this.level = level;
    }

    public Whisky toEntity() {
        return Whisky.builder()
                .country(this.country)
                .region(this.region)
                .distillery(this.distillery)
                .age(this.age)
                .style(this.style)
                .level(this.level)
                .build();
    }

}
