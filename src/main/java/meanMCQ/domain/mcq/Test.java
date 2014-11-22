package meanMCQ.domain.mcq;

import com.fasterxml.jackson.annotation.JsonIgnore;
import meanMCQ.domain.account.TesterAccount;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by red on 11/15/14.
 */
@Entity
public class Test {
    @Id
    @GeneratedValue
    private Long id;

    private Date schedule;
    private int duration;       // in minutes
    private double totalMarks;

    @JsonIgnore
    @ManyToOne
    private Questionnaire questionnaire;

    @JsonIgnore
    @ManyToOne
    private TesterAccount tester;

    @OneToMany(mappedBy = "test")
    private Set<Result> results = new HashSet<>();

    public Test() {
    }

    public Test(Date schedule, int duration, double totalMarks, Questionnaire questionnaire, TesterAccount tester) {
        this.schedule = schedule;
        this.duration = duration;
        this.totalMarks = totalMarks;
        this.questionnaire = questionnaire;
        this.tester = tester;
    }

    public Long getId() {
        return id;
    }

    public Set<Result> getResults() {
        return results;
    }

    public void setResults(Set<Result> results) {
        this.results = results;
    }

    public Questionnaire getQuestionnaire() {
        return questionnaire;
    }

    public double getTotalMarks() {
        return totalMarks;
    }

    public int getDuration() {

        return duration;
    }

    public Date getSchedule() {
        return schedule;
    }
}
