package com.example.SportsClubMember.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.example.SportsClubMember.domain.AppUser;
import com.example.SportsClubMember.domain.AppUserRepository;
import com.example.SportsClubMember.domain.SignUpForm;

import jakarta.validation.Valid;

@Controller
public class UserController {
	@Autowired
    private AppUserRepository repository; 
	
    @RequestMapping(value = "signup")
    public String addStudent(Model model){
    	model.addAttribute("signupform", new SignUpForm());
        return "signup";
    }	
    
    @RequestMapping(value = "saveuser", method = RequestMethod.POST)
    public String save(@Valid @ModelAttribute("signupform") SignUpForm signupForm, BindingResult bindingResult) {
    	System.out.println(bindingResult.toString());
    	if (!bindingResult.hasErrors()) { // validation errors
    		if (signupForm.getPassword().equals(signupForm.getPasswordCheck())) { // check password match		
	    		String pwd = signupForm.getPassword();
		    	BCryptPasswordEncoder bc = new BCryptPasswordEncoder();
		    	String hashPwd = bc.encode(pwd);
	
		    	AppUser newUser = new AppUser();
		    	newUser.setPasswordHash(hashPwd);
		    	newUser.setUsername(signupForm.getUsername());
		    	newUser.setEmail(signupForm.getEmail());
		    	newUser.setRole("USER");
		    	
		    	if (repository.findByUsername(signupForm.getUsername()) != null) {
	    			bindingResult.rejectValue("username", "error.userexists", "Username already exists");    	
	    			return "signup";		    		
		    	} else if (repository.findByEmail(signupForm.getEmail()) != null) {
		    		bindingResult.rejectValue("email", "error.emailexists", "Email already exists");    	
	    			return "signup";
		    	} else {
		    		repository.save(newUser);
		    	}
		    	
    		}
    		else {
    			bindingResult.rejectValue("passwordCheck", "error.pwdmatch", "Passwords does not match");    	
    			return "signup";
    		}
    	}
    	else {
    		return "signup";
    	}
    	return "redirect:/login";    	
    }    
}
