package domain;

import javax.persistence.OneToMany;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by red on 11/16/14.
 */
public class TesterAccount extends Account {
    // foreign attributes
    @OneToMany(mappedBy = "tester")
    private Set<Test> tests = new HashSet<>();

    // constructors
    public TesterAccount() {
    }

    public TesterAccount(String name, String password) {
        super(name, password);
    }
}
