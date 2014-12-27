package meanMCQ.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Collection;

/**
 * Created by red on 11/22/14.
 */
@Entity
public class Answer {
    @Id
    @GeneratedValue
    private Long id;

    @ManyToMany(fetch = FetchType.LAZY)
    @NotNull
    private Collection<Choice> choices;

    @ManyToOne(fetch = FetchType.LAZY)
    @NotNull
    private Question question;

    @ManyToOne(fetch = FetchType.LAZY)
    @NotNull
    private McqTest mcqTest;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @NotNull
    private User user;

    public Answer() {
    }

    //@JsonCreator
    public Answer(Collection<Choice> choices, Question question, McqTest mcqTest, User user) {
        this.choices = choices;
        this.question = question;
        this.mcqTest = mcqTest;
        this.user = user;
    }

    public Long getId() {
        return id;
    }

    public Collection<Choice> getChoices() {
        return choices;
    }

    public Question getQuestion() {
        return question;
    }

    public McqTest getMcqTest() {
        return mcqTest;
    }

    public User getUser() {
        return user;
    }

}
