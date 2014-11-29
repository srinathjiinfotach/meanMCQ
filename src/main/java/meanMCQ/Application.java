package meanMCQ;

import meanMCQ.domain.Choice;
import meanMCQ.domain.Question;
import meanMCQ.service.ChoiceRepository;
import meanMCQ.service.QuestionRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;
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
    CommandLineRunner init(QuestionRepository questionRepository, ChoiceRepository choiceRepository) {

        return (evt) -> {
            for (int i = 1; i <= 10; i++) {
                Question q = questionRepository.save(new Question("This is question " + i + "?"));
                Choice c1 = new Choice("Choice1", q, false);
                Choice c2 = new Choice("Choice2", q, false);
                Choice c3 = new Choice("Choice3", q, true);
                Choice c4 = new Choice("Choice4", q, false);
                Set<Choice> choices = new HashSet<>(Arrays.asList(c1, c2, c3, c4));
                choiceRepository.save(choices);

            }

        };
    }
}
