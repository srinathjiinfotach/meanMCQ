package meanMCQ.service;

import meanMCQ.domain.McqQuestion;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by red on 11/25/14.
 */
public interface QuestionRepository extends JpaRepository<McqQuestion, Long> {
}
