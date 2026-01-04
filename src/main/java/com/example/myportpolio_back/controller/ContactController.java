package com.example.myportpolio_back.controller;

import com.example.myportpolio_back.dto.ContactRequestDto;
import com.example.myportpolio_back.service.ContactService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/contacts")
@RequiredArgsConstructor
public class ContactController {
    private final ContactService contactService;

    @PostMapping
    public ResponseEntity<String> sendContact(@RequestBody ContactRequestDto requestDto) {
        contactService.saveContact(requestDto);
        return ResponseEntity.ok("메시지가 성공적으로 전송되었습니다.");
    }
}