package meanMCQ.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

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

    @Temporal(TemporalType.TIMESTAMP)
    public Date schedule;
    public int duration; // in minutes

    @ManyToMany(fetch = FetchType.LAZY)
    @Cascade(CascadeType.REFRESH)
    public Set<Question> questions;

    @JsonIgnore
    @ManyToMany(fetch = FetchType.LAZY)
    @Cascade(CascadeType.REFRESH)
    public Set<User> users;

    public McqTest() {
    }

    public McqTest(Date schedule, int duration) {
        this.schedule = schedule;
        this.duration = duration;
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

    public Set<User> getUsers() {
        return users;
    }

    public Set<Question> getQuestions() {
        return questions;
    }

    public void setQuestions(Set<Question> questions) {
        this.questions = questions;
    }

    public void setUsers(Set<User> users) {
        this.users = users;
    }

    // check test validity
    public boolean isValid() {
        final long ONE_MINUTE_IN_MILLIS = 60000;//millisecs
        long t = this.schedule.getTime();
        Date schedule_ends = new Date(t + (this.duration * ONE_MINUTE_IN_MILLIS));
        Date current_time = new Date();

        if (schedule_ends.before(current_time))
            return false;

        return true;
    }
}
