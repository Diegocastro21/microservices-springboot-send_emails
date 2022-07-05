package com.castro.microservices.controllers;

import java.util.List;

import com.castro.microservices.services.MailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.castro.microservices.models.Student;
import com.castro.microservices.services.PersonService;
import com.castro.microservices.services.StudentService;

@RestController
@RequestMapping("/api/colegio")
public class StudentController {

    @Autowired
    private StudentService studentService;

    @Autowired
    private PersonService personService;

    private MailService mailService;

    @PostMapping("/estudiante")
    public Student createStudent(@Validated @RequestBody Student student) {
        Student newStudent = studentService.saveStudent(student);

        if (newStudent != null) {
            mailService.sendEmail(newStudent.getEmail(), "INSCRIPCION REGISTRADA", newStudent.toString());
        }
        return studentService.saveStudent(student);
    }
    @GetMapping("/estudiantes")
    public List<Student> readStudents(){
        return studentService.getAllStudents();
    }

    @GetMapping("/estudiante/{id}")
     public Student findStudent(@PathVariable String id){
        return studentService.getStudent(id);
     }

    @PutMapping("/estudiante/{id}")
    public Student updateStudent(@PathVariable String id, @Validated @RequestBody Student student) {
        return studentService.saveStudent(student);
    }

    @PutMapping("/estudiantes/{id}")
    public void disableStudent(@PathVariable String id) {
        studentService.softDeleteStudent(id);
        personService.softDeletePerson(id);
    }

    @DeleteMapping("/estudiante/{id}")
    public void deleteStudent(@PathVariable String id) {
        personService.deletePerson(id);
    }


}