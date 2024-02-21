package com.khpl.uzikbbang.api.service.whisky.request;

import java.util.List;

import com.khpl.uzikbbang.domain.whisky.Whisky;

import lombok.Getter;

@Getter
public class WhiskyServiceEdit {

    private String title;
    private String content;
    private int price;
    private List<String> options;
    private String country;
    private String region;
    private String distillery;
    private int age;
    private String style;

    public Whisky toEntity() {
        return null;
    }

}
