package com.example.moveinsync.sms.entity;

import lombok.*;
import org.springframework.stereotype.Service;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    private Integer id;

    @Column(name = "name", nullable = false)
    public String name;

    @Column(name = "class_name", nullable = false)
    public String className;

}
