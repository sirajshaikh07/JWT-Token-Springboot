package com.security.securityproject.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.security.securityproject.Entity.Student;

@Repository
public interface StudentRepo extends JpaRepository<Student,Integer>
{
@Query(nativeQuery = true, value = "SELECT * FROM student WHERE name = :name")
public Student getByUserName(@Param("name") String name);

}
    

