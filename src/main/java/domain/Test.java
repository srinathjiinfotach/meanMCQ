package domain;

import com.fasterxml.jackson.annotation.JsonIgnore;

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

    // getter methods
    public Long getId() {
        return id;
    }

    // constructors
    public Test() {
    }
}
