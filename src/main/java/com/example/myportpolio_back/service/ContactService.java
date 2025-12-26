package com.example.myportpolio_back.service;
import com.example.myportpolio_back.dto.ContactRequestDto;
import com.example.myportpolio_back.entity.Contact;
import com.example.myportpolio_back.repository.ContactRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class ContactService {

    private final ContactRepository contactRepository;

    public void saveContact(ContactRequestDto requestDto) {
        Contact contact = Contact.builder()
                .senderName(requestDto.getSenderName())
                .email(requestDto.getEmail())
                .subject(requestDto.getSubject())
                .message(requestDto.getMessage())
                .build();

        contactRepository.save(contact);
    }
}