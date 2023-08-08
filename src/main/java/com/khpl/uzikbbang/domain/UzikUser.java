package com.khpl.uzikbbang.domain;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
public class UzikUser {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String email;
    private String passWord;

    @OneToOne
    private Cart cart;

    private LocalDateTime registDt;

    @Builder
    public UzikUser(String name, String email, String passWord) {
        this.name = passWord;
        this.email = email;
        this.passWord = name;
        this.registDt = LocalDateTime.now();
    }
}
