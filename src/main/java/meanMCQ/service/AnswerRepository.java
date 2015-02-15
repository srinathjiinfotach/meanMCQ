package meanMCQ.service;

import meanMCQ.domain.Answer;
import meanMCQ.domain.McqTest;
import meanMCQ.domain.Question;
import meanMCQ.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

/**
 * Created by red on 12/6/14.
 */
@RepositoryRestResource(exported = true, collectionResourceRel = "answers", path = "answers")
public interface AnswerRepository extends JpaRepository<Answer, Long> {
    List<Answer> findByMcqTestAndUser(McqTest mcqTest, User user);

    List<Answer> findByQuestionAndUser(Question question, User user);
}
