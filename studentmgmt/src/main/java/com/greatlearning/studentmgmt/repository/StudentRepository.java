package com.greatlearning.studentmgmt.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.greatlearning.studentmgmt.entity.Student;

public interface StudentRepository extends JpaRepository<Student, Integer> {
	
	//List<Student> findByFirstNameContainsAndCourseContainsAllIgnoreCase(String firstName, String course);

}