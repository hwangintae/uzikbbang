package com.khpl.uzikbbang.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;

import com.khpl.uzikbbang.dto.MenuEdit;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Menu {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long userId;

    private String title;

    @Lob
    private String content;
    private int price;
    private boolean useAt;

    private String mainGroup;
    private String subGroup;

    private String option;

    @Builder
    public Menu(MenuEdit menuEdit) {
        this.title = menuEdit.getTitle();
        this.content = menuEdit.getContent();
        this.price = menuEdit.getPrice();
        this.useAt = true;
        this.option = String.join(",", menuEdit.getOptions());
    }
}
