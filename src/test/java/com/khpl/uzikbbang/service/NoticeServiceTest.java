package com.khpl.uzikbbang.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.khpl.uzikbbang.domain.Notice;
import com.khpl.uzikbbang.repository.NoticeRepository;
import com.khpl.uzikbbang.request.NoticeCreate;
import com.khpl.uzikbbang.request.NoticeEdit;

@SpringBootTest
public class NoticeServiceTest {

    @Autowired
    private NoticeService noticeService;

    @Autowired
    private NoticeRepository noticeRepository;

    @BeforeEach
    void clean() {
        noticeRepository.deleteAll();
    }

    @Test
    @DisplayName("notice 저장")
    void testWrite() {
        NoticeCreate noticeCreate = NoticeCreate.builder()
            .title("황인태")
            .content("박수현")
        .build();

        noticeService.write(noticeCreate);

        Notice notice = noticeRepository.findById(1L).get();

        assertEquals("황인태", notice.getTitle());
        assertEquals("박수현", notice.getContent());
    }

    @Test
    void testEdit() {
        NoticeCreate noticeCreate = NoticeCreate.builder()
            .title("황인태")
            .content("박수현")
        .build();

        noticeService.write(noticeCreate);

        NoticeEdit noticeEdit = NoticeEdit.builder()
            .title("황수현")
            .content("박인태")
        .build();

        noticeService.edit(1L, noticeEdit);

        Notice notice = noticeRepository.findById(1L).get();

        assertEquals("황수현", notice.getTitle());
        assertEquals("박인태", notice.getContent());
    }

    
}
