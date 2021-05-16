package io.EnglishLevelGame.EnglishLevelGame.students;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import io.EnglishLevelGame.EnglishLevelGame.Services.GroupService;
import io.EnglishLevelGame.EnglishLevelGame.Services.StudentService;



@Controller
@RequestMapping("{groupId}/students")

public class StudentsController {
	
	@Autowired
	private StudentService studentService;
	
	@Autowired
	private GroupService groupService;

	@GetMapping()
	public String GetAllStudentsInGroup
	(@PathVariable("groupId") Integer groupId, Model model){
	
		Group group = groupService.showOneGroup(groupId);
		List<Student> students = studentService.findAllStudentInOneGroup(group);
		model.addAttribute("students", students);
		return "studentList";
	}
	
	@GetMapping("/addstudents")
	public String showAddStudentspForm(@PathVariable("groupId") 
		Integer groupId, Model model) {
		model.addAttribute("student", new Student());
		return "addEditStudent";
		}
	
	@PostMapping("/addstudents")
	public String addStudents
	(@PathVariable("groupId") 
	Integer groupId, Student student) {
		Group group = groupService.showOneGroup(groupId);
		student.setGroup(group);
		studentService.saveStudent(student);
		return "redirect:/";
	}
	
	@GetMapping("{studentId}/editstudents")
	public String showEditStudentspForm
	(@PathVariable("studentId") Long studentId, 
	Model model) {
		model.addAttribute("student", studentService.showOneStudent(studentId));
		return "addEditStudent";
		}
	
	@GetMapping("{studentId}")
	public String showStudents
	(@PathVariable("studentId") Long studentId, 
	Model model) {
		model.addAttribute("student", studentService.showOneStudent(studentId));
		return "showStudent";
		}
	
	@PostMapping("{studentId}/editstudents")
	public String editStudents
	(Student student) {
		studentService.saveStudent(student);
		return "redirect:/";
	}
	
	@DeleteMapping()
	public String removeStudent(Student student) {
		studentService.deleteStudent(student);
		return "redirect:/";
	}
	
	

}
