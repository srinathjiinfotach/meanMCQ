package meanMCQ.service;

import meanMCQ.domain.Answer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

/**
 * Created by red on 12/6/14.
 */
@RepositoryRestResource(exported = false, collectionResourceRel = "answer", path = "answers")
public interface AnswerRepository extends JpaRepository<Answer, Long> {
}
