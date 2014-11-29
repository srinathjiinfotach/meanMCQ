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
                Set<Choice> choices = new HashSet<Choice>(4);
                choices.add(new Choice("Choice1", false));
                choices.add(new Choice("Choice2", false));
                choices.add(new Choice("Choice3", true));
                choices.add(new Choice("Choice4", false));
                choices.forEach(c -> c.setQuestion(q));
                choiceRepository.save(choices);
            }

        };
    }
}
