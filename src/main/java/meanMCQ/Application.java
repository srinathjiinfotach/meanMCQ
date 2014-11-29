package meanMCQ;

import meanMCQ.domain.McqChoice;
import meanMCQ.domain.McqQuestion;
import meanMCQ.service.AccountRepository;
import meanMCQ.service.QuestionRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by red on 11/28/14.
 */
@Configuration
@ComponentScan
@EnableAutoConfiguration
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Bean
    CommandLineRunner init(QuestionRepository questionRepository, AccountRepository accountRepository) {

        return (evt) -> {
            for (int i = 1; i <= 10; i++) {
                McqQuestion q = new McqQuestion("This is question " + i + "?");
                Set<McqChoice> mcqChoices = new HashSet<McqChoice>(4);
                mcqChoices.add(new McqChoice("Choice1", false));
                mcqChoices.add(new McqChoice("Choice2", false));
                mcqChoices.add(new McqChoice("Choice3", true));
                mcqChoices.add(new McqChoice("Choice4", false));
                q.setMcqChoices(mcqChoices);
                mcqChoices.forEach(c -> c.setMcqQuestion(q));
                questionRepository.save(q);
            }

        };
    }
}
