package meanMCQ.service;

import meanMCQ.domain.Questionnaire;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by red on 11/25/14.
 */
public interface QuestionnaireRepository extends JpaRepository<Questionnaire, Long> {
}
