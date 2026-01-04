package com.example.myportpolio_back.service;

import com.example.myportpolio_back.dto.SkillResponseDto;
import com.example.myportpolio_back.repository.SkillRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class SkillService {
    private final SkillRepository skillRepository;

    public List<SkillResponseDto> getAllSkills() {
        return skillRepository.findAll().stream()
                .map(SkillResponseDto::from)
                .collect(Collectors.toList());
    }
}