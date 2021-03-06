package com.castro.microservices.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.castro.microservices.models.Student;
import com.castro.microservices.repositories.IStudentRepository;

@Service
public class StudentService {

    @Autowired
    private IStudentRepository iStudentRepository;

    public Student saveStudent(Student student) {
        return iStudentRepository.save(student);
    }

    public List<Student> getAllStudents(){
        return iStudentRepository.findAll();
    }


    public Student getStudent(String id){
        return iStudentRepository.getOne(id);
    }

    public void softDeleteStudent(String id) {
        iStudentRepository.softDeleteStudent(id);
    }



}



