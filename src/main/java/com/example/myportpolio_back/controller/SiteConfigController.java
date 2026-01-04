package com.example.myportpolio_back.controller;

import com.example.myportpolio_back.entity.SiteConfig;
import com.example.myportpolio_back.service.SiteConfigService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class SiteConfigController {
    private final SiteConfigService service;

    // 누구나 접근 가능 (프론트엔드 테마 적용용)
    @GetMapping("/config")
    public ResponseEntity<SiteConfig> getConfig() {
        return ResponseEntity.ok(service.getConfig());
    }

    // 관리자만 접근 가능 (X-Admin-Password 헤더 검증 대상)
    @PutMapping("/admin/config")
    public ResponseEntity<String> updateConfig(@RequestBody SiteConfig config) {
        service.updateConfig(config);
        return ResponseEntity.ok("사이트 설정이 업데이트되었습니다.");
    }
}