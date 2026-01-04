package com.example.myportpolio_back.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProjectRequestDto {
    private String title;                // 프로젝트 명
    private String description;          // 프로젝트 개요
    private String period;               // 프로젝트 제작 기간
    private String teamSize;             // 제작 인원
    private String content;              // 주요 페이지 소개 및 상세 내용
    private String githubUrl;            // Git 주소
    private String demoUrl;              // 배포 사이트 주소 (데모 URL)
    private String thumbnailUrl;         // 썸네일 이미지 URL
    private String erdImageUrl;          // ERD 다이어그램 URL
    private String architectureImageUrl; // 기술 아키텍처 URL

    // 기술 스택은 기존 Skill 엔티티의 ID 리스트로 전달받습니다
    private List<Long> skillIds;
}