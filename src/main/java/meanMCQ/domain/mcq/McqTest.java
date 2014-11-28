package meanMCQ.domain.mcq;

import com.fasterxml.jackson.annotation.JsonIgnore;
import meanMCQ.domain.account.Account;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

/**
 * Created by red on 11/15/14.
 */
@Entity
public class McqTest {
    @Id
    @GeneratedValue
    private Long id;

    private Date schedule;
    private int duration;       // in minutes

    @JsonIgnore
    @ManyToOne
    private Questionnaire questionnaire;

    @JsonIgnore
    @ManyToOne
    private Account account;

    @JsonIgnore
    @OneToMany(mappedBy = "mcqTest")
    private Set<Answer> answers;

    public McqTest() {
    }

    public McqTest(Date schedule, int duration, Questionnaire questionnaire, Account account) {
        this.schedule = schedule;
        this.duration = duration;
        this.questionnaire = questionnaire;
        this.account = account;
    }

    public Long getId() {
        return id;
    }

    public Questionnaire getQuestionnaire() {
        return questionnaire;
    }

    public int getDuration() {
        return duration;
    }

    public Date getSchedule() {
        return schedule;
    }

    public Set<Answer> getAnswers() {
        return answers;
    }
}
