package com.khpl.uzikbbang.repository;

import java.util.List;

import com.khpl.uzikbbang.domain.Notice;
import com.khpl.uzikbbang.request.Page;

public interface NoticeRepositoryDsl {
    List<Notice> getList(Page page);
}
