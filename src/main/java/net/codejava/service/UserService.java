package net.codejava.service;

import java.io.File;
import java.util.*;

import org.apache.tomcat.util.http.fileupload.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.codejava.model.Pending;
import net.codejava.model.User;
import net.codejava.repository.PendingRepo;
import net.codejava.repository.UserRepo;

@Service
public class UserService {
	
	@Autowired
	UserRepo repo;
	
	@Autowired
	PendingRepo pending;

	List<User> list = new ArrayList<>();

	/*public UserSecurityService() {
		list.add(new User("abc","1234","abc@gmail.com"));
		list.add(new User("xyz","1234","xyz@gmail.com"));

	}*/
	
	//get all users
	public List<User> getAllUsers(){
		return repo.findAll();
	}

	public List<Pending> getAllPendingUsers(){
		return pending.findAll();
	}
	public Pending getPendingUser(String username){
		Pending user = pending.findByUsername(username);
		return user;
	}
	
	//get single user
	public User getUser(String username) {
		
		User user = repo.findByUsername(username);
		return user;
	}

	public boolean userExists(String username) {
		Pending user = pending.findByUsername(username);
		if (user != null) {
			return true;
		}
		return false;
	}

	public boolean deleteUser(String username, String filename, String path) {
		boolean status = false;
		try {
			if (username != null && filename != null) {
				System.out.println("deleting user " + username);

				repo.deleteById(username);
				//repo.deleteEmployeeWithFile(username, filename);
				//File fileToDelete = new File(path);
				//FileUtils fileToDelete = new FileUtils();
				//status = fileToDelete.deleteDirectory(path);
				FileUtils.deleteDirectory(new File(path));
				System.out.println();
				return status;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return status;
		}
		return status;
	}

	public boolean deletePendingUser(String username, String filename, String path) {
		boolean status = false;
		try {
			if (username != null && filename != null) {
				System.out.println("deleting user " + username);

				pending.deleteById(username);
	
				FileUtils.deleteDirectory(new File(path));
				System.out.println();
				return status;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return status;
		}
		return status;
	}
	
	/*//add user
	public Usersecurity addUser(Usersecurity user) {
		list.add(user);
		
		return user;
	}*/

}
