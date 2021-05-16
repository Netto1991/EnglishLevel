package io.EnglishLevelGame.EnglishLevelGame.Services;

import java.util.List;

import io.EnglishLevelGame.EnglishLevelGame.students.Group;
import io.EnglishLevelGame.EnglishLevelGame.students.Student;

public interface StudentService {
	
	Student showOneStudent(Long studentId);
	
	List<Student> findAllStudentInOneGroup(Group group);
	
	Iterable<Student> findAllStudent();
	
	void saveStudent(Student student);
	
	void deleteStudent(Student student);
}
