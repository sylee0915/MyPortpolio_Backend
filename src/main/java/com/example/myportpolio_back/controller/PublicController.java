package com.example.myportpolio_back.controller;

import com.example.myportpolio_back.dto.ContactRequestDto;
import com.example.myportpolio_back.dto.ProjectResponseDto;
import com.example.myportpolio_back.dto.SkillResponseDto;
import com.example.myportpolio_back.entity.SiteConfig;
import com.example.myportpolio_back.service.ContactService;
import com.example.myportpolio_back.service.ProjectService;
import com.example.myportpolio_back.service.SiteConfigService;
import com.example.myportpolio_back.service.SkillService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class PublicController {
    private final ProjectService projectService;
    private final SkillService skillService;
    private final ContactService contactService;
    private final SiteConfigService siteConfigService;

    // 1. 프로젝트 관련 (기존 ProjectController에서 이동)
    @GetMapping("/projects")
    public ResponseEntity<List<ProjectResponseDto>> getProjects() {
        return ResponseEntity.ok(projectService.getAllProjects());
    }

    @GetMapping("/projects/{id}")
    public ResponseEntity<ProjectResponseDto> getProject(@PathVariable Long id) {
        return ResponseEntity.ok(projectService.getProjectById(id));
    }

    // 2. 기술 스택 관련 (기존 SkillController에서 이동)
    @GetMapping("/skills")
    public ResponseEntity<List<SkillResponseDto>> getAllSkills() {
        return ResponseEntity.ok(skillService.getAllSkills());
    }

    // 3. 연락처 전송 관련 (기존 ContactController에서 이동)
    @PostMapping("/contacts")
    public ResponseEntity<String> sendContact(@RequestBody ContactRequestDto requestDto) {
        contactService.saveContact(requestDto);
        return ResponseEntity.ok("메시지가 성공적으로 전송되었습니다.");
    }

    // 4. 사이트 설정 조회 (기존 SiteConfigController에서 이동)
    @GetMapping("/config")
    public ResponseEntity<SiteConfig> getConfig() {
        return ResponseEntity.ok(siteConfigService.getConfig());
    }
}