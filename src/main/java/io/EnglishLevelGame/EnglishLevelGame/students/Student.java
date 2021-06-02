package io.EnglishLevelGame.EnglishLevelGame.students;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;

import com.sun.istack.NotNull;

@Entity
@Table(name = "Students")
public class Student {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long Id;
	@NotNull
	@Size(min=2, max=15)
	private String name;
	@NotNull
	@Size(min=2, max=15)
	private String surname;
	@NotNull
	@Min(1)
	@Max(5)
	private int course;
	@NotNull
	@Max(10)
	private Integer level;
	
	@ManyToOne
    @JoinColumn(name="group_id", nullable=false)
	private Group group;
	
	
	public Student() {
	}

	public Student(Long groupName, String name, String surname, int course) {
		this.name = name;
		this.surname = surname;
		this.course = course;
		this.level = 0;
	}

	public Long getId() {
		return Id;
	}


	public Group getGroup() {
		return group;
	}

	public void setGroup(Group group) {
		this.group = group;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public int getCourse() {
		return course;
	}

	public void setCourse(int course) {
		this.course = course;
	}

	public Integer getLevel() {
		return level;
	}

	public void setLevel(Integer level) {
		this.level = level;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		String result = this.getName() + " " + this.getSurname();
		return result;
	}
	
	

}
