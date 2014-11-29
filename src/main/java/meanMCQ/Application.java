package meanMCQ;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

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

//    @Bean
//    CommandLineRunner init(QuestionRepository questionRepository, AccountRepository accountRepository) {
//
//        return (evt) -> {
//            for (int i = 1; i <= 10; i++) {
//                McqQuestion q = new McqQuestion("This is question " + i + "?");
//                Set<McqChoice> mcqChoices = new HashSet<McqChoice>(4);
//                mcqChoices.add(new McqChoice("Choice 1", false));
//                mcqChoices.add(new McqChoice("Choice 2", false));
//                mcqChoices.add(new McqChoice("Choice 3", true));
//                mcqChoices.add(new McqChoice("Choice 4", false));
//                q.setMcqChoices(mcqChoices);
//                mcqChoices.forEach(c -> c.setMcqQuestion(q));
//                questionRepository.save(q);
//            }
//
//        };
//    }
}
