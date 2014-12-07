package meanMCQ.service;

import meanMCQ.domain.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

/**
 * Created by red on 11/25/14.
 */
@RepositoryRestResource(exported = false, collectionResourceRel = "question", path = "questions",
        itemResourceRel = "choices")
public interface QuestionRepository extends JpaRepository<Question, Long> {
}
