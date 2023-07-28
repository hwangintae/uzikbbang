package com.khpl.uzikbbang.service;

import static java.util.stream.Collectors.toList;

import java.util.List;

import org.springframework.stereotype.Service;

import com.khpl.uzikbbang.domain.Notice;
import com.khpl.uzikbbang.repository.NoticeRepository;
import com.khpl.uzikbbang.response.NoticeResponse;
import com.request.NoticeCreate;
import com.request.Page;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class NoticeService {
    
    private final NoticeRepository noticeRepository;

    public void write(NoticeCreate noticeCreate) {

        Notice notice = Notice.builder()
                    .title(noticeCreate.getTitle())
                    .content(noticeCreate.getContent())
                .build();

        noticeRepository.save(notice);
    }

    public List<NoticeResponse> getList(Page page) {
        return noticeRepository.getList(page).stream()
                                    .map(NoticeResponse::new)
                                .collect(toList());
    }
}
