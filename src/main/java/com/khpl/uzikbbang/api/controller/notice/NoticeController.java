package com.khpl.uzikbbang.api.controller.notice;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.khpl.uzikbbang.api.controller.Page;
import com.khpl.uzikbbang.api.controller.notice.request.NoticeCreate;
import com.khpl.uzikbbang.api.controller.notice.request.NoticeEdit;
import com.khpl.uzikbbang.api.service.notice.NoticeService;

import lombok.RequiredArgsConstructor;


@RestController
@RequestMapping("/notice")
@RequiredArgsConstructor
public class NoticeController {

    private final NoticeService noticeService;

    @PostMapping(value="")
    public void post(@RequestBody NoticeCreate request) {
        noticeService.write(request);
    }

    @GetMapping(value="/list")
    public List<NoticeResponse> getNoticeList(@ModelAttribute Page page) {
        return noticeService.getList(page);
    }

    @PatchMapping(value = "/{id}")
    public void patch(@PathVariable Long id, @RequestBody NoticeEdit noticeEdit) {
        noticeService.edit(id, noticeEdit);
    }
    
    @DeleteMapping(value = "/{id}")
    public void delete(@PathVariable Long id) {
        noticeService.delete(id);
    }
}
