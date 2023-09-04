package com.khpl.uzikbbang.request;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class NoticeCreate {

    private String title;
    private String content;

    @Builder
    public NoticeCreate(String title, String content) {
        this.title = title;
        this.content = content;
    }
}
