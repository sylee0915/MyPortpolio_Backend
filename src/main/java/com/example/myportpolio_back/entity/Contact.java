package com.example.myportpolio_back.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Contact {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long contactId;

    private String senderName;
    private String email;
    private String subject;

    @Column(columnDefinition = "TEXT")
    private String message;

    @CreatedDate
    private LocalDateTime createdAt;

    @Builder
    public Contact(String senderName, String email, String subject, String message) {
        this.senderName = senderName;
        this.email = email;
        this.subject = subject;
        this.message = message;
        this.createdAt = LocalDateTime.now();
    }
}