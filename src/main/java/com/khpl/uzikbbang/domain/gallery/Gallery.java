package com.khpl.uzikbbang.domain.gallery;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;

import com.khpl.uzikbbang.api.controller.gallery.GalleryEditor;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Gallery {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    @Lob
    private String content;

    @Builder
    public Gallery(String title, String content) {
        this.title = title;
        this.content = content;
    }

    public GalleryEditor.GalleryEditorBuilder toEditor() {
        return GalleryEditor.builder()
            .title(this.title)
            .content(this.content);
    }

    public void edit(GalleryEditor editor) {
        this.title = editor.getTitle();
        this.content = editor.getContent();
    }
}
