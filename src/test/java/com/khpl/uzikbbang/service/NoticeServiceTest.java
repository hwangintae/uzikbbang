package com.khpl.uzikbbang.service;

import static java.util.stream.Collectors.toList;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;
import java.util.stream.IntStream;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.khpl.uzikbbang.api.controller.notice.request.NoticeCreate;
import com.khpl.uzikbbang.api.controller.notice.request.NoticeEdit;
import com.khpl.uzikbbang.api.service.notice.NoticeService;
import com.khpl.uzikbbang.domain.notice.Notice;
import com.khpl.uzikbbang.domain.notice.NoticeRepository;

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
    @DisplayName("notice 수정 테스트")
    void testEdit() {
        Notice notice = Notice.builder()
            .title("황인태")
            .content("박수현")
        .build();

        noticeRepository.save(notice);

        NoticeEdit noticeEdit = NoticeEdit.builder()
            .title("황수현")
            .content("박인태")
        .build();

        noticeService.edit(notice.getId(), noticeEdit);

        Notice result = noticeRepository.findById(notice.getId()).get();

        assertEquals("황수현", result.getTitle());
        assertEquals("박인태", result.getContent());
    }

    @Test
    @DisplayName("notice 삭제 테스트")
    void testDelete() {

        List<Notice> notices = IntStream.range(0, 2)
            .mapToObj(i ->  Notice.builder()
                            .title("삭제" + i)
                            .content("테스트" + i)
                        .build())
            .collect(toList());

        noticeRepository.saveAll(notices);

        noticeService.delete(notices.get(0).getId());

        List<Notice> result = noticeRepository.findAll();

        assertEquals(1, result.size());
        assertEquals("삭제1", result.get(0).getTitle());
        assertEquals("테스트1", result.get(0).getContent());
        
    }

    
}
