package meanMCQ.domain.account;

import meanMCQ.domain.application.Result;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by red on 11/16/14.
 */
@Entity
public class PupilAccount extends GeneralAccount {
    // foreign attributes
    @OneToMany(mappedBy = "pupil")
    private Set<Result> results = new HashSet<>();

    // constructors
    public PupilAccount() {
    }

    public PupilAccount(String name, String password) {
        super(name, password);
    }
}
