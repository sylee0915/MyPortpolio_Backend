package com.example.myportpolio_back.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
@Table(name = "site_configs")
public class SiteConfig {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // 메인 페이지 콘텐츠
    private String mainTitle;
    private String subTitle;
    private String mainImageUrl;

    // 테마 색상 설정 (Hex Code: 예: #374151)
    private String primaryColor;    // 메인 배경 및 기본 색상
    private String secondaryColor;  // 포인트 및 버튼 색상
    private String navColor;        // 네비게이션 바 색상

    /**
     * 설정을 업데이트하는 비즈니스 로직
     */
    public void update(String mainTitle, String subTitle, String mainImageUrl,
                       String primaryColor, String secondaryColor, String navColor) {
        this.mainTitle = mainTitle;
        this.subTitle = subTitle;
        this.mainImageUrl = mainImageUrl;
        this.primaryColor = primaryColor;
        this.secondaryColor = secondaryColor;
        this.navColor = navColor;
    }
}