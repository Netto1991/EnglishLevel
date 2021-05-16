package io.EnglishLevelGame.EnglishLevelGame.Services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.EnglishLevelGame.EnglishLevelGame.students.Group;
import io.EnglishLevelGame.EnglishLevelGame.students.MyStudentRepo;
import io.EnglishLevelGame.EnglishLevelGame.students.Student;

@Service
public class StudentServiseImpl implements StudentService {
	
	@Autowired
	private MyStudentRepo studentRepo;
	
	

	@Override
	public Student showOneStudent(Long studentId) {
		return studentRepo.findById(studentId).get();
		
	}

	@Override
	public List<Student> findAllStudentInOneGroup(Group group) {
		
		return studentRepo.findByGroup(group);
	}

	@Override
	public Iterable<Student> findAllStudent() {
		
		return studentRepo.findAll();
	}

	@Override
	public void saveStudent(Student student) {
		studentRepo.save(student);
		
	}

	@Override
	public void deleteStudent(Student student) {
		studentRepo.delete(student);
		
	}

}
