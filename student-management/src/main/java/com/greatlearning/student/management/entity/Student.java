package com.greatlearning.student.management.entity;

import javax.persistence.Column;



import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="student")

@Data
@NoArgsConstructor
public class Student {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private long id;
	@Column(name="fname")
	private String firstName;
	@Column(name="lname")
    private String lastName;
	@Column(name="course")
	private String course;
	@Column(name="country")
	private String country;
	
			
	public Student(String firstName, String lastName, String course, String country) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.course = course;
		this.country = country;
	
      }

}
	

	

		        
			   
   
        
	    