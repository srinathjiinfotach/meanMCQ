package meanMCQ.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

import javax.persistence.*;

/**
 * Created by red on 11/15/14.
 */
@Entity
public class McqResult {
    @Id
    @GeneratedValue
    private Long id;

    private double marks;

    @ManyToOne(fetch = FetchType.LAZY)
    @Cascade(CascadeType.REFRESH)
    private User user;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @Cascade(CascadeType.REFRESH)
    private McqTest mcqTest;

    public McqResult() {
    }

    public McqResult(double marks, User user, McqTest mcqTest) {
        this.marks = marks;
        this.user = user;
        this.mcqTest = mcqTest;
    }

    public Long getId() {
        return id;
    }

    public double getMarks() {
        return marks;
    }

    public User getUser() {
        return user;
    }

    public McqTest getMcqTest() {
        return mcqTest;
    }

    @Override
    public String toString() {
        return user.toString() + " : " + this.marks;
    }
}
