package com.greatlearning.studentmgmt.controller;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.greatlearning.studentmgmt.entity.Student;
import com.greatlearning.studentmgmt.exception.StudentException;
import com.greatlearning.studentmgmt.service.StudentService;

import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping("/students")
@Slf4j
public class MyStudentController {

	@Autowired
	private StudentService studentService;

	@RequestMapping("/list")
	public String listStudents(Model theModel) {
		List<Student> studentList = studentService.findAll();
		theModel.addAttribute("Students", studentList);
		return "list-Students";
	}

	@RequestMapping("/showFormForAdd")
	public String showFormForAdd(Model theModel) {
		Student theStudent = new Student();
		theModel.addAttribute("Student", theStudent);
		return "Student-form";
	}

	@RequestMapping("/showFormForUpdate")
	public String showFormForUpdate(@RequestParam("studentId") int theId, Model theModel) {

		try {
			Student theStudent = studentService.findById(theId);
			theModel.addAttribute("Student", theStudent);
		} catch (StudentException e) {
			// log.error(e.getMessage());
			System.out.println(e.getMessage());
		}
		return "Student-form";
	}

	@PostMapping("/save")
	public String save(@RequestParam("id") int id, @RequestParam("firstName") String firstName,
			@RequestParam("lastName") String lastName, @RequestParam("course") String course,
			@RequestParam("country") String country) {
		Student theStudent = new Student();
		if (id != 0) {
			try {
				theStudent = studentService.findById(id);
				theStudent.setFirstName(firstName);
				theStudent.setLastName(lastName);
				theStudent.setCourse(course);
				theStudent.setCountry(country);
			} catch (StudentException e) {
				// log.error(e.getMessage());
				System.out.println(e.getMessage());
			}
		} else
			theStudent = new Student(firstName, lastName, course, country);

		studentService.save(theStudent);
		return "redirect:/students/list";
	}

	@RequestMapping("/delete")
	public String delete(@RequestParam("studentId") Integer id) {
		studentService.deleteById(id);

		return "redirect:/students/list";
	}

	@RequestMapping(value = "/403")
	public ModelAndView accesssDenied(Principal user) {
		ModelAndView model = new ModelAndView();
		if (user != null) {
			model.addObject("msg", "Hi " + user.getName() + ", you do not have permission to access this page!");
		} else {
			model.addObject("msg", "You do not have permission to access this page!");
		}
		model.setViewName("403");
		return model;
	}
}