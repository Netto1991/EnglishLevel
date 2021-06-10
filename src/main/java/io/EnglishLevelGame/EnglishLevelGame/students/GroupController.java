package io.EnglishLevelGame.EnglishLevelGame.students;



import java.security.Principal;
import java.util.Set;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import io.EnglishLevelGame.EnglishLevelGame.Services.GroupService;


@Controller
@RequestMapping("/")
public class GroupController {
	
	
	@Autowired
	private GroupService groupService;
	
	
	@GetMapping()
	public String index(Model model, Principal principal) {
		model.addAttribute("groups", groupService.findAllGroup());
		if(principal != null) {
		model.addAttribute("loginUser",  principal.getName());
		}
		return "index";
		
	}
	
	@GetMapping("/addgroup")
	@PreAuthorize("hasRole(STUDENT.name())")
	public String addGroup(Model model) {
		model.addAttribute("group", new Group());
		return "editGroup";
	}
	@GetMapping("/{groupId}")
	@PreAuthorize("hasRole(STUDENT.name())")
	public String showGroup(@PathVariable("groupId") Integer groupId, 
			Model model) {
		
		model.addAttribute("group", groupService.showOneGroup(groupId));
		return "showGroup";
	}
	
	@GetMapping("/{groupId}/edit")
	public String showEditGroupForm(@PathVariable("groupId") 
		Integer groupId, Model model) {
		Group group = groupService.showOneGroup(groupId);
		model.addAttribute("group", group);
		if(group.getStudents().size() == 0) {
			System.out.println("LIST IS EMPTY");
		}
		for(Student student: group.getStudents()) {
			System.out.println(student.toString());
		}
		return "editGroup";
		}
	
	
	@PostMapping({"/addgroup"})
	public String addGroup(@Valid Group group, BindingResult bindingResult) 
	{
		if (bindingResult.hasErrors()) {
			return "editGroup";
		}
		groupService.saveGroup(group);
				return "redirect:/";
	}
	
	@PostMapping({"/{groupId}/edit"})
	public String editGroup(@Valid Group group, 
			@PathVariable("groupId") Integer groupId,
			BindingResult bindingResult) 
		{
			if (bindingResult.hasErrors()) {
				return "editGroup";
		}
		
		group.setGroupId(groupId);
		Set<Student> students = group.getStudents().stream()
				.peek((student) -> student.setGroup(group))
				.collect(Collectors.toSet());
		group.setStudents(students);
		groupService.saveGroup(group);
		
		return "redirect:/";
	}
	
	@DeleteMapping()
	public String removeGroup(Group group) {
		groupService.deleteGroup(group);
		return "redirect:/";
	}
	
}
