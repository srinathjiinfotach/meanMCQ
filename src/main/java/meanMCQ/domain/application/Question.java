package meanMCQ.domain.application;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by red on 11/15/14.
 */
@Entity
public class Question {
    // local attributes
    @Id
    @GeneratedValue
    private Long id;

    private String content;
    private Long answerId;

    // foreign attributes
    @OneToMany(mappedBy = "question")
    private Set<Choice> choices = new HashSet<>();

    @JsonIgnore
    @ManyToOne
    private Questionnaire questionnaire;

    // constructors
    public Question(String content) {
        this.content = content;
        this.answerId = 0L;
    }

    public Question() {
    }

    // getter methods
    public String getContent() {
        return content;
    }

    //setter methods
    public void setAnswerId(Long answerId) {
        this.answerId = answerId;
    }

    public Long getId() {
        return id;
    }
}
