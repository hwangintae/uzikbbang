package com.khpl.uzikbbang.domain.menu;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;

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

    private String title;

    @Lob
    private String content;
    private int price;
    private MenuUseAt menuUseAt;

    private String option;

    @Builder
    public Menu(String title, String content, int price, MenuUseAt menuUseAt, String option) {
        this.title = title;
        this.content = content;
        this.price = price;
        this.menuUseAt = menuUseAt;
        this.option = option;
    }
}
