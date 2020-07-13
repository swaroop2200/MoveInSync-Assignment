package com.example.moveinsync.sms.repository;

import com.example.moveinsync.sms.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student,Integer> {
}
