package meanMCQ.service;

import meanMCQ.domain.Choice;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by red on 11/29/14.
 */
public interface ChoiceRepository extends JpaRepository<Choice, Long> {
}
