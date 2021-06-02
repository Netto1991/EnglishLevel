package io.EnglishLevelGame.EnglishLevelGame.securityController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import io.EnglishLevelGame.EnglishLevelGame.authenticationUser.UserAuth;
import io.EnglishLevelGame.EnglishLevelGame.security.SecurityLoginService;
import io.EnglishLevelGame.EnglishLevelGame.security.UserService;
import io.EnglishLevelGame.EnglishLevelGame.security.UserValidate;
import io.EnglishLevelGame.EnglishLevelGame.uploadFiles.StorageService;

@Controller
public class SecurityController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private UserValidate userValidate;
	
	@Autowired
	private StorageService storageService;
	
//	@Autowired
//	private SecurityLoginService securityLoginService;
//	
//	
	
	@GetMapping("/registration")
	public String showRegistrationUser(Model model) {
		model.addAttribute("userForm", new UserAuth());
		return "registration";
	}
	
	@PostMapping("/registration")
	public String registrationUser(@RequestParam("file") MultipartFile file,
									RedirectAttributes redirectAttributes, 
									@ModelAttribute("userForm") UserAuth userForm, 
									BindingResult bindingResult) {
		userValidate.validate(userForm, bindingResult);
		if (bindingResult.hasErrors()) {
			return "registration";
		}
		storageService.store(file);
		redirectAttributes.addFlashAttribute("message",
				"You successfully uploaded " + file.getOriginalFilename() + "!");
		userForm.setImage(storageService.load(file.getOriginalFilename()));
		
		userService.save(userForm);
		
//		securityLoginService.autoLogin(userForm.getUsername(), userForm.getPassword());
		return "redirect:/";
	}
	
	@GetMapping("/login")
    public String login(Model model, String error, String logout) {
        if (error != null)
            model.addAttribute("error", "Your username and password is invalid.");

        if (logout != null)
            model.addAttribute("message", "You have been logged out successfully.");

        return "login";
    }
	

	
}
