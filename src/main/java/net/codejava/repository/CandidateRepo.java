package net.codejava.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import net.codejava.model.Candidate;


@Repository
public interface CandidateRepo extends JpaRepository<Candidate, String> {

    public Candidate findByFirstname(String firstname);

    public Candidate findByParty(String partyname);
} 