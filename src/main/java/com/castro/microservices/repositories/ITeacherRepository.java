package com.castro.microservices.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.castro.microservices.models.Teacher;

import java.util.List;

@Repository
public interface ITeacherRepository extends JpaRepository<Teacher, String> {

    @Query(value = "SELECT t FROM Teacher t WHERE t.salary>=:salary AND SIZE(t.courses)>=:courses_num")
    List<Teacher> customTeacherQuery(@Param("salary") Double salary, @Param("courses_num") Integer courses_num);

}

