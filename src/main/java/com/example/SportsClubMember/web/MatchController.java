package com.example.SportsClubMember.web;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.SportsClubMember.domain.Game;
import com.example.SportsClubMember.domain.GameRepository;
import com.example.SportsClubMember.domain.MatchTypeRepository;
import com.example.SportsClubMember.domain.Member;
import com.example.SportsClubMember.domain.MemberRepository;

@Controller
public class MatchController {
	
	@Autowired
	private MemberRepository repository;
	
	@Autowired
	private GameRepository grepository;
	
	@Autowired
	private MatchTypeRepository mrepository;
	
	@Autowired
	private MemberService matchService;
	
	// Match list
	@RequestMapping(value = "/gamelist")
	public String matchList(Model model, @Param("opponent") String opponent) {
		model.addAttribute("games", grepository.findAll());
		
		List<Game> game = matchService.listGames(opponent);
		model.addAttribute("games", game);
		return "gamelist";
	}
	
	// RESTful service to get all matches
	@RequestMapping(value="/games", method = RequestMethod.GET)
	public @ResponseBody List<Game> gameListRest() {
		return (List<Game>) grepository.findAll();
	}
	
	// RESTful service to get match by id
	@RequestMapping(value="/game/{gameid}", method= RequestMethod.GET)
	public @ResponseBody Optional<Game> findGameRest(@PathVariable("gameid") Long gameid){
		return grepository.findById(gameid);
	}
	
	// Add new match
	@RequestMapping(value="/addgame")
	@PreAuthorize("hasAuthority('ADMIN')")
	public String addGame (Model model) {
		model.addAttribute("game", new Game());
		model.addAttribute("matchType", mrepository.findAll());
		return "addgame";
	}
	
	// Save new match
	@RequestMapping(value="/savegame", method =RequestMethod.POST)
	public String save(Game game) {
		grepository.save(game);
		return "redirect:gamelist";
	}
		
	// Delete a match
	@RequestMapping(value ="/deletegame/{id}", method = RequestMethod.GET)
	@PreAuthorize("hasAuthority('ADMIN')")
	public String deleteGame(@PathVariable("id") Long gameid, Game game) {
		grepository.deleteById(gameid);
		return "redirect:../gamelist";
	}
	
	// Edit a match
	@RequestMapping(value="/editgame/{id}")
	@PreAuthorize("hasAuthority('ADMIN')")
	public String editGame(@PathVariable("id") Long gameid, Model model) {
		model.addAttribute("game", grepository.findById(gameid));
		model.addAttribute("matchType", mrepository.findAll());
		return "editgame";
	}
	
	// Adding players for the match
	@RequestMapping(value = "addGameMembers/{id}", method = RequestMethod.GET)
	public String addPlayers(@PathVariable("id") Long gameid, Model model){
	    model.addAttribute("members", repository.findAll());
	    model.addAttribute("game", grepository.findById(gameid).get());
	    return "addGameMembers";
	}
	    	    
	@RequestMapping(value="/game/{gameid}/members", method=RequestMethod.GET)
	public String gameMembersAdd(@RequestParam(value="action", required=true) String action, @PathVariable Long gameid, @RequestParam Long id, Model model) {
	    Optional<Member> member = repository.findById(id);
		Optional<Game> game = grepository.findById(gameid);

			if (game.isPresent() && action.equalsIgnoreCase("save")) {
				if (!game.get().hasMember(member.get())) {
					game.get().getMembers().add(member.get());
				}
				grepository.save(game.get());
				model.addAttribute("game", repository.findById(gameid));
				model.addAttribute("members", repository.findAll());
				return "redirect:/gamelist";
			}

			model.addAttribute("developers", repository.findAll());
			return "redirect:/gamelist";
			
		}    
	
}
