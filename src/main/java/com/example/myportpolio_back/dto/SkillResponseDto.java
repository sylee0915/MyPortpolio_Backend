package com.example.myportpolio_back.dto;

import com.example.myportpolio_back.entity.Skill;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@AllArgsConstructor
@Builder
public class SkillResponseDto {
    private Long skillId;
    private String name;
    private String category;
    private String iconUrl;

    public static SkillResponseDto from(Skill skill) {
        return SkillResponseDto.builder()
                .skillId(skill.getSkillId())
                .name(skill.getName())
                .category(skill.getCategory())
                .iconUrl(skill.getIconUrl())
                .build();
    }
}