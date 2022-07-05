package com.castro.microservices.services;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;

import org.hibernate.Filter;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.castro.microservices.models.Course;
import com.castro.microservices.repositories.ICourseRepository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Service
public class CourseService {

    @PersistenceContext
    private EntityManager courseManager;

    @Autowired
    private ICourseRepository iCourseRepository;

    public Course saveCourse(Course course) {
        return iCourseRepository.save(course);
    }

    public List<Course> getAllCourses(){
        return iCourseRepository.findAll();
    }

    public List<Course> getAvailableCourses(String start_date){


        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        LocalDate _start_date = LocalDate.parse(start_date, formatter);

        Filter filter = courseManager.unwrap(Session.class).enableFilter("filterByDate");
        filter.setParameter("_start_date", _start_date);
        List<Course> availableCourses = iCourseRepository.findAll();
        courseManager.unwrap(Session.class).disableFilter("filterByDate");

        return availableCourses;
    }

    public void deleteCourse(Long id) {
        iCourseRepository.deleteById(id);
    }

}

