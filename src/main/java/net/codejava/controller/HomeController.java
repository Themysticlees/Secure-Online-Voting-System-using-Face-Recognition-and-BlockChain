package net.codejava.controller;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.security.Principal;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import net.codejava.helper.Message;
import net.codejava.model.User;
import net.codejava.repository.UserRepo;
import net.codejava.service.EmailService;
import net.codejava.service.UserService;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
// After Authentication, user can use this link and method

@RequestMapping("/public")
public class HomeController {

	// All been of other classes are here
	@Autowired
	UserService userservice;

	@Autowired
	EmailService emailservice;

	@Autowired
	BCryptPasswordEncoder passwordEncoder;

	@Autowired
	UserRepo repo;

	// ---------------------------USER HOME
	// PAGE--------------------------------------//

	// Returns the user home of the user when this url is encountered,
	// however this page can only be accesed by registered users
	@GetMapping("/home")
	public String getHome(Principal principle, Model model) {

		String username = principle.getName();
		User user = userservice.getUser(username);

		// storing the user details in model so that we can use it in the html page
		model.addAttribute("currentUser", user);
		return "index2.html";
	}
	// ------------------------------------------------ //

	// user can go to account setting page where user can set there details //
	@GetMapping("/home/account")
	public String getAccountdetails(Principal principle, Model model) {
		String username = principle.getName();
		User user = userservice.getUser(username);
		model.addAttribute("currentUser", user);
		return "account.html";
	}

	// ----------------------------------------------- //

	// click vote from navbar which will trigger this URL //
	@GetMapping("/home/faceverification")
	public String page(Principal principle) throws IOException, InterruptedException {

		String username = principle.getName();
		String param2 = username;
		// call main.py to execute python program of face recognition \\
		ProcessBuilder pb = new ProcessBuilder("python",
				"src\\main\\java\\net\\codejava\\controller/main.py",
				"" + param2).inheritIO();
		Process p = pb.start();
		p.waitFor();
		// call out.txt file from template to get 0/1. 0 means unsuccessful verification
		// 1 means successful
		File file = new File("src\\main\\resources\\templates/out.txt");
		BufferedReader reader = new BufferedReader(new FileReader(file));
		String st;
		char c = '\0';
		// Condition holds true till
		// there is character in a string
		while ((st = reader.readLine()) != null) {
			System.out.println(st.length());
			// take the value 0/1 at 13th character from out.txt file
			c = st.charAt(13);
		}
		// Print the string
		System.out.println(c);
		reader.close();
		// Goto otp verification page
		if (c == '1') {
			return "redirect:/public/otpsend";
		}

		return "redirect:/public/home";
	}

	// ------------------------------------------------- //

	// generate a otp and send it to the user's mail for verify user
	@GetMapping("/otpsend")
	public String otpsend(Principal principle, HttpSession session) {

		String username = principle.getName();
		User user = userservice.getUser(username);
		String firstName = user.getFirstname();

		String email = user.getEmail();

		System.out.println("Email: " + email);
		// generating otp
		int min = 100000;
		int max = 999999;
		Integer otp = (int) (Math.random() * (max - min + 1) + min);
		System.out.println(otp);
		// write code for send otp to email...
		String subject = "OTP from SOVS";
		String message = " " +
				"<h2>Hi " + "<b>" + firstName + ",</b><br>" + "Thank you for your successfull face verfication."+
				"Your OTP is " +
				"<h1>" +
				otp +
				"</h1></h2>";
		String to = email;
		boolean flag = this.emailservice.sendEmail(subject, message, to);

		if (flag) {
			// store otp and email id in http session
			session.setAttribute("otp", otp);
			session.setAttribute("email", email);
			// Integer check=(int) session.getAttribute("otp");
			return "verify_OTP.html";
		} else {
			return "redirect:/public/otpsend";
		}
	}

	// ------------------------------------------- //

	// call from verify_OTP.html page to check otp if true then go to voting page \\
	@PostMapping("/verify-otp")
	public String verify(@RequestParam("digit-1") String d1, @RequestParam("digit-2") String d2,
			@RequestParam("digit-3") String d3, @RequestParam("digit-4") String d4, @RequestParam("digit-5") String d5,
			@RequestParam("digit-6") String d6, HttpSession session) {

		// combining the 6 digits into one single string
		String res = d1 + d2 + d3 + d4 + d5 + d6;
		Integer otp = Integer.parseInt(res);

		// fetching old otp from session
		Integer old_otp = (Integer) session.getAttribute("otp");
		// System.out.println(old_otp + " " + otp); // [ If required then uncomment ]

		// ********************Match session otp with otp entered by user
		// ******************************
		if (old_otp.equals(otp)) {
			// String email=(String)session.getAttribute("email");
			System.out.println("succesfull");

			return "redirect:/vote/votepage";
		}
		// if otp not match then user will automatically logout \\
		return "redirect:/logout";
	}

	// ----------------------------------------------- //

	// user home page --> account setting --> update password
	@PostMapping("home/update/update_password")
	public String changePassword(@RequestParam("oldPassword") String oldPassword,
			@RequestParam("newPassword") String newPassword, Principal principal, HttpSession session) {

		oldPassword = oldPassword.substring(0, oldPassword.length() - 1);

		// System.out.println("Old Password " + oldPassword);
		// System.out.println("New Password " + newPassword); // [If required then
		// uncomment]

		String username = principal.getName();
		// System.out.println(username); // [If required then uncomment]
		User currentuser = userservice.getUser(username);

		// match oldpassword with user given password
		if (this.passwordEncoder.matches(oldPassword, currentuser.getPassword())) {
			currentuser.setPassword(this.passwordEncoder.encode(newPassword));
			this.repo.save(currentuser);

			session.setAttribute("message", new Message("your password is changed", "success"));
			// session.setAttribute("message", new Message);
		} else {
			session.setAttribute("message", new Message("wrong password", "danger"));
		}

		return "redirect:/public/home/account";
	}
}
