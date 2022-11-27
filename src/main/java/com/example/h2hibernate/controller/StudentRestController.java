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
        return "El usuario " + student.getId() + " ha sido borrado.";
    }

    @PostMapping("/centro/matricular/{idStudent}/{idSubject}")
    public String matriculateUser(@PathVariable int idStudent, @PathVariable int idSubject) {
        String message = "";
        Subject subject = subjectService.findById(idSubject);
        Student student = userService.findById(idStudent);
        if (student.getSubjects().indexOf(subject) == -1) {
            subject.getStudents().add(student);
            subject.setTotalStudents(subject.getTotalStudents() + 1);
            student.getSubjects().add(subject);
            userService.update(student);
            subjectService.update(subject);
            message = "Añadida asignatura " + idSubject + " al estudiante " + idStudent;
        } else {
            message = "El alumno ya está matriculado en esa asignatura o esa asignatura no existe.";
        }

        return message;
    }

    @DeleteMapping("/centro/matricular/{idStudent}/{idSubject}")
    public String deregisterUser(@PathVariable int idStudent, @PathVariable int idSubject) {
        String message = "";
        Subject subject = subjectService.findById(idSubject);
        Student student = userService.findById(idStudent);
        if (student.getSubjects().indexOf(subject) != -1) {
            subject.getStudents().remove(student);
            subject.setTotalStudents(subject.getTotalStudents() - 1);
            student.getSubjects().remove(subject);
            userService.update(student);
            subjectService.update(subject);
            message = "Asignatura " + idSubject + " removida del estudiante " + idStudent;
        } else {
            message = "El alumno no está matriculado en esa asignatura o esa asignatura no existe.";
        }

        return message;
    }
}
