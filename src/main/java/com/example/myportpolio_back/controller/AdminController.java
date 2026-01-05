package com.example.myportpolio_back.controller;

import com.example.myportpolio_back.dto.ProjectRequestDto;
import com.example.myportpolio_back.dto.ProjectResponseDto;
import com.example.myportpolio_back.entity.SiteConfig;
import com.example.myportpolio_back.service.ProjectService;
import com.example.myportpolio_back.service.SiteConfigService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/admin")
@RequiredArgsConstructor
public class AdminController {
    private final ProjectService projectService;
    private final SiteConfigService siteConfigService;

    // 1. 관리자 비밀번호 검증용 API (신규 제안)
    // AdminPasswordFilter를 통과하면 인증된 것으로 간주함
    @GetMapping("/verify")
    public ResponseEntity<String> verifyAdmin() {
        return ResponseEntity.ok("인증 성공");
    }

    // 2. 새 프로젝트 등록 (기존 ProjectController에서 이동 및 경로 수정)
    @PostMapping("/projects")
    public ResponseEntity<ProjectResponseDto> createProject(@RequestBody ProjectRequestDto requestDto) {
        return ResponseEntity.ok(projectService.saveProject(requestDto));
    }

    // 3. 사이트 설정 업데이트 (기존 SiteConfigController에서 이동)
    @PutMapping("/config")
    public ResponseEntity<String> updateConfig(@RequestBody SiteConfig config) {
        siteConfigService.updateConfig(config);
        return ResponseEntity.ok("사이트 설정이 업데이트되었습니다.");
    }

    @PutMapping("/projects/{id}")
    public ResponseEntity<ProjectResponseDto> updateProject(@PathVariable Long id, @RequestBody ProjectRequestDto requestDto) {
        return ResponseEntity.ok(projectService.updateProject(id, requestDto));
    }

    @DeleteMapping("/projects/{id}")
    public ResponseEntity<Void> deleteProject(@PathVariable Long id) {
        projectService.deleteProject(id);
        return ResponseEntity.noContent().build();
    }
}