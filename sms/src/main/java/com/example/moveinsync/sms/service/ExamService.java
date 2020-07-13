package com.example.moveinsync.sms.service;


import com.example.moveinsync.sms.dto.ExamDTO;
import com.example.moveinsync.sms.dto.StudentDTO;
import com.example.moveinsync.sms.entity.Exam;
import com.example.moveinsync.sms.entity.Student;
import com.example.moveinsync.sms.entity.Subject;
import com.example.moveinsync.sms.repository.ExamRepository;
import com.example.moveinsync.sms.repository.StudentRepository;
import com.example.moveinsync.sms.repository.SubjectRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ExamService {

    @Autowired
    private ExamRepository examRepository;
    @Autowired
    private StudentRepository studentRepository;
    @Autowired
    private SubjectRepository subjectRepository;
    @Autowired
    private ModelMapper modelMapper;


    public String addExamMarks(ExamDTO examDTO) {
        try {
            Optional<Student> student = studentRepository.findById(examDTO.getStudentId());
            if (student.isPresent() == false) {
                return "Student dosen't exit with given id";
            }
            Optional<Subject> subject = subjectRepository.findById(examDTO.getSubjectId());
            if (subject.isPresent() == false) {
                return "Subject dosen't exit with given id";
            }
            Exam exam = modelMapper.map(examDTO, Exam.class);
            exam.setStudent(student.get());
            exam.setSubject(subject.get());
            System.out.println(exam);
            examRepository.save(exam);
            return "Marks added successfully";
        } catch (Exception e) {
            return "Sorry there was an error please try later";
        }
    }

    public String getTopStudent() {
            try {
                Optional<List<Exam>> examList = Optional.ofNullable(examRepository.findAll());
                if(!examList.isPresent())
                    return "exam db is empty";
                Double maxMark = 0.0;
                Student topStudent = new Student();
                for(Exam examDetail : examList.get()) {
                    if (examDetail.getMarks() > maxMark) {
                        maxMark = examDetail.getMarks();
                        topStudent = examDetail.getStudent();
                    }
                }
                return modelMapper.map(topStudent,StudentDTO.class).toString();
            } catch (Exception e) {
                return "Sorry there was an error please try later";
            }

        }


    public String getTopStudentBtwDates(String fromDate, String toDate) {
        try {
            Optional<Integer> student_id = examRepository.getTopStudentBtwDates(fromDate, toDate);
            if(!student_id.isPresent()) {
                return "No top student exits with the given dates";
            }
            Optional<Student> student = studentRepository.findById(student_id.get());
            if(!student.isPresent()) {
                return "Student not present in the db";
            }

            StudentDTO studentDTO = modelMapper.map(student.get(),StudentDTO.class);
            return studentDTO.toString();
        } catch (Exception e) {
            return "Sorry there was an error please try later";
        }
    }


    public String getTopStudentBtwDatesByClass(String fromDate, String toDate, String className) {
        try {
            Optional<List<Exam>> marksList = examRepository.getExamMarksBtwDatesByClass(fromDate, toDate, className);
            if(!marksList.isPresent()) {
                return "No marks exits with the given dates and class";
            }
            Double maxMark = 0.0;
            Student topStudent = new Student();
            for(Exam examMarks : marksList.get()) {
                if (examMarks.getMarks() > maxMark) {
                    maxMark = examMarks.getMarks();
                    topStudent = examMarks.getStudent();
                }
            }
            return modelMapper.map(topStudent,StudentDTO.class).toString();
        } catch (Exception e) {
            return "Sorry there was an error please try later";
        }
    }
}


