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
    // local attributes
    @Id
    @GeneratedValue
    private Long id;

    private Date schedule;
    private int duration;       // in minutes
    private double totalMarks;

    // foreign attributes
    @JsonIgnore
    @ManyToOne
    private Questionnaire questionnaire;

    @JsonIgnore
    @ManyToOne
    private TesterAccount tester;

    @OneToMany(mappedBy = "test")
    private Set<Result> results = new HashSet<>();

    // constructors
    public Test() {
    }

    public Test(Date schedule, int duration, double totalMarks, Questionnaire questionnaire, TesterAccount tester) {
        this.schedule = schedule;
        this.duration = duration;
        this.totalMarks = totalMarks;
        this.questionnaire = questionnaire;
        this.tester = tester;
    }

    // getter methods
    public Long getId() {
        return id;
    }

    public Set<Result> getResults() {
        return results;
    }
}
