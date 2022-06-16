package net.codejava.controller;

import java.io.File;
import java.io.IOException;

import java.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.ui.Model;

import net.codejava.helper.EmailTemplate;
import net.codejava.model.Candidate;
import net.codejava.model.Pending;
import net.codejava.model.User;
import net.codejava.repository.CandidateRepo;
import net.codejava.repository.PendingRepo;
import net.codejava.repository.UserRepo;
import net.codejava.repository.VoteRepo;
import net.codejava.service.CandidateService;
import net.codejava.service.UserService;
import net.codejava.service.EmailService;


@Controller
@RequestMapping("/admin")
public class UserSecurityController {
	
	@Autowired
	UserService userService;

	@Autowired
	CandidateRepo candidateRepo;

	@Autowired
	CandidateService candidateService;

	@Autowired
	EmailService emailservice;

	@Autowired
	EmailTemplate emailTemplate;

	@Autowired
	PendingRepo pendingRepo;

	@Autowired
	UserRepo repo;

	@Autowired
	VoteRepo voterepo;

	//all users
	// @GetMapping("/")
	// public String getUsers(Principal principle,Model model){

	// 	String username = principle.getName();
	// 	User user = userService.getUser(username);
	// 	model.addAttribute("currentUser", user);
	// 	List<User> users = userService.getAllUsers();
	// 	model.addAttribute("users", users);
	// 	return "users.html";
	// }

	@GetMapping("/")
	public String admin(Model model){

		String userCount=""+(repo.findUserCount()-1L);
		String pendingCount=""+pendingRepo.findPendingCount();
		String voteCount=""+(voterepo.findcount()-1);
		String remCount=""+((repo.findUserCount()-1L)-(voterepo.findcount()-1));
		
		model.addAttribute("usercount", userCount);
		model.addAttribute("pendingcount", pendingCount);
		model.addAttribute("votecount", voteCount);
		model.addAttribute("remainingcount", remCount);		
		System.out.println(userCount+"--------------------"+pendingCount);
		return "admin.html";
	}

	

	@GetMapping("/users")
	public String users(Model model){
		List<User> users = userService.getAllUsers();
		model.addAttribute("users", users);
		return "databasepart.html";
	}


	@GetMapping("/candidatelist")
	public String candidatelist(Model model) {
		 List<Candidate> candidates = candidateService.getAllCandidates();
		 model.addAttribute("allcandidates", candidates);
		return "candidate.html";
	}

	@GetMapping("/pendingrequest")
	public String pending(Model model){
		List<Pending> users = userService.getAllPendingUsers();
		model.addAttribute("pendingUsers", users);
		return "pendingrequest.html";
	}

	@GetMapping("/notifications")
	public String notifications(){
		return "notifications.html";
	}

	@GetMapping("/candidates")
    public String candidates(Model model) {
		
		Candidate candidate = new Candidate();
		model.addAttribute("candidate", candidate);
		
        List<String> listparty = Arrays.asList("Choose party name","cpm","aap","bjp","tmc");
		model.addAttribute("listparty", listparty);
        
        return "candidatesetting.html";
    }

	
	//return single user
	@GetMapping("/{userName}")
	public User getUser(@PathVariable("userName") String userName) {
		return this.userService.getUser(userName);
	}
	
