package com.example.myportpolio_back.controller;

import com.example.myportpolio_back.dto.SkillResponseDto;
import com.example.myportpolio_back.service.SkillService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/skills")
@RequiredArgsConstructor
public class SkillController {
    private final SkillService skillService;

    // 모든 기술 스택 조회
    @GetMapping
    public ResponseEntity<List<SkillResponseDto>> getAllSkills() {
        return ResponseEntity.ok(skillService.getAllSkills());
    }
}