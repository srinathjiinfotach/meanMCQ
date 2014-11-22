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
    // local attributes
    @Id
    @GeneratedValue
    private Long id;

    private double marks;

    // foreign attributes
    @JsonIgnore
    @ManyToOne
    private PupilAccount pupil;

    @JsonIgnore
    @ManyToOne
    private Test test;

    // constructors
    public Result() {
    }

    public Result(double marks, PupilAccount pupil) {
        this.marks = marks;
        this.pupil = pupil;
    }

    // getter methods
    public Long getId() {
        return id;
    }
}
