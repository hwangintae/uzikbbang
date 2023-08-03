package com.khpl.uzikbbang.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.khpl.uzikbbang.request.NoticeCreate;
import com.khpl.uzikbbang.request.Page;
import com.khpl.uzikbbang.response.NoticeResponse;
import com.khpl.uzikbbang.service.NoticeService;

import lombok.RequiredArgsConstructor;


@RestController
@RequestMapping("/notice")
@RequiredArgsConstructor
public class NoticeController {

    private final NoticeService noticeService;

    @GetMapping("/ping")
    public String pingPong() {
        return "pong";
    }

    @PostMapping(value="")
    public void post(@RequestBody NoticeCreate request) {
        noticeService.write(request);
    }

    @GetMapping(value="/list")
    public List<NoticeResponse> getNoticeList(@ModelAttribute Page page) {
        return noticeService.getList(page);
    }

    @PatchMapping(value = "/{noticeId}")
    public void path(@PathVariable Long noticeId) {
        noticeService.
    }
    
}
