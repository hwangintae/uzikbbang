package com.khpl.uzikbbang.controller;

import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertEquals;
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
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.khpl.uzikbbang.domain.Notice;
import com.khpl.uzikbbang.repository.NoticeRepository;
import com.request.NoticeCreate;

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

        // given
        NoticeCreate request = NoticeCreate.builder()
                        .title("타이틀")
                        .content("콘텐츠")
                    .build();

        String jsonStr = objectMapper.writeValueAsString(request);

        // when
        mockMvc.perform(MockMvcRequestBuilders.post("/notice")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonStr))
                    .andExpect(status().isOk())
                    .andDo(print());

        // then
        Notice notice = noticeRepository.findById(1L).get();

        assertEquals("타이틀", notice.getTitle());
        assertEquals("콘텐츠", notice.getContent());
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
                        .contentType(MediaType.APPLICATION_JSON))
                        .andExpect(status().isOk())
                        .andExpect(jsonPath("$.length()", is(10)))
                        .andExpect(jsonPath("$[0].title").value("타이틀19"))
                        .andExpect(jsonPath("$[0].content").value("콘텐츠19"))
                        .andDo(print());
    }
}
