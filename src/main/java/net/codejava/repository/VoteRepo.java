package net.codejava.repository;

import java.util.Date;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import net.codejava.model.Votedata;

public interface VoteRepo extends JpaRepository<Votedata, String> {

	@Query("SELECT u FROM Votedata u WHERE u.username = :username")
	public Votedata findByUsername(String username);

	@Query(value = "SELECT * FROM votedata  WHERE date = (SELECT date FROM votedata order by date desc limit 1)", nativeQuery = true)
	public Votedata findLastEntry();

	@Modifying
	@Transactional
	@Query(value = "INSERT INTO votecopy(username,currhash,date,prevhash) values(:username,:currhash,:date,:prevhash) ", nativeQuery = true)
	public void copyData(@Param("username") String username,@Param("currhash")String currhash,@Param("date")Date date,@Param("prevhash")String prevhash);
	//public Votedata copyData(String username,String currhash, Date date, String prevhash);
	
	@Modifying
	@Transactional
	@Query(value="INSERT INTO votedata SELECT * FROM votecopy;",nativeQuery=true)
	public void copyData();

	@Query(value = "SELECT count(*) FROM votecopy", nativeQuery = true)
	public Long findcount();

	public Votedata findByCurrhash(String currhash);

}
