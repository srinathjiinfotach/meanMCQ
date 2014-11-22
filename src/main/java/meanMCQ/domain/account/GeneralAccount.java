package meanMCQ.domain.account;

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
    @Id
    @GeneratedValue
    private Long id;

    public GeneralAccount(String name, String password) {
        this.username = name;
        this.password = password;
    }

    public GeneralAccount() {
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

}
