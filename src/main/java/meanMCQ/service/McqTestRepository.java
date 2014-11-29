package meanMCQ.service;

import meanMCQ.domain.McqTest;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by red on 11/25/14.
 */
public interface McqTestRepository extends JpaRepository<McqTest, Long> {
}
