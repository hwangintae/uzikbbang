package com.khpl.uzikbbang.domain.notice;

import java.util.List;

import com.khpl.uzikbbang.api.controller.Page;

public interface NoticeRepositoryDsl {
    List<Notice> getList(Page page);
}
