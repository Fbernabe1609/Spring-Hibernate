package com.example.h2hibernate.controller;

import com.example.h2hibernate.model.Subject;
import com.example.h2hibernate.service.StudentServiceImpl;
import com.example.h2hibernate.service.SubjectServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class SubjectRestController {
    @Autowired
    private SubjectServiceImpl subjectService;

    @Autowired
    private StudentServiceImpl studentService;

    @GetMapping("/centro/asignaturas")
    public List<Subject> findAll() {
        return subjectService.findAll();
    }

    @GetMapping("/centro/asignaturas/{id}")
    public Subject getSubject(@PathVariable int id) {
        return subjectService.findById(id);
    }

    @PostMapping("/centro/asignaturas/new")
    public Subject addSubject(@RequestBody Subject subject) {
        subjectService.save(subject);
        return subject;
    }

    @PutMapping("/centro/asignaturas/update")
    public Subject updateSubject(@RequestBody Subject subject) {
        subjectService.update(subject);
        return subject;
    }

    @DeleteMapping("/centro/asignaturas/{id}")
    public String deleteSubject(@PathVariable int id) {
        Subject subject = subjectService.findById(id);
        subjectService.deleteById(id);
        return "Deleted subject id = " + subject.getId();
    }
}
