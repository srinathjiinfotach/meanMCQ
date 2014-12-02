package meanMCQ.domain;

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

    public String content;

    @OneToMany(mappedBy = "question", cascade = CascadeType.ALL)
    public Set<Choice> choices = new HashSet<Choice>();

    public Question() {
    }

    public Question(String content) {
        this.content = content;
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
