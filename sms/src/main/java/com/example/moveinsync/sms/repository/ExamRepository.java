package com.example.moveinsync.sms.repository;


import com.example.moveinsync.sms.entity.Exam;
import com.example.moveinsync.sms.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface ExamRepository extends JpaRepository<Exam,Integer> {

    //Selects student Id who secured highest marks within the given dates
    @Query(
            value = "SELECT Student_id from exam e Where marks = (SELECT max(marks) FROM exam e WHERE e.date >= ?1  AND e.date<= ?2)",
            nativeQuery = true)
    Optional<Integer> getTopStudentBtwDates(String fromDate, String toDate);


    //gets all exam marks details from within the given dates and specified classroom
    @Query(
            value = "SELECT * FROM exam e WHERE e.date >= ?1  AND e.date<= ?2 AND e.student_id IN (Select id from Student s where s.class_name = ?3",
            nativeQuery = true)
    Optional<List<Exam>> getExamMarksBtwDatesByClass(String fromDate, String toDate,String className);
}
