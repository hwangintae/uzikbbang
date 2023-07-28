package com.khpl.uzikbbang.repository;

import java.util.List;

import com.khpl.uzikbbang.domain.Notice;
import com.request.Page;

public interface NoticeRepositoryDsl {
    List<Notice> getList(Page page);
}
