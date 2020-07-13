package com.example.moveinsync.sms.repository;

import com.example.moveinsync.sms.entity.Subject;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SubjectRepository extends JpaRepository<Subject,Integer> {
}
