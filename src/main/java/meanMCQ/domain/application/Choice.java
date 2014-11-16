package meanMCQ.domain.application;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

/**
 * Created by red on 11/15/14.
 */
@Entity
public class Choice {
    // local attributes
    @Id
    @GeneratedValue
    private Long id;

    private String content;

    // foreign attributes
    @JsonIgnore
    @ManyToOne
    private Question question;

    // constructors
    public Choice(String content) {
        this.content = content;
    }

    public Choice() {
    }

    // getter methods
    public String getContent() {
        return content;
    }

    public Long getId() {
        return id;
    }
}
