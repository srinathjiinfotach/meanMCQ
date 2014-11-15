package domain;

import policy.AccountPolicy;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * Created by Red on 11/15/14.
 * Description: User account Model
 *  *
 */

@Entity
public class Account {
    @Id
    @GeneratedValue
    private Long id;

    private AccountPolicy.AccountRole accountType;

    @JsonIgnore
    public String password;
    public String username;


    public Long getId() {
        return id;
    }

    public String getPassword() {
        return password;
    }

    public String getUsername() {
        return username;
    }

    public Account(String name, String password, AccountPolicy.AccountRole accountType) {
        this.username = name;
        this.password = password;
        this.accountType = accountType;
    }

    Account() { // jpa only
    }

}
