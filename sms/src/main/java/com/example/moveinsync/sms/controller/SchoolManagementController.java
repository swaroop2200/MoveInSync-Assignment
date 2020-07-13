package com.example.moveinsync.sms.controller;


import com.example.moveinsync.sms.dto.ExamDTO;
import com.example.moveinsync.sms.dto.StudentDTO;
import com.example.moveinsync.sms.entity.Exam;
import com.example.moveinsync.sms.entity.Student;
import com.example.moveinsync.sms.entity.Subject;
import com.example.moveinsync.sms.service.ExamService;
import com.example.moveinsync.sms.service.StudentService;
import com.example.moveinsync.sms.service.SubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(SchoolManagementController.SCM_ENDPOINT)
public class SchoolManagementController {

    public static final String SCM_ENDPOINT = "/scm/v1";
    public static final String ADD_STUDENT_API = "/addStudent";
    public static final String ADD_STUDENT_MARKS_API = "/addStudentMarks";
    public static final String ADD_SUBJECT_API = "/addSubject";
    public static final String TOP_STUDENT_API ="/topStudent";
    public static final String TOP_STUDENT_BTW_DATES_API ="/topStudentBtwDates";
    public static final String TOP_STUDENT_BTW_DATES_BYCLASS_API ="topStudentBtwDates/class";

    @Autowired
    private StudentService studentService;
    @Autowired
    private ExamService examService;
    @Autowired
    private SubjectService subjectService;

    /*
        to register a student
     */
    @PostMapping(ADD_STUDENT_API)
    public String addStudent(@RequestBody StudentDTO student) {
        return studentService.addStudent(student);
    }


    /*
        add marks of a student
     */
    @PostMapping(ADD_STUDENT_MARKS_API)
    public String addStudentMarks(@RequestBody ExamDTO examDTO) {
        return examService.addExamMarks(examDTO);
    }


    /*
        register a student
     */
    @PostMapping(ADD_SUBJECT_API)
    public String getAddSubject(@RequestBody Subject subject) {
        return subjectService.addSubject(subject);
    }


    /*
        get the highest scoring student of all subjects ,exams, class
     */
    @GetMapping(TOP_STUDENT_API)
    public String getTopStudent() {
        return examService.getTopStudent();

    }


    /*
        get the top student btw the specified dates
     */
    @GetMapping(TOP_STUDENT_BTW_DATES_API)
    public String getTopStudentBtwDates(@RequestParam("fromDate") String fromDate, @RequestParam("toDate") String toDate) {
        return examService.getTopStudentBtwDates(fromDate, toDate);
    }


    /*
        get the top student btw the specified dates and specified class
     */
    @GetMapping(TOP_STUDENT_BTW_DATES_BYCLASS_API)
    String getTopStudentBtwDatesByclass(@RequestParam("fromDate") String fromDate, @RequestParam("toDate") String toDate, @RequestParam("class") String className) {
        return examService.getTopStudentBtwDatesByClass(fromDate, toDate, className);
    }

}
