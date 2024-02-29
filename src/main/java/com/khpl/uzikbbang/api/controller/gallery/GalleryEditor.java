package com.khpl.uzikbbang.api.controller.gallery;

import lombok.Builder;
import lombok.Getter;

@Getter
public class GalleryEditor {
    private String title;
    private String content;

    @Builder
    public GalleryEditor(String title, String content) {
        this.title = title;
        this.content = content;
    }
}
