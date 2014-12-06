package meanMCQ.domain;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

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
    public Long id;

    public String content;

    @OneToMany(mappedBy = "question", fetch = FetchType.LAZY)
    @Cascade(CascadeType.ALL)
    public Set<Choice> choices = new HashSet<>();

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

    @Override
    public String toString() {
        return this.content;
    }
}
