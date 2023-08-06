package com.khpl.uzikbbang.request;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class GalleryCreate {
    private String title;
    private String content;

    @Builder
    public GalleryCreate(String title, String content) {
        this.title = title;
        this.content = content;
    }
}
