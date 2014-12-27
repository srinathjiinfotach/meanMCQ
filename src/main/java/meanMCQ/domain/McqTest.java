package meanMCQ.domain;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import meanMCQ.configurations.DateSerializer;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
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

    @NotNull
    public String title;
    @JsonSerialize(using = DateSerializer.class)
    @NotNull
    public Date schedule;
    @NotNull
    public int duration; // in minutes

    @ManyToMany(fetch = FetchType.LAZY)
    public Collection<Question> questions;

    @ManyToMany(fetch = FetchType.LAZY)
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
