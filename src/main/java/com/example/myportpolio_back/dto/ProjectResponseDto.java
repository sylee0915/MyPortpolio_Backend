package com.example.myportpolio_back.dto;

import com.example.myportpolio_back.entity.Project;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ProjectResponseDto {
    private Long id;
    private String title;
    private String description;
    private String githubUrl;
    private String thumbnailUrl;

    public static ProjectResponseDto from(Project project) {
        return new ProjectResponseDto(
                project.getProjectId(),
                project.getTitle(),
                project.getDescription(),
                project.getGithubUrl(),
                project.getThumbnailUrl()
        );
    }
}