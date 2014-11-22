package meanMCQ.domain.mcq;

import com.fasterxml.jackson.annotation.JsonIgnore;
import meanMCQ.domain.account.Account;

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
    private Account account;

    @JsonIgnore
    @ManyToOne
    private Test test;

    public Result() {
    }

    public Result(double marks, Account account, Test test) {
        this.marks = marks;
        this.account = account;
        this.test = test;
    }

    public Long getId() {
        return id;
    }

    public double getMarks() {
        return marks;
    }

    public Account getAccount() {
        return account;
    }

    public Test getTest() {
        return test;
    }

}
