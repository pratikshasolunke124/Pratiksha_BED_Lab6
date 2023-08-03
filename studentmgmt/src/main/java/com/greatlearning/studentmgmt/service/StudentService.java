package com.greatlearning.studentmgmt.service;

import java.util.List;

import com.greatlearning.studentmgmt.entity.Student;
import com.greatlearning.studentmgmt.exception.StudentException;

public interface StudentService {

	public List<Student> findAll();

	public Student findById(int theId) throws StudentException;

	public Student save(Student thestudent);

	public void deleteById(int theId);

	//public List<Student> searchBy(String firstName, String course);

}