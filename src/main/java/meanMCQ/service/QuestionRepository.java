package meanMCQ.service;

import meanMCQ.domain.Question;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by red on 11/25/14.
 */
public interface QuestionRepository extends JpaRepository<Question, Long> {
}
