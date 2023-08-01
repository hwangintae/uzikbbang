package com.khpl.uzikbbang.request;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class NoticeCreate {

    private String title;
    private String content;

    @Builder
    public NoticeCreate(String title, String content) {
        this.title = title;
        this.content = content;
    }
}
