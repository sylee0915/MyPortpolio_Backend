package com.example.myportpolio_back.service;

import com.example.myportpolio_back.dto.SkillResponseDto;
import com.example.myportpolio_back.entity.Skill;
import com.example.myportpolio_back.repository.SkillRepository;
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

    // 초기 데이터 로딩을 위한 별도 메서드
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

    public List<SkillResponseDto> getAllSkills() {
        return skillRepository.findAll().stream()
                .map(SkillResponseDto::from)
                .collect(Collectors.toList());
    }

    @Transactional
    public SkillResponseDto addSkill(String name, String category) {
        Skill skill = Skill.builder()
                .name(name)
                .category(category)
                .build();
        return SkillResponseDto.from(skillRepository.save(skill));
    }

    @Transactional
    public void deleteSkill(Long id) {
        if (!skillRepository.existsById(id)) {
            throw new RuntimeException("해당 기술 스택이 존재하지 않습니다.");
        }
        skillRepository.deleteById(id);
    }
}