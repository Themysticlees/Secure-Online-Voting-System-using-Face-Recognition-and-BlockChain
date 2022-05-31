package net.codejava.smartcontract;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;


import net.codejava.helper.SHA256;
import net.codejava.model.User;
import net.codejava.model.Votedata;
import net.codejava.repository.UserRepo;
import net.codejava.repository.VoteRepo;

/**
 * Data fetch
 */
@Component
public class VoteSmartContract {
    @Autowired
    VoteRepo vrepo;

    @Autowired
    UserRepo urepo;
    

    public boolean checkTable()throws NoSuchAlgorithmException, UnsupportedEncodingException{
        Sort sort = Sort.by(Sort.Direction.ASC, "date");
        List<Votedata> data = vrepo.findAll(sort);
        String[] party={"cpm","bjp","aap","tmc"};
        
        String tempPrevHash="0";
        for(Votedata d:data){
            String adhaar=d.getUsername();
            System.out.println(">>>>>>>>>>>>>>>>"+adhaar);
            String previousBlockHash=d.getPrevhash();
            String currBlockHash=d.getCurrhash();
            System.out.println(">>>>>>>>>>>>"+currBlockHash);
            User user=urepo.findByUsername(adhaar);
            System.out.println(user);
            String name=user.getFirstname();

            int f=0;
            for(String i:party){
                String[] transaction = {adhaar,name,i};
                String tempHash= SHA256.getSHA(transaction,previousBlockHash);

                System.out.println(">>>>>>>>>>>Temp Hash Generated>>>>>>>>>>>>>>"+tempHash);
                if(tempHash.equals(currBlockHash) && previousBlockHash.equals(tempPrevHash)){
                    f=1;
                    break;
                }
            }

            if(f==0){
                System.out.println("Tampering Found at " +adhaar);
                System.out.println("Original current hash "+currBlockHash);
                return false;
            }

            tempPrevHash=currBlockHash;
        }
        return true;
    }

    public void correctTableValues(){
        
        System.out.println("Rectifying the values ! Please wait !");
        vrepo.deleteAll();
        vrepo.copyData();
        System.out.println("Data is now rectified ! Enjoy");
    }

    // public  
    // kibhabe vote count
    // individual
    // votecount according
    // ton their party
    // return object(party name list,)

}
