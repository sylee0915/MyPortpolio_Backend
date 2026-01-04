package com.example.myportpolio_back.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "skills")
public class Skill {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long skillId;

    private String name;
    private String category; // e.g., "Frontend", "Backend"
    private String iconUrl;

    @Builder
    public Skill(String name, String category, String iconUrl) {
        this.name = name;
        this.category = category;
        this.iconUrl = iconUrl;
    }
}