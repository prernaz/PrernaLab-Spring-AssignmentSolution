package com.greatlearning.student.management.service;

import java.util.List;
import javax.transaction.Transactional;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.greatlearning.student.management.entity.Student;
import com.greatlearning.student.management.repository.StudentRepository;

// StudentServiceImpl class implements the StudentService interface 
//and annotated with @Service     
//@Autowired to inject StudentRepository as a private variable 
//implement methods by using the studentRepository instance.

@Service
public class StudentServiceImpl implements StudentService{
     
	 @Autowired
	 private StudentRepository studentRepo;    
    	
	@Override
	public List<Student> findAll() {
	    return studentRepo.findAll();
	}


    @Override
	public Student findById(long id) {
	    return studentRepo.findById(id).get();
	}
	

	@Override
	public void save(Student student) {
		studentRepo.save(student);
	}

	@Override
	public void deleteById(long id) {
		studentRepo.deleteById(id);
	}

}

