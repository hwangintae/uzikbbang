package com.khpl.uzikbbang.api.controller.gallery.request;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class GalleryCreate {
    private String title;
    private String content;

    @Builder
    public GalleryCreate(String title, String content) {
        this.title = title;
        this.content = content;
    }
}
