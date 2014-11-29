package meanMCQ.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by red on 11/15/14.
 */
@Entity
public class Questionnaire {
    @Id
    @GeneratedValue
    private Long id;

    @OneToMany(mappedBy = "questionnaire")
    private Set<Question> questions = new HashSet<>();

    public Questionnaire() {
    }

    public Questionnaire(Set<Question> questions) {
        this.questions = questions;
    }

    public Long getId() {
        return id;
    }

    public Set<Question> getQuestions() {
        return questions;
    }
}
