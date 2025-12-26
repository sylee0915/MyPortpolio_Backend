package com.example.myportpolio_back.controller;

import com.example.myportpolio_back.dto.ProjectResponseDto;
import com.example.myportpolio_back.service.ProjectService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/projects")
@RequiredArgsConstructor
public class ProjectController {
    private final ProjectService projectService;

    @GetMapping
    public ResponseEntity<List<ProjectResponseDto>> getProjects() {
        return ResponseEntity.ok(projectService.getAllProjects());
    }
}