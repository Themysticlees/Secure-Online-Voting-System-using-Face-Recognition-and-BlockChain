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

import net.codejava.helper.EmailTemplate;
import net.codejava.helper.Message;
import net.codejava.model.Candidate;
import net.codejava.model.User;
import net.codejava.repository.CandidateRepo;
import net.codejava.repository.VoteRepo;
import net.codejava.service.CandidateService;
import net.codejava.service.EmailService;
import net.codejava.service.UserService;
import net.codejava.service.VoteService;
import net.codejava.smartcontract.VoteSmartContract;

// import net.codejava.service.BlockChain;

@Controller
@RequestMapping("/vote")
public class VoteController {
    @Autowired
    private VoteService voteService;

    @Autowired
    VoteSmartContract smartcontract;

    @Autowired
    private VoteRepo voteRepo;

    @Autowired
    private UserService userService;

    @Autowired
    private CandidateService candidateService;

    @Autowired
    EmailService emailservice;

    @Autowired
    EmailTemplate emailTemplate;

    @Autowired
    CandidateRepo candidaterepo;
    
    @GetMapping("/votepage")
    public String vote(Principal principal,HttpSession session, Model model) {
        //String name = principal.getName();
        //check for existing voter


        //if(!voteService.userExists(name)){
            List<Candidate> candidates = candidateService.getAllCandidates();
		    model.addAttribute("candidates", candidates);
            //session.setAttribute("status", new Message("Thanks for voting!", "success"));
            return "vote.html";
        // }
        // else
        // {
        //     session.setAttribute("status", new Message("You have already voted. Thanks!", "danger"));
        //     return "redirect:/public/home";
        // }
    }

    @GetMapping("/votecasted/{choice}")
    public String getParty(@PathVariable("choice") String choice, Principal principal, HttpSession session) throws NoSuchAlgorithmException, UnsupportedEncodingException {
        
        System.out.println(choice);
        String name = principal.getName();
        // if (!voteService.userExists(name)) {
        //     System.out.println(voteRepo.findcount() + "=====================" + voteRepo.count());
        //     if ((voteRepo.findcount() != voteRepo.count())) {
        
        //         smartcontract.correctTableValues();
        //         session.setAttribute("status", new Message("You have already voted. Thanks!", "danger"));
        //     }
        //     else {
                User user = userService.getUser(name);
                System.out.println(user);
                voteService.isSuccessfull(choice, user.getUsername(), user.getFirstname());
                String hash = voteRepo.findByUsername(name).getCurrhash();

                String f="Vote successfuly polled";
			    String s="You have successfuly voted. Your HashValue (Please save this for future reference) ";
			    String t=hash;

                String email=user.getEmail();
                String subject="Vote Confirmed!";
                
                
                String message = emailTemplate.getTemplate(f, s, t);
                emailservice.sendEmail(subject, message, email);
                
                session.setAttribute("status", new Message("Thanks for voting!", "success"));
            // }
        //}
        
        // else{
        //     session.setAttribute("status", new Message("You have already voted. Thanks!", "danger"));
        // }

        return "redirect:/public/home";
    }

    @GetMapping("/showResults")
    public String getResults(Model model) throws NoSuchAlgorithmException{

        String winningParty=smartcontract.voteCount();

        System.out.println("-------------Winning Party-------------");

        System.out.println(winningParty);

        Candidate winner=candidaterepo.findByParty(winningParty);
        model.addAttribute("winningParty",winner);

        //return to result page
        return "result.html";
    }
}
