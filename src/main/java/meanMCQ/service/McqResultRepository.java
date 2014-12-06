package meanMCQ.service;

import meanMCQ.domain.McqResult;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

/**
 * Created by red on 11/25/14.
 */
@RepositoryRestResource(exported = false)
public interface McqResultRepository extends JpaRepository<McqResult, Long> {
}
