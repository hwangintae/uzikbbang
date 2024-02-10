package com.khpl.uzikbbang.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.khpl.uzikbbang.entity.Gallery;

public interface GalleryRepository extends JpaRepository<Gallery, Long>, GalleryRepositoryDsl{
    
}
