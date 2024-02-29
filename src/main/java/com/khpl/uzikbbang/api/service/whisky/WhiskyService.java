package com.khpl.uzikbbang.api.service.whisky;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.khpl.uzikbbang.api.service.whisky.request.WhiskyServiceEdit;
import com.khpl.uzikbbang.domain.whisky.WhiskyRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class WhiskyService {

    private final WhiskyRepository whiskyRepository;

    @Transactional
    public void save(WhiskyServiceEdit whiskyServiceEdit) {
        whiskyRepository.save(whiskyServiceEdit.toEntity());
    }
}
