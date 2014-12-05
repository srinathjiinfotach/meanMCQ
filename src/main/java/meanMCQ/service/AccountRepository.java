package meanMCQ.service;

import meanMCQ.domain.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.Optional;

/**
 * Created by red on 11/29/14.
 */
@RepositoryRestResource(exported = false)
public interface AccountRepository extends JpaRepository<Account, Long> {
    Optional<Account> findByUsername(String username);
}
