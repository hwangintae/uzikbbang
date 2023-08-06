package com.khpl.uzikbbang.repository;

import java.util.List;

import com.khpl.uzikbbang.domain.Gallery;
import com.khpl.uzikbbang.request.Page;

public interface GalleryRepositoryDsl {
    List<Gallery> getList(Page page);
}
