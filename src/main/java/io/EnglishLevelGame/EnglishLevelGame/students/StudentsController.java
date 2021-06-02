package io.EnglishLevelGame.EnglishLevelGame.students;


import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
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
	Integer groupId, @Valid Student student, BindingResult bindingResult) throws DuplicateStudentException {
			if (bindingResult.hasErrors()) {
				return "addEditStudent";
			}
		Group group = groupService.showOneGroup(groupId);
		student.setGroup(group);
		Student tempStudent = studentService.showOneStudent(student.getId());
		if(tempStudent != null) {
			throw new DuplicateStudentException("This Student already exist");
		}
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
	
	@PostMapping("{studentId}/editstudents")
	public String editStudents
	(@Valid Student student, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			return "addEditStudent";
		}
		studentService.saveStudent(student);
		return "redirect:/";
	}
	
	@GetMapping("{studentId}")
	public String showStudents
	(@PathVariable("studentId") Long studentId, 
	Model model) {
		model.addAttribute("student", studentService.showOneStudent(studentId));
		return "showStudent";
		}
	
	@PostMapping(value = "{studentId}", name = "incrimentLevel")
	public String incrementLevel(@PathVariable("studentId") Long studentId,
								@PathVariable("groupId") int groupId,
									Model model) {
		Student student = studentService.showOneStudent(studentId);
		int level = student.getLevel();
		if(level < 5) {
			student.setLevel(level+1);
			studentService.saveStudent(student);
		}
		model.addAttribute("groupId", groupId);
		return "redirect:/{groupId}/students";
	}
	
	@PostMapping(value = "{studentId}", name = "decrimentLevel")
	public String decrementLevel(@PathVariable("studentId") Long studentId,
								@PathVariable("groupId") int groupId,
									Model model) {
		Student student = (Student) model.getAttribute("student");
		int level = student.getLevel();
		if(level != 0) {
			student.setLevel(level-1);
			studentService.saveStudent(student);
		}
		model.addAttribute("groupId", groupId);
		return "redirect:/{groupId}/students/";
	}
		
	@DeleteMapping()
	public String removeStudent(Student student) {
		studentService.deleteStudent(student);
		return "redirect:/";
	}


}
