package com.example.myportpolio_back.controller;

import com.example.myportpolio_back.dto.ProjectRequestDto;
import com.example.myportpolio_back.dto.ProjectResponseDto;
import com.example.myportpolio_back.dto.SkillResponseDto;
import com.example.myportpolio_back.entity.SiteConfig;
import com.example.myportpolio_back.service.ProjectService;
import com.example.myportpolio_back.service.SiteConfigService;
import com.example.myportpolio_back.service.SkillService;
import lombok.Getter;
import lombok.Setter; // 추가
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor; // 추가
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/admin")
@RequiredArgsConstructor
public class AdminController {
    private final ProjectService projectService;
    private final SiteConfigService siteConfigService;
    private final SkillService skillService;

    @GetMapping("/verify")
    public ResponseEntity<String> verifyAdmin() {
        return ResponseEntity.ok("인증 성공");
    }

    @PostMapping("/projects")
    public ResponseEntity<ProjectResponseDto> createProject(@RequestBody ProjectRequestDto requestDto) {
        return ResponseEntity.ok(projectService.saveProject(requestDto));
    }

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

    @PostMapping("/skills")
    public ResponseEntity<SkillResponseDto> addSkill(@RequestBody SkillAddRequest request) {
        return ResponseEntity.ok(skillService.addSkill(request.getName(), request.getCategory()));
    }

    @DeleteMapping("/skills/{id}")
    public ResponseEntity<Void> deleteSkill(@PathVariable Long id) {
        skillService.deleteSkill(id);
        return ResponseEntity.noContent().build();
    }

    @Getter
    @Setter // 추가: JSON 바인딩을 위해 필수
    @NoArgsConstructor
    @AllArgsConstructor // 추가
    static class SkillAddRequest {
        private String name;
        private String category;
    }
}