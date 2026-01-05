package com.example.myportpolio_back.service;

import com.example.myportpolio_back.dto.ProjectRequestDto;
import com.example.myportpolio_back.dto.ProjectResponseDto;
import com.example.myportpolio_back.entity.Project;
import com.example.myportpolio_back.entity.Skill;
import com.example.myportpolio_back.repository.ProjectRepository;
import com.example.myportpolio_back.repository.SkillRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ProjectService {
    private final ProjectRepository projectRepository;
    private final SkillRepository skillRepository;

    // 모든 프로젝트 조회
    public List<ProjectResponseDto> getAllProjects() {
        return projectRepository.findAll().stream()
                .map(ProjectResponseDto::from)
                .collect(Collectors.toList());
    }

    // 단일 프로젝트 조회 (추가)
    public ProjectResponseDto getProjectById(Long id) {
        Project project = projectRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("프로젝트를 찾을 수 없습니다. ID: " + id));
        return ProjectResponseDto.from(project);
    }

    // 새 프로젝트 저장 (관리자용)
    @Transactional
    public ProjectResponseDto saveProject(ProjectRequestDto requestDto) {
        // 1. 기술 스택 조회
        List<Skill> skills = skillRepository.findAllById(requestDto.getSkillIds());

        // 2. Project 엔티티 생성
        Project project = Project.builder()
                .title(requestDto.getTitle())
                .description(requestDto.getDescription())
                .period(requestDto.getPeriod())
                .teamSize(requestDto.getTeamSize())
                .content(requestDto.getContent())
                .githubUrl(requestDto.getGithubUrl())
                .demoUrl(requestDto.getDemoUrl())
                .thumbnailUrl(requestDto.getThumbnailUrl())
                .erdImageUrl(requestDto.getErdImageUrl())
                .architectureImageUrl(requestDto.getArchitectureImageUrl())
                .skills(skills)
                .build();

        // 3. 저장 및 DTO 반환
        Project savedProject = projectRepository.save(project);
        return ProjectResponseDto.from(savedProject);
    }

    @Transactional
    public ProjectResponseDto updateProject(Long id, ProjectRequestDto requestDto) {
        Project project = projectRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("프로젝트를 찾을 수 없습니다. ID: " + id));

        List<Skill> skills = skillRepository.findAllById(requestDto.getSkillIds());

        // 엔티티 필드 업데이트 (더티 체킹 활용)
        project.update(
                requestDto.getTitle(),
                requestDto.getDescription(),
                requestDto.getPeriod(),
                requestDto.getTeamSize(),
                requestDto.getContent(),
                requestDto.getGithubUrl(),
                requestDto.getDemoUrl(),
                requestDto.getThumbnailUrl(),
                requestDto.getErdImageUrl(),
                requestDto.getArchitectureImageUrl(),
                skills
        );

        return ProjectResponseDto.from(project);
    }

    @Transactional
    public void deleteProject(Long id) {
        Project project = projectRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("프로젝트를 찾을 수 없습니다. ID: " + id));
        projectRepository.delete(project);
    }
}