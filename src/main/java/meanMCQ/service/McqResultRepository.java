package meanMCQ.service;

import meanMCQ.domain.McqResult;
import meanMCQ.domain.McqTest;
import meanMCQ.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

/**
 * Created by red on 11/25/14.
 */
@RepositoryRestResource(exported = false, collectionResourceRel = "mcqresults", path = "mcqresults")
public interface McqResultRepository extends JpaRepository<McqResult, Long> {
    List<McqResult> findByUser(User user);
    List<McqResult> findByMcqTestAndUser(McqTest mcqTest, User user);

    List<McqResult> findByMcqTest(McqTest mcqTest);
}