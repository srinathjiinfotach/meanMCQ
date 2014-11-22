package meanMCQ.domain.mcq;

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
    @Id
    @GeneratedValue
    private Long id;

    private String content;

    @JsonIgnore
    @ManyToOne
    private Question question;

    public Choice() {
    }

    public Choice(String content) {
        this.content = content;
    }

    public String getContent() {
        return content;
    }
    public Long getId() {
        return id;
    }
}
