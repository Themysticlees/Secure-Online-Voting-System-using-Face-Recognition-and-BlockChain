package net.codejava.controller;

import java.io.File;
import java.io.IOException;
import java.security.Principal;
import java.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.ui.Model;

import net.codejava.model.Candidate;
import net.codejava.model.Pending;
import net.codejava.model.User;
import net.codejava.repository.CandidateRepo;
import net.codejava.repository.PendingRepo;
import net.codejava.repository.UserRepo;
import net.codejava.service.UserService;

@Controller
@RequestMapping("/admin")
public class UserSecurityController {
	
	@Autowired
	UserService userService;

	@Autowired
	CandidateRepo candidateRepo;

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
	public String admin(){
		return "admin.html";
	}

	@GetMapping("/users")
	public String users(Model model){
		List<User> users = userService.getAllUsers();
		model.addAttribute("users", users);
		return "databasepart.html";
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


	// @GetMapping("/pending")
	// public String getPendingUsers(Model model){

		
	// 	List<Pending> users = userService.getAllPendingUsers();
	// 	model.addAttribute("pendingUsers", users);
	// 	return "PendingUsers.html";
	// }

	
	//return single user
	@GetMapping("/{userName}")
	public User getUser(@PathVariable("userName") String userName) {
		return this.userService.getUser(userName);
	}
	
	// when update photo delete previous photo from directory
	@GetMapping("/removeFile/{username}/{fileName}")
	public String removeFileHandler (@PathVariable("username") String username, @PathVariable("fileName") String fileName, Model model) {		
		//ModelAndView mav = new ModelAndView("redirect:/image-upload/employees");
		String path = null;
		File file =null;

		try{
			path = "user-photos/" + username;
			System.out.println(path);
			file=new File(path);
			if(file.exists())
			{
				boolean status = userService.deleteUser(username,fileName,path);
				List<User> users = userService.getAllUsers();
				model.addAttribute("users", users);
				return "redirect:/users/";
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return "redirect:/users/";
	}


	@Autowired
	PendingRepo pendingRepo;

	@Autowired
	UserRepo repo;

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

			repo.save(user);

			pendingRepo.deleteById(username);
			List<Pending> users = userService.getAllPendingUsers();
			model.addAttribute("users", users);
			return "redirect:/users/pending";
			
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return "redirect:/users/";
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
		
}
