package meanMCQ.service;

import meanMCQ.domain.Choice;
import meanMCQ.domain.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

/**
 * Created by red on 12/7/14.
 */
@RepositoryRestResource(exported = false, collectionResourceRel = "choice", path = "choices")
public interface ChoiceRepository extends JpaRepository<Choice, Long> {
    List<Choice> findByQuestionAndAnswer(Question question, boolean answer);
}
