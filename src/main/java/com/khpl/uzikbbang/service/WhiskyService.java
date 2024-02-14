package com.khpl.uzikbbang.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.khpl.uzikbbang.entity.Whisky;
import com.khpl.uzikbbang.repository.WhiskyRepository;
import com.khpl.uzikbbang.request.WhiskyEdit;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class WhiskyService {

    private final WhiskyRepository whiskyRepository;

    public void save(Long menuId, WhiskyEdit whiskyEdit) {
        Whisky whisky = Whisky.builder()
                .menuId(menuId)
                .whiskyEdit(whiskyEdit)
                .build();

        whiskyRepository.save(whisky);
    }

    public List<Whisky> findByMenuId(Long menuId) {
        return whiskyRepository.findByMenuId(menuId);
    }
}
