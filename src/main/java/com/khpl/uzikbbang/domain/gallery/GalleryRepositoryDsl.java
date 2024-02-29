package com.khpl.uzikbbang.domain.gallery;

import java.util.List;

import com.khpl.uzikbbang.api.controller.Page;

public interface GalleryRepositoryDsl {
    List<Gallery> getList(Page page);
}
