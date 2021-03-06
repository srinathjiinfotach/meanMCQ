package meanMCQ;

import meanMCQ.domain.*;
import meanMCQ.service.McqTestRepository;
import meanMCQ.service.QuestionRepository;
import meanMCQ.service.UserRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.context.embedded.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.rest.webmvc.config.RepositoryRestMvcConfiguration;
import org.springframework.http.HttpStatus;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

/**
 * Created by red on 11/28/14.
 */
@Configuration
@ComponentScan
@EnableJpaRepositories
@Import(RepositoryRestMvcConfiguration.class)
@EnableAutoConfiguration
public class App {
    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
    }

    // CORS
    @Bean
    FilterRegistrationBean corsFilter(
            @Value("${tagit.origin:http://mcq.redmoses.me}") String origin) {
        return new FilterRegistrationBean(new Filter() {
            public void doFilter(ServletRequest req, ServletResponse res,
                                 FilterChain chain) throws IOException, ServletException {
                HttpServletRequest request = (HttpServletRequest) req;
                HttpServletResponse response = (HttpServletResponse) res;
                String method = request.getMethod();
                // this origin value could just as easily have come from a database
                response.setHeader("Access-Control-Allow-Origin", origin);
                response.setHeader("Access-Control-Allow-Methods",
                        "POST,GET,OPTIONS,DELETE");
                response.setHeader("Access-Control-Max-Age", Long.toString(60 * 60));
                response.setHeader("Access-Control-Allow-Credentials", "true");
                response.setHeader(
                        "Access-Control-Allow-Headers",
                        "Origin,Accept,X-Requested-With,Content-Type,Access-Control-Request-Method,Access-Control-Request-Headers,Authorization");
                if ("OPTIONS".equals(method)) {
                    response.setStatus(HttpStatus.OK.value());
                } else {
                    chain.doFilter(req, res);
                }
            }

            public void init(FilterConfig filterConfig) {
            }

            public void destroy() {
            }
        });
    }

    @Bean
    CommandLineRunner init(QuestionRepository questionRepository, UserRepository userRepository,
                           McqTestRepository mcqTestRepository) {


        return (evt) -> {
            Collection<Question> existing_questions = questionRepository.findAll();
            if (existing_questions.isEmpty()) {
                userRepository.save(new User("Teacher", "pass", UserRole.EXAMINER));

                Collection<Question> questions = new ArrayList<>();

                for (int i = 1; i <= 10; i++) {
                    userRepository.save(new User("Student" + i, "pass", UserRole.STUDENT));
                    Question q = new Question("This is question " + i + "?");

                    Collection<Choice> choices = new ArrayList<>();
                    choices.add(new Choice("Choice 1", false));
                    choices.add(new Choice("Choice 2", false));
                    choices.add(new Choice("Choice 3", true));
                    choices.add(new Choice("Choice 4", false));
                    q.setChoices(choices);
                    choices.forEach(c -> c.setQuestion(q));
                    questionRepository.save(q);
                    questions.add(q);
                }

                McqTest mcqTest = new McqTest("Test 1", new Date(), 30);
                mcqTest.setQuestions(questions);
                mcqTestRepository.save(mcqTest);
            }

        };
    }
}