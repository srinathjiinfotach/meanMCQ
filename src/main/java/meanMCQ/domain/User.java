package meanMCQ.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

/**
 * Created by Red on 11/15/14.
 * Description: User account Model
 * *
 */

@Entity
public class User {
    @NotNull
    private String username;
    @NotNull
    private String password;

    @Id
    @GeneratedValue
    private Long id;

    @Enumerated(EnumType.STRING)
    public UserRole role;

    public User(String username, String password, UserRole role) {
        this.username = username;
        this.password = password;
        this.role = role;
    }

    public User() {
    }

    public Long getId() {
        return id;
    }

    @JsonIgnore
    public String getPassword() {
        return password;
    }

    public String getUsername() {
        return username;
    }

    public UserRole getRole() {
        return role;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return this.username + " - " + this.role;
    }
}
