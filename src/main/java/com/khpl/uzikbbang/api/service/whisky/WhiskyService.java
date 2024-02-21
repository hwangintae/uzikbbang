package com.khpl.uzikbbang.api.service.whisky;

import java.util.List;

import org.springframework.stereotype.Service;

import com.khpl.uzikbbang.api.service.whisky.request.WhiskyServiceEdit;
import com.khpl.uzikbbang.domain.whisky.Whisky;
import com.khpl.uzikbbang.domain.whisky.WhiskyRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class WhiskyService {

    private final WhiskyRepository whiskyRepository;

    public void save(WhiskyServiceEdit whiskyServiceEdit) {
        whiskyRepository.save(whiskyServiceEdit.toEntity());
    }

    public List<Whisky> findByMenuId(Long menuId) {
        return whiskyRepository.findByMenuId(menuId);
    }
}
