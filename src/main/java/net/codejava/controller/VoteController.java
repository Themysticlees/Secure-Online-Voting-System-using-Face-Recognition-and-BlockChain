package net.codejava.controller;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.security.Principal;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import java.util.*;
import net.codejava.helper.Message;
import net.codejava.model.Candidate;
import net.codejava.model.User;
import net.codejava.service.CandidateService;
import net.codejava.service.UserService;
import net.codejava.service.VoteService;

// import net.codejava.service.BlockChain;

@Controller
@RequestMapping("/vote")
public class VoteController {
    @Autowired
    private VoteService voteService;

    @Autowired
    private UserService userService;

    @Autowired
    private CandidateService candidateService;
    
    @GetMapping("/votepage")
    public String vote(Principal principal,HttpSession session, Model model) {
        String name = principal.getName();
        //check for existing voter
        if(!voteService.userExists(name)){
            List<Candidate> candidates = candidateService.getAllCandidates();
		    model.addAttribute("candidates", candidates);
            //session.setAttribute("status", new Message("Thanks for voting!", "success"));
            return "vote.html";
        }
        else
        {
            session.setAttribute("status", new Message("You have already voted. Thanks!", "danger"));
            return "redirect:/public/home";
        }
    }

    @GetMapping("/votecasted/{choice}")
    public String getParty(@PathVariable("choice") String choice, Principal principal, HttpSession session) throws NoSuchAlgorithmException, UnsupportedEncodingException {
        
        System.out.println(choice);
        String name = principal.getName();
        if(!voteService.userExists(name)){
            User user = userService.getUser(name);
            System.out.println(user);
            voteService.isSuccessfull(choice, user.getUsername(), user.getFirstname());
            session.setAttribute("status", new Message("Thanks for voting!", "success"));
        }else{
            session.setAttribute("status", new Message("You have already voted. Thanks!", "danger"));
        }

        return "redirect:/public/home";
    }
}
