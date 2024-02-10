package com.khpl.uzikbbang.service;

import static java.util.stream.Collectors.toList;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.khpl.uzikbbang.dto.NoticeEditor;
import com.khpl.uzikbbang.dto.NoticeEditor.NoticeEditorBuilder;
import com.khpl.uzikbbang.entity.Notice;
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

    @Transactional
    public void edit(Long id, NoticeEdit edit) {
        Notice notice = noticeRepository.findById(id).get();

        NoticeEditorBuilder builder = notice.toEditor();

        NoticeEditor editor = builder
            .title(edit.getTitle())
            .content(edit.getContent())
            .build();
            
        notice.edit(editor);
    }

    public void delete(Long id) {
        noticeRepository.deleteById(id);
    }
}
