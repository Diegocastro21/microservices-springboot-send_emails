package com.castro.microservices.repositories;

import com.castro.microservices.models.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface IPersonRepository extends JpaRepository<Person, String> {

    @Transactional
    @Modifying
    @Query("UPDATE Person p SET p.status = false WHERE p.id =:id")
    void softDeletePerson(@Param("id") String id);



}


