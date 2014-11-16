package meanMCQ.repositories;

import meanMCQ.domain.account.GeneralAccount;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * Created by red on 11/15/14.
 */
public interface AccountRepository extends JpaRepository<GeneralAccount, Long> {
    Optional<GeneralAccount> findByUsername(String username);
}