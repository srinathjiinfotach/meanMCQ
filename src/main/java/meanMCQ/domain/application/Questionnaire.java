package meanMCQ.domain.application;

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
    // local attributes
    @Id
    @GeneratedValue
    private Long id;

    // foreign attributes
    @OneToMany(mappedBy = "questionnaire")
    private Set<Choice> questions = new HashSet<>();

    @OneToMany(mappedBy = "questionnaire")
    private Set<Test> tests = new HashSet<>();

    // constructors
    public Questionnaire() {
    }

    // getter methods
    public Long getId() {
        return id;
    }
}
