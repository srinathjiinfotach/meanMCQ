package meanMCQ.domain.mcq;

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
    private Set<Choice> questions = new HashSet<>();

    @OneToMany(mappedBy = "questionnaire")
    private Set<Test> tests = new HashSet<>();

    public Questionnaire() {
    }

    public Questionnaire(Set<Choice> questions) {
        this.questions = questions;
    }

    public Long getId() {
        return id;
    }

    public Set<Choice> getQuestions() {
        return questions;
    }
}
