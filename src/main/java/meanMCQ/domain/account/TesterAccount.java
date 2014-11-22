package meanMCQ.domain.account;

import meanMCQ.domain.mcq.Test;

import javax.persistence.OneToMany;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by red on 11/16/14.
 */
public class TesterAccount extends GeneralAccount {
    @OneToMany(mappedBy = "tester")
    private Set<Test> tests = new HashSet<>();

    public TesterAccount() {
    }

    public TesterAccount(String name, String password) {
        super(name, password);
    }

    public Set<Test> getTests() {
        return tests;
    }
}
