package com.khpl.uzikbbang.domain.gallery;

import org.springframework.data.jpa.repository.JpaRepository;

public interface GalleryRepository extends JpaRepository<Gallery, Long>, GalleryRepositoryDsl{
    
}
