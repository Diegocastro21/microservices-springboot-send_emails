package com.castro.microservices.repositories;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.castro.microservices.models.Student;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface IStudentRepository extends JpaRepository<Student, String> {

    @Transactional
    @Modifying
    @Query("UPDATE Student s SET s.status = 0 WHERE s.id =:id")
    void softDeleteStudent(@Param("id") String id);

}

