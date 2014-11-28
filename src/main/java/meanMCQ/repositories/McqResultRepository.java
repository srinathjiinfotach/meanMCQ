package meanMCQ.repositories;

import meanMCQ.domain.mcq.McqResult;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by red on 11/25/14.
 */
public interface McqResultRepository extends JpaRepository<McqResult, Long> {
}
