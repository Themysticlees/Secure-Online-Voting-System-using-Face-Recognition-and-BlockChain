package net.codejava.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import net.codejava.model.Pending;

public interface PendingRepo extends JpaRepository<Pending, String>{
	
    
	@Query("SELECT u FROM Pending u WHERE u.username = :username")
	public Pending findByUsername(String username);
	/*
	@Query("delete from User e where e.username =:username and fileName =:fileName")
	public void deleteEmployeeWithFile(String username, String fileName);
	*/
	

}
