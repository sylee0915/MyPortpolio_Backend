package com.example.myportpolio_back.service;

import com.example.myportpolio_back.entity.SiteConfig;
import com.example.myportpolio_back.repository.SiteConfigRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class SiteConfigService {
    private final SiteConfigRepository repository;

    public SiteConfig getConfig() {
        return repository.findCurrentConfig()
                .orElseGet(() -> repository.save(SiteConfig.builder()
                        .mainTitle("기본 타이틀")
                        .primaryColor("#374151")
                        .secondaryColor("#0D9488")
                        .build()));
    }

    @Transactional
    public void updateConfig(SiteConfig updateData) {
        SiteConfig config = getConfig();
        config.update(
                updateData.getMainTitle(),
                updateData.getSubTitle(),
                updateData.getMainImageUrl(),
                updateData.getPrimaryColor(),
                updateData.getSecondaryColor(),
                updateData.getNavColor()
        );
    }
}