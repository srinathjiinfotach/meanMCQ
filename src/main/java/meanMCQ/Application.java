package meanMCQ;

import meanMCQ.domain.mcq.Choice;
import meanMCQ.domain.mcq.Question;
import meanMCQ.repositories.QuestionRepository;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by red on 11/28/14.
 */
@PropertySource("classpath:application.properties")
@Configuration
@ComponentScan
@EnableAutoConfiguration
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Bean
    CommandLineRunner init (QuestionRepository questionRepository) {

        Choice c1 = new Choice("Choice1",false);
        Choice c2 = new Choice("Choice2",false);
        Choice c3 = new Choice("Choice3",true);
        Choice c4 = new Choice("Choice4",false);
        Set<Choice> choices = new HashSet<>(Arrays.asList(c1,c2,c3,c4));

        return (evt) -> {
            for (int i = 1; i <= 10; i++) {
                questionRepository.save(new Question("This is question "+i+"?", choices));
            }
        };
    }
}
