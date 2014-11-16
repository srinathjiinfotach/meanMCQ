package domain.account;

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
public abstract class GeneralAccount {
    @JsonIgnore
    public String password;
    public String username;
    // local attributes
    @Id
    @GeneratedValue
    private Long id;


    // constructors
    public GeneralAccount(String name, String password) {
        this.username = name;
        this.password = password;
    }

    GeneralAccount() { // jpa only
    }

    // getter methods
    public Long getId() {
        return id;
    }

    public String getPassword() {
        return password;
    }

    public String getUsername() {
        return username;
    }

}
