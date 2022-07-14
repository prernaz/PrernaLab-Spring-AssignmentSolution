package com.greatlearning.student.management.entity;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "users")
@Data
public class User {
		@Id
		@GeneratedValue(strategy = GenerationType.IDENTITY)
		@Column(name="id")
		private Long id ;
		@Column(name="username")
		private String username;
		@Column(name="password")
		private String password;
				
		@ManyToMany(cascade = CascadeType.ALL,
		fetch = FetchType.EAGER)
        @JoinTable(name = "user_roles",joinColumns = @JoinColumn(name = "user_id"),
			inverseJoinColumns = @JoinColumn(name = "role_id"))
		private List<Role> roles;
		
		public User(String username, String password, List<Role> roles) {
			super();
			this.username = username;
			this.password = password;
			this.roles = roles;
		}
				
   }