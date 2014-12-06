package meanMCQ.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

/**
 * Created by red on 11/15/14.
 */
@Entity
public class McqResult {
    @Id
    @GeneratedValue
    private Long id;

    private double marks;

    @JsonIgnore
    @ManyToOne
    private User student;

    @JsonIgnore
    @ManyToOne
    private McqTest mcqTest;

    public McqResult() {
    }

    public McqResult(double marks, User student, McqTest mcqTest) {
        this.marks = marks;
        this.student = student;
        this.mcqTest = mcqTest;
    }

    public Long getId() {
        return id;
    }

    public double getMarks() {
        return marks;
    }

    public User getStudent() {
        return student;
    }

    public McqTest getMcqTest() {
        return mcqTest;
    }

    @Override
    public String toString() {
        return student.toString() + " : " + this.marks;
    }
}
