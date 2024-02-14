package com.khpl.uzikbbang.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.khpl.uzikbbang.request.WhiskyEdit;

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
    public Whisky(Long menuId, WhiskyEdit whiskyEdit) {
        this.menuId = menuId;
        this.country = whiskyEdit.getCountry();
        this.region = whiskyEdit.getRegion();
        this.distillery = whiskyEdit.getDistillery();
        this.age = whiskyEdit.getAge();
        this.style = whiskyEdit.getStyle();
    }
}
