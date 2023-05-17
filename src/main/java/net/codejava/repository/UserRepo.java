package net.codejava.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import net.codejava.model.User;

public interface UserRepo extends JpaRepository<User, String>{
	
	@Query("SELECT u FROM User u WHERE u.username = :username")
	public User findByUsername(String username);

	@Query("delete from User e where e.username =:username and fileName =:fileName")
	public void deleteEmployeeWithFile(String username, String fileName);

	@Query(value = "SELECT count(*) FROM user", nativeQuery = true)
	public Long findUserCount();

}
