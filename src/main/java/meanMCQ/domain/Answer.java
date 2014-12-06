package meanMCQ.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

/**
 * Created by red on 11/22/14.
 */
@Entity
public class Answer {
    @Id
    @GeneratedValue
    private Long id;

    @JsonIgnore
    @ManyToOne
    private Question question;
    @JsonIgnore
    @ManyToOne
    private Choice choice;
    @JsonIgnore
    @ManyToOne
    private McqTest mcqTest;
    @JsonIgnore
    @ManyToOne
    private User student;

    public Answer() {
    }

    public Answer(Question question, Choice choice, McqTest mcqTest, User student) {
        this.question = question;
        this.choice = choice;
        this.mcqTest = mcqTest;
        this.student = student;
    }

    @Override
    public String toString() {
        return this.choice.toString();
    }
}
