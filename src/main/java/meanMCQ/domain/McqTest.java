package meanMCQ.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

import javax.persistence.*;
import java.util.Collection;
import java.util.Date;

/**
 * Created by red on 11/15/14.
 */
@Entity
public class McqTest {
    @Id
    @GeneratedValue
    private Long id;

    public String title;
    @Temporal(TemporalType.TIMESTAMP)
    public Date schedule;
    public int duration; // in minutes

    @ManyToMany(fetch = FetchType.LAZY)
    @Cascade(CascadeType.REFRESH)
    public Collection<Question> questions;

    @JsonIgnore
    @ManyToMany(fetch = FetchType.LAZY)
    @Cascade(CascadeType.REFRESH)
    public Collection<User> users;

    public McqTest() {
    }

    //@JsonCreator
    public McqTest(String title, Date schedule, int duration) {
        this.title = title;
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

    public Collection<User> getUsers() {
        return users;
    }

    public Collection<Question> getQuestions() {
        return questions;
    }

    public void setQuestions(Collection<Question> questions) {
        this.questions = questions;
    }

    public void setUsers(Collection<User> users) {
        this.users = users;
    }

    // check test validity
    public boolean isValid() {
        final long ONE_MINUTE = 60000; // in millisecs
        long t = this.schedule.getTime();
        Date schedule_ends = new Date(t + (this.duration * ONE_MINUTE));
        Date current_time = new Date();

        if (schedule_ends.before(current_time))
            return false;

        return true;
    }
}
