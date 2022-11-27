package com.example.h2hibernate.controller;

import com.example.h2hibernate.model.Student;
import com.example.h2hibernate.model.Subject;
import com.example.h2hibernate.service.StudentServiceImpl;
import com.example.h2hibernate.service.SubjectServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class StudentRestController {

    @Autowired
    private SubjectServiceImpl subjectService;

    @Autowired
    private StudentServiceImpl userService;

    @GetMapping("/centro/alumnos")
    public List<Student> findAll() {

        return userService.findAll();
    }

    @GetMapping("/centro/alumnos/{studentId}")
    public Student getUser(@PathVariable int studentId) {
        return userService.findById(studentId);
    }

    @PostMapping("/centro/alumnos/new")
    public Student addUser(@RequestBody Student student) {
        userService.save(student);
        return student;
    }

    @PutMapping("/centro/alumnos/update")
    public Student updateUser(@RequestBody Student student) {
        userService.update(student);
        return student;
    }

    @DeleteMapping("/centro/alumnos/{studentId}")
    public String deleteUser(@PathVariable int studentId) {
        Student student = userService.findById(studentId);
        userService.deleteById(studentId);
        return "Deleted user id = " + student.getId();
    }

    @PutMapping("/centro/matricular/{idStudent}/{idSubject}")
    public String matriculateUser(@PathVariable int idStudent, @PathVariable int idSubject) {
        Subject subject = subjectService.findById(idSubject);
        Student student = userService.findById(idStudent);
        subject.getStudents().add(student);
        subject.setTotalStudents(subject.getTotalStudents() + 1);
        student.getSubjects().add(subject);
        userService.update(student);
        subjectService.update(subject);
        return "Añadida asignatura " + idSubject + " al estudiante " + idStudent;
    }
    @DeleteMapping("/centro/matricular/{idStudent}/{idSubject}")
    public String deregisterUser(@PathVariable int idStudent, @PathVariable int idSubject) {
        Subject subject = subjectService.findById(idSubject);
        Student student = userService.findById(idStudent);
        subject.getStudents().remove(student);
        subject.setTotalStudents(subject.getTotalStudents() - 1);
        student.getSubjects().remove(subject);
        userService.update(student);
        subjectService.update(subject);
        return "Asignatura removida " + idSubject + " del estudiante " + idStudent;
    }
}
