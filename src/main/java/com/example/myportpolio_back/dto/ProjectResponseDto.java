package com.example.myportpolio_back.dto;

import com.example.myportpolio_back.entity.Project;
import com.example.myportpolio_back.entity.Skill;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@AllArgsConstructor
@Builder
public class ProjectResponseDto {
    private Long projectId;
    private String title;
    private String description;    // 프로젝트 개요
    private String period;         // 제작 기간
    private String teamSize;       // 제작 인원
    private String content;        // 주요 페이지 소개 및 상세 내용
    private String githubUrl;      // Git 주소
    private String demoUrl;        // 배포 사이트 주소
    private String thumbnailUrl;
    private String erdImageUrl;    // ERD 다이어그램 URL
    private String architectureImageUrl; // 기술 아키텍처 URL
    private List<String> skills;   // 기술 스택 이름 리스트 (또는 필요 시 Skill 객체 리스트)

    public static ProjectResponseDto from(Project project) {
        return ProjectResponseDto.builder()
                .projectId(project.getProjectId())
                .title(project.getTitle())
                .description(project.getDescription())
                .period(project.getPeriod())
                .teamSize(project.getTeamSize())
                .content(project.getContent())
                .githubUrl(project.getGithubUrl())
                .demoUrl(project.getDemoUrl())
                .thumbnailUrl(project.getThumbnailUrl())
                .erdImageUrl(project.getErdImageUrl())
                .architectureImageUrl(project.getArchitectureImageUrl())
                .skills(project.getSkills().stream()
                        .map(Skill::getName) // 기술 스택의 이름만 추출하여 리스트로 변환
                        .collect(Collectors.toList()))
                .build();
    }
}