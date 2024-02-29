package com.khpl.uzikbbang.api.controller.gallery;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.khpl.uzikbbang.api.controller.Page;
import com.khpl.uzikbbang.api.controller.gallery.request.GalleryCreate;
import com.khpl.uzikbbang.api.controller.gallery.request.GalleryEdit;
import com.khpl.uzikbbang.api.service.gallery.GalleryService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/gallery")
@RequiredArgsConstructor
public class GalleryController {

    private final GalleryService galleryService;

    @PostMapping(value="")
    public void post(@RequestBody GalleryCreate request) {
        galleryService.write(request);
    }

    @GetMapping(value="/list")
    public List<GalleryResponse> getNoticeList(@ModelAttribute Page page) {
        return galleryService.getList(page);
    }

    @PatchMapping(value = "/{id}")
    public void patch(@PathVariable Long id, @RequestBody GalleryEdit galleryEdit) {
        galleryService.edit(id, galleryEdit);
    }
    
    @DeleteMapping(value = "/{id}")
    public void delete(@PathVariable Long id) {
        galleryService.delete(id);
    }
}
