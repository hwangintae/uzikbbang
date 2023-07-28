package com.khpl.uzikbbang.response;

import com.khpl.uzikbbang.domain.Notice;

import lombok.Builder;
import lombok.Getter;

@Getter
public class NoticeResponse {

    private final Long id;
    private final String title;
    private final String content;

    public NoticeResponse(Notice notice) {
        this.id = notice.getId();
        this.title = notice.getTitle();
        this.content = notice.getContent();
    }

    @Builder
    public NoticeResponse(Long id, String title, String content) {
        this.id = id;
        this.title = title;
        this.content = content;
    }
}
