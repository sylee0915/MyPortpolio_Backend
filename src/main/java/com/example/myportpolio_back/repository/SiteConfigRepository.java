package com.example.myportpolio_back.repository;

import com.example.myportpolio_back.entity.SiteConfig;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface SiteConfigRepository extends JpaRepository<SiteConfig, Long> {
    default Optional<SiteConfig> findCurrentConfig() {
        return findAll().stream().findFirst();
    }
}