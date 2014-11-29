package meanMCQ.service;

import meanMCQ.domain.Account;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by red on 11/29/14.
 */
public interface AccountRepository extends JpaRepository<Account, Long> {
}
