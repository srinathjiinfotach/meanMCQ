package meanMCQ.service;

import meanMCQ.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.Optional;

/**
 * Created by red on 11/29/14.
 */
@RepositoryRestResource(exported = true, collectionResourceRel = "users", path = "users")
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);
}
