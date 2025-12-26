package com.example.myportpolio_back.service;

import com.example.myportpolio_back.dto.ProjectResponseDto;
import com.example.myportpolio_back.repository.ProjectRepository;
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

    public List<ProjectResponseDto> getAllProjects() {
        return projectRepository.findAll().stream()
                .map(ProjectResponseDto::from)
                .collect(Collectors.toList());
    }
}