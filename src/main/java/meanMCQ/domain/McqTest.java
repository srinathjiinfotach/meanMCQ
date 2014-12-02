package meanMCQ.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;

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
    private int duration; // in minutes

    @JsonIgnore
    @ManyToOne
    private Account account;

    @OneToMany
    private Set<Question> questions;

    public McqTest() {
    }

    public McqTest(Date schedule, int duration, Account account) {
        this.schedule = schedule;
        this.duration = duration;
        this.account = account;
    }

    public Long getId() {
        return id;
    }

    public int getDuration() {
        return duration;
    }

    public Date getSchedule() {
        return schedule;
    }


}
