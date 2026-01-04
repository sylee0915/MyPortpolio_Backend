package com.example.myportpolio_back.controller;

import com.example.myportpolio_back.dto.ProjectRequestDto;
import com.example.myportpolio_back.dto.ProjectResponseDto;
import com.example.myportpolio_back.service.ProjectService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/projects")
@RequiredArgsConstructor
public class ProjectController {
    private final ProjectService projectService;

    // 1. 모든 프로젝트 목록 조회 (공개용)
    @GetMapping
    public ResponseEntity<List<ProjectResponseDto>> getProjects() {
        return ResponseEntity.ok(projectService.getAllProjects());
    }

    // 2. 새 프로젝트 등록 (관리자용)
    @PostMapping("/admin")
    public ResponseEntity<ProjectResponseDto> createProject(@RequestBody ProjectRequestDto requestDto) {
        ProjectResponseDto savedProject = projectService.saveProject(requestDto);
        return ResponseEntity.ok(savedProject);
    }
}