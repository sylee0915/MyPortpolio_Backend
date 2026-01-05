package com.example.myportpolio_back.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
@Table(name = "projects")
public class Project {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long projectId;

    @Column(nullable = false)
    private String title;

    private String description;    // 프로젝트 개요
    private String period;         // 제작 기간
    private String teamSize;       // 제작 인원

    @Column(columnDefinition = "TEXT")
    private String content;        // 주요 페이지 소개 및 상세 내용

    private String githubUrl;      // Git 주소
    private String demoUrl;        // 배포 사이트 주소
    private String thumbnailUrl;
    private String erdImageUrl;    // ERD 다이어그램
    private String architectureImageUrl; // 기술 아키텍처

    @ManyToMany
    @JoinTable(
            name = "project_skills",
            joinColumns = @JoinColumn(name = "project_id"),
            inverseJoinColumns = @JoinColumn(name = "skill_id")
    )
    @Builder.Default
    private List<Skill> skills = new ArrayList<>();

    public void update(String title, String description, String period, String teamSize,
                       String content, String githubUrl, String demoUrl, String thumbnailUrl,
                       String erdImageUrl, String architectureImageUrl, List<Skill> skills) {
        this.title = title;
        this.description = description;
        this.period = period;
        this.teamSize = teamSize;
        this.content = content;
        this.githubUrl = githubUrl;
        this.demoUrl = demoUrl;
        this.thumbnailUrl = thumbnailUrl;
        this.erdImageUrl = erdImageUrl;
        this.architectureImageUrl = architectureImageUrl;
        this.skills = skills;
    }
}