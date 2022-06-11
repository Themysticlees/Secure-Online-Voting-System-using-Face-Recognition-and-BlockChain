package net.codejava.service;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.codejava.model.Block;
import net.codejava.model.User;
import net.codejava.model.Votedata;
import net.codejava.repository.VoteRepo;
import net.codejava.smartcontract.VoteSmartContract;

@Service
public class VoteService {

    @Autowired
    private VoteRepo voterepo;

    @Autowired
    VoteSmartContract smartcontract;

    public boolean isSuccessfull(String choice, String adhhar, String name)
            throws NoSuchAlgorithmException, UnsupportedEncodingException {

       
        //correct table
        // (voterepo.findcount() != voterepo.count())
        if (!smartcontract.checkTable()) {
            smartcontract.correctTableValues();
        }
        
        Votedata lastEntry = voterepo.findLastEntry();
        System.out.println(lastEntry);
        System.out.println(adhhar+"-----"+name+"-----"+choice+"-----"+lastEntry.getCurrhash());
        String[] data = { adhhar, name, choice };
        Block candidate = new Block(data, lastEntry.getCurrhash());

        Votedata vote = new Votedata();
        vote.setUsername(adhhar); 

        vote.setCurrhash(candidate.getBlockHash());
        vote.setPrevhash(candidate.getPreviousBlockHash());
        Date date = new Date();
        vote.setDate(date);
        System.out.println("*************** Vote Saved*********************");
        voterepo.save(vote);
        voterepo.copyData(vote.getUsername(), vote.getCurrhash(), vote.getDate(), vote.getPrevhash());

        return true;
    }

    public boolean userExists(String username) {
        Votedata user = voterepo.findByUsername(username);
        if (user != null) {
            return true;
        }
        return false;
    }

    public int countVotes() {
        
        return 0;
    }

    // public static void pollVotes(String choiced, String adhaarid, String uname) {

    //     ArrayList<Block> blockchain1 = new ArrayList<Block>();

    //     Scanner sc = new Scanner(System.in);

    //     // first blockchain for First party
    //     // Array = adhar id + first name + choice party

    //     // System.out.println("first block is "+firstPartyBlock.toString());

    //     /*
    //      * // first blockchain for Second party
    //      * String[] initialSecondPartyValues={"000000000000","BJP"};
    //      * int secondPartyHash=0001;
    //      * Block secondPartyBlock = new Block(initialSecondPartyValues,secondPartyHash);
    //      * secondPartyHash=secondPartyBlock.getBlockHash();
    //      * //System.out.println(secondPartyBlock.hashCode());
    //      * blockchain2.add(secondPartyBlock);
    //      */
    //     // System.out.println("first block is "+secondPartyBlock.toString());

    //     // System.out.println("Enter your Adhar id");
    //     String adhaar = adhaarid;

    //     // System.out.println("Enter your First Name");
    //     String name = uname;

    //     // System.out.println("Enter which party you want to vote");
    //     String choice = choiced;
    //     long candidateHash = 0;
    //     String[] FirstPartyValues = { adhaar, name, choice };
    //     Block candidate = new Block(FirstPartyValues, candidateHash);
    //     candidateHash = candidate.getBlockHash();

    //     blockchain1.add(candidate);
    //     /*
    //      * if (name.equals("TMC")) {
    //      * String[] FirstPartyValues = {adhaar, name};
    //      * Block firstParty = new Block(FirstPartyValues, firstPartyHash);
    //      * firstPartyHash = firstParty.getBlockHash();
    //      * //System.out.println(firstParty.hashCode());
    //      * blockchain1.add(firstParty);
    //      * } else if (name.equals("BJP")) {
    //      * String[] SecondPartyValues = {adhaar, name};
    //      * Block secondParty = new Block(SecondPartyValues, secondPartyHash);
    //      * secondPartyHash = secondParty.getBlockHash();
    //      * //System.out.println(secondParty.hashCode());
    //      * blockchain2.add(secondParty);
    //      * } else {
    //      * System.out.println("bye");
    //      * break;
    //      * }
    //      * System.out.println("again..........");
    //      */
    //     // System.out.println("Do you want to exit ? (1/0)");
    //     int n = sc.nextInt();
    //     if (n == 1)
    //         break;

    //     /*
    //      * System.out.println(blockchain1.toString());
    //      * //System.out.println(blockchain2.toString());
    //      * 
    //      * System.out.println(blockchain1.size());
    //      * /*
    //      * if (blockchain1.size()>blockchain2.size())
    //      * {
    //      * System.out.println("TMC Won");
    //      * }
    //      * else
    //      * System.out.println("BJP Won");
    //      */
    // }

    // public static String declareResult(ArrayList<Block> blockchain1) {

    //     int prevHash = blockchain1.get(0).getBlockHash();

    //     int BJP = 0, CPM = 0, TMC = 0;

    //     for (int i = 1; i < blockchain1.size(); i++) {
    //         Block temp = blockchain1.get(i);
    //         int blockHash = Arrays.hashCode(new int[] { Arrays.hashCode(temp.getTransaction()), prevHash });

    //         if (blockHash != temp.getBlockHash())
    //             return "Alert! System has been tampered. Location " + i;

    //         String[] transaction = temp.getTransaction().clone();
    //         transaction[2] = "BJP";
    //         blockHash = Arrays.hashCode(new int[] { Arrays.hashCode(transaction), prevHash });
    //         if (blockHash == temp.getBlockHash()) {
    //             BJP++;
    //             prevHash = temp.getBlockHash();
    //             continue;
    //         }

    //         transaction[2] = "CPM";
    //         blockHash = Arrays.hashCode(new int[] { Arrays.hashCode(transaction), prevHash });
    //         if (blockHash == temp.getBlockHash()) {
    //             CPM++;
    //             prevHash = temp.getBlockHash();
    //             continue;
    //         }

    //         transaction[2] = "TMC";
    //         blockHash = Arrays.hashCode(new int[] { Arrays.hashCode(transaction), prevHash });
    //         if (blockHash == temp.getBlockHash()) {
    //             TMC++;
    //             prevHash = temp.getBlockHash();
    //             continue;
    //         }

    //     }

    //     if (BJP > CPM && BJP > TMC)
    //         return "BJP wins!";
    //     else if (TMC > CPM)
    //         return "TMC wins!";
    //     else
    //         return "CPM wins!";
    // }

    // // public static void main(String[] args)
    // // {
    // // ArrayList<Block> blockchain1 = new ArrayList<Block>();
    // // pollVotes(blockchain1);

    // // System.out.println(declareResult(blockchain1));

    // // }

}
