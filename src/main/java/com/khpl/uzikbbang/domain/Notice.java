package com.khpl.uzikbbang.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PUBLIC)
public class Notice {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    
    @Lob
    private String content;

    @Builder
    public Notice(String title, String content) {
        this.title = title;
        this.content = content;
    }

    public NoticeEditor.NoticeEditorBuilder toEditor() {
        return NoticeEditor.builder()
            .title(this.title)
            .content(this.content);
    }

    public void edit(NoticeEditor editor) {
        this.title = editor.getTitle();
        this.content = editor.getContent();
    }

}
