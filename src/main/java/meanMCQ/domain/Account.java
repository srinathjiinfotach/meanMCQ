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
    public String username;
    public String password;

    @Id
    @GeneratedValue
    private Long id;

    public AccountRole accountRole;

    public Account(String username, String password, AccountRole accountRole) {
        this.username = username;
        this.password = password;
        this.accountRole = accountRole;
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

    public AccountRole getAccountRole() {
        return accountRole;
    }
}
