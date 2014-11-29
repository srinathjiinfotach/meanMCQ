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
public class McqAnswer {
    @Id
    @GeneratedValue
    private Long id;

    @JsonIgnore
    @ManyToOne
    private McqQuestion mcqQuestion;
    @JsonIgnore
    @ManyToOne
    private McqChoice mcqChoice;
    @JsonIgnore
    @ManyToOne
    private McqTest mcqTest;
    @JsonIgnore
    @ManyToOne
    private Account account;

    public McqAnswer() {
    }

    public McqAnswer(McqQuestion mcqQuestion, McqChoice mcqChoice, McqTest mcqTest, Account account) {
        this.mcqQuestion = mcqQuestion;
        this.mcqChoice = mcqChoice;
        this.mcqTest = mcqTest;
        this.account = account;
    }
}
