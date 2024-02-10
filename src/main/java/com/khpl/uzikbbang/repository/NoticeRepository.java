package com.khpl.uzikbbang.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.khpl.uzikbbang.entity.Notice;

public interface NoticeRepository extends JpaRepository<Notice, Long>, NoticeRepositoryDsl {
    
}
