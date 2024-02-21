package com.khpl.uzikbbang.domain.whisky;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Whisky {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long menuId;
    private String country;
    private String region;
    private String distillery;
    private int age;
    private String style;

    @Builder
    public Whisky(Long menuId, String country, String region, String distillery, int age, String style) {
        this.menuId = menuId;
        this.country = country;
        this.region = region;
        this.distillery = distillery;
        this.age = age;
        this.style = style;
    }
}