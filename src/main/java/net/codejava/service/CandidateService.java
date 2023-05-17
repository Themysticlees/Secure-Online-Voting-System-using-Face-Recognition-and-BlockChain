package net.codejava.service;
import java.io.File;
import java.util.*;

import org.apache.tomcat.util.http.fileupload.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import net.codejava.model.Candidate;
import net.codejava.repository.CandidateRepo;

@Service
public class CandidateService {
    
    @Autowired
    CandidateRepo candidateRepo;
    
    public List<Candidate> getAllCandidates() {

        return candidateRepo.findAll();
    }

    public Candidate getCandidate(String firstname) {
        return candidateRepo.findByFirstname(firstname);
    }

    public boolean deleteCandidates(String username, String filename, String path) {
        boolean status = false;
        try {
            if (username != null && filename != null) {
                System.out.println("deleting user " + username);

                candidateRepo.deleteById(username);
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
}
