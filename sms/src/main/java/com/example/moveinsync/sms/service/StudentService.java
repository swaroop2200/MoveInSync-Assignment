package com.example.moveinsync.sms.service;

import com.example.moveinsync.sms.dto.StudentDTO;
import com.example.moveinsync.sms.entity.Student;
import com.example.moveinsync.sms.repository.StudentRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StudentService {

    @Autowired
    private StudentRepository studentRepository;
    @Autowired
    private ModelMapper modelMapper;

    public String addStudent(StudentDTO studentDTO) {
        try {
            Student student = modelMapper.map(studentDTO, Student.class);
            studentRepository.save(student);
            return "Student added successfully";
        } catch (Exception e) {
            return "Sorry there was an error please try later";
        }
    }
}
