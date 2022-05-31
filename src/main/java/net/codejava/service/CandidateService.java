package net.codejava.service;
import java.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import net.codejava.model.Candidate;
import net.codejava.repository.CandidateRepo;

@Service
public class CandidateService {
    
    @Autowired
    CandidateRepo candidateRepo;
    
    public List<Candidate> getAllCandidates(){
        
        return candidateRepo.findAll();
    }
}
