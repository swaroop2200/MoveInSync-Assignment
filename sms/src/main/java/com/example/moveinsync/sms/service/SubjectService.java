package com.example.moveinsync.sms.service;

import com.example.moveinsync.sms.entity.Subject;
import com.example.moveinsync.sms.repository.SubjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SubjectService {

    @Autowired
    private SubjectRepository subjectRepository;

    public String addSubject(Subject subject) {
        try{
            subjectRepository.save(subject);
            return "Subject added successfully";
        } catch (Exception e) {
            return "Sorry there was an error please try later";
        }
    }
}
