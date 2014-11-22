package meanMCQ.domain.mcq;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by red on 11/15/14.
 */
@Entity
public class Question {
    @Id
    @GeneratedValue
    private Long id;

    private String content;

    @OneToMany(mappedBy = "question")
    private Set<Choice> choices = new HashSet<>();

    @JsonIgnore
    @ManyToOne
    private Questionnaire questionnaire;

    public Question(String content) {
        this.content = content;
    }

    public Question() {
    }

    public String getContent() {
        return content;
    }

    public Set<Choice> getChoices() {
        return choices;
    }

    public void setChoices(Set<Choice> choices) {
        this.choices = choices;
    }

    public Long getId() {
        return id;
    }
}
