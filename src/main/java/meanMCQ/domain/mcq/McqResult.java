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
public class McqResult {
    @Id
    @GeneratedValue
    private Long id;

    private double marks;

    @JsonIgnore
    @ManyToOne
    private Account account;

    @JsonIgnore
    @ManyToOne
    private McqTest mcqTest;

    public McqResult() {
    }

    public McqResult(double marks, Account account, McqTest mcqTest) {
        this.marks = marks;
        this.account = account;
        this.mcqTest = mcqTest;
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

    public McqTest getMcqTest() {
        return mcqTest;
    }

}
