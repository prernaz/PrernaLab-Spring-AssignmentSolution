package com.greatlearning.student.management.controller;
import com.greatlearning.student.management.entity.Student;

import com.greatlearning.student.management.service.StudentService;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.context.Theme;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
@RequestMapping("/student")
	
public class StudentController {
	
/*@Autowired to inject StudentService as a private variable*/ 
	
	@Autowired
	private StudentService studentservice;
	 
	@RequestMapping("/list")
	public  String listStudents(Model model) {
		
		model.addAttribute("students", studentservice.findAll());
		return "student-list";
	}
	
			
	@RequestMapping("/showFormForAdd")
	public  String showFormForAdd(Model model) { 
		Student student = new Student();
	    model.addAttribute("student", student);
		model.addAttribute("mode", "Add");
		return "student-form";
	}
	
	@RequestMapping("/showFormForUpdate")
	public  String showFormForUpdate(Model model, @RequestParam("id") long id) { 
	 	model.addAttribute("student", studentservice.findById(id));
		model.addAttribute("mode", "Update");
		return "student-form";
	}
	  		
	@PostMapping("/save")
	public  String saveStudent(@RequestParam("id") long id, 
			@RequestParam("fname") String firstName,
			@RequestParam("lname") String lastName,
			@RequestParam("course") String course,
		    @RequestParam("country") String country){
		       
		   Student student = null;
		   if(id==0) {
			 student = new Student(firstName,lastName,course,country);
		    }
		  
		   else { 
			student = studentservice.findById(id);
			student.setFirstName(firstName);
			student.setLastName(lastName);
			student.setCourse(course);
			student.setCountry(country);
		}
		   
		   studentservice.save(student);
		  	return "redirect:/student/list";
	 }
	
	 	
	  @RequestMapping("/delete")
	  public String delete(@RequestParam("id") long id) {
		studentservice.deleteById(id);
		return "redirect:/student/list";
	   }
	  
	  @RequestMapping("/403")
	  public String showErrorpage() {
		  return "403";
	  }
	}
   
 /*@RequestMapping("/delete")
  public String delete(@RequestParam("studentId") long studentId) {
	
	studentservice.delete(studentId);
	return "redirect:list";
   }

  }
 */   
		  