package meanMCQ.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * Created by Red on 11/15/14.
 * Description: User account Model
 * *
 */

@Entity
public class Account {
    @JsonIgnore
    public String password;
    public String username;
    @Id
    @GeneratedValue
    private Long id;

    private Role.UserRole accountType;

    public Account(String password, String username, Role.UserRole accountType) {
        this.password = password;
        this.username = username;
        this.accountType = accountType;
    }

    public Account() {
    }

    public Long getId() {
        return id;
    }

    public String getPassword() {
        return password;
    }

    public String getUsername() {
        return username;
    }

    public Role.UserRole getAccountType() {
        return accountType;
    }
}
