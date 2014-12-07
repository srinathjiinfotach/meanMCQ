package meanMCQ.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

import javax.persistence.*;
import java.util.Set;

/**
 * Created by red on 11/22/14.
 */
@Entity
public class Answer {
    @Id
    @GeneratedValue
    private Long id;

    @ManyToMany(fetch = FetchType.LAZY)
    @Cascade(CascadeType.REFRESH)
    private Set<Choice> choices;

    @ManyToOne(fetch = FetchType.LAZY)
    @Cascade(CascadeType.REFRESH)
    private Question question;

    @ManyToOne(fetch = FetchType.LAZY)
    @Cascade(CascadeType.REFRESH)
    private McqTest mcqTest;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @Cascade(CascadeType.REFRESH)
    private User user;

    public Answer() {
    }

    public Answer(Set<Choice> choices, Question question, McqTest mcqTest, User user) {
        this.choices = choices;
        this.question = question;
        this.mcqTest = mcqTest;
        this.user = user;
    }

    public Long getId() {
        return id;
    }

    public Set<Choice> getChoices() {
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