	// when update photo delete previous photo from directory
	@GetMapping("/removeFile/{username}/{fileName}")
	public String removeFileHandler(@PathVariable("username") String username,
			@PathVariable("fileName") String fileName, Model model) {
		//ModelAndView mav = new ModelAndView("redirect:/image-upload/employees");
		String path = null;
		File file = null;

		try {
			path = "user-photos/" + username;
			System.out.println(path);
			file = new File(path);
			if (file.exists()) {
				boolean status = userService.deleteUser(username, fileName, path);
				List<User> users = userService.getAllUsers();
				model.addAttribute("users", users);
				return "redirect:/admin/users";
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "redirect:/admin/users";
	}
	


	// when update photo delete previous photo from directory
	@GetMapping("/removePendingFile/{username}/{fileName}")
	public String removePendingFileHandler(@PathVariable("username") String username,
			@PathVariable("fileName") String fileName, Model model) {
		// ModelAndView mav = new ModelAndView("redirect:/image-upload/employees");
		String path = null;
		File file = null;

		try {
			path = "user-photos/" + username;
			System.out.println(path);

				String f="Registration not completed";
				String s="Your registration is incomplete. Please review your documents and register again. Thank you !";
				String t="Not Accepted";
				String message=emailTemplate.getTemplate(f, s, t);
				String subject="Registration not completed";
				String email=userService.getPendingUser(username).getEmail();
				this.emailservice.sendEmail(subject, message, email);
			file = new File(path);
			if (file.exists()) {
				boolean status = userService.deletePendingUser(username, fileName, path);
				List<User> users = userService.getAllUsers();
				model.addAttribute("users", users);

				return "redirect:/admin/users";
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "redirect:/admin/users";
	}

	@GetMapping("/removeCandidateFile/{username}/{fileName}")
	public String removeCandidateFileHandler(@PathVariable("username") String username,
			@PathVariable("fileName") String fileName,Model model) {
		// ModelAndView mav = new ModelAndView("redirect:/image-upload/employees");
		String path = null;
		File file = null;

		try {
			path = "candidate-photos/" + username;
			System.out.println(path);
			file = new File(path);
			if (file.exists()) {
				boolean status = candidateService.deleteCandidates(username, fileName, path);
				List<Candidate> candidates = candidateService.getAllCandidates();
				model.addAttribute("allcandidates", candidates);
				return "redirect:/admin/candidatelist";
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "redirect:/admin/candidatelist";
	}

	

	@GetMapping("/approveFile/{username}/{fileName}")
	public String approveFileHandler (@PathVariable("username") String username, @PathVariable("fileName") String fileName, Model model) {		
		//ModelAndView mav = new ModelAndView("redirect:/image-upload/employees");

		try{
			Pending puser = userService.getPendingUser(username);
			User user=new User();
			user.setAddress(puser.getAddress());
			user.setUsername(username);
			user.setBirthday(puser.getBirthday());
			user.setCity(puser.getCity());
			user.setEmail(puser.getEmail());
			user.setFirstname(puser.getFirstname());
			user.setGender(puser.getGender());
			user.setLastname(puser.getLastname());
			user.setMobileno(puser.getMobileno());
			user.setPassword(puser.getPassword());
			user.setPhotos(puser.getPhotos());
			user.setZip(puser.getZip());
			user.setState(puser.getState());
			user.setRole(puser.getRole());
			user.setAdhaarpdf(puser.getAdhaarpdf());

			repo.save(user);

			pendingRepo.deleteById(username);
			List<Pending> users = userService.getAllPendingUsers();
			model.addAttribute("users", users);

			String f="Your Registration is Successful!";
			String s="Your registration has been accepted. You can now login in your account using your adhar id and password used while registration. Thank you !";
			String t="Accepted";

			String message = emailTemplate.getTemplate(f, s, t);
			String subject="Registration completed !";
			String email=userService.getUser(username).getEmail();
			this.emailservice.sendEmail(subject, message, email);

			return "redirect:/admin/pendingrequest";
			
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return "redirect:/admin/";
	}



	@PostMapping("/candidates/register")
	public String candidateRegister(@ModelAttribute("candidate") Candidate candidate,
	@RequestParam("image") MultipartFile multipartFile) throws IOException {

		
		String fileName = multipartFile.getOriginalFilename();
		

		String link="/images/"+candidate.getParty()+".jpeg";
		
		candidate.setPartypic(link);

		String uploadDir = "candidate-photos/" + candidate.getUsername();
		FileUploadUtil.saveFile(uploadDir, fileName, multipartFile);
		candidate.setCandidatepic(fileName);
		Candidate candidateTemp = candidateRepo.save(candidate);


		return "redirect:/admin/candidates";
	
	}

	@GetMapping("/startVote")
	public String startVote(){
		User admin = userService.getUser("admin");
		admin.setVotestatus("1");
		repo.save(admin);
		return "redirect:/admin/";
	}

	@GetMapping("/restartVote")
	public String restartVote(){
		User admin = userService.getUser("admin");
		admin.setVotestatus("0");
		
		repo.save(admin);
		return "redirect:/admin/";
	}

	@GetMapping("/endVote")
	public String endVote(){
		User admin = userService.getUser("admin");
		admin.setVotestatus("2");
		
		repo.save(admin);
		return "redirect:/admin/";
	}
		
}
