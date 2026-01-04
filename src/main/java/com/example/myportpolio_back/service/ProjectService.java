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

    // 새 프로젝트 저장 (관리자용)
    @Transactional
    public ProjectResponseDto saveProject(ProjectRequestDto requestDto) {
        // 1. 기술 스택 조회
        List<Skill> skills = skillRepository.findAllById(requestDto.getSkillIds());

        // 2. Project 엔티티 생성 (Builder 패턴 권장)
        // Note: Project 엔티티에 필드와 @Builder 또는 생성자가 추가되어 있어야 합니다.
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
}