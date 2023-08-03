package com.khpl.uzikbbang.request;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class NoticeEdit {
    private String title;
    private String content;

    @Builder
    public NoticeEdit(String title, String content) {
        this.title = title;
        this.content = content;
    }
}
