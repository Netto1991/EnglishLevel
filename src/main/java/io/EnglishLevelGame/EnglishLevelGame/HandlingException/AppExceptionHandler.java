package io.EnglishLevelGame.EnglishLevelGame.HandlingException;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import io.EnglishLevelGame.EnglishLevelGame.students.DuplicateStudentException;

@ControllerAdvice
public class AppExceptionHandler {
	
	
	@ExceptionHandler(DuplicateStudentException.class)
	public String handleDuplicateStudent(DuplicateStudentException e, Model model) {
		model.addAttribute("message", e.getMessage());
		return "error/duplicate";
	}

}
