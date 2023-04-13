package com.example.SportsClubMember.web;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.SportsClubMember.domain.Member;
import com.example.SportsClubMember.domain.MemberRepository;
import com.example.SportsClubMember.domain.StatusRepository;

@Controller
public class MemberController {

	@Autowired
	private MemberRepository repository;
	
	@Autowired
	private StatusRepository srepository;
	
	@Autowired
	private MemberService memberService;
	
	// Login page
	@RequestMapping(value = "/login")
	public String login() {
		return "login";
	}
	
	// Welcome page
	@RequestMapping(value = "/homepage")
	public String homepage(Model model) {
		UserDetails user = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String username = user.getUsername();
		System.out.println("USERNAME: " + username);
    	model.addAttribute("name", username);
    	
		return "homepage";
	}
	
	// Club member list
	@RequestMapping(value = { "/", "/memberlist"})
	public String memberList(Model model, @Param("memberName") String memberName) {
		UserDetails user = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String username = user.getUsername();
		System.out.println("USERNAME: " + username);
    	model.addAttribute("name", username);
    	model.addAttribute("members", repository.findAll());
    	
    	List<Member> member = memberService.listAll(memberName);
    	model.addAttribute("members", member);
    	return "memberlist";
	}
	
	// RESTful service to get all members
    @RequestMapping(value="/members", method = RequestMethod.GET)
    public @ResponseBody List<Member> memberListRest() {	
        return (List<Member>) repository.findAll();
    }    

	// RESTful service to get member by id
    @RequestMapping(value="/member/{id}", method = RequestMethod.GET)
    public @ResponseBody Optional<Member> findMemberRest(@PathVariable("id") Long id) {	
    	return repository.findById(id);
    }       
    
	// Add new member
	@RequestMapping(value = "/add")
	public String addMember(Model model) {

		model.addAttribute("member", new Member());
		model.addAttribute("status", srepository.findAll());
		return "addmember";
	}

	// Save new member
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public String save(Member member) {
		repository.save(member);
		return "redirect:memberlist";
	}

	// Delete a member
	@RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
	@PreAuthorize("hasAuthority('ADMIN')")
	public String deleteMember(@PathVariable("id") Long memberId, Model model) {
		repository.deleteById(memberId);
		return "redirect:../memberlist";
	}

	// Edit a member
	@RequestMapping(value = "/edit/{id}")
	public String editMember(@PathVariable("id") Long memberId, Model model) {
		model.addAttribute("member", repository.findById(memberId));
		model.addAttribute("status", srepository.findAll());
		return "editmember";
	}
}
