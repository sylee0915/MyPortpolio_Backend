package com.example.myportpolio_back.service;

import com.example.myportpolio_back.dto.SkillResponseDto;
import com.example.myportpolio_back.entity.Skill;
import com.example.myportpolio_back.repository.SkillRepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class SkillService {
    private final SkillRepository skillRepository;

    @PostConstruct
    @Transactional
    public void initDefaultSkills() {
        if (skillRepository.count() == 0) {
            List<Skill> defaultSkills = Arrays.asList(
                    Skill.builder().name("Java").category("Backend").build(),
                    Skill.builder().name("Spring Boot").category("Backend").build(),
                    Skill.builder().name("React").category("Frontend").build(),
                    Skill.builder().name("TypeScript").category("Frontend").build(),
                    Skill.builder().name("MariaDB").category("Database").build(),
                    Skill.builder().name("AWS").category("DevOps").build()
            );
            skillRepository.saveAll(defaultSkills);
        }
    }

    /**
     * 모든 기술 스택 조회 (DTO 반환 컨벤션 준수)
     */
    public List<SkillResponseDto> getAllSkills() {
        return skillRepository.findAll().stream()
                .map(SkillResponseDto::from)
                .collect(Collectors.toList());
    }

    /**
     * 관리자용: 새로운 기술 스택 추가
     */
    @Transactional
    public SkillResponseDto addSkill(String name, String category) {
        Skill skill = Skill.builder()
                .name(name)
                .category(category)
                .build();
        return SkillResponseDto.from(skillRepository.save(skill));
    }

    /**
     * 관리자용: 기술 스택 삭제
     */
    @Transactional
    public void deleteSkill(Long id) {
        if (!skillRepository.existsById(id)) {
            throw new RuntimeException("해당 기술 스택이 존재하지 않습니다.");
        }
        skillRepository.deleteById(id);
    }
}