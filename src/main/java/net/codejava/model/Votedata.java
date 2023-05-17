package net.codejava.model;

import java.util.*;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Votedata {

    @Id
    String username;

    String prevhash;
    String currhash;
    Date date;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPrevhash() {
        return prevhash;
    }

    public void setPrevhash(String prevhash) {
        this.prevhash = prevhash;
    }

    public String getCurrhash() {
        return currhash;
    }

    public void setCurrhash(String currhash) {
        this.currhash = currhash;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {

        this.date = date;
    }

    @Override
    public String toString() {
        return "Votedata [currhash=" + currhash + ", prevhash=" + prevhash + ", username=" + username + "]";
    }

}