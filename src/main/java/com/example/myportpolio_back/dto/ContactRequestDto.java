package com.example.myportpolio_back.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ContactRequestDto {
    private String senderName;
    private String email;
    private String subject;
    private String message;
}