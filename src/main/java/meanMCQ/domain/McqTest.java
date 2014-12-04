package meanMCQ.domain;

import com.fasterxml.jackson.annotation.JsonManagedReference;

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

    public Date schedule;
    public int duration; // in minutes

    @JsonManagedReference
    @OneToMany(cascade = CascadeType.DETACH)
    public Set<Question> questions;

    public McqTest() {
    }

    public McqTest(Date schedule, int duration, Set<Question> questions) {
        this.schedule = schedule;
        this.duration = duration;
        this.questions = questions;
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

    public Set<Question> getQuestions() {
        return questions;
    }

    public void setQuestions(Set<Question> questions) {
        this.questions = questions;
    }
}
