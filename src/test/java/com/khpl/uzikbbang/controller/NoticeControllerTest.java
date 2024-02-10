package com.khpl.uzikbbang.controller;

import static org.hamcrest.Matchers.is;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.khpl.uzikbbang.entity.Notice;
import com.khpl.uzikbbang.repository.NoticeRepository;
import com.khpl.uzikbbang.request.NoticeCreate;
import com.khpl.uzikbbang.request.NoticeEdit;

@SpringBootTest
@AutoConfigureMockMvc
public class NoticeControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private NoticeRepository noticeRepository;

    @BeforeEach
    void clean() {
        noticeRepository.deleteAll();
    }

    @Test
    @DisplayName("/notice post 요청시 DB에 값이 저장된다.")
    void postTest() throws Exception {
        NoticeCreate request = NoticeCreate.builder()
                .title("타이틀")
                .content("콘텐츠")
                .build();

        String jsonStr = objectMapper.writeValueAsString(request);

        mockMvc.perform(post("/notice")
                .contentType(APPLICATION_JSON)
                .content(jsonStr))
                .andExpect(status().isOk())
                .andDo(print());
    }

    @Test
    @DisplayName("/notice/list 요청시 notice 목록이 출력된다.")
    void getNoticeListTest() throws Exception {

        // given
        List<Notice> list = IntStream.range(0, 20)
                .mapToObj(i -> Notice.builder()
                        .title("타이틀" + i)
                        .content("콘텐츠" + i)
                        .build())
                .collect(Collectors.toList());

        noticeRepository.saveAll(list);

        // expected
        mockMvc.perform(MockMvcRequestBuilders.get("/notice/list?page=0&size=10")
                .contentType(APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()", is(10)))
                .andExpect(jsonPath("$[0].title").value("타이틀19"))
                .andExpect(jsonPath("$[0].content").value("콘텐츠19"))
                .andDo(print());
    }

    @Test
    @DisplayName("/notice/{noticeId} 수정")
    void testPatch() throws Exception {

        // given
        Notice notice = Notice.builder()
                .title("타이틀")
                .content("콘텐츠 수정 전")
                .build();

        noticeRepository.save(notice);

        NoticeEdit noticeEdit = NoticeEdit.builder()
                .title("타이틀 수정")
                .content("황인태")
                .build();

        String jsonStr = objectMapper.writeValueAsString(noticeEdit);

        // expected
        mockMvc.perform(MockMvcRequestBuilders.patch("/notice/1")
                .contentType(APPLICATION_JSON)
                .content(jsonStr))
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("/notice/{noticeId} 삭제")
    void testDelete() throws Exception {

        // given
        Notice notice = Notice.builder()
                .title("타이틀")
                .content("콘텐츠 수정 전")
                .build();

        noticeRepository.save(notice);

        // expected
        mockMvc.perform(MockMvcRequestBuilders.delete("/notice/" + notice.getId())
                .contentType(APPLICATION_JSON))
                .andExpect(status().isOk());
    }

}
