package meanMCQ.service;

import meanMCQ.domain.McqTest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

/**
 * Created by red on 11/25/14.
 */
@RepositoryRestResource(exported = false, collectionResourceRel = "mcqtests", path = "mcqtests", itemResourceRel = "question")
public interface McqTestRepository extends JpaRepository<McqTest, Long> {
    List<McqTest> findByTitle(String title);
}
