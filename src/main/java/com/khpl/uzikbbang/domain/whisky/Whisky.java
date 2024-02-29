package com.khpl.uzikbbang.domain.whisky;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.khpl.uzikbbang.domain.Level;

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
    private String country;
    private String region;
    private String distillery;
    private int age;
    private String style;
    private Level level;

    @Builder
    public Whisky(String country, String region, String distillery, int age, String style, Level level) {
        this.country = country;
        this.region = region;
        this.distillery = distillery;
        this.age = age;
        this.style = style;
        this.level = level;
    }
}
