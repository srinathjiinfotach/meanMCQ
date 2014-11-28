package meanMCQ.repositories;

import meanMCQ.domain.mcq.Question;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by red on 11/25/14.
 */
public interface QuestionRepository extends JpaRepository<Question, Long> {
}
