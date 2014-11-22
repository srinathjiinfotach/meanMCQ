package meanMCQ.domain.mcq;

import com.fasterxml.jackson.annotation.JsonIgnore;
import meanMCQ.domain.account.PupilAccount;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

/**
 * Created by red on 11/15/14.
 */
@Entity
public class Result {
    @Id
    @GeneratedValue
    private Long id;

    private double marks;

    @JsonIgnore
    @ManyToOne
    private PupilAccount pupil;

    @JsonIgnore
    @ManyToOne
    private Test test;

    public Result() {
    }

    public Result(double marks, PupilAccount pupil, Test test) {
        this.marks = marks;
        this.pupil = pupil;
        this.test = test;
    }

    public Long getId() {
        return id;
    }

    public double getMarks() {
        return marks;
    }

    public PupilAccount getPupil() {
        return pupil;
    }

    public Test getTest() {
        return test;
    }

}
