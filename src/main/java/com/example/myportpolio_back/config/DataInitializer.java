package com.example.myportpolio_back.config;

import com.example.myportpolio_back.service.SkillService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DataInitializer implements CommandLineRunner {
    private final SkillService skillService;

    @Override
    public void run(String... args) throws Exception {
        // 애플리케이션 시작 시 기술 스택 초기화 실행
        skillService.initDefaultSkills();
    }
}