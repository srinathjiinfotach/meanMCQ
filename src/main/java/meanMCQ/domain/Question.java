package meanMCQ.domain;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Collection;

/**
 * Created by red on 11/15/14.
 */
@Entity
public class Question {
    @Id
    @GeneratedValue
    private Long id;

    @NotNull
    public String content;

    @OneToMany(mappedBy = "question", fetch = FetchType.LAZY)
    @Cascade(CascadeType.ALL)
    public Collection<Choice> choices;

    public Question() {
    }

    //@JsonCreator
    public Question(String content) {
        this.content = content;
    }

    public String getContent() {
        return content;
    }

    public Collection<Choice> getChoices() {
        return choices;
    }

    public void setChoices(Collection<Choice> choices) {
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
