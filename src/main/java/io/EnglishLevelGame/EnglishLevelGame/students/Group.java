package io.EnglishLevelGame.EnglishLevelGame.students;

import java.util.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.annotations.DynamicUpdate;

@Entity
@DynamicUpdate
@Table(name = "Groups")
public class Group {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer groupId;
	@NotNull
	@Size(min=2, max=30)
	private String name;
	@NotNull
	private Integer numberOfGroup;
	private Date startLearning;
	
	@OneToMany(cascade = CascadeType.ALL, 
        orphanRemoval = true)
	 private Set<Student> students;
	
	public Group() {
	}



	public Group(String name, Integer numberOfGroup, Date startLearning) {
		this.name = name;
		this.numberOfGroup = numberOfGroup;
		this.startLearning = startLearning;
	}
	
	

	public Group(@NotNull @Size(min = 2, max = 30) String name, @NotNull Integer numberOfGroup, Date startLearning,
			Set<Student> students) {
		super();
		this.name = name;
		this.numberOfGroup = numberOfGroup;
		this.startLearning = startLearning;
		this.students = students;
	}



	public Integer getGroupId() {
		return groupId;
	}
	

	public void setGroupId(Integer groupId) {
		this.groupId = groupId;
	}



	public String getName() {
		return name;
	}



	public void setName(String name) {
		this.name = name;
	}



	public Integer getNumberOfGroup() {
		return numberOfGroup;
	}



	public void setNumberOfGroup(Integer numberOfGroup) {
		this.numberOfGroup = numberOfGroup;
	}



	public Date getStartLearning() {
		return startLearning;
	}



	public void setStartLearning(Date startLearning) {
		this.startLearning = startLearning;
	}



	public Set<Student> getStudents() {
		return students;
	}



	public void setStudents(Set<Student> students) {
		if (this.students == null) {
			this.students = students;
		}
		else {
			this.students.retainAll(students);
			this.students.addAll(students);
		}
	}
	
	
	
	
}
