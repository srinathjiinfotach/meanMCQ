package meanMCQ.domain.application;

import com.fasterxml.jackson.annotation.JsonIgnore;
import meanMCQ.domain.account.TesterAccount;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by red on 11/15/14.
 */
@Entity
public class Test {
    // local attributes
    @Id
    @GeneratedValue
    private Long id;

    // foreign attributes
    @JsonIgnore
    @ManyToOne
    private Questionnaire questionnaire;

    @JsonIgnore
    @ManyToOne
    private TesterAccount tester;

    @OneToMany(mappedBy = "test")
    private Set<Result> results = new HashSet<>();

    // constructors
    public Test() {
    }

    // getter methods
    public Long getId() {
        return id;
    }
}
