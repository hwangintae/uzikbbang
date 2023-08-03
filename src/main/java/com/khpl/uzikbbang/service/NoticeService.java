package com.khpl.uzikbbang.service;

import static java.util.stream.Collectors.toList;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.khpl.uzikbbang.domain.Notice;
import com.khpl.uzikbbang.repository.NoticeRepository;
import com.khpl.uzikbbang.request.NoticeCreate;
import com.khpl.uzikbbang.request.NoticeEdit;
import com.khpl.uzikbbang.request.Page;
import com.khpl.uzikbbang.response.NoticeResponse;

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

    public void edit(Long noticeId, NoticeEdit noticeEdit) {
        Notice notice = noticeRepository.findById(noticeId).get();

        
    }
}
