package com.greatlearning.student.management.service;

import com.greatlearning.student.management.entity.Student;
import java.util.List;

public interface StudentService {

public List<Student> findAll();
public Student findById(long id);
public void save(Student student);    
public void deleteById(long id); 

}
	