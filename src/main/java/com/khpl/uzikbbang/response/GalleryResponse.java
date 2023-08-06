package com.khpl.uzikbbang.response;

import com.khpl.uzikbbang.domain.Gallery;

import lombok.Builder;
import lombok.Getter;

@Getter
public class GalleryResponse {

    private final Long id;
    private final String title;
    private final String content;

    public GalleryResponse(Gallery notice) {
        this.id = notice.getId();
        this.title = notice.getTitle();
        this.content = notice.getContent();
    }

    @Builder
    public GalleryResponse(Long id, String title, String content) {
        this.id = id;
        this.title = title;
        this.content = content;
    }
}
