package meanMCQ.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;

/**
 * Created by Red on 11/15/14.
 * Description: User account Model
 * *
 */

@Entity
public class User {
    @NotEmpty
    public String username;
    @JsonIgnore
    @NotEmpty
    public String password;

    @Id
    @GeneratedValue
    private Long id;

    @Enumerated(EnumType.STRING)
    private UserRole role;

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

    public String getPassword() {
        return password;
    }

    public String getUsername() {
        return username;
    }

    public UserRole getRole() {
        return role;
    }

    @Override
    public String toString() {
        return this.username;
    }
}
