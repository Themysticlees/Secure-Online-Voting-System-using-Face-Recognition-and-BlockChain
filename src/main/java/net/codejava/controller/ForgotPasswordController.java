//  ********************************** use for forget password  ******************************************
package net.codejava.controller;

import javax.servlet.http.HttpSession;

import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import net.codejava.model.User;
import net.codejava.repository.UserRepo;
import net.codejava.service.EmailService;
import net.codejava.service.UserService;

@Controller
@RequestMapping("/forgotpassword")
public class ForgotPasswordController {

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    Random random = new Random(1000);

    @Autowired
    UserService userservice;

    @Autowired
    UserRepo repo;

    @GetMapping("/")
    public String forgotPass() {
        return "sendotp.html";
    }

    // -----------------------------------------------------------------
    @Autowired
    EmailService emailservice;

    @PostMapping("/otpsend")
    public String otpsend(@RequestParam("adhaarid") String username, HttpSession session) {
        // System.out.println("Email: " + email);
        User user = userservice.getUser(username);
        String email = user.getEmail();
        // generating otp
        //Integer otp = random.nextInt(999999);
        int min=100000;
		int max=999999;
		Integer otp = (int)(Math.random()*(max-min+1)+min);
        System.out.println(otp);

        // write code for send otp to email...

        String subject = "Forget Password";
        String message = "OTP for renewing your password = " + otp;
        String to = email;
        boolean flag = this.emailservice.sendEmail(subject, message, to);

        if (flag) {
            session.setAttribute("otp", otp);
            session.setAttribute("email", email);
            session.setAttribute("username", username);

            // Integer check=(int) session.getAttribute("otp");
            return "verifyotp.html";

        } else {

            return "sendotp";
        }

    }

    // ---------------------------------------------------------------------- //

    @PostMapping("/verify-otp")
    public String verify(@RequestParam("digit-1") String d1,@RequestParam("digit-2") String d2,@RequestParam("digit-3") String d3,@RequestParam("digit-4") String d4,@RequestParam("digit-5") String d5,@RequestParam("digit-6") String d6, HttpSession session) {
		
        String res = d1+d2+d3+d4+d5+d6;
		
		Integer otp=Integer.parseInt(res);

        Integer old_otp = (Integer) session.getAttribute("otp");

        System.out.println(old_otp + " " + otp);

        if (old_otp.equals(otp)) {

            System.out.println("succesfull");

            return "newpassword.html";
        }
        return "/index";
    }

    // -------------------------------------------------- //

    // Give new password to update 
    @PostMapping("/newpassword")
	public String newpassword(@RequestParam("password") String newpassword, HttpSession session) {
		String username = (String) session.getAttribute("username");
		String email = (String) session.getAttribute("email");
		User user = userservice.getUser(username);
        System.out.println(newpassword);
		user.setPassword(passwordEncoder.encode(newpassword));

        System.out.println(user);

        repo.save(user);

		return "redirect:/index";
	}

    
}
