package net.codejava.helper;

import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
// import com.google.common.hash.Hashing;



public class SHA256 {

    public static String getSHA(String[] input, String prevHash) throws NoSuchAlgorithmException {
        
        String password="";
        for(String str:input){
            password+=str;
        }

        password+=prevHash;

        //System.out.println(password.toString());
        MessageDigest md = MessageDigest.getInstance("SHA-256");
        byte[] hashInBytes = md.digest(password.getBytes(StandardCharsets.UTF_8));

        StringBuilder sb = new StringBuilder();
        for (byte b : hashInBytes) {
            sb.append(String.format("%02x", b));
        }
        return sb.toString();
    }
}