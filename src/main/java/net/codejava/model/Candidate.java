package net.codejava.model;

import java.beans.Transient;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Candidate{

    @Id
    //@GeneratedValue(strategy = GenerationType.AUTO)
    private String username;
    
    private String firstname;
    private String lastname;
  
    private String party;

    private String partypic;
    private String candidatepic;

    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public String getFirstname() {
        return firstname;
    }
    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }
    public String getLastname() {
        return lastname;
    }
    public void setLastname(String lastname) {
        this.lastname = lastname;
    }
   
    
    public String getParty() {
        return party;
    }
    public void setParty(String party) {
        this.party = party;
    }
    public String getPartypic() {
        return partypic;
    }
    public void setPartypic(String partypic) {
        this.partypic = partypic;
    }
    public String getCandiatepic() {
        return candidatepic;
    }
    public void setCandidatepic(String canidatepic) {
        this.candidatepic = canidatepic;
    }
    
    @Transient
    public String getCandidateImagePath() {
        if (candidatepic == null || username == null)
            return null;

        return "/candidate-photos/" + username + "/" + candidatepic;
    }

    @Transient
    public String getCandidatePicImagePath() {
        if (candidatepic == null || username == null)
            return null;

        return candidatepic;
    }
    
   
    
}