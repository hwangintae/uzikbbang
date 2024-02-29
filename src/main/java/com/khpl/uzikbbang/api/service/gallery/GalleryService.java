package com.khpl.uzikbbang.api.service.gallery;

import static java.util.stream.Collectors.toList;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.khpl.uzikbbang.api.controller.Page;
import com.khpl.uzikbbang.api.controller.gallery.GalleryEditor;
import com.khpl.uzikbbang.api.controller.gallery.GalleryResponse;
import com.khpl.uzikbbang.api.controller.gallery.GalleryEditor.GalleryEditorBuilder;
import com.khpl.uzikbbang.api.controller.gallery.request.GalleryCreate;
import com.khpl.uzikbbang.api.controller.gallery.request.GalleryEdit;
import com.khpl.uzikbbang.domain.gallery.Gallery;
import com.khpl.uzikbbang.domain.gallery.GalleryRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class GalleryService {
    
    private final GalleryRepository galleryRepository;

    public void write(GalleryCreate galleryCreate) {

        Gallery gallery = Gallery.builder()
                    .title(galleryCreate.getTitle())
                    .content(galleryCreate.getContent())
                .build();

        galleryRepository.save(gallery);
    }

    public List<GalleryResponse> getList(Page page) {
        return galleryRepository.getList(page).stream()
                                    .map(GalleryResponse::new)
                                .collect(toList());
    }

    @Transactional
    public void edit(Long id, GalleryEdit edit) {
        Gallery gallery = galleryRepository.findById(id).get();

        GalleryEditorBuilder builder = gallery.toEditor();

        GalleryEditor editor = builder
            .title(edit.getTitle())
            .content(edit.getContent())
            .build();
            
        gallery.edit(editor);
    }

    public void delete(Long id) {
        galleryRepository.deleteById(id);
    }
}
